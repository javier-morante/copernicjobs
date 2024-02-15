import { useState, useEffect } from "react"
import CheckboxComponent from "../../home/general_form_components/CheckboxComponent"
import DropDownComponent from "../../home/general_form_components/DropDownComponent"
import FieldComponent from "../../home/general_form_components/FieldComponent"
import MultilineFieldComponent from "../../home/general_form_components/MultilineFieldComponent"
import SaveCancelComponent from "../../home/general_form_components/SaveCancelComponent"
import updateOfferRequest from "../../../axios/offers/updateOfferRequest"
import { useNavigate } from "react-router-dom"

import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";


export default function EditOfferForm(
    {offerData}
) {
    const navigate = useNavigate()

    const [formData, setFormData] = useState({
        id: offerData.id,
        title: offerData.title,
        location: offerData.location,
        shortDescription: offerData.shortDescription,
        requirementsDescription: offerData.requirementsDescription,
        functionsDescription: offerData.functionsDescription,
        technologiesDescription: offerData.technologiesDescription,
        urgency: offerData.urgency,
        workday: offerData.workday,
        degree: offerData.degree.name,
        salaryInterval: offerData.salaryInterval,
        vacancies: offerData.vacancies,
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
        updateOfferRequest(formData)
        .then((status) => {
            if(status) {navigate("/home/offers")}
        })
    }

    useEffect(() => {
        console.log(offerData)
        console.log(offerData.vacancies)
    }, [offerData])

    return (
        <form onSubmit={handleSubmit} className="p-5">

            {/* Work Place and Urgency */}
            <div className="flex gap-5">

                <div className="w-1/2">
                    <FieldComponent type="text" title="Títol de l'oferta" name="title" 
                        action={handleInput} value={formData.title} 
                        necessary/>
                </div>

                {/* Urgency DropDown Component*/}
                <div className="w-1/2">
                    <DropDownComponent title="Urgència" 
                        formData={formData} handleInput={handleInput}
                        name="urgency" value={formData.urgency}
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
                                name: "Desenvolupament de Aplicacions Multiplataforma (DAM)",
                            },
                            {
                                value: "DAW",
                                name: "Desenvolupament de Aplicacions Web (DAW)",
                            },
                            {
                                value: "ASIX",
                                name: "Administracio de Sistemes Informatics en Xarxa (ASIX)",
                            },
                            {
                                value: "SMIX",
                                name: "Sistemes Microinformatics I Xarxes (SMIX)",
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
                <SaveCancelComponent path={"/home/offers"}/>
            </div>

            <ToastContainer/>
        </form>
    )
}