import Student from "./Student"
import FitButton from "../../home/general_form_components/FitButton"
import FullButton from "../../home/general_form_components/FullButton"
import Dropdown from "../../home/general_form_components/Dropdown"
import { useEffect, useState } from "react"
import getSubscribedStudents from "../../../axios/offers/GetSubscribedStudents"
import studentProfileModalRequest from "../../../axios/student_profile_modal/StudentProfileModalRequest"
import downloadCurriculum from "../../../axios/upload_download/DownloadCurriculum"
import JSZip from "jszip"

function SubscribedStudents({ offerId }) {
    const [students, setStudents] = useState([])

    useEffect(() => {
        const fetchStudents = async () => {
            try {
                const fetchedStudents = await getSubscribedStudents(offerId)
                setStudents(fetchedStudents)
            } catch (error) {
                console.log("Error fetching students: " + error)
            }
        }

        fetchStudents()
    }, [])

    const handleDownloadCurriculums = () => {
        const fetchStudentData = async () => {
            try {
                const zip = new JSZip()
                const pdfPromises = students.map(async (student) => {
                    const fetchedStudent = await studentProfileModalRequest(student.email)
                    const fetchedCurriculum = await downloadCurriculum(fetchedStudent.curriculumPath)
                    
                    const pdfBlob = new Blob([fetchedCurriculum], { type: "application/pdf" })
                    const pdfFileName = `CV-${fetchedStudent.name}-${fetchedStudent.surname}.pdf`
                    zip.file(pdfFileName, pdfBlob)
                }) 

                await Promise.all(pdfPromises)
                const zipBlob = await zip.generateAsync({ type: "blob" })
                const url = URL.createObjectURL(zipBlob)
                const link = document.createElement("a")
                link.href = url
                link.setAttribute("download", `Currículums`)
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
        <div className="w-full h-fit p-5 space-y-4 rounded-2xl bg-white border-2 shadow-xl flex-col">
            {students && students.length > 0 ? (
                <div>
                    <FullButton color="brand_orange" text="Descarregar tots els currículums" action={handleDownloadCurriculums}/>
                    {students.map((student, index) => (
                        <Student key={index} name={student.name} surname={student.surname} email={student.email} />
                    ))}
                </div>
            ) : (
                <p className="text-center">No hi ha cap estudiant inscrit</p>
            )}
        </div>
    )
}

export default SubscribedStudents