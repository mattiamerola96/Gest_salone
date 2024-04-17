<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="it.rf.salone.model.Cliente" %>
<%@ page import="it.rf.salone.model.Appuntamento" %>

<%
    // Non è necessario ottenere il servizio AppuntamentoService dal contesto della richiesta
    // Utilizza direttamente il servizio già iniettato nel controller
    List<Appuntamento> prenotazioni = (List<Appuntamento>) request.getAttribute("prenotazioni");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Cliente</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2 class="mt-5">Benvenuto nel dashboard, Cliente!</h2>
        <!-- Aggiungi qui il contenuto del dashboard -->
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Data</th>
                    <th scope="col">Orario</th>
                    <!-- Aggiungi altre colonne se necessario -->
                </tr>
            </thead>
            <tbody>
                <!-- Itera attraverso le prenotazioni del cliente e visualizzale nella tabella -->
                <% for (Appuntamento prenotazione : prenotazioni) { %>
                    <tr>
                        <td><%= prenotazione.getDataAppuntamento() %></td>
                        <td><%= prenotazione.getOrario() %></td>
                        <!-- Aggiungi altre colonne se necessario -->
                    </tr>
                <% } %>
            </tbody>
        </table>
        <a href="/Prenotazione">Prenotati!</a>
        <br>
        <a href="/login" class="btn btn-danger mt-3">Logout</a>
    </div>
</body>
</html>
