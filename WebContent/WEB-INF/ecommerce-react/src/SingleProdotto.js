import React from "react";
import App from "./App";
import ReactDOM from 'react-dom';
import ListaProdotti from './ListaProdotti';
import Carrello from "./Carrello";
export default class SingleProdotto extends React.Component {
 
    constructor(props) {
        super(props);
        this.state = {
          error: null,
          isLoaded: false,
          items: [],
          quantita: 1,
          idP: this.props.idP
        };
        this.handleChange=this.handleChange.bind(this);
        this.handleSubmit=this.handleSubmit.bind(this);
    }
    newRender(){

        ReactDOM.render(
          <React.StrictMode>
            <ListaProdotti  />
          </React.StrictMode>,
          document.getElementById('Content')
        );
      
      
       }


       handleChange(event) {
        const target = event.target;
            this.setState({quantita: event.target.value});
      }


      handleSubmit(event) {
          
          console.log("sono nel submit...");
        const ordine={
          id:null,
          productID: this.state.idP,
        quantity:this.state.quantita,
        }
        const data= JSON.stringify(ordine);
        console.log(data);
        fetch('http://localhost:8080/AnankeSpring/api/carrello', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: data,
        })
        .then(response => response.json())
        .then(data => {
          this.setState.user=data;
        })
        .catch((error) => {
          console.error('Errore della  fetch login:'+ error);
          this.setState({
            isLoaded: true,
            error
          })
  
        });
        
        event.preventDefault();
        ReactDOM.render(
            <React.StrictMode>
              <Carrello  />
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
<p className="price">{this.state.items.price}€</p>
<button type="button" onClick={this.newRender} >Torna alla lista dei prodotti</button>

</div>
<div id="acquista">
<form onSubmit={this.handleSubmit}>
    <label>Quantit&agrave; </label>
    <input type="hidden"  name="id" value="${prodotto.id}" />
<input type="number" onChange={this.handleChange} name="quan" min="1" max="10" value={this.state.quantita} />
<input type="submit" value="Aggiungi all'ordine"></input>

</form>
</div>
</div>
);


    }
 
    }


