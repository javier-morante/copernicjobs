import axiosInstance from "../AxiosInstance"
import { toast } from 'react-toastify'


const backendUrl = import.meta.env.VITE_BACKEND_URL


export default async function companyProfileSetData(formData) {
    try {
        const response = await axiosInstance.post(
            `${backendUrl}/api/user-profile/set-company-data`,
            formData,
            {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }
        )

        if(response.status === 200) {
            toast.success("Informació actualitzada!");
            return
        }

    } catch (error) {
        toast.warning("Error al actualitzar la informació");
    }
}