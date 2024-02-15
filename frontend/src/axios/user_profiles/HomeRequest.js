
import axiosInstance from "../AxiosInstance"

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default async function homeRequest(setUserProfileData,initailImg) {
    try {
        const response = await axiosInstance.get(
            `${backendUrl}/api/user-profile/get-user-data`,
        )

        if(response.status === 200) {
            setUserProfileData(response.data)
            initailImg && (initailImg(response.data.iconPath));
            return true
        }

    } catch (error) {
        console.log(`Error => ${error} 🤔`)
        return false
    }

    console.log("Invalid Access Request 😡")
    return false
}