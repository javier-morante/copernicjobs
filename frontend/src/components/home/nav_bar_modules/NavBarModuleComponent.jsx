import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link } from "react-router-dom";

export default function NavBarModuleComponent(
    {icon, title, link, closeMenuAction}
) {

    return (
        <Link onClick={closeMenuAction} to={link} className="md:w-fit w-full flex place-items-center justify-start gap-2
            hover:text-brand_orange transition
            md:text-lg text-xl">
            <FontAwesomeIcon icon={icon}/>
            <h1>{title}</h1>
        </Link>
    )

}