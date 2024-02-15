import { useState, useEffect } from "react";

import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import { createAdministratorRequest, getAdministratorsRequest, updateAdministratorRequest } from '../../../../axios/user_management/administratorManagementRequest'
import { deleteUserRequest, lockUserRequest, updateUserPasswordRequest } from '../../../../axios/user_management/userManagementRequest'


import AdministratorTag from "./AdministratorTag";
import AdministratorManagementUpdateForm from "./AdministratorManagementUpdateForm";
import AdministratorManagementCreateForm from "./AdministratorManagementCreateForm";
import AdministratorManagementPasswordUpdateForm from "./AdministratorManagementPasswordUpdateForm";


import "../../../../index.css"

export default function AdministratorManagementScreen() {

    // Administrators Data ------------------------------------------------------------------------------------
    const [administratorsData, setAdministratorsData] = useState([])

    const handleUserUpdate = () => { getAdministratorsRequest(setAdministratorsData) }

    useEffect(() => {
        handleUserUpdate()
    }, [])
    
    // Student Creation Form Data -----------------------------------------------------------------------
    // State and methods that manage the data inside the student creation form
    const [formData, setFormData] = useState({
        userId: "",
        name: "",
        nif: "",
        phone: "",
        email: "",
        password: "",
    })

    const clearFormData = () => {
        setFormData({
            userId: "",
            name: "",
            nif: "",
            phone: "",
            email: "",
            password: "",
        })
    }

    // Handle Create ----------------------------------------
    const handleCreateInput = (e) => {
        const {name, value} = e.target
        setFormData({
            ...formData,
            [name]: value
        })
    }

    const handleCreateSubmit = (e) => {
        e.preventDefault()
        createAdministratorRequest(formData)
        .then((status) => {
            if(status) {
                clearFormData()
                handleCreateAdministratorPopup()
                handleUserUpdate()
            }
        })
    }

    // Handle Update ----------------------------------------
    const handleUpdateInput = (e) => {
        const {name, value} = e.target
        setFormData({
            ...formData,
            [name]: value
        })
    }

    const handleUpdateSubmit = (e) => {
        e.preventDefault()
        updateAdministratorRequest(formData, handleUserUpdate)
        .then((status) => {
            if(status) {
                clearFormData()
                handleUpdateAdministratorPopup()
                handleUserUpdate()
            }
        })
        
    }

    // Handle Update Password ----------------------------------------
    const handleUpdatePasswordInput = (e) => {
        const {name, value} = e.target
        setFormData({
            ...formData,
            [name]: value
        })
    }

    const handleUpdatePasswordSubmit = (e) => {
        e.preventDefault()
        console.log(formData)
        updateUserPasswordRequest(formData)
        .then((status) => {
            if(status) {
                clearFormData()
                handleUpdateAdministratorPasswordPopup()
                handleUserUpdate()
            }
        })
    }

    // Create Administrator Popup -----------------------------------------------------------------------------
    const [createAdministratorPopupStatus, setCreateAdministratorPopupStatus] = useState(false)

    const handleCreateAdministratorPopup = () => {
        setCreateAdministratorPopupStatus(!createAdministratorPopupStatus)
        clearFormData()
    }


    // Update Administrator Popup -----------------------------------------------------------------------------
    const [updateAdministratorPopupStatus, setUpdateAdministratorPopupStatus] = useState(false)

    const handleUpdateAdministratorPopup = (userData) => {
        setUpdateAdministratorPopupStatus(!updateAdministratorPopupStatus)
        if(userData) {
            setFormData({
                userId: userData.userId,
                name: userData.name,
                nif: userData.dni,
                phone: userData.phone,
                email: userData.email,
                password: "",
            })
        }
    }

    // Update Administrator Password Popup -------------------------------------------------------------------
    const [updateAdministratorPasswordPopupStatus, setUpdateAdministratorPasswordPopup] = useState(false)
    
    const handleUpdateAdministratorPasswordPopup = (userData) => {
        setUpdateAdministratorPasswordPopup(!updateAdministratorPasswordPopupStatus)
        if(userData) {
            setFormData({
                userId: userData.userId,
                name: "",
                nif: "",
                phone: "",
                email: userData.email,
                password: "",
            })
        }
    }

    // User Lock ---------------------------------------------------------------------------------------
    const handleUserLock = (userId) => {
        const result = window.confirm("Are you shure you want to lock the user")
        if(result) {
            lockUserRequest(userId, handleUserUpdate)
        }
    }


    // User Delete --------------------------------------------------------------------------------------
    const handleUserDelete = (id) => {
        const result = window.confirm("Are you shure you want to delete the user?")
        if(result) {
            deleteUserRequest(id, handleUserUpdate)
        }
    }

    // Search Bar Filter --------------------------------------------------------------------------------
    const [searchBarFilter, setSearchBarFilter] = useState("")

    const handleSearchBarFilter = (e) => {
        const {name, value} = e.target
        setSearchBarFilter(() => value)
    }


    return <div className="w-full h-full grid gap-5 p-5 place-items-center">
        
        {/* Search Bar and User Creation Button*/}
        <div className="w-full flex gap-5 bg-white p-4 rounded-md shadow-xl">
            <div className="w-full font-bold text-xl flex gap-2
                place-items-center justify-left">

                <FontAwesomeIcon icon="search"/>
                <input type="text" placeholder="Buscar administradors"
                    className="w-full placeholder:text-light_gray_3 focus:outline-none" onChange={handleSearchBarFilter}/>
            </div>

            <button onClick={handleCreateAdministratorPopup} className="rounded-md w-9 h-9 bg-brand_orange
                hover:scale-105 transition text-white text-xl">
                    <FontAwesomeIcon icon={"add"}/>
            </button>
        </div>


        {/* Users List, including the search box filter */}
        <div className="w-full h-full grid gap-5 px-5">
            <div className="overflow-y-auto scroll-hide max-h-[38rem] grid gap-5 p-1">
                {
                    administratorsData.map((element, index) => {
                        const fullName = `${element.name} ${element.surname}`

                        return (fullName.includes(searchBarFilter))
                            ?(
                                <AdministratorTag key={index} administratorData={element} 
                                    handleUserDelete={() => handleUserDelete(element.userId)}
                                    handleUserUpdate={() => handleUpdateAdministratorPopup(element)}
                                    handleUserLock={() => handleUserLock(element.userId)}
                                    handleUserPasswordUpdate={() => {
                                        handleUpdateAdministratorPasswordPopup(element)
                                    }}/>
                            )
                            : null
                    })
                }
            </div>
        </div>
        
        {
            /* Create User Popup Management Component */
            (createAdministratorPopupStatus)
                ? <AdministratorManagementCreateForm 
                    managePopup={handleCreateAdministratorPopup}
                    handleSubmit={handleCreateSubmit} 
                    handleInput={handleCreateInput} 
                    formData={formData}/>
                : null
        }

        {   
            /* Update User Popup Management Component */
            (updateAdministratorPopupStatus)
                ? <AdministratorManagementUpdateForm 
                    managePopup={handleUpdateAdministratorPopup}
                    handleSubmit={handleUpdateSubmit} 
                    handleInput={handleUpdateInput} 
                    formData={formData}/>
                : null
        }

        {   
            /* Update User Popup Management Component */
            (updateAdministratorPasswordPopupStatus)
                ? <AdministratorManagementPasswordUpdateForm
                    managePopup={handleUpdateAdministratorPasswordPopup}
                    handleSubmit={handleUpdatePasswordSubmit} 
                    handleInput={handleUpdatePasswordInput} 
                    formData={formData}/>
                : null
        }

        <ToastContainer/>
    </div>
}