import React from "react";
import NavBarComponent from "../general_authentication_components/NavBarComponent"
import AccessRequestForm from "./AccessRequestForm"



export default function AccessRequestScreen() {
    return <div className="w-screen h-screen flex-col">
        <NavBarComponent/>
        
        <div className="h-7/12 flex place-items-center justify-center p-10">
            <AccessRequestForm/>
        </div>
    </div>
}