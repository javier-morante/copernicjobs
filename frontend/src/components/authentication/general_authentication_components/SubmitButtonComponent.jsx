import React from "react";

export default function SubmitButtonComponent({buttonContent}) {
    return <button className="w-full bg-brand_orange rounded-md
        hover:scale-105 hover:cursor-pointer transition
        text-white p-2">
        {buttonContent}
    </button>
}