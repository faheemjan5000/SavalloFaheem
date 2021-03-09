import React from "react";
import ElementoCarrello from "./ElementoCarrello";
import Prodotto from'./Prodotto'

export default class Carrello extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          error: null,
          isLoaded: false,
          items: [],
totale:0,
    };
    
    }
    
    componentDidMount() {
        this.setState({});
        fetch("http://localhost:8080/AnankeSpring/api/carrello/get")
          .then(res => res.json())
          .then(
            (result) => {
                console.table(result);
              this.setState({
                isLoaded: true,
                items: result
              });
            },
            // Note: it's important to handle errors here
            // instead of a catch() block so that we don't swallow
            // exceptions from actual bugs in components.
            (error) => {
              this.setState({
                isLoaded: true,
                error
              });
            }
          )
      }
    
    


      
      render() {
        const { error, isLoaded, items } = this.state;
        if (error) {
          return <div>Attenzione : {error.message}</div>;
        } else if (!isLoaded) {
          return( <div id="myProgress">
          <div id="myBar"></div>
        </div>);
        } else {
          return (
<table>
    <tr>
<th></th>
<th>Nome Prodotto</th>
<th> Descrizione Prodotto</th>
<th> Prezzo Unitario Prodotto</th>
<th> Quantità</th>
<th> Elimina</th>
</tr>

            {items.map(item => (
                <ElementoCarrello totale={this.state.totale} idO={item.id} id={item.productID} quantita={item.quantity} key={item.id}/>
              ))}
<tfoot>
<tr>
    <td colSpan={4}>Totale Ordine</td>
    <td>{this.state.totale}</td>
</tr>
<tr>
    <td colSpan={5}><button type="button">Finalizza l'ordine</button></td>
   
</tr>
</tfoot>
</table>          );
        }
      }
    }

    
    

