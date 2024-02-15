import axiosInstance from "../AxiosInstance"
import { toast } from 'react-toastify'


const backendUrl = import.meta.env.VITE_BACKEND_URL


export default async function studentProfileSetData(formData) {
    try {
        const response = await axiosInstance.post(
            `${backendUrl}/api/user-profile/set-student-data`,
            formData
        )

        if(response.status === 200) {
            toast.success("Informació actualitzada!");
            return
        }

    } catch (error) {
        if(error.response) {
            toast.warning("Error al actualitzar la informació");
        }
    }
}