import axiosInstance from "../AxiosInstance"
import { toast } from 'react-toastify'

const backendUrl = import.meta.env.VITE_BACKEND_URL

// POST Request that creates a new user based on the provided data
async function createAdministratorRequest(formData) {
    try {
        const response = await axiosInstance.post(
            `${backendUrl}/api/user-management/administrator/create-administrator`,
            formData
        )

        if(response.status === 200) {
            toast.success("Administrador creat!");
            return true
        }

    } catch (error) {
        toast.warning("Error al crear l'administrador");
    }

    return false
}

// GET Request that fetch all the users from the database and then
// sets them as the value for the students state
async function getAdministratorsRequest(setStudents) {
    try {
        const response = await axiosInstance.get(
            `${backendUrl}/api/user-management/administrator/get-administrators`,
        )

        if(response.status === 200) {
            setStudents(response.data)
            return true
        }

    } catch (error) {
        toast.warning("Error al carregar els administradors");
    }

    return false
}

// POST Request that updates a user data based on the provided data
async function updateAdministratorRequest(formData, reloadUsers) {
    try {
        const response = await axiosInstance.post(
            `${backendUrl}/api/user-management/administrator/update-administrator`,
            formData
        )

        if(response.status === 200) {
            toast.success("Administrador actualitzat!");
            reloadUsers()
            return true
        }

    } catch (error) {
        toast.warning("Error al actualitzar l'administrador");
    }

    return false
}

export { createAdministratorRequest, getAdministratorsRequest, updateAdministratorRequest}