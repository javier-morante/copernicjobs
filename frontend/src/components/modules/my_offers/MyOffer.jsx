import { useEffect, useState } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faGraduationCap, faWallet } from '@fortawesome/free-solid-svg-icons'
import IconButton from '../../home/general_form_components/IconButton'
import StatusIconButton from '../offers/StatusIconButton'
import CompanyProfile from '../../modals/CompanyProfile'
import { disableOffer } from '../../../axios/offers/disableOffer'

export default function MyOffer(props) {
    const [isModalOpen, setModalOpen] = useState(false)

    const openModal = () => {
        setModalOpen(true)
    }

    const closeModal = (e) => {
        e.stopPropagation()
        setModalOpen(false)
    }

    useEffect(() => {
        console.log(props)
    }, [])

    const handleOfferStatus = (e, offerId) => {
        e.preventDefault()

        disableOffer(offerId)
        .then((status) => {
            if(status) {props.reloadOffers()}
        })
    }

    return (
        <div>
            <div id="offer" className="shadow-lg w-full h-full space-y-2 p-5 rounded-2xl bg-white  select-none">
                <div className="flex space-x-4">
                    <img src={props.image} className="size-12"></img>
                    <div className="flex-grow">
                        <h2 className="font-bold text-xl h-auto">{props.title}</h2>
                        {
                            (!props.company.includes("@"))
                                ? (
                                    <p 
                                    onClick={(e) => { openModal(); e.stopPropagation() }} 
                                    id="modalLink" 
                                    className="text-honolulu_blue w-fit h-auto cursor-pointer hover:underline">
                                        {props.company}
                                    </p>
                                )
                                : (
                                    <p>
                                        {props.company}

                                    </p>
                                )
                        }
                    </div>
                    <div className="space-y-1">
                        <p className="text-xs text-warning_red">{props.urgency}</p>
                    </div>
                </div>
                <p className='w-full md:h-16 h-20'>{props.description}</p>
                <div className="flex items-end">
                    <div className="flex flex-1 space-x-3">
                        {(props.minSalary && props.maxSalary) && <p className="text-xs text-light_gray_3"><FontAwesomeIcon className="pe-1" icon={faWallet} />{props.minSalary} - {props.maxSalary} € bruts/any</p>}
                        {props.studies && (
                            <p className="text-xs text-light_gray_3"><FontAwesomeIcon className="pe-1" icon={faGraduationCap} />{props.studies.map((degree, index) => (
                                <span key={index}>{index > 0 && ", "}{degree}</span>
                            ))}

                            </p>
                        )}
                    </div>
                    <div className="flex space-x-2">
                        <IconButton id="editOffer" color="brand_orange" icon="pen" />
                        <StatusIconButton action={(e) => { handleOfferStatus(e, props.id) }} id="disableOffer" statusButton={props.isEnabled} preColor="bg-warning_red" postColor="bg-valid_green" preIcon="calendar-times" postIcon="calendar-check" />
                    </div>
                </div>
            </div>
            <CompanyProfile isOpen={isModalOpen} onClose={(e) => closeModal(e)} nif={props.nif} />
        </div>
    )
}