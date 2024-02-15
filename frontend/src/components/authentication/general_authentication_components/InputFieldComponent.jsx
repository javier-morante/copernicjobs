import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { library } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';

library.add(fas)

export default function InputFieldComponent({type, name, value, action, placeholder, icon}) {
    return <div className="w-full flex gap-1 p-0.5 border-2 border-grey-200 rounded-md ">

        <div className="w-10 p-1 place-items-center justify-center text-center
            bg-brand_orange rounded-md">
            <FontAwesomeIcon icon={icon} className="text-white"/>
        </div>

        <input type={type} name={name} value={value} onChange={action} placeholder={placeholder} 
            className="w-full p-1 px-2 focus:outline-none"/>
    </div>
}