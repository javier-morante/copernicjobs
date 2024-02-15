import axios from "axios";
import { toast } from "react-toastify";

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default async function accessRequestRequest(formData) {
    try {
        const response = await axios.post(
            `${backendUrl}/api/register/register-access`,
            formData
        )

        if(response.status === 201) {
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


export function accessRequestRequest2(formData) {
    return new Promise((resolve) => {
        toast.promise(
            axios.post(`${backendUrl}/api/register/register-access`, formData),
            {
                loading: 'Creant Registre...', // Mensaje mientras se resuelve la promesa
                success: {render() {
                    resolve(true); // Resolvemos la promesa con true si la solicitud es exitosa
                    return 'Sol·licitud de registre enviada!'; // Mensaje de éxito
                }},
                error: { render(e) {
                    resolve(false); // Rechaamos la promesa con false si hay un error
                    return e.data.response.data; // Mensaje de error
                }},
            }
        );
    });
}