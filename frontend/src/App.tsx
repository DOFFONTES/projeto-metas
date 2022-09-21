import Cabecalho from "./components/Cabecalho";
import CardDeVendas from "./components/CardDeVendas";
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {

  return (
      <>
          <ToastContainer />
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
