import axiosInstance from "../AxiosInstance"
import { toast } from 'react-toastify'

const backendUrl = import.meta.env.VITE_BACKEND_URL

// POST Request that creates a new user based on the provided data
async function disableOffer(offerId) {

    console.log("Offer Id => ", offerId)

    try {
        const response = await axiosInstance.get(
            `${backendUrl}/api/offers/update-offer-status`,
            {params: {offerId: offerId}}
        )

        if(response.status === 200) {
            toast.success(response.data);
            return true
        }

    } catch (error) {
        toast.warning(error.response.data);
    }

    return false
}


export { disableOffer }