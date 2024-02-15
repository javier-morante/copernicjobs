import axiosInstance from "../AxiosInstance"

const backendUrl = import.meta.env.VITE_BACKEND_URL


async function setStudentRolePrivileges(formData) {
    try {
        const response = await axiosInstance.post(
            `${backendUrl}/api/role_management/update-student-permissions`,
            formData
        )

        if(response.status === 200) {
            console.log("Valid Access Request! 😊")
            return true
        }

    } catch (error) {
        console.log(`Error => ${error} 🤔`)
        return false
    }

    console.log("Invalid Access Request 😡")
    return false
}


async function setCompanyRolePrivileges(formData) {
    try {
        const response = await axiosInstance.post(
            `${backendUrl}/api/role_management/update-company-permissions`,
            formData
        )

        if(response.status === 200) {
            console.log("Valid Access Request! 😊")
            return true
        }

    } catch (error) {
        console.log(`Error => ${error} 🤔`)
        return false
    }

    console.log("Invalid Access Request 😡")
    return false
}


async function setAdministratorRolePrivileges(formData) {
    try {
        const response = await axiosInstance.post(
            `${backendUrl}/api/role_management/update-administrator-permissions`,
            formData
        )

        if(response.status === 200) {
            console.log("Valid Access Request! 😊")
            return true
        }

    } catch (error) {
        console.log(`Error => ${error} 🤔`)
        return false
    }

    console.log("Invalid Access Request 😡")
    return false
}

export {setStudentRolePrivileges, setCompanyRolePrivileges, setAdministratorRolePrivileges}