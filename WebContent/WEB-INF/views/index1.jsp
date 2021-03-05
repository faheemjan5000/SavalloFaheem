<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="it.beije.model.*"%>
        <%@page import="java.util.*"%>
          <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
   <link href="<c:url value="/WEB-INF/resources/style.css" />" rel="stylesheet">
    <meta charset="UTF-8">
     <title>Ecommerce</title>
     
     
     
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
	out.print("<div> Benvenuto : "+user.getFirstNname()+"  "+ user.getSecondName() +" <a href=\"./ordini\">I miei Ordini </a> <a href=\"./carrello\"><img src=\"./WebContent/WEB-INF/img/carrello.png\" width=\"30px\" </a></div>");
%>
  <a style="position:absolute; right:0px;" href="./logout">Logout</a>
	<%
	}}
%>
<div id="Content">
<c:if test = "${prodotti != null}">

<c:forEach var = "i" items="${prodotti}">
<div class="prodotti">
<img src="https://picsum.photos/300/300/?blur?random=${i.id}">
<p>${i.name} </p>
<a href="./prodotto?id=${i.id}">Acquista</a>

</div>

</c:forEach>

</c:if>




</div>
    </div>
</body>
</html>