import IncidenceForm from "./IncidenceForm";
import { useState } from "react";
import IncidenceM from "./IncidenceM";
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import DropDownComponent from "../../home/general_form_components/DropDownComponent";

function IncidencesScreen() {
    const [popupStatus, setPopupStatus] = useState(false)
    const [updateComponent, setUpdateComponent] = useState(false);
    const [filterD, setFilterD] = useState({
        filter: "ALL"
    });

    const handleInput = (e) => {
        const { name, value } = e.target;
        setFilterD({
            ...filterD,
            [name]: value
        });
    };

    const filter = (elementsToFilter)=>{
        return elementsToFilter.filter((element)=> 
        element.category === filterD.filter || filterD.filter === "ALL")
    }

    const manageUpdate = () => {
        setUpdateComponent(!updateComponent)
    }

    const managePopup = () => {
        setPopupStatus(!popupStatus)
    }


    return (
        <>
            <div>
                <div className="flex w-full px-5 gap-4 items-end">
                    <DropDownComponent title="Tipus"
                        classBody="w-full"
                        classTitle="font-bold text-xl mb-2"
                        classSel="w-full rounded shadow-xl p-2"
                        formData={filterD} handleInput={handleInput}
                        name="filter" value={filterD.filter}
                        options={[
                            {
                                value: "ALL",
                                name: "Totes"
                            },
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
                    <button onClick={managePopup} className="text-xl bg-brand_orange text-white rounded h-9 max-sm:mr-4 sm:w-9 w-9 hover:scale-105 transition">
                        <FontAwesomeIcon icon="plus"/>
                    </button>
                </div>

                <div className={`fixed top-0 z-50 w-screen h-screen bg-neutral-950/[0.5] ${popupStatus ? "grid" : "hidden"} place-items-center`}>
                    <div className="sm:w-6/12 sm:h-11/12 bg-white rounded p-4">
                        <h1 className="font-bold text-3xl text-center">Crea una incidència</h1>
                        <IncidenceForm manageUpdate={manageUpdate} managePopup={managePopup} />
                    </div>
                </div>
                <div className="px-12 pt-12">
                    <IncidenceM updateComponent={updateComponent} filter={filterD} filterMethod={filter}/>
                </div>
            </div>
            <ToastContainer />
        </>
    )

}

export default IncidencesScreen;