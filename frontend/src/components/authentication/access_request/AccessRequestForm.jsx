import React from "react";
import { useState } from "react";

import { accessRequestRequest2 } from "../../../axios/authentication/AccessRequestRequest";

import InputFieldComponent from "../general_authentication_components/InputFieldComponent";
import SubmitButtonComponent from "../general_authentication_components/SubmitButtonComponent";
import FormHelperComponent from "../general_authentication_components/FormHelperComponent";

import { ToastContainer } from 'react-toastify'



export default function AccessRequestForm() {

    // State that stores the status of the password visibility
    const [passwordVisibility, setPasswordVisibility] = useState(false)

    // Handle changes in the password visibility toggle
    const handlePasswordVisibilityChange = () => {
        setPasswordVisibility(!passwordVisibility)
    }

    // State that stores the data inside of the form fields
    const [accessRequestData, setAccessRequestData] = useState({
        companyName: "",
        email: "",
        contactPhone: "",
        nif: "",
        password: ""
    })

    // Handle Changes in the input fields of the form
    const handleInputChange = (e) => {
        const { name, value } = e.target

        setAccessRequestData({
            ...accessRequestData,
            [name]: value,
        })
    }

    // Handle the Form Submit action and send the request to the backend
    const handleSubmit = async (e) => {
        e.preventDefault()
        accessRequestData.contactPhone = accessRequestData.contactPhone.replace(/\s/g, '');
        accessRequestRequest2(accessRequestData)
        .then((status) => {

            (status) ? navigate("/sign-in") : alert("Invalid Credentials")
            
        }).catch((e) => { console.log(e) })
    }

    return (
        <>
            <form onSubmit={handleSubmit} className="lg:w-1/3 sm:w-1/2  w-11/12 p-5 rounded-lg
        bg-white shadow-xl border-2">

                {/* Title Screen */}
                <h1 className="text-3xl font-bold select-none">
                    Sol·licita accés
                </h1>

                <br />

                <div className="w-full grid gap-5">

                    {/* Necesary Input Fields */}
                    <InputFieldComponent type="text" name="companyName" value={accessRequestData.companyName}
                        action={handleInputChange} placeholder={"Nom de l'empresa"} icon="id-card" />

                    <InputFieldComponent type="email" name="email" value={accessRequestData.email}
                        action={handleInputChange} placeholder={"Correu de contacte"} icon="building" />

                    <InputFieldComponent type="phone" name="contactPhone" value={accessRequestData.contactPhone}
                        action={handleInputChange} placeholder={"Telèfon de contacte"} icon="phone" />

                    <InputFieldComponent type="text" name="nif" value={accessRequestData.nif}
                        action={handleInputChange} placeholder={"NIF"} icon="building" />

                    <InputFieldComponent type={(passwordVisibility) ? "text" : "password"}
                        name="password" value={accessRequestData.password}
                        action={handleInputChange} placeholder={"Contrasenya"} icon="lock" />

                    {/* Password Visibility CheckBox*/}
                    <div className="flex place-items-center justify-left gap-2 px-2">
                        <input type="checkbox" onChange={handlePasswordVisibilityChange}
                            className="w-3 h-3 cursor-pointer" id="showPassword"/>
                        <label htmlFor="showPassword" className="text-sm select-none cursor-pointer">Mostrar contrasenya</label>
                    </div>
                </div>

                <br /><br />

                {/* Submit Button */}
                <div className="w-full flex place-items-center justify-center">
                    <SubmitButtonComponent buttonContent={"Envia"} />
                </div>

                <br />

                {/* Bottom Helper */}
                <div className="w-full flex place-items-center justify-center">
                    <FormHelperComponent text="Ja estàs registrat?"
                        linkText="Inicia sessió" link="/sign-in" />
                </div>

            </form>
            <ToastContainer/>
        </>
    )
}