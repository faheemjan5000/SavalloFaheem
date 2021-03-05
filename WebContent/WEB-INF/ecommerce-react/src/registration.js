import React from "react";
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

export default class Registration extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
                  name:'',
                  surname: '',
                  email: '',
                  password : null,
                 };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({name: event.target.name,
                   surname: event.target.surname,
                   email: event.target.email,
                   password: event.target.password,
                         
                  });
  }


  handleSubmit(event) {
   event.preventDefault();
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title: 'React POST Request Example' })
    };
    fetch('http://localhost:8080/SavalloFaheem/registrazione', requestOptions)
        .then(response => response.json())
        .then(data => this.setState({ name:  this.state.name,
                                      surname: this.state.surname,
                                      email: this.state.email,
                                      password: this.state.password,
        }));
    
    
   
  }



  render() {
    return (
      <div>
      <h2> Registeration form </h2> <br/>
      <form onSubmit={this.handleSubmit}>
        
          Name:<br/>
          <input type="text" name="name" onChange={this.handleChange} /> <br/>
          surname:<br/>
          <input type="text" name="surname" onChange={this.handleChange} /> <br/>
          Email:<br/>
          <input type="text" name="email" onChange={this.handleChange} /> <br/>
         Password:<br/>
          <input type="password" name="password" onChange={this.handleChange} /> <br/><br/>
        
        <input type="submit" value="Submit" />
      </form>
        </div>
    );
  }
}




