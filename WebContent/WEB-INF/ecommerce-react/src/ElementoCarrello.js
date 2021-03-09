import React from "react";
import App from "./App";
import ReactDOM from 'react-dom';
import ListaProdotti from './ListaProdotti';
import Carrello from "./Carrello";
export default class ElementoCarrello extends React.Component {
 
    constructor(props) {
        super(props);
        this.state = {
          error: null,
          isLoaded: false,
          idO: this.props.idO,
          idP: this.props.id,
          prodotto:null,
          items:[],
          quantita: this.props.quantita,
          totale:this.props.totale
        };
        this.handleChange=this.handleChange.bind(this);
        this.deleteP=this.deleteP.bind(this);
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
              body:data,
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
    



      }

deleteP(){
    const linkdelete="http://localhost:8080/AnankeSpring/api/carrello/delete/"+this.state.idO;
    fetch(linkdelete, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
      })
      .then(response => response.json())
      .catch((error) => {
        console.error('Errore della  fetch login:'+ error);
        this.setState({
          isLoaded: true,
          error
        })


        ReactDOM.render(
            <React.StrictMode>
              <Carrello  />
            </React.StrictMode>,
            document.getElementById('Content')
          );
        

      });
      

}

    componentDidMount() {
        const linkfetch="http://localhost:8080/AnankeSpring/api/prodotto/"+this.state.idP;
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

this.setState.totale+= this.state.quantita*this.state.items.price;

      }

  render() {
    const sorgenteimg="https://picsum.photos/300/300/?blur?random"+this.props.idP;
    return(
<tr>
<img src={sorgenteimg}/>
<td>{this.state.items.name} </td>
<td>{this.state.items.desc}</td>
<td className="price">{this.state.items.price}€ </td>
<td><input type="number" onChange={this.handleChange} name="quan" min="1" max="10" value={this.state.quantita} /> </td>
<td><a href="index.html"><img onClick={this.deleteP} src="images/delete.png"></img></a></td>
</tr>
);

    }
 
    }


