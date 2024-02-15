import Cookies from "js-cookie"

export default function jwtDecode() {
    const decodeToken = (token) => {
        const payloadBase64 = token.split(".")[1]
        const decodePayload = atob(payloadBase64)
        const parsedPyload = JSON.parse(decodePayload)
        return parsedPyload
    }

    const token = Cookies.get('token')
    return (token)? decodeToken(token): {rol: ""}
}