import UserManagementNavigationItem from "./general_components/UserManagementNavigationItem";
import { useEffect, useState } from "react";

export default function UserManagementScreen() {

    const [selectedStates, setSelectedStates] = useState({
        administrator: false,
        company: false,
        student: false,
    })

    const handlePathUpdate = () => {
        const { pathname } = location

        setSelectedStates({
            administrator: pathname.includes("/home/user-management/administrator"),
            company: pathname.includes("/home/user-management/company"),
            student: pathname.includes("/home/user-management/student"),
        })
    }

    useEffect(() => {
        handlePathUpdate()
    }, [])

    return <div className="p-1 px-5">

        <div className="w-full h-fit grid grid-cols-3 text-center p-3
            bg-white shadow-xl rounded-md font-bold">

            
            <UserManagementNavigationItem title="Administradors"
                icon="user-tie"
                link={"/home/user-management/administrator"}
                selected={selectedStates.administrator}
                action={handlePathUpdate}/>
            
            <UserManagementNavigationItem title="Empreses"
                icon="building"
                link={"/home/user-management/company"}
                selected={selectedStates.company}
                action={handlePathUpdate}/>
            
            <UserManagementNavigationItem title="Estudiants"
                icon="graduation-cap"
                link={"/home/user-management/student"}
                selected={selectedStates.student}
                action={handlePathUpdate}/>

        </div>

    </div>
}