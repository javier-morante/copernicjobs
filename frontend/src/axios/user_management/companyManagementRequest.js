import axiosInstance from "../AxiosInstance"
import { toast } from 'react-toastify'

const backendUrl = import.meta.env.VITE_BACKEND_URL

// POST Request that creates a new user based on the provided data
async function createCompanyRequest(formData) {
    try {
        const response = await axiosInstance.post(
            `${backendUrl}/api/user-management/company/create-company`,
            formData
        )

        if(response.status === 200) {
            toast.success("Empresa creada!");
            return true
        }

    } catch (error) {
        toast.warning("Error al crear l'empresa");
    }

    return false
}

// GET Request that fetch all the users from the database and then
// sets them as the value for the students state
async function getCompanysRequest(setStudents) {
    try {
        const response = await axiosInstance.get(
            `${backendUrl}/api/user-management/company/get-companies`,
        )

        if(response.status === 200) {
            setStudents(response.data)
            return true
        }

    } catch (error) {
        toast.warning("Error al carregar les empreses");
    }

    return false
}

// POST Request that updates a user data based on the provided data
async function updateCompanyRequest(formData, reloadUsers) {
    try {
        const response = await axiosInstance.post(
            `${backendUrl}/api/user-management/company/update-company`,
            formData
        )

        if(response.status === 200) {
            toast.success("Empresa actualitzada!");
            reloadUsers()
            return true
        }

    } catch (error) {
        toast.warning("Error al actualitzar l'empresa");
    }

    return false
}

export { createCompanyRequest, getCompanysRequest, updateCompanyRequest}