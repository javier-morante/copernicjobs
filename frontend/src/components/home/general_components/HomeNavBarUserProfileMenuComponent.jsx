import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { library } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { Link } from "react-router-dom";
import LogOutButtonComponent from "./LogOutButtonComponent";
library.add(fas)


export default function HomeNavBarUserProfileMenuComponent() {

    return <div className="xl:w-2/12 md:w-3/12 sm:1/6 absolute top-16 right-2 xl:right-9
        grid gap-5 p-5 bg-white drop-shadow-xl rounded-md ">

        <Link to={"/user-profile"} className="w-full flex place-items-center gap-2
            hover:text-brand_orange hover:cursor-pointer transition">
            <FontAwesomeIcon icon="gear" />
            Configuració
        </Link>

        <Link to={"/password-recovery-one"} className="w-full flex place-items-center gap-2
            hover:text-brand_orange hover:cursor-pointer transition">
            <FontAwesomeIcon icon="lock" />
            Restaurar contrasenya
        </Link>

        <LogOutButtonComponent />
    </div>
}