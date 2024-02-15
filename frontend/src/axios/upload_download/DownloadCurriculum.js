import axiosInstance from "../AxiosInstance"

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default async function downloadCurriculum(fileCode) {
    try {
        const res = await axiosInstance.get(
            `${backendUrl}/api/download/pdf/${fileCode}`, { responseType: "blob" }
        )
        return res.data
    } catch (error) {
        console.log(`Error ${error}`)
        return null
    }
}