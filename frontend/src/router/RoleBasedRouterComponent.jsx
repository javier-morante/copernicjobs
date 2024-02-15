import { Navigate } from "react-router-dom"
import jwtDecode from "../utils/jwtDecode"

// The following component takes an array filled with object containing all the users and the
// component that belongs to that user, then when the user access the path, whe component will
// redirect the user automatically to the assigned role 


export default function RoleBasedRouterComponent({rolesAndPaths}) {
    const payload = jwtDecode()

    // The following method checks all the rolesAndPaths provided and redirects the user, depending of
    // their role, to the provided path
    const roleBasedValidation = () => {
        for (let i = 0; i < rolesAndPaths.length; i++) {
            if(payload.rol === rolesAndPaths[i].rol) {
                return rolesAndPaths[i].component
            }
        }
        return <Navigate to={"/sign-in"}/>
    }

    return (
        <div>
            {roleBasedValidation()}
        </div>
    )
}