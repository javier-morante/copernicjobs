import { useEffect, useState } from "react"
import { getIncidences } from '../../../axios/incidences/IncidencesRequest'
import IncidenceTag from "./IncidenceTag"

export default function IncidenceM({ updateComponent, filter, filterMethod }) {
    const [incidences, setIncidences] = useState([])

    useEffect(() => {
        getIncidences(setIncidences, filterMethod)
    }, [updateComponent, filter])

    return (
        <div className="scroll-bar-style overflow-y-auto max-w-full rounded-md flex gap-4 flex-col">
            {incidences.length > 0 ? (
                incidences.map((inci) => (
                    <IncidenceTag key={inci.id} incidence={inci} />
                ))
            ) : (
                <p className="col-span-2 text-2xl text-center">No hi ha incidències</p>
            )}
        </div>
    )
}