import axiosInstance from "../AxiosInstance"

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default async function companyProfileModalRequest(nif) {
    try {
        const res = await axiosInstance.get(
            `${backendUrl}/api/company/get-by-nif`, 
            {params: {nif: nif}}
        )
        return res.data
    } catch (error) {
        console.log(`Error ${error}`)
        return null
    }
}