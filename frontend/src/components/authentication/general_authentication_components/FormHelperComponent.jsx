import React from "react";
import { Link } from "react-router-dom";

export default function FormHelperComponent({text, linkText, link}) {
    return <Link to={link} className="md:text-base text-xs">
        {text}
        &#160;
        <span className="font-bold transition 
            hover:underline hover:cursor-pointer">
            {linkText}
        </span>
    </Link>
}