import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

import passwordRecoveryTwoRequest from "../../../axios/password_recovery/PasswordRecoveryTwoRequest";

import InputFieldComponent from "../general_authentication_components/InputFieldComponent";
import SubmitButtonComponent from "../general_authentication_components/SubmitButtonComponent";
import FormHelperComponent from "../general_authentication_components/FormHelperComponent";



export default function PasswordRecoveryPhaseTwoForm() {
    const navigate = useNavigate()

    // State that stores the status of the password visibility
    const [passwordVisibility, setPasswordVisibility] = useState(false)

    // Handle changes in the password visibility toggle
    const handlePasswordVisibilityChange = () => {
        setPasswordVisibility(!passwordVisibility)
    }

    // State that stores the data inside of the form fields
    const [passwordRecoveryTwoFormData, setpasswordRecoveryTwoFormData] = useState({
        email: "",
        code: "",
        password: "",
        repeatPassword: ""
    })

    // Handle Changes in the input fields of the form
    const handleInputChange = (e) => {
        const { name, value } = e.target

        setpasswordRecoveryTwoFormData({
            ...passwordRecoveryTwoFormData,
            [name]: value,
        })
    }

    // Handle the Form Submit action and send the request to the backend
    const handleSubmit = async (e) => {
        e.preventDefault()

        passwordRecoveryTwoRequest(passwordRecoveryTwoFormData)
            .then((status) => {
                (status) ? navigate("/sign-in") : alert("Invalid Credentials")
            })
    }

    return (
        <form onSubmit={handleSubmit} className="lg:w-1/3 sm:w-1/2  w-11/12 p-5 rounded-lg
        bg-white shadow-xl border-2">

            {/* Title Screen */}
            <h1 className="text-3xl font-bold select-none">
                Recuperar contrasenya
            </h1>

            <br />

            <div className="w-full grid gap-5">

                {/* Necesary Input Fields */}
                <InputFieldComponent type="email" name="email" value={passwordRecoveryTwoFormData.email}
                    action={handleInputChange} placeholder={"Correu"} icon="envelope" />

                <InputFieldComponent type="text" name="code" value={passwordRecoveryTwoFormData.code}
                    action={handleInputChange} placeholder={"Clau de recuperació"} icon="key" />

                <InputFieldComponent type={(passwordVisibility) ? "text" : "password"} name="password"
                    value={passwordRecoveryTwoFormData.password} action={handleInputChange}
                    placeholder={"Contrasenya"} icon="lock" />

                <InputFieldComponent type={(passwordVisibility) ? "text" : "password"} name="repeatPassword"
                    value={passwordRecoveryTwoFormData.repeatPassword} action={handleInputChange}
                    placeholder={"Repeteix la contrasenya"} icon="lock" />

                {/* Password Visibility CheckBox*/}
                <div className="flex place-items-center justify-left gap-2 px-2">
                    <input type="checkbox" onChange={handlePasswordVisibilityChange}
                        className="w-3 h-3 cursor-pointer" id="showPassword" />
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
            <div className="w-full grid place-items-center justify-center">

                <FormHelperComponent text="Tornar enrere?"
                    linkText="Iniciar sessió" link="/sign-in" />

            </div>

        </form>
    )
}