import React, { useEffect, useState } from 'react';
import Highcharts, { keys } from 'highcharts';
import HighchartsReact from 'highcharts-react-official';

import exporting from 'highcharts/modules/exporting'; // Importa el módulo de exportación
import exportData from 'highcharts/modules/export-data'; // Importa el módulo de exportación de datos

import { getEnrollmentQuantity } from '../../../../axios/reports/ReportsRequests'


// Inicializa los módulos de exportación
exporting(Highcharts);
exportData(Highcharts);

const monthNames = [
    "Gener", "Febrer", "Març", "Abril",
    "Maig", "Juny", "Juliol", "Agost",
    "Setembre", "Octubre", "Novembre", "Desembre"
];

export default function EnrollmentLineChartComponent() {
    
    const [enrollmentMonths, setEnrollmentMonths] = useState([])

    useEffect(() => {
        getEnrollmentQuantity(setEnrollmentMonths)
    }, [])

    const options = {
        chart: {
            type: 'line'
        },
        yAxis: {
            title: {
                text: "Nombre d'inscripcions"
            }
        },
        xAxis: {
            categories: Object.keys(enrollmentMonths).map((key) => {
                return monthNames[key]
            }),
            title: {
              text: null
            },
            gridLineWidth: 1,
            lineWidth: 0
        },
        title: {
            text: "Inscripcions d'usuaris en ofertes"
        },
        colors: ['#DB4CB2'],
        series: [
            {
                name: "Usuaris inscrits",
                data: Object.values(enrollmentMonths),
            },
        ]
    };

    return (
        <div className=''>
            <HighchartsReact highcharts={Highcharts} options={options}/>
        </div>
    )
}