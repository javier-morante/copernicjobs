import axiosInstance from "../AxiosInstance"
import { toast } from 'react-toastify'

const backendUrl = import.meta.env.VITE_BACKEND_URL

// POST Request that creates a new user based on the provided data
async function deleteUserRequest(userId, updateUsers) {
    try {
        const response = await axiosInstance.get(
            `${backendUrl}/api/user-management/delete-user`,
            {params: {userId: userId}}
        )

        if(response.status === 200) {
            toast.success("Usuari eliminat!")
            updateUsers()
            return true
        }

    } catch (error) {
        toast.warning("Error al eliminar l'usuari");
        console.log(error)
    }

    return false
}

// GET Request that fetch all the users from the database and then
// sets them as the value for the students state
async function lockUserRequest(userId, updateUsers) {
    try {
        const response = await axiosInstance.get(
            `${backendUrl}/api/user-management/lock-user`,
            {params: {userId: userId}}
        )

        if(response.status === 200) {
            updateUsers()
            toast.success("Usuari bloquejat!")
            return true
        }

    } catch (error) {
        toast.warning("Error al bloquejar l'usuari");
        console.log(error)
    }

    return false
}

// POST Request that updates a user data based on the provided data
async function updateUserPasswordRequest(formData, reloadUsers) {
    try {
        const response = await axiosInstance.post(
            `${backendUrl}/api/user-management/update-user-password`,
            formData
        )

        if(response.status === 200) {
            toast.success("Contrasenya de l'usuari actualitzada!");
            reloadUsers()
            return true
        }

    } catch (error) {
        toast.warning("Error al actualitzar la contrasenya de l'usuari");
    }

    return false
}

export { deleteUserRequest, lockUserRequest, updateUserPasswordRequest }