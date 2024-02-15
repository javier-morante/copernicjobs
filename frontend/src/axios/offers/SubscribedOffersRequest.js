import axiosInstance from "../AxiosInstance";

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default async function subscribedOffersRequest() {
    try {
        const res = await axiosInstance.get(`${backendUrl}/api/offers/get-subscribed-offers`)

        return res.data
    } catch (error) {
        console.log(`Error: ${error}`)
    }
}