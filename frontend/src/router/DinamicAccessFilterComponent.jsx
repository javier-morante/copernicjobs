import { useEffect, useState } from "react"
import { Navigate, useNavigate } from "react-router-dom"

import { getNavBarModulePermissions } from "../axios/nav_bar/navBarUserModules"


// The following component is incharged of allowing or dening access to users dinamicaly,
// fetches the api and retrives all the privileges of each userRole over the provided module
// then extracts the actually authenticated user role from the payload of the JWT authentication token
// and finally verifies inside of the retrieved data if the actual authenticated user has or not
// privileges over the requested module, if everithing works successfully the module is displayed
// if not, the user will be redirected to the home screen

export default function DinamicAccessFilterComponent(
    {children, moduleName}
) { 
    const navigate = useNavigate()

    const [validationStatus, setValidationStatus] = useState(false)
    
    useEffect(() => {
        getNavBarModulePermissions(moduleName)
        .then((status) => {
            (status)? setValidationStatus(true) : navigate("/home")
        })
    }, [])


    return (
        <div className="">
            { (validationStatus)? <>{children}</> : <h1>Invalid Access</h1> }
        </div>
    )
}