import axiosInstance from "../AxiosInstance";

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default async function unsubscribeStudentRequest(id) {
    try {
        const res = await axiosInstance.get(
            `${backendUrl}/api/offers/unsubscribe`, {params: {id}}
        )

        return res.status
    } catch (error) {
        console.log(`Error: ${error}`)
    }
}