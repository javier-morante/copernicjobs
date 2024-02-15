import { useState, useEffect } from "react";
import homeRequest from "../../../axios/user_profiles/HomeRequest";

import RoleBasedRouterComponent from "../../../router/RoleBasedRouterComponent";
import StudentUserProfileScreen from "./student_user_profile/StudentUserProfileScreen";
import CompanyUserProfileScreen from "./company_user_profile/CompanyUserProfileScreen";
import AdministratorUserProfileScreen from "./administrator_user_profile/AdministratorUserProfileScreen";
import NavBar from "../general_components/NavBar";
import Footer from "../footer_modules/Footer";

export default function UserProfileScreen() {

    const [userProfile, setUserProfile] = useState([])

    useEffect(() => {
        homeRequest(setUserProfile)
    }, [])


    return (
        <div>
            <NavBar/>
            
         
            <div className="mt-40">
                <RoleBasedRouterComponent rolesAndPaths={[
                    {
                        rol: "ROLE_STUDENT",
                        component: <StudentUserProfileScreen/>
                    },
                    {
                        rol: "ROLE_COMPANY",
                        component: <CompanyUserProfileScreen/> 
                    },
                    {
                        rol: "ROLE_ADMINISTRATOR",
                        component: <AdministratorUserProfileScreen/> 
                    }
                ]}/>
            </div>
            <br/><br/>
            <Footer/>
        </div>
    )
}