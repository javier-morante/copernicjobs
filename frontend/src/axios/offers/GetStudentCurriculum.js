import axiosInstance from "../AxiosInstance";

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default async function getStudentCurriculums(code) {
    try {
        const res = await axiosInstance.get(`${backendUrl}/api/download/pdf/${code}`)

        return res.data
    } catch (error) {
        console.log(`Error: ${error}`)
    }
}