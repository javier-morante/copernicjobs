import axiosInstance from "../AxiosInstance"

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default async function studentProfileModalRequest(email) {
    try {
        const res = await axiosInstance.get(
            `${backendUrl}/api/student/get-by-email`, 
            {params: {email: email}}
        )
        return res.data
    } catch (error) {
        console.log(`Error ${error}`)
        return null
    }
}