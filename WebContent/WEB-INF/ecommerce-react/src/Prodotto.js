import React from "react";

import "./Prodotto.css";

export default class Prodotto extends React.Component {
 

  render() {
    const link="./prodotto?id="+this.props.id;
    const sorgenteimg="https://picsum.photos/300/300/?blur?random"+this.props.id;
return(
<div className="prodotti">
<img src={sorgenteimg}/>
<p>{this.props.name} </p>
<a href={link}>Acquista</a>

</div>


);

    }


    }
    

