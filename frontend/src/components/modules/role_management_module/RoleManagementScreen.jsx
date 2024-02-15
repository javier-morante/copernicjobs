import { useEffect, useState } from "react";
import DropdownContainer from "./DropdownContainer";
import StudentRoleManagementForms from "./forms/StudentRoleManagementForms";
import AdministratorRoleManagementForms from "./forms/AdministratorRoleManagementForms";
import CompanyRoleManagementForms from "./forms/CompanyRoleManagementForms";
import { getRolePrivileges } from "../../../axios/role_management/gerRolePrivileges";
import { setAdministratorRolePrivileges, setCompanyRolePrivileges, setStudentRolePrivileges } from "../../../axios/role_management/setRolePrivileges";

export default function RoleManagementScreen() {

    const [popupText, setPopupText] = useState("")
    const [popupStatus, setPopupStatus] = useState(false)

    const managePopup = () => {
        setPopupStatus(!popupStatus)
    }

    // Student state and Actions
    const [studentFormData, setStudentFormData] = useState({
        myOffers: false,
        createOffer: false,
        incidents: false,
        laboralInformation: false,
        offers: false,
        myInscriptions: false,
    })

    const handleStudentInput = (e) => {
        const {name, value} = e.target
        setStudentFormData({
            ...studentFormData,
            [name]: !studentFormData[name]
        })
    }

    const handleStudentSubmit = (e) => {
        e.preventDefault()
        setStudentRolePrivileges(studentFormData)
        .then((status) => {
            (status)
            ? setPopupText("Roles Updated Successfully") 
            : setPopupText("Error")

            if(!popupStatus) { managePopup() }
        })
    }

    // Company state and Actions
    const [companyFormData, setCompanyFormData] = useState({
        myOffers: false,
        createOffer: false,
        incidents: false,
        offers: false,
    })

    const handleCompanyInput = (e) => {
        const {name, value} = e.target
        setCompanyFormData({
            ...companyFormData,
            [name]: !companyFormData[name]
        })
    }

    const handleCompanySubmit = (e) => {
        e.preventDefault()
        setCompanyRolePrivileges(companyFormData)
        .then((status) => {
            (status)
            ? setPopupText("Roles Updated Successfully") 
            : setPopupText("Error")

            if(!popupStatus) { managePopup() }
        })
    }

    // Administrator state and Actions
    const [administratorFormData, setAdministratorFormData] = useState({
        myOffers: false,
        createOffer: false,
        incidents: false,
        offers: false,
        requests: false,
        users: false,
        reports: false,
    })

    const handleAdministratorInput = (e) => {
        const {name, value} = e.target
        setAdministratorFormData({
            ...administratorFormData,
            [name]: !administratorFormData[name]
        })
    }

    const handleAdministratorSubmit = (e) => {
        e.preventDefault()
        setAdministratorRolePrivileges(administratorFormData)
        .then((status) => {
            (status)
            ? setPopupText("Roles Updated Successfully") 
            : setPopupText("Error")

            if(!popupStatus) { managePopup() }
        })
    }


    useEffect(() => {
        getRolePrivileges("STUDENT", setStudentFormData)
        getRolePrivileges("COMPANY", setCompanyFormData)
        getRolePrivileges("ADMINISTRATOR", setAdministratorFormData)
    }, [])


    return (
        <div>
            <DropdownContainer containerTitle={"Administrador"}>

                <h1 className="text-2xl font-bold">
                    Mòduls de l'administrador
                </h1>

                <div className="p-5">
                    <AdministratorRoleManagementForms formData={administratorFormData} 
                        handleInput={handleAdministratorInput}
                        handleSubmit={handleAdministratorSubmit}/>
                </div>

            </DropdownContainer>


            <DropdownContainer containerTitle={"Empresa"}>

                <h1 className="text-2xl font-bold">
                    Mòduls de l'empresa
                </h1>

                <div className="p-5">
                    <CompanyRoleManagementForms formData={companyFormData} 
                        handleInput={handleCompanyInput}
                        handleSubmit={handleCompanySubmit}/>
                </div>

            </DropdownContainer>


            <DropdownContainer containerTitle={"Estudiant"}>

                <h1 className="text-2xl font-bold">
                    Mòduls de l'estudiant
                </h1>

                <div className="p-5">
                    <StudentRoleManagementForms 
                        formData={studentFormData} 
                        handleInput={handleStudentInput}
                        handleSubmit={handleStudentSubmit}/>
                </div>

            </DropdownContainer>
        </div>
    )
}