import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { library } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';
library.add(fas)

import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import HomeNavBarUserProfileMenuComponent from "./HomeNavBarUserProfileMenuComponent";

import "../../../index.css"
import AccessFilterComponent from "../../../router/AccessFilterComponent";
import jwtDecode from "../../../utils/jwtDecode";

const backendUrl = import.meta.env.VITE_BACKEND_URL


export default function HomeNavBarComponent({userName, userImage}) {
    const [userRole, setUserRole] = useState("")

    const [profilePopupStatus, setProfilePopupStatus] = useState(false)

    const handlePopupStatus = () => {
        setProfilePopupStatus(!profilePopupStatus)
    }

    useEffect(() => {
        setUserRole(jwtDecode().rol)
    }, [])

    return <div className="w-full h-auto bg-brand_orange flex p-3 px-5">
        <div className="flex flex-1 place-items-center justify-left gap-5">
            <Link to={"/home"} className="hover:scale-110 hover:cursor-pointer transition">
                <img src={`${backendUrl}/api/images/logo.png`}
                    className="w-auto h-14"/>
            </Link>
            <h1 className="text-2xl text-white font-bold">
                Borsa Copèrnic
            </h1>
        </div>

        <div onClick={handlePopupStatus} className="no-print flex place-items-center justify-center text-white">
            <div className="w-fit h-fit flex place-items-center justify-center gap-2
                hover:scale-105 hover:cursor-pointer transition origin-right">
                <h1>{userName}</h1>

                {
                    userRole === "ROLE_COMPANY" && (
                        <img src={userImage} className="w-10 h-10 rounded-full object-cover"/>
                    )   
                }

                <FontAwesomeIcon icon={"caret-down"} className={`transition 
                    ${(profilePopupStatus)? "rotate-180" : "rotate-0"}`}/>
            </div>
        </div>
        {(profilePopupStatus)? <HomeNavBarUserProfileMenuComponent/> : null}
    </div>
}