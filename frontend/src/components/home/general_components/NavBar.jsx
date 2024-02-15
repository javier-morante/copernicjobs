import { useState, useEffect } from "react";

import homeRequest from "../../../axios/user_profiles/HomeRequest";
import { navBarUserModules } from "../../../axios/nav_bar/navBarUserModules";

import NavBarModules from "../nav_bar_modules/NavBarModules";
import HomeNavBarComponent from "./HomeNavBarComponent";

import "../../../index.css"

export default function NavBar() {
    
    const regex = /^\s*$/;

    const [userProfile, setUserProfile] = useState([])
    
    const [userModules, setUserModules] = useState([])

    useEffect(() => {
        homeRequest(setUserProfile)
        navBarUserModules(setUserModules)
    }, [])

    return (
        <div className="fixed top-0 left-0 z-40 w-full md:shadow-md">
            
            <HomeNavBarComponent userName={userProfile.name} 
                userImage={userProfile.iconPath?userProfile.iconPath:"https://cdn-icons-png.flaticon.com/512/3135/3135715.png"}/>

            <NavBarModules modules={userModules.filter((element) => {
                return element.isEnabled
            })}/>
        </div>
    )
}