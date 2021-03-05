import React from "react";
import Prodotto from'./Prodotto'

export default class ListaProdotti extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
          error: null,
          isLoaded: false,
          items: []
        };
    }
    
    componentDidMount() {
        fetch("http://localhost:8080/AnankeSpring/api/prodotti")
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
          return <div>Loading...</div>;
        } else {
          return (
        <div className="prodotti">           
      {items.map(item => (
                <Prodotto id={item.id} key={item.id} name={item.name}/>
              ))}
            </div> 
          );
        }
      }
    }

    
    

