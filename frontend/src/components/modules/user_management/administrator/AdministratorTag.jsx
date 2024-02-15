import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import "../../../../index.css"

export default function AdministratorTag(
    { administratorData, handleUserDelete, handleUserUpdate, handleUserLock, handleUserPasswordUpdate }
) {
    return (
        <div className="w-full h-28 flex place-items-center justify-center shadow-md
            bg-white rounded-md p-3">

            <div className="flex-1">
                <h1 className="font-bold text-xl">
                    {administratorData.name} {administratorData.surname}
                </h1>

                <h1 className="text-sm flex items-center gap-2">
                    <FontAwesomeIcon icon={"phone"} />
                    {administratorData.phone}
                </h1>

                <h1 className="text-sm flex items-center gap-2">
                    <FontAwesomeIcon icon={"envelope"} />
                    {administratorData.email}
                </h1>
            </div>

            <div className="flex gap-2 text-xl text-center text-white">
                <button onClick={handleUserPasswordUpdate} className="rounded-md w-9 h-9 bg-brand_orange
                hover:scale-105 transition">
                    <FontAwesomeIcon icon={"key"} />
                </button>

                <button onClick={handleUserUpdate} className="rounded-md w-9 h-9 bg-brand_orange
                hover:scale-105 transition">
                    <FontAwesomeIcon icon={"edit"} />
                </button>

                <button onClick={handleUserLock} className={`rounded-md w-9 h-9
                hover:scale-105 transition
                ${(administratorData.isEnabled) ? "bg-valid_green" : "bg-warning_red"}`}>
                    <FontAwesomeIcon icon={"lock"} />
                </button>

                <button onClick={handleUserDelete} className="rounded-md w-9 h-9 bg-warning_red
                hover:scale-105 transition">
                    <FontAwesomeIcon icon={"trash"} />
                </button>
            </div>
        </div>
    )
}