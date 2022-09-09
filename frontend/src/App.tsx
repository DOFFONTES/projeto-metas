import BotaoDeNotificacao from "./components/BotaoDeNotificacao";
import Cabecalho from "./components/Cabecalho";
import CardDeVendas from "./components/CardDeVendas";

function App() {

  return (
      <>
          <Cabecalho/>
          <main>
              <section id="vendas">
                  <div className="dsmeta-container">
                      <CardDeVendas />
                  </div>
              </section>
          </main>
      </>
  )
}

export default App
