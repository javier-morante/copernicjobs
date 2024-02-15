import { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import EnrollmentLineChartComponent from "./charts/EnrollmentLineChartComponent";
import OfferPublishmentChartComponent from "./charts/OfferPublishmentChartComponent";
import UsersBarChartComponent from "./charts/UsersBarChartComponent";
import "../../../index.css"
import UserTrafficLineChartComponent from "./charts/UserTrafficLineChartComponent";

export default function ReportsModuleScreen() {


    return <div className="grid gap-5 p-5">
        <div className="grid grid-cols-1 gap-5">
            <div className="w-full flex place-items-center justify-center p-5 
                bg-white rounded-md shadow-lg no-print">
                <h1 className="w-full text-xl font-bold">
                    Usuaris de l'aplicació
                </h1>
            </div>

            <div className="grid xl:grid-cols-2 grid-cols-1 gap-5">
                <div className="p-5 bg-white rounded-md shadow-xl">
                    <UsersBarChartComponent/>
                </div>

                <div className="p-5 bg-white rounded-md shadow-xl">
                    <UserTrafficLineChartComponent/>
                </div>
            </div>

            <div className="w-full flex place-items-center justify-center p-5 
                bg-white rounded-md shadow-lg no-print">
                <h1 className="w-full text-xl font-bold">
                    Trànsit d'ofertes
                </h1>
            </div>

            <div className="grid xl:grid-cols-2 grid-cols-1 gap-5">
                <div className="p-5 bg-white rounded-md shadow-xl">
                    <OfferPublishmentChartComponent/>
                </div>

                <div className="p-5 bg-white rounded-md shadow-xl">
                    <EnrollmentLineChartComponent/>
                </div>
            </div>
        </div>

        <div className="fixed top-44 right-8 flex gap-5">
            <button onClick={() => {window.location.reload()}} 
                className="w-10 h-10 bg-brand_orange text-white rounded-md 
                hover:scale-105 transition">
                <FontAwesomeIcon icon={"rotate-right"}/>
            </button>
            <button onClick={() => {print()}} 
                className="w-10 h-10 bg-brand_orange rounded-md 
                hover:scale-105 text-white transition">
                <FontAwesomeIcon icon={"download"}/>
            </button>
        </div>
    </div>
}