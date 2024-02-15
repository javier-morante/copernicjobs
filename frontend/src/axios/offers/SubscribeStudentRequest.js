import axiosInstance from "../AxiosInstance";

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default async function subscribeStudentRequest(id) {
    try {
        const res = await axiosInstance.get(
            `${backendUrl}/api/offers/subscribe`, {params: {id: id}}
        )

        return res.status
    } catch (error) {
        console.log(`Error: ${error}`)
        throw error
    }
}