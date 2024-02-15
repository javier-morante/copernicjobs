import React, { useEffect, useState } from 'react';
import Highcharts, { keys } from 'highcharts';
import HighchartsReact from 'highcharts-react-official';

import exporting from 'highcharts/modules/exporting'; // Importa el módulo de exportación
import exportData from 'highcharts/modules/export-data'; // Importa el módulo de exportación de datos

import { getOffersPublishment } from '../../../../axios/reports/ReportsRequests';

// Inicializa los módulos de exportación
exporting(Highcharts);
exportData(Highcharts);

const monthNames = [
    "Gener", "Febrer", "Març", "Abril",
    "Maig", "Juny", "Juliol", "Agost",
    "Setembre", "Octubre", "Novembre", "Desembre"
];

export default function OfferPublishmentChartComponent() {
    
    const [offerPublishment, setOfferPublishment] = useState([])

    useEffect(() => {
        getOffersPublishment(setOfferPublishment)
    }, [])

    const options = {
        chart: {
            type: 'line'
        },
        yAxis: {
            title: {
                text: "Nombre d'ofertes publicades"
            }
        },
        xAxis: {
            categories: Object.keys(offerPublishment).map((key) => {
                return monthNames[key]
            }),
            title: {
              text: null
            },
            gridLineWidth: 1,
            lineWidth: 0
        },
        title: {
            text: "Publicacions d'ofertes de feina"
        },
        colors: ['#EA7369'],
        series: [
            {
                name: "Ofertes publicades",
                data: Object.values(offerPublishment)
            }
        ]
    };

    return (
        <div className=''>
            <HighchartsReact highcharts={Highcharts} options={options}/>
        </div>
    )
}