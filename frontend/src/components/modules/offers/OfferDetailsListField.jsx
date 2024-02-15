export default function OfferDetailsListField({ title, list }) {
    return (
        <div className="bg-white shadow-xl w-full h-fit p-5 rounded-2xl space-y-2">
            <h2 className="font-bold text-xl">{title}</h2>
            {(list && list.length > 0) && list.map((degree, index) => (
                <p key={index}>{degree}</p>
            ))}
        </div>
    )
}