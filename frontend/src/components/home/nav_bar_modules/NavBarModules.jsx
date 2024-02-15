import { useEffect, useState } from "react";
import NavBarModuleComponent from "./NavBarModuleComponent";
import NavBar from "../general_components/NavBar";
import { icon } from "@fortawesome/fontawesome-svg-core";
import moduleIconParser from "../../../utils/moduleIconParser";

import "../../../index.css"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

export default function NavBarModules(
    {modules}
) {

    const [menuState, setMenuState] = useState(false)
    
    const handleMenuState = () => {
        setMenuState(!menuState)
    }

    return (

        <div className="no-print">
            <button onClick={handleMenuState} 
                className="w-full flex p-2 px-5 place-items-center gap-2 md:hidden text-xl shadow-xl bg-gray-100">
                <FontAwesomeIcon icon={(!menuState)? "bars": "times"}
                    className="w-5"/>
                <h1>
                    Menu
                </h1>
            </button>


            <div className="md:p-0 p-2">
                <div className={`w-full
                    md:flex md:gap-5 p-2 px-5 md:bg-gray-100
                    flex-cols md:visible rounded-md  bg-white shadow-xl grid gap-5
                    ${(!menuState)? "hidden": "visible"}`}>
                    
                    <NavBarModuleComponent
                        icon={"gear"}
                        title={"Configuració"}
                        link={"/user-profile"}/>

                    {
                        modules.map((element, index) => {
                            const moduleData = moduleIconParser(element.name)
                            return (
                                <NavBarModuleComponent key={index}
                                    icon={moduleData.icon} 
                                    title={moduleData.name}
                                    link={`/home${moduleData.link}`}
                                    closeMenuAction={handleMenuState}/>
                            )
                        })
                    }
                </div>
            </div>

        </div>
    )
}