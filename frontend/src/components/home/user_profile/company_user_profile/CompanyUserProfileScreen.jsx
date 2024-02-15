import { useEffect, useRef, useState } from "react";

import CompanyUserProfileForm from "./CompanyUserProfileForm";
import CompanyUserSettingsForm from "./CompanyUserSettingsForm";

import companyProfileSetData from "../../../../axios/user_profiles/CompanyProfileRequest";
import homeRequest from "../../../../axios/user_profiles/HomeRequest";

import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function CompanyUserProfileScreen(
    {setPopupText, managePopup, popupStatus}
) {

    // State that stores all the user data
    const [formData, setFormData] = useState({
        name: "",
        nif: "",
        phone: "",
        email: "",
        description: "",
        iconPath: "",
        image:"",
        webPageUrl: "",
        resolvedReportNotification: false
    })

    const [initialImageUrl, setInitialImageUrl] = useState('');

    const ref = useRef(null);

    useEffect(()=>console.log("data",formData),[formData])

    const handleFileChange = (event,ref) => {

        const eventToImage = event?event.target.files.length:false
        const refToImage = ref?ref.target.files.length:false
        const image = eventToImage || refToImage?URL.createObjectURL(event?event.target.files[0]:ref.current.files[0]):initialImageUrl
        setFormData({
            ...formData,
            image: event?event.target.files[0]:ref.current.files[0],
            iconPath: image !== "No Icon Path"?image:'',

        })
        
    };

    
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

        companyProfileSetData(formData)
        .then(()=>setTimeout(() => {
            homeRequest(setFormData, setInitialImageUrl)
        }, 300))
        
    }

    // Reload the user data at the beggining of the component life cycle
    useEffect(() => {
        homeRequest(setFormData, setInitialImageUrl)
    }, [])

    return (
        <div className="w-full flex place-items-center justify-center
            overflow-hidden ">

            <div className="sm:w-6/12 w-10/12">
                {/* Student User Profile Form */}
                <CompanyUserProfileForm 
                    formData={formData} handleInput={handleInput} 
                    handleFileChange={handleFileChange}
                    handleSubmit={handleSubmit}
                    imageRef={ref}/>

                <br/>
                <br/>

                <CompanyUserSettingsForm 
                    formData={formData} handleInput={handleCheckboxInput}
                    handleSubmit={handleSubmit}/>

                <br/>
                <br/>
            </div>

            <ToastContainer/>

        </div>
    )
}