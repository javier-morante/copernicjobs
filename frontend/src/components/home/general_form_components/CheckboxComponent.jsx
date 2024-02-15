export default function CheckboxComponent(
    {text, action, value, name}
) {
    return (
        <div className="flex gap-2">
            <input id="checkbox" type="checkbox" checked={value} 
                onChange={action} name={name} className="cursor-pointer"/>
            <label htmlFor="checkbox" className="select-none cursor-pointer">
                {text}
            </label>
        </div>
    )
}