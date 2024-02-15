import { useState } from "react";
import Confetti from "./Confeti";
import FooterUserInfoFrame from "./FooterUserInfoFrame";
import FooterPopup from "./FooterPopup";


export default function Footer() {
    
    const [footerStatus, setFooterStatus] = useState(false)

    const handleFooterStatus = () => {
        setFooterStatus(!footerStatus)
    }


    return (
        <div className="md:flex hidden fixed bottom-0 left-0 w-full bg-brand_orange p-2
             place-items-center justify-center z-50">

            <FooterPopup footerStatus={footerStatus} handleFooterStatus={handleFooterStatus}/>
            
            <h1 onClick={handleFooterStatus}
                className="hover:scale-105 hover:cursor-pointer hover:underline transition
                text-white">
                Desenvolupat per Adrián García, Javier Morante i Jan Pomar
            </h1>
        </div>
    )
}