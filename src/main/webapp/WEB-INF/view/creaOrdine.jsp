<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="it.rf.salone.model.Operatore" %>
<%@ page import="it.rf.salone.service.OperatoreService" %>

 
 
 <!-- potrebbe non essere ottimale ma mi dava errori (da ricordare ) -->
<%
    OperatoreService operatoreService = new OperatoreService();
    List<Operatore> operatori = operatoreService.getOperatoriByRuolo("operatore");
    request.setAttribute("operatori", operatori);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Creazione Ordine</title>
</head>
<body>
    <form action="/creaOrdine" method="post">
        <!-- Altri campi del form -->

        <label for="operatore">Seleziona l'Operatore:</label>
        <select name="operatore" id="operatore">
            <% for (Operatore operatore : operatori) { %>
                <option value="<%= operatore.getCf() %>">
                    <%= operatore.getNome() %> <%= operatore.getCognome() %>
                </option>
            <% } %>
        </select>

        <button type="submit">Crea Ordine</button>
    </form>
</body>
</html>