import { Link, useNavigate } from 'react-router-dom'
import NavBarComponent from '../authentication/general_authentication_components/NavBarComponent'

const backendUrl = import.meta.env.VITE_BACKEND_URL


export default function ErrorScreen() {
    const navigate = useNavigate()

    return (
        <div class="flex flex-col h-screen w-screen text-center">
            <NavBarComponent/>
            <div class="h-4/6 flex flex-col items-center justify-center">
                <h1 class="text-7xl font-bold pb-4">S'ha trobat un error</h1>
                <p class="text-lg pb-16">Sembla que alguna cosa ha anat malament</p>
                <Link onClick={() => {navigate(-1)}}>
                    <button className='bg-brand_orange px-10 py-2 rounded-md text-white
                        hover:scale-110 hover:bg-orange-300 transition'>
                        Torna enrere
                    </button>
                </Link>
            </div>
            <div class="flex justify-end pb-4">
                <img class="w-9/12"  src={`${backendUrl}/api/images/Error.png`} />
            </div>
        </div>
    )
}
