export default function LargeButton ({ id, action, color, text }) {
    return (
        <button id={id} onClick={action} 
            className={`bg-${color} rounded-md text-light_gray_1 w-48 h-fit py-1 px-4
            hover:scale-105 transition `}>
            {text}
        </button>
    )
}