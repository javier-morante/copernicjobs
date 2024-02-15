import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { useState } from "react"

export default function DropdownContainer(
    {children, containerTitle}
) {
    const [containerState, setContainerState] = useState(false)

    const manageContainerState = () => {
        setContainerState(!containerState)
    }

    return (
        <div className="w-full p-5">
            <div className="w-full grid gap-5 sm:p-2 sm:px-16 px-0 rounded-md select-none">
                <div onClick={manageContainerState} 
                    className="flex place-items-center justify-start gap-5
                    p-5 rounded-md bg-brand_orange text-white
                    hover:cursor-pointer hover:scale-[1.02] transition">
                    
                    <FontAwesomeIcon icon={"caret-down"} id="cacatua"
                        className={`${(containerState)? 'rotate-180' : 'rotate-0'}`}/>

                    <h1 className="text-xl">
                        {containerTitle}
                    </h1>
                </div>

                <div className={`${(containerState)? 'block' : 'hidden' }
                    bg-gray-100 shadow-2xl p-5 rounded-md`}>
                    {children}
                </div>
            </div>
        </div>
    )
}