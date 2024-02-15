export default function DropDownComponent(
    { title, name, value, options, handleInput, classSel ,classTitle ,classBody}
) {
    return (
        <div className={classBody|| "w-full space-y-2"}>
            <p className={classTitle|| "font-bold"}>
                {title}
            </p>
            <select name={name} value={value} onChange={handleInput}
                className={classSel || "w-full border-2 border-light_gray_2 px-1 rounded-md focus:outline-none"}>

                {options && options.map((element, index) => {
                    return <option key={index} value={element.value}>
                        {element.name}
                    </option>
                })}
            </select>
        </div>
    )
}