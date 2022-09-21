import Imagem from "../../assets/img/icone-de-notificacao.svg"

import "./styles.css"
import axios from "axios";
import {BASE_URL} from "../../utils/request";
import {toast} from "react-toastify";


type Props = {
    vendaId: number;
}

const BotaoDeNotificacao = ( { vendaId } : Props )  => {

  function pegaClick(id: number){

      console.log(id);

      axios.get(`${BASE_URL}/vendas/${id}/notificacao`)
          .then( () => {
              toast.info("SMS enviado com sucesso");
          });
  }

  return (
      <div className="dsmeta-red-btn" onClick={() => pegaClick(vendaId) }>
        <img src={Imagem} alt="teste"/>
      </div>
  )
}

export default BotaoDeNotificacao;
