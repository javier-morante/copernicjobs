import axiosInstance from "../AxiosInstance"
import { toast } from 'react-toastify'

const backendUrl = import.meta.env.VITE_BACKEND_URL

// POST Request that creates a new user based on the provided data
async function createStudentRequest(formData) {
    try {
        const response = await axiosInstance.post(
            `${backendUrl}/api/user-management/student/create-student`,
            formData
        )

        if(response.status === 200) {
            toast.success("Estudiant creat!");
            return true
        }

    } catch (error) {
        toast.warning("Error al crear l'estudiant");
    }

    return false
}

// GET Request that fetch all the users from the database and then
// sets them as the value for the students state
async function getStudentsRequest(setStudents) {
    try {
        const response = await axiosInstance.get(
            `${backendUrl}/api/user-management/student/get-students`,
        )

        if(response.status === 200) {
            setStudents(response.data)
            return true
        }

    } catch (error) {
        toast.warning("Error al carregar els estudiants");
    }

    return false
}

// POST Request that updates a user data based on the provided data
async function updateStudentRequest(formData, reloadUsers) {
    try {
        const response = await axiosInstance.post(
            `${backendUrl}/api/user-management/student/update-student`,
            formData
        )

        if(response.status === 200) {
            toast.success("Estudiant actualitzat!");
            reloadUsers()
            return true
        }

    } catch (error) {
        toast.warning("Error al actualitzar l'estudiant");
    }

    return false
}

export { createStudentRequest, getStudentsRequest, updateStudentRequest}