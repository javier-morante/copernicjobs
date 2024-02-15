import SaveCancelComponent from '../../home/general_form_components/SaveCancelComponent'
import FieldComponent from '../../home/general_form_components/FieldComponent'
import { postProfesionalProfile, getProfesionalProfile,getCurriculum } from '../../../axios/profesional_profile/ProfesionalProfile'
import { useEffect, useRef, useState } from 'react'
import { ToastContainer } from 'react-toastify'
import DropDownComponent from '../../home/general_form_components/DropDownComponent'
import FileInputComponent from '../../home/general_form_components/FileInputComponent'

export default function ProfesionalProfile() {

    const [stProfesionalProfile, setStProfesionalProfile] = useState({
        curriculum: "",
        curriculumPath: "",
        linkedinUrl: "",
        portfolioUrl: "",
        youtubeUrl: "",
        githubUrl: "",
        degree: "DAM",
    })

    const ref = useRef(null)

    useEffect(()=>console.log(stProfesionalProfile),[stProfesionalProfile])

    useEffect(() => {
        getProfesionalProfile(setStProfesionalProfile)
    }, [])

    const downloadCV = (e)=>{
        e.preventDefault()
        getCurriculum(stProfesionalProfile.curriculumPath)
    }

    const clearInputFile = () => {
        ref.current.value = ""
        ref.current.type = "text";
        ref.current.type = "file";
    }

    const handleFileChange = (event,ref) => {
        setStProfesionalProfile({
            ...stProfesionalProfile,
            curriculum: event?event.target.files[0]:ref.current.files[0],
        })
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target
        setStProfesionalProfile({
            ...stProfesionalProfile,
            [name]: value,
        })
    }

    const handleSubmit = async (e) => {
        let isDonde = true
        e.preventDefault()
        console.log("submit")
        postProfesionalProfile(stProfesionalProfile).then((e) =>{
            if(e){
                clearInputFile()
                getProfesionalProfile(setStProfesionalProfile)
            }
            
        })
    }

    return (
        <>
            <form onSubmit={handleSubmit} className='sm:p-x4 p-4'>
                <div className='grid sm:grid-cols-2 gap-4 '>
                    <FieldComponent
                        title="LinkedIn"
                        type="text"
                        name="linkedinUrl"
                        value={stProfesionalProfile.linkedinUrl}
                        action={handleInputChange} />

                    <FieldComponent
                        title="Portafoli"
                        type="text"
                        name="portfolioUrl"
                        value={stProfesionalProfile.portfolioUrl}
                        action={handleInputChange} />

                    <FieldComponent
                        title="YouTube"
                        type="text"
                        name="youtubeUrl"
                        value={stProfesionalProfile.youtubeUrl}
                        action={handleInputChange} />

                    <FieldComponent
                        title="GitHub"
                        type="text"
                        name="githubUrl"
                        value={stProfesionalProfile.githubUrl}
                        action={handleInputChange} />
                </div>
                <div className='py-4'>
                    <DropDownComponent title="Estudis"
                        formData={stProfesionalProfile}
                        handleInput={handleInputChange}
                        name="degree" value={stProfesionalProfile.degree}
                        options={[
                            {
                                value: "DAM",
                                name: "Desenvolupament d'aplicacions multiplataforma (DAM)",
                            },
                            {
                                value: "DAW",
                                name: "Desenvolupament d'aplicacions web (DAW)",
                            },
                            {
                                value: "ASIX",
                                name: "Administració de sistemes informàtics en xarxa (ASIX)",
                            },
                            {
                                value: "SMIX",
                                name: "Sistemes microinformàtics i xarxes (SMIX)",
                            }, {
                                value: "OTHER",
                                name: "Altres",
                            }
                        ]} />
                </div>

                {stProfesionalProfile.curriculumPath &&
                (<div className='py-2'>
                    <button className='w-4/12 p-1 bg-brand_orange text-white rounded-md transition hover:scale-105' onClick={downloadCV}>Descargar curriculum</button>
                </div>)
                }

                <div className='p-4 pl-30'>
                    <FileInputComponent onChange={handleFileChange} reference={ref} accept="application/pdf" value={stProfesionalProfile.curriculum}/>
                </div>

                
                
                <SaveCancelComponent />
            </form>
            <ToastContainer />
        </>

    )

}