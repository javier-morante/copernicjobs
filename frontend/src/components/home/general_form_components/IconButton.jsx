import { fas } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { library } from '@fortawesome/fontawesome-svg-core'

library.add(fas)

export default function IconButton ({ id, action, color, icon}) {
    return (
        <button id={id} onClick={action} className={`bg-${color} rounded-md w-9 h-9 hover:scale-105 transition`}><FontAwesomeIcon className="text-light_gray_1 pointer-events-none" icon={icon}/></button>
    )
}