import React, { useEffect, useState } from 'react';
import Highcharts, { keys } from 'highcharts';
import HighchartsReact from 'highcharts-react-official';

import exporting from 'highcharts/modules/exporting'; // Importa el módulo de exportación
import exportData from 'highcharts/modules/export-data'; // Importa el módulo de exportación de datos

import { getUserTraffic } from '../../../../axios/reports/ReportsRequests';


// Inicializa los módulos de exportación
exporting(Highcharts);
exportData(Highcharts);

const monthNames = [
    "Gener", "Febrer", "Març", "Abril",
    "Maig", "Juny", "Juliol", "Agost",
    "Setembre", "Octubre", "Novembre", "Desembre"
];

export default function UserTrafficLineChartComponent() {
    
    const [offerTraffic, setOfferTraffic] = useState([])

    useEffect(() => {
        getUserTraffic(setOfferTraffic)
    }, [])

    useEffect(() => {
        console.log("Offer Publishments => ", offerTraffic)
    }, [offerTraffic])

    const options = {
        chart: {
            type: 'line'
        },
        yAxis: {
            title: {
                text: "Inicis de sessió"
            }
        },
        xAxis: {
            categories: Object.keys(offerTraffic).map((key) => {
                return monthNames[key]
            }),
            title: {
              text: null
            },
            gridLineWidth: 1,
            lineWidth: 0
        },
        title: {
            text: "Trànsit d'estudiants"
        },
        colors: ['#EA7369'],
        series: [
            {
                name: "Trànsit d'estudiants",
                data: Object.values(offerTraffic)
            }
        ]
    };

    return (
        <div className=''>
            <HighchartsReact highcharts={Highcharts} options={options}/>
        </div>
    )
}