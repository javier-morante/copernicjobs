import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { ToastContainer, toast } from 'react-toastify'
import offersRequest from '../../../axios/offers/OffersRequest'
import subscribedOffersRequest from '../../../axios/offers/SubscribedOffersRequest'
import subscribeStudentRequest from '../../../axios/offers/SubscribeStudentRequest'
import jwtDecode from '../../../utils/jwtDecode'
import IconTextField from '../../home/general_form_components/IconTextField'
import IconDropdown from './IconDropdown'
import Offer from './Offer'

export default function OfferScreen() {
    const role = jwtDecode().rol
    const navigate = useNavigate()
    const [offers, setOffers] = useState([])
    const [filteredOffers, setFilteredOffers] = useState([])
    const [alreadySubscribedOffers, setAlreadySubscribedOffers] = useState([])

    useEffect(() => {
        fetchOffers()
    }, [])

    useEffect(() => {
        console.log(offers)
    }, [offers])

    const fetchOffers = async () => {
        try {
            const fetchedOffers = await offersRequest()
            setOffers(fetchedOffers)

            const fetchedAlreadySubscribedOffers = await subscribedOffersRequest()
            setAlreadySubscribedOffers(fetchedAlreadySubscribedOffers)

            const updatedOffers = fetchedOffers.filter(offer =>
                !fetchedAlreadySubscribedOffers.some(alreadySubscribedOffer => alreadySubscribedOffer.id === offer.id)
            )

            setOffers(updatedOffers)
            setFilteredOffers(updatedOffers)
        } catch (error) {
            console.log("Error fetching offers: " + error)
        }
    }

    const subscribeStudent = async (offerId) => {
        try {
            await subscribeStudentRequest(offerId)
            toast.success("T'has inscrit a l'oferta")
            fetchOffers()
        } catch (error) {
            toast.error("Necessites pujar un currículum per poder inscriure't")
        }
    }

    const handleClick = (offer, id, e) => {
        if (e.target.id == "editOffer") {
            navigate(`edit/${id}`, { state: { offer }})
        } else if (e.target.id == "subscribe") {
            subscribeStudent(offer.id)
        } else if (e.target.id = "offer") {
            navigate(`details/${id}`, { state: { offer } })
        }
    }

    const handleSearchChange = (e) => {
        const value = e.target.value.toLowerCase()
        const filterValue = document.getElementById("filtres").value
        var filter = null

        filter = offers.filter(offer => {
            return offer.title.toLowerCase().includes(value)
        })

        if (filterValue != "Cap filtre") document.getElementById("filtres").value = "Cap filtre"

        setFilteredOffers(filter)
    }

    const handleFilterChange = (e) => {
        const value = e.target.value
        var filter = null
        const urgencyOrder = {
            "HIGH": 3,
            "MEDIUM": 2,
            "LOW": 1
        }

        switch (value) {
            case "Cap filtre":
                filter = [...offers]
                break
            case "A-Z":
                filter = [...filteredOffers].sort((a, b) => {
                    return a.title.localeCompare(b.title)
                })

                break
            case "Z-A":
                filter = [...filteredOffers].sort((a, b) => {
                    return -(a.title.localeCompare(b.title))
                })

                break
            case "Empresa A-Z":
                filter = [...filteredOffers].sort((a, b) => {
                    return a.companyName.localeCompare(b.companyName)
                })
                break
            case "Empresa Z-A":
                filter = [...filteredOffers].sort((a, b) => {
                    return -(a.companyName.localeCompare(b.companyName))
                })

                break
            case "Més urgència":
                filter = [...filteredOffers].sort((a, b) => {
                    return -(urgencyOrder[a.urgency] - urgencyOrder[b.urgency])
                })

                break
            case "Menys urgència":
                filter = [...filteredOffers].sort((a, b) => {
                    return urgencyOrder[a.urgency] - urgencyOrder[b.urgency]
                })

                break
            case "Més € bruts/any":
                filter = [...filteredOffers].sort((a, b) => {
                    return -(a.salaryInterval.split(" ")[2] - b.salaryInterval.split(" ")[2])
                })

                break
            case "Menys € bruts/any":
                filter = [...filteredOffers].sort((a, b) => {
                    return a.salaryInterval.split(" ")[2] - b.salaryInterval.split(" ")[2]
                })

                break
        }

        setFilteredOffers(filter)
    }

    return (
        <div>
            <div className="p-5 space-y-5">
                {role != "ROLE_COMPANY" &&
                    <div className="flex space-x-3">
                        <div className="md:flex-1 w-4/6">
                            <IconTextField name="searchOffers" placeholder="Buscar ofertes" icon="magnifying-glass" change={(e) => handleSearchChange(e)} />
                        </div>
                        <div className="md:w-1/6 w-2/6">
                            <IconDropdown id="filtres" change={(e) => handleFilterChange(e)} defaultOption="Cap filtre" options={["Cap filtre", "A-Z", "Z-A", "Empresa A-Z", "Empresa Z-A", "Més urgència", "Menys urgència", "Més € bruts/any", "Menys € bruts/any"]} icon="filter" />
                        </div>
                    </div>
                }
                <div className="grid md:grid-cols-2 grid-cols-1 gap-5">
                    {filteredOffers.length > 0 ? (
                        filteredOffers.map((offer, index) => (
                            offer.isEnabled && <Offer key={index} action={(e) => handleClick(offer, index, e)} id={offer.id} title={offer.title}
                            image={offer.companyImage} company={offer.companyName}
                            urgency={offer.urgency} description={offer.shortDescription} nif={offer.companyNif}
                            salaryInterval={offer.salaryInterval} studies={offer.degrees} isEnabled={offer.isEnabled} reloadOffers={fetchOffers} />
                        ))
                    ) : (
                        <p className="text-2xl">No hi ha ofertes</p>
                    )}
                </div>
            </div>
            <ToastContainer/>
        </div>
    )
}