import { Navigate } from "react-router-dom"
import jwtDecode from "../utils/jwtDecode"

// The following filter acts as a wrapper and validates the actual user role, 
// and a provided user valid roles array, then if the actual user role is inside
// of the validRoles array, allows the access to the user and returns
// the child components

export default function AccessFilterComponent({children, validRoles}) {
    const payload = jwtDecode()
    return (validRoles.includes(payload.rol))? <>{children}</> : <Navigate to={"/sign-in"}/>
}