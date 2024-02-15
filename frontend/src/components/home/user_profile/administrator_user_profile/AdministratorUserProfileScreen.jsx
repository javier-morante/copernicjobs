import { useEffect, useState } from "react";

import AdministratorUserProfileForm from "./AdministratorUserProfileForm";
import AdministratorUserSettingsForm from "./AdministratorUserSettingsForm";

import administratorProfileSetData from "../../../../axios/user_profiles/AdministratorProfileRequest";
import homeRequest from "../../../../axios/user_profiles/HomeRequest";

import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";


export default function AdministratorUserProfileScreen() {

    // State that stores all the user data
    const [formData, setFormData] = useState({
        name: "",
        phone: "",
        email: "",
        newAccessRequestNotification: false,
        newReportNotification: false,
    })

    // Handle Text Field Inputs
    const handleInput = (e) => {
        const {name, value} = e.target
        setFormData({
            ...formData,
            [name]: value
        })
    }

    // Handle CheckBox Fields Inputs
    const handleCheckboxInput = (e) => {
        const {name, value} = e.target
        setFormData({
            ...formData,
            [name]: !formData[name]
        })
    }

    // Handle Form Submit
    const handleSubmit = (e) => {
        e.preventDefault()

        administratorProfileSetData(formData)
    }

    // Reload the user data at the beggining of the component life cycle
    useEffect(() => {
        homeRequest(setFormData)
    }, [])

    return (
        <div className="w-full flex place-items-center justify-center
            overflow-hidden ">

            <div className="sm:w-6/12 w-10/12">
                {/* Student User Profile Form */}
                <AdministratorUserProfileForm 
                    formData={formData} handleInput={handleInput}
                    handleSubmit={handleSubmit}/>

                <br/>
                <br/>

                <AdministratorUserSettingsForm 
                    formData={formData} handleInput={handleCheckboxInput}
                    handleSubmit={handleSubmit}/>

                <br/>
                <br/>
            </div>

            <ToastContainer/>

        </div>
    )
}