import React, { useState, useEffect } from 'react';
import Highcharts from 'highcharts';
import HighchartsReact from 'highcharts-react-official';

import exporting from 'highcharts/modules/exporting'; // Importa el módulo de exportación
import exportData from 'highcharts/modules/export-data'; // Importa el módulo de exportación de datos

import { getUsersQuantity } from '../../../../axios/reports/ReportsRequests';

// Inicializa los módulos de exportación
exporting(Highcharts);
exportData(Highcharts);


export default function UsersBarChartComponent() {

    const [usersQuantity, setUsersQuantity] = useState([])

    useEffect(() => {
        getUsersQuantity(setUsersQuantity)
    }, [])

    const options = {
        chart: {
            type: 'bar'
        },
        xAxis: {
            categories: ["Quantitat d'usuaris"],
            title: {
              text: null
            },
            gridLineWidth: 1,
            lineWidth: 0
        },
        title: {
            text: "Usuaris totals"
        },
        colors: ['#7D3AC1', '#DB4CB2', '#EA7369'],
        series: usersQuantity
    };

    return (
        <div className=''>
            <HighchartsReact highcharts={Highcharts} options={options}/>
        </div>
    )
}