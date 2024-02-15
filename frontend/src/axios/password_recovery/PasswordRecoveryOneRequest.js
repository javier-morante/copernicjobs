import axios from "axios";

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default async function passwordRecoveryOneRequest(formData) {
    try {
        const response = await axios.post(
            `${backendUrl}/api/password-recovery/phase-one`,
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