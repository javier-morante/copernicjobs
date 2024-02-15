import { useState, useEffect } from "react";

import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import { createStudentRequest, getStudentsRequest, updateStudentRequest } from '../../../../axios/user_management/studentManagementRequest'
import { deleteUserRequest, lockUserRequest, updateUserPasswordRequest } from '../../../../axios/user_management/userManagementRequest'

import StudentTag from "./StudentTag";
import StudentManagementUpdateForm from "./StudentManagementUpdateForm";
import StudentManagementCreateForm from "./StudentManagementCreateForm";
import StudentManagementPasswordUpdateForm from "./StudentManagementPasswordUpdateForm";

import "../../../../index.css"

export default function StudentManagementScreen() {

    // Students Data ------------------------------------------------------------------------------------
    const [studentsData, setStudentsData] = useState([])

    const handleUserUpdate = () => { getStudentsRequest(setStudentsData) }
    // preguntar adrian
    useEffect(() => { handleUserUpdate(setStudentsData) }, [])

    useEffect(() => { console.log(studentsData) }, [studentsData])

    // Student Forms Data -----------------------------------------------------------------------
    const [formData, setFormData] = useState({
        userId: "",
        name: "",
        surname: "",
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
        createStudentRequest(formData)
        .then((status) => {
            if(status) {
                clearFormData()
                handleCreateStudentPopup()
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
        updateStudentRequest(formData, handleUserUpdate)
        .then((status) => {
            if(status) {
                clearFormData()
                handleUpdateStudentPopup()
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
                handleUpdateStudentPasswordPopup()
                handleUserUpdate()
            }
        })
    }

    // Create Student Popup -----------------------------------------------------------------------------
    const [createStudentPopupStatus, setCreateStudentPopupStatus] = useState(false)

    const handleCreateStudentPopup = () => {
        setCreateStudentPopupStatus(!createStudentPopupStatus)
        clearFormData()
    }


    // Update Student Popup -----------------------------------------------------------------------------
    const [updateStudentPopupStatus, setUpdateStudentPopupStatus] = useState(false)

    const handleUpdateStudentPopup = (userData) => {
        setUpdateStudentPopupStatus(!updateStudentPopupStatus)
        if(userData) {
            console.log("User Data => ", userData)
            setFormData({
                userId: userData.userId,
                name: userData.name,
                surname: userData.surname,
                nif: userData.dni,
                phone: userData.phone,
                email: userData.email,
                password: "",
            })
        }
    }

    // Update Student Password Popup -------------------------------------------------------------------
    const [updateStudentPasswordPopupStatus, setUpdateStudentPasswordPopup] = useState(false)
    
    const handleUpdateStudentPasswordPopup = (userData) => {
        setUpdateStudentPasswordPopup(!updateStudentPasswordPopupStatus)
        if(userData) {
            setFormData({
                userId: userData.userId,
                name: "",
                surname: "",
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
                <input type="text" placeholder="Buscar estudiants"
                    className="w-full placeholder:text-light_gray_3 focus:outline-none" onChange={handleSearchBarFilter}/>
            </div>

            <button onClick={handleCreateStudentPopup} className="rounded-md w-9 h-9 bg-brand_orange
                hover:scale-105 transition text-white text-xl">
                    <FontAwesomeIcon icon={"add"}/>
            </button>
        </div>


        {/* Users List, including the search box filter */}
        <div className="w-full h-full grid gap-5 px-5">
            <div className="overflow-y-auto scroll-hide max-h-[38rem] grid gap-5 p-1">
                {
                    studentsData.map((element, index) => {
                        const fullName = `${element.name} ${element.surname}`

                        return (fullName.includes(searchBarFilter))
                            ?(
                                <StudentTag key={index} studentData={element} 
                                    handleUserDelete={() => handleUserDelete(element.userId)}
                                    handleUserUpdate={() => handleUpdateStudentPopup(element)}
                                    handleUserLock={() => handleUserLock(element.userId)}
                                    handleUserPasswordUpdate={() => {
                                        handleUpdateStudentPasswordPopup(element)
                                    }}/>
                            )
                            : null
                    })
                }
            </div>
        </div>
        
        {
            /* Create User Popup Management Component */
            (createStudentPopupStatus)
                ? <StudentManagementCreateForm 
                    managePopup={handleCreateStudentPopup}
                    handleSubmit={handleCreateSubmit} 
                    handleInput={handleCreateInput} 
                    formData={formData}/>
                : null
        }

        {   
            /* Update User Popup Management Component */
            (updateStudentPopupStatus)
                ? <StudentManagementUpdateForm 
                    managePopup={handleUpdateStudentPopup}
                    handleSubmit={handleUpdateSubmit} 
                    handleInput={handleUpdateInput} 
                    formData={formData}/>
                : null
        }

        {   
            /* Update User Popup Management Component */
            (updateStudentPasswordPopupStatus)
                ? <StudentManagementPasswordUpdateForm
                    managePopup={handleUpdateStudentPasswordPopup}
                    handleSubmit={handleUpdatePasswordSubmit} 
                    handleInput={handleUpdatePasswordInput} 
                    formData={formData}/>
                : null
        }

        <ToastContainer/>
    </div>
}