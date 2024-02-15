export default function FullButton ({ action, color, text}) {
    return (
        <button onClick={action} className={`bg-${color} rounded-md text-light_gray_1 w-full h-fit py-1 px-4 hover:scale-[1.02] transition`}>{text}</button>
    )
}