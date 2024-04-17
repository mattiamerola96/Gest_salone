<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.rf.salone.model.Operatore" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalTime" %>
<%
    ArrayList<Operatore> operatori = (ArrayList<Operatore>) request.getAttribute("operatori");
    
%>

<!DOCTYPE html>
<html>
<head>
    <title>Visualizza Orari Operatori</title>
</head>
<body>

<h2>Orari degli Operatori per ogni Ora</h2>

<table border="1">
    <thead>
        <tr>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Orari Disponibili</th>
        </tr>
    </thead>
    <tbody>
        <% for (Operatore operatore : operatori) { %>
            <tr>
                <td><%= operatore.getNome() %></td>
                <td><%= operatore.getCognome() %></td>
                <td>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Ora</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (LocalTime ora : operatore.getOrariPerOra()) { %>
                                <tr>
                                    <td><%= ora %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </td>
            </tr>
        <% } %>
    </tbody>
</table>

</body>
</html>
