import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { library } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';
library.add(fas)

import { Link } from "react-router-dom";

const backendUrl = import.meta.env.VITE_BACKEND_URL


export default function NavBarComponent() {
    return <div className="w-screen h-auto bg-brand_orange flex p-3">

        <div className="w-11/12 flex place-items-center justify-left gap-5">

            <Link to={"/"} className="hover:scale-110 hover:cursor-pointer transition">
                <img src={`${backendUrl}/api/images/logo.png`}
                    className="w-auto h-14"/>
            </Link>

            <h1 className="text-2xl text-white font-bold">
                Borsa Copèrnic
            </h1>

        </div>

    </div>
}