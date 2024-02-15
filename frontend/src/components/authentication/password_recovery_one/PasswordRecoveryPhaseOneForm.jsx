import React from "react";
import { useState } from "react";

import passwordRecoveryOneRequest from "../../../axios/password_recovery/PasswordRecoveryOneRequest";

import InputFieldComponent from "../general_authentication_components/InputFieldComponent";
import SubmitButtonComponent from "../general_authentication_components/SubmitButtonComponent";
import FormHelperComponent from "../general_authentication_components/FormHelperComponent";

import { useNavigate } from "react-router-dom";


export default function PasswordRecoveryPhaseOneForm() {
    const navigate = useNavigate()
    
    // State that stores the data inside of the form fields
    const [passwordRecoveryOneFormData, setpasswordRecoveryOneFormData] = useState({
        email: "",
    })

    // Handle Changes in the input fields of the form
    const handleInputChange = (e) => {
        const {name, value} = e.target

        setpasswordRecoveryOneFormData({
            ...passwordRecoveryOneFormData,
            [name]: value,
        })
    }

    // Handle the Form Submit action and send the request to the backend
    const handleSubmit = async (e) => {
        e.preventDefault()


        passwordRecoveryOneRequest(passwordRecoveryOneFormData)
        .then((status) => {
            (status)? navigate("/password-recovery-two") : alert("Invalid Email")
        })
    }

    return (
        <form onSubmit={handleSubmit} className="lg:w-1/3 sm:w-1/2  w-11/12 p-5 rounded-lg
        bg-white shadow-xl border-2">
            
            {/* Title Screen */}
            <h1 className="text-3xl font-bold select-none">
                Recuperar contrasenya
            </h1>

            <br/>

            <div className="w-full grid gap-5">

                {/* Necesary Input Fields */}
                <InputFieldComponent type="email" name="email" value={passwordRecoveryOneFormData.email} 
                    action={handleInputChange} placeholder={"Correu"} icon="envelope"/>

            </div>

            <br/><br/><br/><br/>

            {/* Submit Button */}
            <div className="w-full flex place-items-center justify-center">
                <SubmitButtonComponent buttonContent={"Envia"}/>
            </div>

            <br/>

            {/* Bottom Helper */}
            <div className="w-full grid place-items-center justify-center"> 
                 
                <FormHelperComponent text="Tornar enrere?" 
                    linkText="Iniciar sessió" link="/sign-in"/>
            
            </div>

        </form>
    )
}