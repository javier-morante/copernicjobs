export default function OfferDetailsField ({ title, description }) {
    return (
        <div className="bg-white shadow-xl w-full h-fit p-4 rounded-2xl space-y-2">
            <h2 className="font-bold text-xl">{title}</h2>
            <p>{description}</p>
        </div>
    )
}