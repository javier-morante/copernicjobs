export default function OfferDetailsHeader ({ title, company, image, description, urgency }) {
    return (
        <div className="bg-white shadow-xl relative w-full h-fit flex items-center p-5 rounded-2xl ">
            <img src={image} className="size-40 me-8 sm:block hidden"/>
            <div className="flex-col">
                <h1 className="font-bold text-4xl">{title}</h1>
                <h2 className="w-fit text-xl">{company}</h2>
                <p className="pt-5">{description}</p> 
            </div>
            <p className="text-warning_red absolute top-5 right-5">{urgency}</p>
        </div>
    )
}