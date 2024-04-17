<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
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
    <title>Calendario Appuntamenti</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
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
                        <td><%= prenotazione.getCliente() %></td>
                        <!-- Aggiungi altre colonne se necessario -->
                    </tr>
                <% } %>
            </tbody>
        </table>
</body>
</html>