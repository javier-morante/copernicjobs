export default function Number ({ name, placeholder, value }) {
    return (
        <input type="number" min="0" name={name} placeholder={placeholder} value={value} className="w-32 h-full border-2 rounded-md px-2 py-1 bg-white border-light_gray_2 placeholder:text-light_gray_3 text-black focus:outline-none focus:border-black"/>
    )
}