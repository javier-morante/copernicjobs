import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

import offersRequest from '../../../axios/offers/OffersRequest'
import companyProfileModalRequest from '../../../axios/offers/CompanyIdRequest'
import MyOffer from './MyOffer'
import { ToastContainer, toast } from 'react-toastify'


export default function MyOffersScreen() {
    const navigate = useNavigate()
    const [offers, setOffers] = useState([])
    const [companyId, setCompanyId] = useState(0)
    

    useEffect(() => {
        const fetchCompanyId = async() => {
            try {
                const companyId = await companyProfileModalRequest()
                setCompanyId(companyId)
            } catch (error) {
                console.log("Error fetching offers: " + error)
            }
        }

        fetchCompanyId()
    }, [])

    useEffect(() => {
        console.log(companyId)
        fetchOffers()
    }, [companyId])

    const fetchOffers = async () => {
        try {
            const fetchedOffers = await offersRequest()
            setOffers(fetchedOffers)
        } catch (error) {
            console.log("Error fetching offers: " + error)
        }
    }

    useEffect(() => {
        console.log(offers)

    }, [offers])

    const handleClick = (offer, id, e) => {
        if (e.target.id == "editOffer") {
            navigate(`edit/${id}`, { state: { offer } })
        } else if (e.target.id = "offer") {
            navigate(`details/${id}`, { state: { offer } })
        }
    }

    return (
        <div>
            <div className="p-5 space-y-5">
                <div className="grid md:grid-cols-2 grid-cols-1 gap-5">
                    {offers.filter(offer => offer.companyNif == companyId).length > 0 ? (
                        offers.filter(offer => offer.companyNif == companyId).map((offer, index) => (
                            <MyOffer key={index} action={(e) => handleClick(offer, index, e)} id={offer.id} title={offer.title}
                            image={offer.companyImage} company={offer.companyName}
                            urgency={offer.urgency} description={offer.shortDescription} nif={offer.companyNif}
                            minSalary={offer.salaryInterval.split(" ")[0]} maxSalary={offer.salaryInterval.split(" ")[2]}
                            studies={offer.degrees} isEnabled={offer.isEnabled} reloadOffers={fetchOffers}/>
                        ))
                    ) : (
                        <p className="col-span-2 text-2xl text-center">No hi ha ofertes</p>
                    )}
                </div>
            </div>

            <ToastContainer/>

        </div>
    )
}