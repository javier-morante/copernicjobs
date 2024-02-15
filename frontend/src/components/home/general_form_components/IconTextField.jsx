import { fas } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { library } from '@fortawesome/fontawesome-svg-core'

library.add(fas)

export default function IconTextField ({ name, placeholder, icon, change }) {
    return (
        <div className="relative inline-block w-full">
            <div className="absolute inset-y-0 flex items-center pl-2 pointer-events-none">
                <FontAwesomeIcon icon={icon}/>
            </div>
            <input type="text" name={name} onChange={change} placeholder={placeholder} className="shadow-xl w-full h-fit rounded-md ps-7 py-1 bg-white  placeholder:text-light_gray_3 text-black focus:outline-none focus:border-black"/>
        </div>
    )
}