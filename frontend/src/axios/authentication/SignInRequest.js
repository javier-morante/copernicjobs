import axios from "axios";
import Cookies from "js-cookie";

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default async function signInRequest(formData) {
    try {

        const response = await axios.post(
            `${backendUrl}/api/authentication/sign-in`,
            formData
        )

        if(response.status === 200) {
            const requestBody = JSON.parse(JSON.stringify(response.data))
            Cookies.set('token', requestBody.token, {expires: null, sameSite: 'Strict'})
            console.log("Valid Sign In Request! 😊")
            return true
        }

    } catch (error) {
        console.log(`Error => ${error} 🤔`)
        return false
    }

    console.log("Invalid Sign In Request 😡")
    return false
}