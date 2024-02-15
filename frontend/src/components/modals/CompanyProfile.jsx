import { useEffect, useState } from 'react'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { faBuilding, faGlobe, faPhone, faTimes } from "@fortawesome/free-solid-svg-icons"
import companyProfileModalRequest from '../../axios/company_profile_modal/CompanyProfileModalRequest'

export default function CompanyProfile(props) {
    const [company, setCompany] = useState()

    useEffect(() => {
        const fetchCompany = async () => {
            try {
                const fetchedCompany = await companyProfileModalRequest(props.nif)
                setCompany(fetchedCompany)
            } catch (error) {
                console.log("Error fetching offers: " + error)
            }
        }

        fetchCompany()
    }, [])

    return (
        <div>
            {props.isOpen && (
                <div className="fixed inset-0 w-screen h-screen flex items-center justify-center bg-black bg-opacity-30 select-none">
                    <div className="relative w-1/2 h-fit bg-white rounded-lg p-5 space-y-2">
                        <p className="font-bold text-xl"><FontAwesomeIcon icon={faBuilding} className="pe-2" />{company.companyName}</p>
                        <p className="text-light_gray_3">{company.companyDescription}</p>
                        <p><FontAwesomeIcon icon={faPhone} className="pe-2" />{company.phone}</p>
                        <p className="w-fit text-honolulu_blue cursor-pointer hover:underline"><FontAwesomeIcon icon={faGlobe} className="pe-2" /><a href={`https://www.google.com/`} target="_blank" rel="noreferrer">{company.webpage}</a></p>
                        <FontAwesomeIcon icon={faTimes} onClick={props.onClose} className="absolute top-3 right-5 cursor-pointer hover:scale-125 transition"/>
                    </div>
                </div>
            )}
        </div>
    )
}
