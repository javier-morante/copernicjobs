import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

export default function FooterUserInfoFrame(
    {userData}
) {
    return (
        <div className="grid gap-2">
            <h1 className="w-full font-bold">
                {userData.name} 
            </h1>

            <h2>
                {userData.email}
            </h2>

            <div className="flex gap-4">
                <a href={userData.github}>
                    <img src='../../../../public/images/github-sign.png'
                        className="w-8 h-8 
                        hover:scale-110 hover:cursor-pointer transition" />
                </a>

                <a href={userData.linkedin} target="_blank">
                    <img src='../../../../public/images/linkedin.png'
                        href={userData.linkedin}
                        className="w-8 h-8 hover:scale-110 hover:cursor-pointer transition"/>
                </a>
            </div>
        </div>
    )
}