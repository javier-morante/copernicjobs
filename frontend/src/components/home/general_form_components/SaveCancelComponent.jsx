import { Link } from "react-router-dom"

export default function SaveCancelComponent(
    {path}
) {
    return (
        <div className="w-full flex place-items-center justify-left gap-5">
            <button type="submit" className="w-4/12 p-1 
                bg-valid_green text-white rounded-md transition
                hover:scale-105">
                Guardar
            </button>

            <Link to={(path != null) ? path : "/user-profile"} type="submit" className="w-4/12 p-1 
                bg-warning_red text-white text-center rounded-md 
                transition hover:scale-105">
                Cancel·lar
            </Link>
        </div>
    )
}