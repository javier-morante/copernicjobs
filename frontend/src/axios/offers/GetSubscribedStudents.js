import axiosInstance from "../AxiosInstance";

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default async function getSubscribedStudents(offerId) {
    try {
        const res = await axiosInstance.get(`${backendUrl}/api/offers/get-subscribed-students`, { params: {offerId} })

        return res.data
    } catch (error) {
        console.log(`Error: ${error}`)
    }
}