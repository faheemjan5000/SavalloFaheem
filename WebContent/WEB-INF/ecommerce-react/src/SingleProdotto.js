import React from "react";
import App from "./App";
import ReactDOM from 'react-dom';
import ListaProdotti from './ListaProdotti';
export default class SingleProdotto extends React.Component {
 
    constructor(props) {
        super(props);
        this.state = {
          error: null,
          isLoaded: false,
          items: []
        };
    }
    newRender(){

        ReactDOM.render(
          <React.StrictMode>
            <ListaProdotti  />
          </React.StrictMode>,
          document.getElementById('Content')
        );
      
      
       }
    componentDidMount() {
        const linkfetch="http://localhost:8080/AnankeSpring/api/prodotto/"+this.props.idP;
        fetch(linkfetch)
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
    const sorgenteimg="https://picsum.photos/300/300/?blur?random"+this.props.idP;
    return(
    <div>
    <div className="prodotti">  
<img src={sorgenteimg}/>
<p>{this.state.items.name} </p>
<p>{this.state.items.desc}</p>
<p className="price">{this.state.items.price}</p>
<button type="button" onClick={this.newRender} >Acquista</button>

</div>
<div id="acquista">
<form>
    <label>Quantit&agrave; </label>
    <input type="hidden"  name="id" value="${prodotto.id}" />
<input type="number" name="quan" value="1" />
<button type="submit">Aggiungi all'ordine</button>

</form>
</div>
</div>



);





    }
 
    }


