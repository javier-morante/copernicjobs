import { useState, useEffect } from "react";

import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import { createCompanyRequest, getCompanysRequest, updateCompanyRequest } from '../../../../axios/user_management/companyManagementRequest'
import { deleteUserRequest, lockUserRequest, updateUserPasswordRequest } from '../../../../axios/user_management/userManagementRequest'

import CompanyTag from "./CompanyTag";
import CompanyManagementUpdateForm from "./CompanyManagementUpdateForm";
import CompanyManagementCreateForm from "./CompanyManagementCreateForm";
import CompanyManagementPasswordUpdateForm from "./CompanyManagementPasswordUpdateForm";

import "../../../../index.css"

export default function CompanyManagementScreen() {

    // Companys Data ------------------------------------------------------------------------------------
    const [companysData, setCompanysData] = useState([])

    const handleUserUpdate = () => { getCompanysRequest(setCompanysData) }

    useEffect(() => { handleUserUpdate(setCompanysData) }, [])

    useEffect(() => { console.log(companysData) }, [companysData])

    // Company Forms Data -----------------------------------------------------------------------
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
            surname: "",
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
        createCompanyRequest(formData)
        .then((status) => {
            if(status) {
                clearFormData()
                handleCreateCompanyPopup()
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
        updateCompanyRequest(formData, handleUserUpdate)
        .then((status) => {
            if(status) {
                clearFormData()
                handleUpdateCompanyPopup()
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
                handleUpdateCompanyPasswordPopup()
                handleUserUpdate()
            }
        })
    }

    // Create Company Popup -----------------------------------------------------------------------------
    const [createCompanyPopupStatus, setCreateCompanyPopupStatus] = useState(false)

    const handleCreateCompanyPopup = () => {
        setCreateCompanyPopupStatus(!createCompanyPopupStatus)
        clearFormData()
    }


    // Update Company Popup -----------------------------------------------------------------------------
    const [updateCompanyPopupStatus, setUpdateCompanyPopupStatus] = useState(false)

    const handleUpdateCompanyPopup = (userData) => {
        setUpdateCompanyPopupStatus(!updateCompanyPopupStatus)
        if(userData) {
            setFormData({
                userId: userData.userId,
                name: userData.name,
                nif: userData.nif,
                phone: userData.phone,
                email: userData.email,
                password: "",
            })
        }
    }

    // Update Company Password Popup -------------------------------------------------------------------
    const [updateCompanyPasswordPopupStatus, setUpdateCompanyPasswordPopup] = useState(false)
    
    const handleUpdateCompanyPasswordPopup = (userData) => {
        setUpdateCompanyPasswordPopup(!updateCompanyPasswordPopupStatus)
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
                <input type="text" placeholder="Buscar empreses"
                    className="w-full placeholder:text-light_gray_3 focus:outline-none" onChange={handleSearchBarFilter}/>
            </div>

            <button onClick={handleCreateCompanyPopup} className="rounded-md w-9 h-9 bg-brand_orange
                hover:scale-105 transition text-white text-xl">
                    <FontAwesomeIcon icon={"add"}/>
            </button>
        </div>


        {/* Users List, including the search box filter */}
        <div className="w-full h-full grid gap-5 px-5">
            <div className="overflow-y-auto scroll-hide max-h-[38rem] grid gap-5 p-1">
                {
                    companysData.map((element, index) => {
                        const fullName = `${element.name} ${element.surname}`

                        return (fullName.includes(searchBarFilter))
                            ?(
                                <CompanyTag key={index} companyData={element} 
                                    handleUserDelete={() => handleUserDelete(element.userId)}
                                    handleUserUpdate={() => handleUpdateCompanyPopup(element)}
                                    handleUserLock={() => handleUserLock(element.userId)}
                                    handleUserPasswordUpdate={() => {
                                        handleUpdateCompanyPasswordPopup(element)
                                    }}/>
                            )
                            : null
                    })
                }
            </div>
        </div>
        
        {
            /* Create User Popup Management Component */
            (createCompanyPopupStatus)
                ? <CompanyManagementCreateForm 
                    managePopup={handleCreateCompanyPopup}
                    handleSubmit={handleCreateSubmit} 
                    handleInput={handleCreateInput} 
                    formData={formData}/>
                : null
        }

        {   
            /* Update User Popup Management Component */
            (updateCompanyPopupStatus)
                ? <CompanyManagementUpdateForm 
                    managePopup={handleUpdateCompanyPopup}
                    handleSubmit={handleUpdateSubmit} 
                    handleInput={handleUpdateInput} 
                    formData={formData}/>
                : null
        }

        {   
            /* Update User Popup Management Component */
            (updateCompanyPasswordPopupStatus)
                ? <CompanyManagementPasswordUpdateForm
                    managePopup={handleUpdateCompanyPasswordPopup}
                    handleSubmit={handleUpdatePasswordSubmit} 
                    handleInput={handleUpdatePasswordInput} 
                    formData={formData}/>
                : null
        }

        <ToastContainer/>
    </div>
}