import { toast } from "react-toastify";
import axiosInstance from '../AxiosInstance'
import downloadCurriculum from "../../axios/upload_download/DownloadCurriculum"
const backendUrl = import.meta.env.VITE_BACKEND_URL

async function getProfesionalProfile(setter) {
    try {

        const response = await axiosInstance.get(`${backendUrl}/api/profesional-profile`)

        if (response.status == 200) {
            console.log("2", response.data)
            setter(response.data);

            return true
        }
    } catch (error) {
        toast.warning("Error al carregar el perfil professional")
    }
}


async function getCurriculum(code) {
    try {
        const response = await downloadCurriculum(code)
        console.log(response)
        if (response) {
            const pdfBlob = new Blob([response], { type: "application/pdf" })
            console.log(pdfBlob)
            const url = URL.createObjectURL(pdfBlob)
            const link = document.createElement("a")
            link.href = url
            link.setAttribute("download", "curriculum-vitae")
            
            document.body.appendChild(link)
            link.click()

            setTimeout(() => {
                document.body.removeChild(link)
                URL.revokeObjectURL(url)
            }, 0)
            return;
        }
        toast.warning("Error al descarregar el curriculum")
    } catch (error) {
        console.log(error)
        toast.warning("Error al descarregar el curriculum")
    }
}


function postProfesionalProfile(stdata) {
    return new Promise((resolve, reject) => {
        toast.promise(
            axiosInstance.post(`${backendUrl}/api/profesional-profile`, stdata, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }),
            {
                loading: 'Actualizant perfil professional...',
                success: {
                    render: () => {
                        resolve(true);
                        return 'Perfil professional actualizat!';
                    }
                },
                error: {
                    render: (e) => {
                        console.log(e.data.response.data)
                        reject(false);
                        return "Error al actualitzar el perfil professional"
                    }
                },
            }
        );
    });
}

export { getProfesionalProfile, postProfesionalProfile, getCurriculum }