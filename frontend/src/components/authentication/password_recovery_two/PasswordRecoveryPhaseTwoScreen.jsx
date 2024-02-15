import React from "react";
import NavBarComponent from "../general_authentication_components/NavBarComponent";
import PasswordRecoveryPhaseTwoForm from "./PasswordRecoveryPhaseTwoForm";



export default function PasswordRecoveryPhaseTwoScreen() {
    return <div className="w-screen h-screen flex-col">
        <NavBarComponent/>
        
        <div className="h-7/12 flex place-items-center justify-center p-10">
            <PasswordRecoveryPhaseTwoForm/>
        </div>
    </div>
}