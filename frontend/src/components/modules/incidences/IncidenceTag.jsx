import { useEffect, useState } from "react";
import { updateIncidences } from "../../../axios/incidences/IncidencesRequest";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import jwtDecode from "../../../utils/jwtDecode";

export default function IncidenceTag({ incidence }) {
    const [isResolt, setIsResolt] = useState(false);

    const handleResolt = () => {
        incidence.status = "SOLVED"
        updateIncidences(incidence, setIsResolt)
    }

    const isAdministrator = ()=>{
        const token = jwtDecode()
        if(token.rol !== 'ROLE_ADMINISTRATOR'){
            return false;
        }
        return true;
    }

    useEffect(() => {
        if (incidence.status === "SOLVED") setIsResolt(true);

    }, [])

    const transform = (date) => {
        const arrDate = date.split('-')
        return `${arrDate[2]}/${arrDate[1]}/${arrDate[0]}`
    }

    return (
        <div className="shadow-lg rounded-md bg-white p-4 ">
            <div className="flex justify-between p-3 items-center" >
                <h1 className="text-4xl font-bold">{incidence.title}</h1>
                <div className="flex-col text-right space-y-2">
                    <p className="text-warning_red text-xl">{incidence.category}</p>
                    <p className={`text-xl font-bold ${incidence.status === "SOLVED" ? "text-valid_green" : "text-brand_orange"}`}>{incidence.status}</p>
                </div>
            </div>
            <div className="flex justify-between px-4 pb-4 ">
                <div className=" flex w-4/5 gap-4">
                    <div className="flex items-center gap-2">
                        <FontAwesomeIcon icon={incidence.userRole === "COMPANY" ? "fa-solid fa-building" : "fa-solid fa-user"} />
                        <p>{incidence.userName}</p>
                    </div>

                    <div className="flex items-center gap-2">
                        <FontAwesomeIcon icon="fa-solid fa-calendar-days" />
                        <p>{transform(incidence.creationDate)}</p>
                    </div>
                </div>
            </div>
            
            <p className="text-lg p-4 break-words">{incidence.description}</p>
            
            {!isResolt && (isAdministrator())&&<button onClick={handleResolt} className="bg-valid_green text-white w-6/12 py-1 rounded-md font-bold" >Marcar com resolta</button>}
        </div>
    )
}