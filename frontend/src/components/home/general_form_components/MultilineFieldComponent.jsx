import { useState } from "react"

export default function MultilineFieldComponent(
    {title, name, value, action, maxSize, necessary}
) {
    const maxTextSize = maxSize

    return (
        <div className="w-full">
            <div className="flex place-items-baseline justify-left gap-2">
                <h1 className="font-bold">
                    {(necessary) ? <span className="text-warning_red">*</span> : null} {title}
                </h1>

                <span className={`text-xs 
                    ${(value.length > maxTextSize)? "text-warning_red" : "text-black"}`}>

                    {value.length}/{maxTextSize}
                </span>
            </div>

            <textarea name={name} onChange={action} value={value} required={necessary}
                className="w-full h-20 border-light_gray_2 border-2 rounded-md px-2 focus:outline-none"/>
        </div>
    )
}

MultilineFieldComponent.defaultProps = {
    value: "",
}