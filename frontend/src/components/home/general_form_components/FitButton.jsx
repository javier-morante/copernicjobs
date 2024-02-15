function FitButton (props) {
    return (
        <button onClick={props.action} className={`bg-${props.color} rounded-md text-light_gray_1 w-fit h-fit py-1 px-4 hover:scale-[1.02] transition`}>{props.text}</button>
    )
}

export default FitButton