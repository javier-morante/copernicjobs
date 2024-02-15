import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCaretDown } from '@fortawesome/free-solid-svg-icons'

export default function Dropdown(props) {
    return (
        <div className="relative inline-block w-full">
            <select className="bg-white shadow-lg rounded-md text-light_gray_3 h-fit w-full px-2 py-1 appearance-none">
                <option default hidden>{props.default}</option>
                {props.options.map((option, index) => (
                    <option key={index}>{option}</option>
                ))}
            </select>
            <div className="absolute inset-y-0 right-0 flex items-center pr-2 pointer-events-none">
                <FontAwesomeIcon icon={faCaretDown} className="text-light_gray_3" />
            </div>
        </div>
    )
}