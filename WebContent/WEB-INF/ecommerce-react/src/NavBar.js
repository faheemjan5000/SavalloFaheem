import React from "react";
import ReactDOM from 'react-dom';
import LoginComponent from './LoginComponent';
export default class NavBar extends React.Component {
 

    constructor(props) {
        super(props);
        this.state = {
          error: null,
          isLoaded: false,
          userss:null,
        };
    }
    componentDidMount() {
      console.log("User prima");
      console.log(this.state.userss);
      fetch("http://localhost:8080/AnankeSpring/api/session/user")
        .then(res => res.json())
        .then(
          (result) => {
              console.table("utente: "+result);
              console.log("fetch session corretta");

            this.setState({
              isLoaded: true,
              userss: result

            });
          },
          // Note: it's important to handle errors here
          // instead of a catch() block so that we don't swallow
          // exceptions from actual bugs in components.
          (error) => {
            console.table("errore fetch sessione"+error);

            this.setState({
              isLoaded: true,
              error
            });
          }
        )
        console.log("User dopo");
        console.log(this.state.userss);
        console.log(this.state.id);

    }
      render() {
        const { error, isLoaded, userss } = this.state;
        if (error) {
          return (
            <div id="navbar">

            <h1>  Ecommerce</h1>
            </div>


          );
        } else if (!isLoaded) {
          return <div>Loading...</div>;
        } else {
          
          if(userss.id!=undefined ){
            console.log("Sono dentro userssid!)null risultato: "+userss.id==undefined);
            return (
              <div id="navbar">
  
              <h1>  Ecommerce</h1>
              <LoginComponent user={userss} />
              </div>
            );
          }else{
            console.log("Sddd risultato: "+userss.id==undefined);
            return (
            

              <div id="navbar">
    <LoginComponent user={null} />
              <h1>  Ecommerce</h1>
              </div>
              
            );
          }
        
      
      }

    }
    
  }
    ReactDOM.render(
      <React.StrictMode>
        <NavBar />
        
      </React.StrictMode>,
      document.getElementById('topNav')
    );