<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.ArrayList"
    %>
     <%@ page import="it.rf.salone.model.Operatore" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Registrazione Operatore</title>
</head>
<body>




<table align=center>

<th colspan=4><a href="/DashboardBarbiere">HOME</a></th>



<tr><td colspan=4>REGISTRA UN NUOVO OPERATORE:</td></tr>
<tr>
<td colspan=4>
<form action="/registrazioneOperatore" method="post">
       NOME:<br><input type=text name="nome" placeholder="inserisci il nome" required /> <br>
      COGNOME:<br><input type=text name="cognome" placeholder="inserisci il cognome" required /> <br>
     CODICE FISCALE:<br><input type=text name="cf" placeholder="inserisci il codice fiscale" required /> <br>
     USERNAME:<br><input type=text name="username" placeholder="inserisci l'username" required /> <br>
     PASSWORD:<br><input type=text name="password" placeholder="inserisci la password" required /> <br>
     <input type=submit value=REGISTRA ><br>

</form>

     <tr>
       <th colspan="4">

   <% Integer a=(Integer)session.getAttribute("verifica");

   if ( a != null) {
       if (a == 1) {
%>
     <%="Operatore registrato"%>
<%
 } else if (a == 0) {
%>
     <%="L'operatore è già stato registrato"%>
<%
 }
}
request.getSession(false);

// Verifica se la sessione esiste prima di invalidarla
if (session != null) {
// Invalida la sessione
session.invalidate();
}

   %>

   </th>
  </tr>






</table>


</body>
</html>