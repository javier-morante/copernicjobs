import { useNavigate } from "react-router-dom"
import Cookies from "js-cookie"


export default function LogOutButtonComponent() {
    const navigate = useNavigate()

    const logOut = () => {
        Cookies.remove('token')
        navigate("/sign-in")
    }

    return <button onClick={logOut} className="w-full p-2 bg-warning_red text-white rounded-md
        hover:scale-105 hover:cursor-pointer transition">
        Tancar sessió
    </button>
}