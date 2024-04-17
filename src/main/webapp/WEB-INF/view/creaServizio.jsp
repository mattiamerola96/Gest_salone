<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.rf.salone.model.Servizio" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.Duration" %>
<html>
<head>
    <title>Inserisci Nuovo Servizio</title>
    <style>
        table {
            border-collapse: collapse;
            width: 50%;
            margin: auto;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
    </style>
</head>
<body>

<table>
    <tr>
        <th colspan="3">
            <a href="/DashboardBarbiere">HOME</a>
        </th>
    </tr>
    <tr>
        <th colspan="3">
            SERVIZI DISPONIBILI :
        </th>
    </tr>
    <tr>
        <th>NOME</th>
        <th>PREZZO</th>
       
    </tr>
    <% ArrayList<Servizio> lista=(ArrayList<Servizio>)request.getAttribute("servizi");
    if(lista!=null && !lista.isEmpty()) {
        for(Servizio s: lista){
    %>
    <tr>
        <td><%= s.getNomeServizio()%> </td>
        <td><%= s.getPrezzo() %> </td>

    </tr>
    <% } } %>

    <!-- Spazio -->
    <tr>
        <td colspan="3">&nbsp;</td>
    </tr>

    <tr>
        <th colspan="3">
            <h2>Inserisci Nuovo Servizio:</h2>

            <form action="/creaServizio" method="post">
                <label for="nomeServizio">Nome Servizio:</label>
                <input type="text" id="nomeServizio" name="nomeServizio" required><br>

                <label for="prezzo">Prezzo:</label>
                <input type="number" id="prezzo" name="prezzo" step="0.01" required><br>

                <input type="submit" value="Inserisci Servizio">
            </form>
        </th>
    </tr>
</table>

</body>
</html>