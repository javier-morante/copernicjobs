import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import FieldComponent from "../../home/general_form_components/FieldComponent";
import MultilineFieldComponent from "../../home/general_form_components/MultilineFieldComponent";
import DropDownComponent from '../../home/general_form_components/DropDownComponent';
import { postIncidence } from "../../../axios/incidences/IncidencesRequest";

function IncidenceForm({ manageUpdate, managePopup }) {
    const [formData, setFormData] = useState({
        title: "",
        description: "",
        category: "ERROR"
    });

    const clearDataAndClose = ()=>{
        setFormData({
            title: "",
            description: "",
            category: "ERROR"
        })
        managePopup();
    }

    useEffect(() => {
        console.log(formData)
    }, [formData])

    const handleInput = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (formData.category !== "NOT_OPTION") {
            postIncidence(formData)
                .then((status) => {
                    clearDataAndClose();
                    manageUpdate();
                });
        }
    };

    return (
        <form onSubmit={handleSubmit} className="p-4">


            <div className="flex max-md:flex-col">

                <div className="md:w-3/5">
                    <FieldComponent title="Títol" name="title"
                        action={handleInput} value={formData.title}
                        placeholder="incidence title"
                        necessary />
                </div>
                <div className="md:w-1/5 md:mx-auto">
                    <DropDownComponent title="Tipus"
                        classTitle="font-bold mb-2"
                        formData={formData} handleInput={handleInput}
                        name="category" value={formData.category}
                        options={[
                            {
                                value: "ERROR",
                                name: "Error",
                            },
                            {
                                value: "USER",
                                name: "Usuari",
                            },
                            {
                                value: "SERVICE",
                                name: "Servei",
                            },
                            {
                                value: "UI",
                                name: "Interfície",
                            },
                        ]} />
                </div>

            </div>

            <MultilineFieldComponent title="Descripció"
                name="description" maxSize={100}
                value={formData.description} action={handleInput} necessary />

            <div className="flex gap-5">
                <button onClick={handleSubmit} type="submit" className="w-4/12 p-1 
                    bg-valid_green text-white rounded-md transition
                    hover:scale-105">
                    Guardar
                </button>
                <Link to="/home/incidences" onClick={clearDataAndClose} className="w-4/12 p-1 
                    bg-warning_red text-white rounded-md transition
                    text-center hover:scale-105">
                    Cancel·lar
                </Link>
            </div>
        </form>
    )

}

export default IncidenceForm;
