import { setAccesRequest } from '../../../axios/acces_request/AccesRequest'
import { useState } from 'react'

export default function ValidateRequest({ request, index, reload }) {
    const [isVisible, setIsVisible] = useState(true);

    const handleButton = async (validate) => {
        validate ? request.status = "VALIDATED" : request.status = "REJECTED";
        const done = await setAccesRequest(request);
        if (done) {
            setIsVisible(false)
            reload(index);
        }
    }

    return (
        isVisible && (
            <div className="w-full shadow-lx h-fit flex items-center p-5 rounded-2xl shadow-lg bg-white max-md:flex-col max-md:gap-4">
                <div className="flex-1">
                    <h2 className="font-bold text-xl">Nom de l'empresa: {request.companyName}</h2>
                    <h2 className="font-bold text-xl">NIF: {request.nif}</h2>
                    <p>Correu de contacte: {request.email}</p>
                </div>
                <div className="space-x-5">
                    <button to="/home/requests-management" onClick={() => { handleButton(true) }} className="bg-valid_green rounded-md text-light_gray_1 w-48 h-fit py-1 transition px-4 hover:scale-110">
                        Validar l'accés
                    </button>
                    <button to="/home/requests-management" onClick={() => { handleButton(false) }} className="bg-warning_red rounded-md text-light_gray_1 w-48 h-fit py-1 transition px-4 hover:scale-110">
                        Denegar l'accés
                    </button>
                </div>
            </div>
        )
    )
}
