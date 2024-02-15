import { createBrowserRouter, Navigate, Outlet, RouterProvider, useNavigate } from "react-router-dom";

import AccessFilterComponent from "./AccessFilterComponent";

import SignInScreen from "../components/authentication/sign_in/SignInScreen"
import AccessRequestScreen from "../components/authentication/access_request/AccessRequestScreen"
import PasswordRecoveryPhaseOneScreen from "../components/authentication/password_recovery_one/PasswordRecoveryPhaseOneScreen";
import PasswordRecoveryPhaseTwoScreen from "../components/authentication/password_recovery_two/PasswordRecoveryPhaseTwoScreen";
import UserProfileScreen from "../components/home/user_profile/UserProfileScreen";
import DinamicAccessFilterComponent from "./DinamicAccessFilterComponent";
import NavBar from "../components/home/general_components/NavBar";
import RoleManagementScreen from "../components/modules/role_management_module/RoleManagementScreen";
import EditOfferScreen from "../components/modules/edit_offer/EditOfferScreen";
import OfferScreen from "../components/modules/offers/OffersScreen";
import OfferDetailsScreen from "../components/modules/offers/OfferDetailsScreen";
import ValidateRegisterRequestScreen from "../components/modules/validate/ValidateRegisterRequestScreen";
import CreateOfferScreen from "../components/modules/create_offer/CreateOfferScreen";
import UserManagementScreen from "../components/modules/user_management/UserManagementScreen";
import StudentManagementScreen from "../components/modules/user_management/students/StudentManagementScreen";
import CompanyManagementScreen from "../components/modules/user_management/company/CompanyManagementScreen";
import AdministratorManagementScreen from "../components/modules/user_management/administrator/AdministratorManagementScreen";
import IncidencesScreen from "../components/modules/incidences/IncidenceScreen";
import IncidenceTag from "../components/modules/incidences/IncidenceTag";
import MyInscriptionsScreen from "../components/modules/my_inscriptions/MyInscriptionsScreen";
import ReportsModuleScreen from "../components/modules/reports_module/ReportsModuleScreen";
import Footer from "../components/home/footer_modules/Footer";
import ProfesionalProfile from "../components/modules/profesional_profile/ProfesionalProfile";
import MyOffersScreen from "../components/modules/my_offers/MyOffersScreen";
import ErrorScreen from "../components/error/ErrorScreen";


const router = createBrowserRouter([
    {
        path: "/",
        element: (
            <Navigate to="/sign-in" />
        )
    },
    {
        path: "/sign-in",
        element: <SignInScreen/>
    },
    {
        path: "/request-access",
        element: <AccessRequestScreen/>
    },
    {
        path: "/password-recovery-one",
        element: <PasswordRecoveryPhaseOneScreen/>
    },
    {
        path: "/password-recovery-two",
        element: <PasswordRecoveryPhaseTwoScreen/>
    },
    {
        path: "/user-profile",
        element: <AccessFilterComponent validRoles={["ROLE_STUDENT", "ROLE_COMPANY", "ROLE_ADMINISTRATOR"]}>
            <UserProfileScreen/>
        </AccessFilterComponent>
    },
    {
        path:"/test",
        element: <IncidenceTag/>
    },
    {
        path: "/home",
        element: (
            <AccessFilterComponent validRoles={["ROLE_STUDENT", "ROLE_COMPANY", "ROLE_ADMINISTRATOR"]}>
                <NavBar/>
                <div className=" xl:mt-38 lg:mt-36 mt-36 mb-36">
                    <AccessFilterComponent validRoles={["ROLE_STUDENT", "ROLE_COMPANY", "ROLE_ADMINISTRATOR"]}>
                        <Outlet/>
                    </AccessFilterComponent>
                </div>

                <Footer/>
            </AccessFilterComponent>
        ),
        children: [
            // Base Router Outlet
            {
                path: "",
                element: <h1>Select a module</h1>
            },

            // User Tmp Modules
            {
                path: "laboral-information",
                element: <DinamicAccessFilterComponent moduleName={"laboralInformation"}>
                     <ProfesionalProfile/>
                </DinamicAccessFilterComponent>
            },
            {
                path: "offers",
                element: (
                    <DinamicAccessFilterComponent moduleName={"offers"}>
                        <OfferScreen/>
                    </DinamicAccessFilterComponent>
                )
            },
            {
                path: "offers/details/:id",
                element: <OfferDetailsScreen/>
            },
            {
                path: "offers/edit/:id",
                element: <EditOfferScreen/>
            },
            {
                path: "my-inscriptions",
                element: <DinamicAccessFilterComponent moduleName={"myInscriptions"}>
                    <MyInscriptionsScreen/>
                </DinamicAccessFilterComponent>
            },
        
            // Company Tmp Modules
            {
                path: "my-offers",
                element: <DinamicAccessFilterComponent moduleName={"myOffers"}>
                    <MyOffersScreen/>
                </DinamicAccessFilterComponent>
            },
            {
                path: "my-offers/edit/:id",
                element: <EditOfferScreen/>
            },
            {
                path: "my-offers/details/:id",
                element: <OfferDetailsScreen/>
            },
            {
                path: "create-offer",
                element: <DinamicAccessFilterComponent moduleName={"createOffer"}>
                    <CreateOfferScreen/>
                </DinamicAccessFilterComponent>
            },
            {
                path: "incidences",
                element: <DinamicAccessFilterComponent moduleName={"incidents"}>
                    <IncidencesScreen/>
                </DinamicAccessFilterComponent> 
            },
        
            // Administrator Tmp Modules
            {
                path: "requests-management",
                element: <DinamicAccessFilterComponent moduleName={"REQUESTS"}>
                    <ValidateRegisterRequestScreen/>
                </DinamicAccessFilterComponent> 
            },
            {
                path: "role-management",
                element: <DinamicAccessFilterComponent moduleName={"roles"}>
                    <RoleManagementScreen/>
                </DinamicAccessFilterComponent> 
            },
            {
                path: "user-management",
                element: (
                    <DinamicAccessFilterComponent moduleName={"users"}>
                        <UserManagementScreen/>
                        <Outlet/>
                    </DinamicAccessFilterComponent>
                ),
                children: [
                    {
                        path: "student",
                        element: <StudentManagementScreen/>
                    },
                    {
                        path: "company",
                        element: <CompanyManagementScreen/>
                    },
                    {
                        path: "administrator",
                        element: <AdministratorManagementScreen/>
                    }
                ]
            },
            {
                path: "application-reports",
                element: <DinamicAccessFilterComponent moduleName={"reports"}>
                    <ReportsModuleScreen/>
                </DinamicAccessFilterComponent> 
            }
        ]
    },
    {
        path: "*",
        element: <ErrorScreen/>
    }
])

export default function RouterComponent() {
    return (
        <>
            <RouterProvider router={router}/>
        </>
    )
}