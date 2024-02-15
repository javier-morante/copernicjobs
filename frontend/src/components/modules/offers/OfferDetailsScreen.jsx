import { Link, useLocation, useNavigate, useParams } from "react-router-dom"
import jwtDecode from '../../../utils/jwtDecode'
import Offer from './Offer'
import OfferDetailsField from './OfferDetailsField'
import OfferDetailsHeader from './OfferDetailsHeader'
import SubscribedStudents from './SubscribedStudents'
import OfferDetailsListField from "./OfferDetailsListField"
import LargeButton from "../../home/general_form_components/LargeButton"
import MyOffer from "../my_offers/MyOffer"

export default function OfferDetailsScreen () {
    const role = jwtDecode().rol
    const { state } = useLocation()
    const { offer } = state

    return (
        <div className="flex place-content-center">
            <div className="lg:w-8/12 w-11/12">
                {role == "ROLE_COMPANY" ? (
                    <div className="w-full flex gap-5">
                        <div className="w-1/2">
                            <MyOffer title={offer.title} image={offer.companyImage} company={offer.companyName}
                                urgency={offer.urgency} description={offer.shortDescription} nif={offer.companyNif}
                                salaryInterval={offer.salaryInterval} studies={offer.degrees}/>
                        </div>
                        <div className="w-1/2">
                            <SubscribedStudents offerId={offer.id}/>
                        </div>

                        {/* Offer statistics */}
                    </div>
                ) : (
                    <div className="space-y-5 gap-0 p-0">
                        <OfferDetailsHeader title={offer.title} company={offer.companyName} image={offer.companyImage} description={offer.shortDescription} urgency={offer.urgency}/>
                        <div className="grid gap-5 m-5">
                            <OfferDetailsListField title="Informació" list={[
                                offer.location && "Direcció: " + offer.location,
                                offer.workday && "Tipus de jornada: " + offer.workday,
                                offer.salaryInterval && "Sou: " + offer.salaryInterval
                            ]}/>
                            {offer.requirementsDescription && <OfferDetailsField title="Requisits" description={offer.requirementsDescription}/>}
                            {offer.functionsDescription && <OfferDetailsField title="Funcions" description={offer.functionsDescription}/>}
                            {offer.technologiesDescription && <OfferDetailsField title="Tecnologies" description={offer.technologiesDescription}/>}
                            {offer.degrees &&<OfferDetailsListField title="Estudis" list={offer.degrees}/>}
                        </div>
                    </div>
                )}

            </div>
        </div>
    )
}