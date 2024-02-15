import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

export default function UserManagementNavigationItem(
    {title, link, icon, selected, action}
) {
    return( 
        <button onClick={action} 
            className="hover:scale-110 transition">
            <Link to={link}
                className={`${(selected) ? "text-brand_orange scale-110" : "text-black"}
                transition`}>
                <FontAwesomeIcon icon={icon} className="me-2"/>
                {title}
            </Link>
        </button>
    )
}