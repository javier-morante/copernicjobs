export default function FieldComponent({
    title, type, name, value, action, placeholder ,necessary
}) {
    return (

        <div className="w-full space-y-2">
            <p className="font-bold">
                {(necessary) ? <span className="text-warning_red">*</span> : null} {title}
            </p>

            <input type={type} name={name} value={value} 
                onChange={action} required={necessary}
                className="w-full border-2 border-light_gray_2 rounded-md px-2 focus:outline-none"/>
        </div>
    )
}