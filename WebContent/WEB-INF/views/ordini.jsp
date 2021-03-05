<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="it.beije.model.*"%>
        <%@page import="java.util.*"%>
             <%@page import="java.text.*"%>
                  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <link rel="stylesheet" href="/WebContent/WEB-INF/views/style.css">
    <meta charset="UTF-8">
     <title>Carrello</title>
     
     
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

<h1> Ecommerce</h1>

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
      <a style="position:absolute; right:0px;" href="./registrazione">registrati</a>
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
%>
  <a style="position:absolute; right:0px;" href="./logout">Logout</a>
	<%
	}}
%>
</div>
<div id="Content">


  <c:choose>
    <c:when test = "${ordini != null}">	
       <table>
   <tr>
   <th> Numero ordine</th>
   <th> Totale ordine </th>
      <th> Stato ordine </th>
   		<c:forEach var = "i" items="${ordini}">
      </tr>
      <td>${i.id}</td>
       <td>${i.amount}</td>
        <td>${i.state}</td>
      <tr>
      </tr>
      </c:forEach>
   </table>
    </c:when>

    <c:otherwise>
      
    </c:otherwise>
</c:choose>
  

</div>
</body>
</html>