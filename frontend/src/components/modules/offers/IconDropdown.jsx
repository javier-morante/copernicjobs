import { fas } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { library } from '@fortawesome/fontawesome-svg-core'

library.add(fas)

export default function IconDropdown({ id, defaultOption, options, icon, change }) {
    return (
        <div className="relative inline-block w-full">
            <div className="absolute inset-y-0 flex items-center pl-2 pointer-events-none">
                <FontAwesomeIcon icon={icon}/>
            </div>
            <select id={id} onChange={change} className="shadow-xl bg-white border-light_gray_2 rounded-md h-fit w-full ps-7 py-1 appearance-none">
                <option default hidden>{defaultOption}</option>
                {options.map((option, index) => (
                    <option key={index} value={option.value}>{option.name}</option>
                ))}
            </select>
            <div className="absolute inset-y-0 right-0 flex items-center pr-2 pointer-events-none">
                <FontAwesomeIcon icon="caret-down" className="text-light_gray_3" />
            </div>
        </div>
    )
}