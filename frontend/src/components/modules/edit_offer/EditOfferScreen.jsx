import EditOfferForm from "./EditOfferForm";
import { useLocation } from "react-router-dom";

export default function CreateOfferScreen() {
    const { state } = useLocation()
    const { offer } = state

    return (
        <div className="w-full flex place-items-center justify-center">
            <div className="xl:w-6/12 w-10/12">
                <EditOfferForm offerData={offer}/>
            </div>
        </div>
    )
}