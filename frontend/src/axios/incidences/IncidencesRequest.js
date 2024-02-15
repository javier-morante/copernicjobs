import axiosInstance from "../AxiosInstance"
import { toast } from 'react-toastify';


const backendUrl = import.meta.env.VITE_BACKEND_URL

export function postIncidence(data) {

    console.log("Data => ", data)


    return new Promise((resolve) => {
        toast.promise(
            axiosInstance.post(`${backendUrl}/api/incidence`, data),
            {
                loading: 'Creant incidencia...',
                success: {render() {
                    resolve(true);
                    return 'Incidència creada!';
                }},
                error: { render() {
                    resolve(false); // Rechazamos la promesa con false si hay un error
                    return 'Error al crear la incidència'; // Mensaje de error
                }},
            }
        );
    });
}


export async function getIncidences(setIncidences,filterMethod){
    try{
        const response = await axiosInstance.get(
            `${backendUrl}/api/incidence`,
        ) 

        if(response.status === 200) {
            const trueResponse = response.data
            if(!Array.isArray(trueResponse)){
                return false;
            }
            
            setIncidences(filterMethod(trueResponse))
            return true;
        }

    }catch (error) {
        console.log(`Error => ${error} 🤔`)
        return false
    }
}

export function updateIncidences(incidence,setIsResolt) {
    return new Promise((resolve) => {
        toast.promise(
            axiosInstance.post(
                `${backendUrl}/api/incidence/update`,
                incidence
            ),
            {
                loading: 'Resolent incidència...',
                success: {render() {
                    resolve(true);
                    setIsResolt(true)
                    return 'Incidència resolta!';
                }},
                error: { render() {
                    resolve(false); // Rechazamos la promesa con false si hay un error
                    return 'Error al resoldre la incidència'; // Mensaje de error
                }},
            }
        );
    });
}