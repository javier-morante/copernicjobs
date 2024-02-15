import { fas } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { library } from '@fortawesome/fontawesome-svg-core'
import { useState } from 'react'

library.add(fas)

export default function StatusIconButton ({ id, action, statusButton, preColor, postColor, preIcon, postIcon }) {

    return (
        <button id={id} onClick={action} className={`${(statusButton) ? postColor : preColor } rounded-md w-9 h-9 transition duration-300 hover:scale-105`}><FontAwesomeIcon className="text-light_gray_1 pointer-events-none" icon={(status) ? preIcon : postIcon}/></button>
    )
}