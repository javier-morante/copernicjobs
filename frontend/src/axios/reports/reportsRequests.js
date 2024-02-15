import axiosInstance from '../AxiosInstance'

const backendUrl = import.meta.env.VITE_BACKEND_URL

async function getUsersQuantity(setData) {
    try {
        const response = await axiosInstance.get(
            `${backendUrl}/api/reports/get-users-quantity`,
        )

        if(response.status === 200) {
            setData([
                {
                    name: "Estudiants",
                    data: [response.data.studentsQuantity]
                },
                {
                    name: "Empreses",
                    data: [response.data.companyQuantity]
                },
                {
                    name: "Administradors",
                    data: [response.data.administratorQuantity]
                }
            ])
            return true
        }

    } catch (error) {
        console.log(error)
    }

    return false
}


async function getEnrollmentQuantity(setData) {
    try {
        const response = await axiosInstance.get(
            `${backendUrl}/api/reports/get-enrollments-quantity`,
        )

        if(response.status === 200) {
            setData(response.data)
            return true
        }

    } catch (error) {
        console.log(error)
    }

    return false
}

async function getOffersPublishment(setData) {
    try {
        const response = await axiosInstance.get(
            `${backendUrl}/api/reports/get-offer-publishments`,
        )

        if(response.status === 200) {
            console.log("Offer publishment report data => ", response.data)
            setData(response.data)
            return true
        }

    } catch (error) {
        console.log(error)
    }

    return false
}


async function getUserTraffic(setData) {
    try {
        const response = await axiosInstance.get(
            `${backendUrl}/api/reports/get-user-traffic`,
        )

        if(response.status === 200) {
            setData(response.data)

            console.log("User Traffic => ", response.data)
            return true
        }

    } catch (error) {
        console.log(error)
    }

    return false
}



export { getUsersQuantity, getEnrollmentQuantity, getOffersPublishment, getUserTraffic }