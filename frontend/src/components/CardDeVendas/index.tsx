import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { ptBR } from "date-fns/locale";

import "./styles.css";
import BotaoDeNotificacao from "../BotaoDeNotificacao";
import {useEffect, useState} from "react";
import axios from "axios";
import {BASE_URL} from "../../utils/request";
import {Venda} from "../../models/venda";

function CardDeVendas() {

    const min = new Date(new Date().setDate(new Date().getDate() - 365));
    const max = new Date();

    const [minData, setMinData] = useState(min);
    const [maxData, setMaxData] = useState(max);

    const [vendas, setVendas] = useState<Venda[]>([]);

    useEffect(() => {

        const dMin = minData.toISOString().slice(0, 10);
        const dMax = maxData.toISOString().slice(0, 10);

        console.log(BASE_URL);
        axios.get(`${BASE_URL}/vendas/page?dataMin=${dMin}&dataMax=${dMax}`)
            .then(response => {
            setVendas(response.data.content);

        });

    }, [minData, maxData]);

    const teste2 = BASE_URL;

    return (
           <div className="dsmeta-card">
                <h2 className="dsmeta-sales-title">BASE_URL</h2>
                <div>
                    <div className="dsmeta-form-control-container">
                        <DatePicker
                            locale={ptBR}
                            selected={minData}
                            onChange={(date: Date) => setMinData(date)}
                            className="dsmeta-form-control"
                            dateFormat="dd/MM/yyyy"

                        />
                    </div>
                    <div className="dsmeta-form-control-container">
                        <DatePicker
                            locale={ptBR}
                            selected={maxData}
                            onChange={(date: Date) => setMaxData(date)}
                            className="dsmeta-form-control"
                            dateFormat="dd/MM/yyyy"
                        />
                    </div>
                </div>

                <div>
                    <table className="dsmeta-sales-table">
                        <thead>
                        <tr>
                            <th className="show992">ID</th>
                            <th className="show576">Data</th>
                            <th>Vendedor</th>
                            <th className="show992">Visitas</th>
                            <th className="show992">Vendas</th>
                            <th>Total</th>
                            <th>Notificar</th>
                        </tr>
                        </thead>
                        <tbody>
                        {vendas.map(obj => (
                                <tr key={obj.id}>
                                    <td className="show992">{obj.id}</td>
                                    <td className="show576">{obj.data}</td>
                                    <td>{obj.vendedor}</td>
                                    <td className="show992">{obj.visita}</td>
                                    <td className="show992">{obj.vendas}</td>
                                    <td>R$ {obj.total},00</td>
                                    <td>
                                        <div className="dsmeta-red-btn-container">
                                            <div className="dsmeta-red-btn">
                                                <BotaoDeNotificacao vendaId={obj.id}/>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            )
                        )}
                        </tbody>
                    </table>
                </div>

            </div>
       )
}

export default CardDeVendas;
