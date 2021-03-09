import React from "react";
import ReactDOM from 'react-dom';
import Carrello from "./Carrello";

export default class LoginComponent extends React.Component {
 

    constructor(props) {
        super(props);
        this.state = {
          error: null,
          isLoaded: false,
         user:this.props.user,
          email:'',
          password:''
        };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.apriCarrelo=this.apriCarrelo.bind(this);
    }
    
    handleChange(event) {
      const target = event.target;
      if(target.name==="email"){
        console.log("000cambiamento email");
      this.setState({email: event.target.value});
      }
      else
      this.setState({password: event.target.value});
    }
 
    handleSubmit(event) {
      const utente={
        id:null,
        email:this.state.email,
        password:this.state.password,
        firstName:'',
        secondName:'',
      }
      const data= JSON.stringify(utente);
      console.log(data);
      fetch('http://localhost:8080/AnankeSpring/api/login/user', {
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
        event.preventDefault();

      });

    }


apriCarrelo(){

  ReactDOM.render(
    <React.StrictMode>
<Carrello />
    </React.StrictMode>,
    document.getElementById('Content')
  );

}

      render() {
        const { error } = this.state;
        if (error) {
          return (
            <form onSubmit={this.handleSubmit}>
        <label>
          Nome:
          <input
            type="text"
            value={this.state.email}
            onChange={this.handleChange}
          />
        </label>
        <label>
          Password:
          <input
            type="password"
            value={this.state.password}
            onChange={this.handleChange}
          />
        </label>
        <input type="submit" value="Submit" />
        <p>Attenzione email o password errataa</p>
      </form>
            );
      }else{ 
        if(this.state.user!=null&&this.state.user.id!=null)
        if(this.state.password===this.state.user.password)
        return(
<div>
Benvenuto {this.user.firstName}   {this.user.secondName}
</div>
      );else return(
        <div id="login">
        <form onSubmit={this.handleSubmit}>
        <label>
          Nome:
          <input
            type="text"
            name="email"
            value={this.state.email}
            onChange={this.handleChange}
          />
        </label>
        <label>
          Password:
          <input
            type="password"
            name="password"
            value={this.state.password}
            onChange={this.handleChange}
          />
        </label>
        <input type="submit" value="Submit" />
      </form>
      </div>);
            else{
              return (
                <div id="login">
                <form onSubmit={this.handleSubmit}>
              <label>
          Email:
          <input
            type="text"
            name="email"
            value={this.state.email}
            onChange={this.handleChange}
          />
        </label>
        <label>
          Password:
          <input
            type="password"
            name="password"
            value={this.state.password}
            onChange={this.handleChange}
          />
        </label>
            <input type="submit" value="Login" />
            <button type="button" onClick={this.apriCarrelo}> <img src="images/cart.png"></img> </button>
          </form>
         
             </div>
            );
               
            }

    
    
  }
  }
}