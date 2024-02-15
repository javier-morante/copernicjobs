import axiosInstance from "../AxiosInstance"
import jwtDecode from "../../utils/jwtDecode";

const backendUrl = import.meta.env.VITE_BACKEND_URL

async function navBarUserModules(setFormData) {
    try {
        const response = await axiosInstance.get(
            `${backendUrl}/api/nav-bar/get-user-module-permissions`
        )

        if(response.status === 200) {
            setFormData(response.data)
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

async function getNavBarModulePermissions(moduleName) {
    try {

        const response = await axiosInstance.get(
            `${backendUrl}/api/nav-bar/get-module-permissions`,
            {params: {moduleName}}
        )

        if(response.status === 200) {
            const data = JSON.parse(JSON.stringify(response.data))

            console.log("Valid Access Request! 😊")
            return validateUserRole(data)
        }

    } catch (error) {
        console.log(`Error => ${error} 🤔`)
        return false
    }

    console.log("Invalid Access Request 😡")
    return false
}

function validateUserRole(userRoles) {
    const role = jwtDecode().rol

    const filteredPermissions = userRoles.filter((element) => {
        return (`ROLE_${element.role}` === role)
    })

    if(filteredPermissions[0]) {
        return filteredPermissions[0].isEnabled                
    }

    return false
}


export {navBarUserModules, getNavBarModulePermissions}