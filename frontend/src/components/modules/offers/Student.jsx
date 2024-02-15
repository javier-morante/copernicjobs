import { useState } from 'react'
import FitButton from '../../home/general_form_components/FitButton'
import StudentProfile from '../../modals/StudentProfile'
import downloadCurriculum from '../../../axios/upload_download/DownloadCurriculum'
import studentProfileModalRequest from '../../../axios/student_profile_modal/StudentProfileModalRequest'

export default function Student({ name, surname, email }) {
    const [isModalOpen, setModalOpen] = useState(false)
    
    const openModal = () => {
        setModalOpen(true)
    }
    
    const closeModal = () => {
        setModalOpen(false)
    }

    const handleDownloadCurriculum = () => {
        const fetchStudentData = async () => {
            try {
                const fetchedStudent = await studentProfileModalRequest(email)
                const fetchedCurriculum = await downloadCurriculum(fetchedStudent.curriculumPath)

                const pdfBlob = new Blob([fetchedCurriculum], { type: "application/pdf" })
                const url = URL.createObjectURL(pdfBlob)
                const link = document.createElement("a")
                link.href = url
                link.setAttribute("download", `CV-${fetchedStudent.name}-${fetchedStudent.surname}`)
                document.body.appendChild(link)
                link.click()
                
                setTimeout(() => {
                    document.body.removeChild(link)
                    URL.revokeObjectURL(url)
                }, 0)
            } catch (error) {
                console.log("Error fetching data: " + error)
            }
        }

        fetchStudentData()
    }

    return (
        <div className="w-full h-fit p-5 rounded-2xl bg-white shadow-lg">
            <div className="flex-col">
                <h2 className="font-bold text-xl h-auto pb-2">{name} {surname}</h2>
                <div className="space-x-2">
                    <FitButton color="brand_orange" text="Descarregar currículum" action={handleDownloadCurriculum}/>
                    <FitButton color="brand_orange" text="Perfil" action={openModal}/>
                </div>
            </div>
            <StudentProfile isOpen={isModalOpen} onClose={closeModal} email={email}/>
        </div>
    )
}
