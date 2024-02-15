import axiosInstance from "../AxiosInstance"
import { toast } from 'react-toastify'

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default async function createOfferRequest(formData) {
    try {
        const response = await axiosInstance.post(
            `${backendUrl}/api/offers/create-offer`,
            formData
        )

        if(response.status === 200) {
            toast.success("Oferta creada!");
            return true
        }

    } catch (error) {
        console.log(error)
        if(error.response) {
            toast.warning("Error al crear l'oferta");
        }
    }

    return false
}