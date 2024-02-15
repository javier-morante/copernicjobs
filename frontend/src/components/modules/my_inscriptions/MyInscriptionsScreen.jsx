import { useEffect, useState } from "react"
import { ToastContainer, toast } from "react-toastify"
import subscribedOffersRequest from "../../../axios/offers/SubscribedOffersRequest"
import unsubscribeStudentRequest from '../../../axios/offers/UnsubscribeStudentRequest'
import Offer from "../offers/Offer"

export default function MyInscriptionsScreen() {
    const [offers, setOffers] = useState([])

    useEffect(() => {
        fetchOffers()
    }, [])

    const fetchOffers = async () => {
        try {
            const fetchedOffers = await subscribedOffersRequest()
            setOffers(fetchedOffers)
        } catch (error) {
            console.log("Error fetching offers: " + error)
        }
    }

    const unsubscribeStudent = async (offerId) => {
        await unsubscribeStudentRequest(offerId)
        toast.success("T'has desinscrit de l'oferta")
        fetchOffers()
    }

    const handleClick = (offer, id, e) => {
        if (e.target.id == "editOffer") {
            navigate(`edit/${id}`, { state: { offer }})
        } else if (e.target.id == "unsubscribe") {
            unsubscribeStudent(offer.id)
        } else if (e.target.id = "offer") {
            navigate(`details/${id}`, { state: { offer } })
        }
    }

    return (
        <div className="grid md:grid-cols-2 grid-cols-1 gap-5 p-5">
            {offers.length > 0 ? (
                offers.map ((offer, index) => (
                    <Offer key={index} action={(e) => handleClick(offer, index, e)} id={offer.id} title={offer.title}
                    image={offer.companyImage} company={offer.companyName}
                    urgency={offer.urgency} description={offer.shortDescription} nif={offer.companyNif}
                    salaryInterval={offer.salaryInterval} studies={offer.degrees} />
                ))
            ) : (
                <p className="col-span-2 text-2xl text-center">No hi ha ofertes</p>
            )}
            <ToastContainer/>
        </div>
    )
}