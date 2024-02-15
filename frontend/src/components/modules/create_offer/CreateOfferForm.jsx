import { useState, useEffect } from "react"
import CheckboxComponent from "../../home/general_form_components/CheckboxComponent"
import DropDownComponent from "../../home/general_form_components/DropDownComponent"
import FieldComponent from "../../home/general_form_components/FieldComponent"
import MultilineFieldComponent from "../../home/general_form_components/MultilineFieldComponent"
import SaveCancelComponent from "../../home/general_form_components/SaveCancelComponent"
import createOfferRequest from "../../../axios/offers/createOfferRequest"

import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom"

export default function CreateOfferForm() {
    const navigate = useNavigate()


    const [formData, setFormData] = useState({
        title: "",
        location: "",
        shortDescription: "",
        requirementsDescription: "",
        functionsDescription: "",
        technologiesDescription: "",
        urgency: "HIGH",
        workday: "FULL",
        degree: "OTHER",
        salaryInterval: "",
        vacancies: 0,
    })

    const handleInput = (e) => {
        const {name, value} = e.target
        setFormData({
            ...formData,
            [name]: value
        })
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        createOfferRequest(formData)
        .then((status) => {
            if(status) {navigate("/home/offers")}
        })
    }

    return (
        <form onSubmit={handleSubmit} className="p-5">

            {/* Work Place and Urgency */}
            <div className="flex gap-5">
                <div className="w-full">
                    <FieldComponent type="text" title="Títol de l'oferta" name="title" 
                        action={handleInput} value={formData.title} 
                        necessary/>
                </div>

                {/* Urgency DropDown Component*/}
                <div className="w-full">
                    <DropDownComponent title="Urgència" 
                        formData={formData} handleInput={handleInput}
                        name="urgency" value={handleInput.urgency}
                        options={[
                            {
                                value: "HIGH",
                                name: "Alta",
                            },
                            {
                                value: "MEDIUM",
                                name: "Mitjana",
                            },
                            {
                                value: "LOW",
                                name: "Baixa",
                            },
                        ]}/>
                </div>

            </div>

            <br/>

            {/* Site and working day */}
            <div className="flex gap-5">

                <div className="w-1/2">
                    <FieldComponent type="text" title="Seu" name="location" 
                        action={handleInput} value={formData.location}
                        necessary/>
                </div>

                {/* Urgency DropDown Component*/}
                <div className="w-1/2">
                    <DropDownComponent title="Tipus de jornada" 
                        formData={formData} handleInput={handleInput}
                        name="workday" value={formData.workday}
                        options={[
                            {
                                value: "FULL",
                                name: "Completa",
                            },
                            {
                                value: "HALF",
                                name: "Mitja",
                            },
                            {
                                value: "FLEXIBLE",
                                name: "Flexible",
                            },
                            {
                                value: "MORNING",
                                name: "Matí",
                            },
                            {
                                value: "AFTERNOON",
                                name: "Tarda",
                            },
                            {
                                value: "NIGHT",
                                name: "Nit",
                            },
                        ]}/>
                </div>

            </div>

            <br/>

            {/* Brief description */}
            <div>
                <MultilineFieldComponent title="Descripció breu"
                    name="shortDescription" maxSize={200} 
                    value={formData.shortDescription} action={handleInput}
                    necessary/>
            </div>

            <br/>

            {/* Requirements */}
            <div>
                <MultilineFieldComponent title="Requisits"
                    name="requirementsDescription" maxSize={800} 
                    value={formData.requirementsDescription} action={handleInput}
                    necessary/>
            </div>

            <br/>

            {/* Functions */}
            <div>
                <MultilineFieldComponent title="Funcions"
                    name="functionsDescription" maxSize={800} 
                    value={formData.functionsDescription} action={handleInput}
                    necessary/>
            </div>

            <br/>

            {/* Technologies */}
            <div>
                <MultilineFieldComponent title="Tecnologies"
                    name="technologiesDescription" maxSize={800} 
                    value={formData.technologiesDescription} action={handleInput}
                    necessary/>
            </div>

            <br/>

            
            <div>
                <DropDownComponent title="Estudis" 
                        formData={formData} handleInput={handleInput}
                        name="degree" value={formData.degree}
                        options={[
                            {
                                value: "DAM",
                                name: "Desenvolupament d'aplicacions multiplataforma (DAM)",
                            },
                            {
                                value: "DAW",
                                name: "Desenvolupament d'aplicacions web (DAW)",
                            },
                            {
                                value: "ASIX",
                                name: "Administració de sistemes informàtics en xarxa (ASIX)",
                            },
                            {
                                value: "SMIX",
                                name: "Sistemes microinformàtics i xarxes (SMIX)",
                            },
                            {
                                value: "OTHER",
                                name: "Altres",
                            },
                        ]}/>
               
            </div>

            <br/>

            <div className="flex gap-5">
                <div className="w-full">
                    <FieldComponent type="text" title="Interval de sou" name="salaryInterval" 
                        action={handleInput} value={formData.salaryInterval}
                        necessary/>
                </div>

                <div className="w-full">
                    <FieldComponent type="number" title="Número de vacants" name="vacancies" 
                        action={handleInput} value={formData.vacancies}
                        necessary/>
                </div>
            </div>

            <div className="mt-10">
                <SaveCancelComponent/>
            </div>
            
            <ToastContainer/>
        </form>
    )
}