import axios from "axios";

const backendUrl = import.meta.env.VITE_BACKEND_URL

async function getAccesRequest(data) {
    try {
        const response = await axios.get(
            `${backendUrl}/api/register/get-all`
        )

        if(response.status === 200) {
            return response.data;
        }

    } catch (error) {
        console.log(`Error => ${error} 🤔`)
        return [];
    }
}

async function setAccesRequest(data) {
    try {
        const response = await axios.post(
            `${backendUrl}/api/register/validation-request`,data
        )

        if(response.status === 200) {
            return true
        }

    } catch (error) {
        console.log(`Error => ${error} 🤔`)
        return false;
    }
}

export { getAccesRequest,setAccesRequest }