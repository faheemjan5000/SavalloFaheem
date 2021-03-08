import React from "react";
import SingleProdotto from './SingleProdotto';
import "./Prodotto.css";
import ReactDOM from 'react-dom';

export default class Prodotto extends React.Component {
 
  constructor(props) {
    super(props);
    this.state = {
      idP: this.props.id
    };
    this.newRender = this.newRender.bind(this);
}
 
  newRender(){
    console.log('newRender... ', this.state.idP);

  ReactDOM.render(
    <React.StrictMode>
<SingleProdotto idP={this.state.idP}/>
    </React.StrictMode>,
    document.getElementById('Content')
  );


 }

  render() {
    const sorgenteimg="https://picsum.photos/300/300/?blur?random"+this.props.id;
return(
<div className="prodotti">
<img src={sorgenteimg}/>
<p>{this.props.name} </p>
<button type="button" onClick={this.newRender} >Acquista</button>

</div>


);

    }


    }
    

