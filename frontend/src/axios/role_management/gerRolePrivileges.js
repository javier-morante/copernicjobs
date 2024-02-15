import axiosInstance from "../AxiosInstance"

const backendUrl = import.meta.env.VITE_BACKEND_URL


async function getRolePrivileges(role, setFormData) {
    try {
        const response = await axiosInstance.get(
            `${backendUrl}/api/role_management/get-role-permissions`,
            {params: {userRole: role}}
        )

        if(response.status === 200) {
            setFormData(() => JSON.parse(JSON.stringify(response.data)))
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


export {getRolePrivileges}