import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { faBriefcase, faEnvelope, faPhone, faTimes, faUserGraduate } from "@fortawesome/free-solid-svg-icons"
import { faGithubSquare, faLinkedin, faYoutubeSquare } from "@fortawesome/free-brands-svg-icons"
import FitButton from "../home/general_form_components/FitButton"
import { useEffect, useState } from "react"
import studentProfileModalRequest from "../../axios/student_profile_modal/StudentProfileModalRequest"
import downloadCurriculum from "../../axios/upload_download/DownloadCurriculum"

export default function StudentProfile({ isOpen, onClose, email }) {
    const [student, setStudent] = useState()

    useEffect (() => {
        const fetchStudent = async () => {
            try {
                const fetchedStudent = await studentProfileModalRequest(email)
                setStudent(fetchedStudent)
            } catch (error) {
                console.log("Error fetching student: " + error)
            }
        }

        fetchStudent()
    }, [])

    const handleDownloadCurriculum = () => {
        const fetchStudentData = async () => {
            try {
                const fetchedCurriculum = await downloadCurriculum(student.curriculumPath)
                
                const pdfBlob = new Blob([fetchedCurriculum], { type: "application/pdf" })
                const url = URL.createObjectURL(pdfBlob)
                const link = document.createElement("a")
                link.href = url
                link.setAttribute("download", `CV-${student.name}-${student.surname}`)
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
        <div>
            {isOpen && (
                <div className="fixed inset-0 w-screen h-screen flex items-center justify-center bg-black bg-opacity-10 backdrop-blur-sm">
                    <div className="relative xl:w-1/2 h-fit bg-white rounded-lg p-5 space-y-2">
                        <p className="font-bold text-xl"><FontAwesomeIcon icon={faUserGraduate} className="pe-2" />{student.name} {student.surname}</p>
                        <div className="grid grid-cols-2 w-fit gap-32">
                            {(student.publicEmail || student.publicPhone) && (
                                <div>
                                    <p className="font-bold text-xl">Contacte</p>
                                    {student.publicEmail && <p><FontAwesomeIcon icon={faPhone} className="pe-2" />{student.phone}</p>}
                                    {student.publicPhone && <p><FontAwesomeIcon icon={faEnvelope} className="pe-2" />{student.email}</p>}
                                </div>
                            )}
                            
                            {(student.publicLinkedinUrl || student.publicPortfolioUrl || student.publicYoutubeUrl ||  student.publicGithubUrl) && (
                                <div>
                                    <p className="font-bold text-xl">Enllaços</p>
                                    {student.publicYoutubeUrl && <a href={student.youtubeUrl} target="_blank"><FontAwesomeIcon icon={faYoutubeSquare} className="text-youtube_red text-xl pe-2"/></a>}
                                    {student.publicLinkedinUrl && <a href={student.linkedinUrl} target="_blank"><FontAwesomeIcon icon={faLinkedin} className="text-linkedin_blue text-xl pe-2"/></a>}
                                    {student.publicGithubUrl && <a href={student.githubUrl} target="_blank"><FontAwesomeIcon icon={faGithubSquare} className="text-github_black text-xl pe-2"/></a>}
                                    {student.publicPortfolioUrl && <a href={student.portfolioUrl} target="_blank"><FontAwesomeIcon icon={faBriefcase} className="text-portfolio text-xl"/></a>}
                                </div>
                            )}
                        </div>
                        <FontAwesomeIcon icon={faTimes} onClick={(onClose)} className="absolute top-3 right-5 cursor-pointer hover:scale-125 transition"/>
                        <div className="xl:absolute xl:bottom-5 xl:right-5 sm:mt-5"><FitButton color="brand_orange" text="Descarregar currículum" action={handleDownloadCurriculum}/></div>
                    </div>
                </div>
            )}
        </div>
    )
}
