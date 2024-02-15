import { useState } from "react"
import Confetti from "./Confeti"
import FooterUserInfoFrame from "./FooterUserInfoFrame"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"

export default function FooterPopup(
    { footerStatus, handleFooterStatus }
) {

    return (footerStatus)
        ? (
            <div className="fixed top-0 left-0 w-full h-full
                flex place-items-center justify-center
                bg-black bg-opacity-50 z-50">

                <div className="w-full flex place-items-center justify-center">
                    <Confetti/>

                    <div className="w-5/12 grid gap-5
                        bg-white rounded-md p-5 z-50">

                        <div className="flex">
                            <h1 className="w-full font-bold text-2xl">
                                Desenvolupadors
                            </h1>

                            <FontAwesomeIcon icon="close" onClick={handleFooterStatus}
                                className="hover:scale-125 hover:cursor-pointer transition"/>

                        </div>


                        <hr/>

                        <FooterUserInfoFrame userData={{
                            name: "Adrián García",
                            email: "adriangargom04@gmail.com",
                            linkedin: "https://es.linkedin.com/in/adriangargom",
                            github: "https://github.com/adriangargom",
                        }}/>

                        <hr/>

                        <FooterUserInfoFrame userData={{
                            name: "Jan Pomar",
                            email: "janpose04@gmail.com",
                            linkedin: "https://es.linkedin.com/in/jan-pomar-serra",
                            github: "https://github.com/Pomardinho",
                        }}/>

                        <hr/>

                        <FooterUserInfoFrame userData={{
                            name: "Javier Morante",
                            email: "javichu.monu@gmail.com",
                            linkedin: "",
                            github: "https://github.com/javier-morante",
                        }}/>

                    </div>
                </div>
            </div>
        )
        : null
}