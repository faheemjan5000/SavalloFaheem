<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page import="it.beije.model.*"%>
          <%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="style.css">
    <meta charset="UTF-8">
    <title>Ecomerce|registrazione</title>
    
    
    
    <style>
    body{
width: 100%;
height: 100%;
margin: 0;
padding: 0;
}
#navbar{
    width: 100%;
    height: 10%;
    display: block;
    background-color: deepskyblue;
margin:0;
align-items: center;
align-content: center;
text-align: center;
}
#conteiner{
    width: 100%;
    height: 100%;
    margin: 0%;
padding: 0;
min-height: 900px;
}
#login{
    position: absolute;
   right: 0px;
top:65px;
}
h1{
    display: inline-block;
}
#Content{
	margin-top:30px;
	align-items: center;
align-content: center;
text-align: center;
}
.prodotti{
float: left;
width: 33%;
height: 400px;
margin-top:30px;
}
    </style>
</head>
<body>
   
    <div id="conteiner"> 
           <div id="navbar">

<h1>  Ecommerce</h1>

<% 
String login= session.getAttribute("login")!=null?(String)session.getAttribute("login"):"" ;
boolean sessione= session.getAttribute("session")!=null?(boolean)session.getAttribute("session"):false;
if(!sessione&& !login.equals("ok")){
	
%>
            <div id="login"> 
                <form action="./login" method="post">
                    <input type="text" name="email" placeholder="mario@gmail.com" required>     <input type="password" name="password"  required> <button type="submit">login</button>
                </form>
              
            </div>
      <br>
      <br> 
     
          <% 
if(session.getAttribute("login")!=null){

if(login.equals("error")){
	 session.removeAttribute("login");
	out.print("<p>Attenzione utente o password sbagliata</p>");
}
}
%>
        </div>
        <% 
        }
if(session.getAttribute("login")!=null){

if(login.equals("ok")){
	Users user=(Users)session.getAttribute("users");
	out.print("<div> Benvenuto : "+user.getFirstNname()+"  "+ user.getSecondName() +"</div>");

}}


if(!login.equals("ok"))
{

%>
<h2 align="center"> Registrati| Compila tutti i campi</h2>
<div id="Content">
    <form action="./registrazione" method="post">
        <input type="text" name="email" placeholder="mario@gmail.com" required>  <br>
        <input type="text" name="name" placeholder="nome" required><br>
        <input type="text" name="surname" placeholder="cognome" required><br>
        <input type="password" name="password" placeholder="password" required><br>
         <button type="submit">Registrati</button>
    </form>
    
    <%
}

    if(session.getAttribute("registrazione")!=null&&(boolean)session.getAttribute("registrazione")==false){

    	out.print("<p>Attenzione utente già registrato si prega di riprovare</p>");
    	session.removeAttribute("registrazione");
    }
    %>
</div>

    </div>
</body>
</html>