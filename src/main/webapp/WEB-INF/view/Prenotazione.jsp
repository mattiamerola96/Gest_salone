<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.rf.salone.model.Operatore" %>
<%@ page import="it.rf.salone.model.Servizio" %>
<%@ page import="it.rf.salone.model.FasciaOraria" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%
    ArrayList<Operatore> operatori = (ArrayList<Operatore>) request.getAttribute("operatori");
    ArrayList<Servizio> servizi = (ArrayList<Servizio>) request.getAttribute("servizi");
    
%>

<!DOCTYPE html>
<html>
<head>
    <title>Prenotazione</title>
    <style>
        table {
            border-collapse: collapse;
            width: 50%;
            margin: 20px auto;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        p {
            margin: 20px auto;
        }
    </style>
</head>
<body>

<form action="/conferma-prenotazione" method="post" id="confermaPrenotazioneForm">

    <!-- Servizi Disponibili -->
    <table align="center">
        <tr>
            <th colspan="4"><a href="/DashboardCliente">HOME</a></th>
        </tr>
        <tr>
            <th colspan="3">SERVIZI DISPONIBILI :</th>
        </tr>
        <tr>
            <th>NOME</th>
            <th>PREZZO</th>
            
            <th>SELEZIONA</th>
        </tr>
        <% 
        if (servizi != null && !servizi.isEmpty()) {
            for (Servizio s : servizi) { 
        %>
            <tr>
                <td><%= s.getNomeServizio()%></td>
                <td><%= s.getPrezzo()%></td>
                
                <td><input type="checkbox" name="serviziSelezionati" value="<%= s.getIdServizio() %>"></td>
            </tr>
        <% 
            }
        } else { 
        %>
            <tr>
                <td colspan="4">Nessun servizio disponibile</td>
            </tr>
        <% } %>
    </table>
    
<%
    String operatoreCf = ""; // Inizializzala con il valore desiderato, ad esempio recuperato dalla lista degli operatori
   
%>



<!-- Operatori Disponibili -->
<table align="center">
    <tr>
        <th colspan="3">OPERATORI DISPONIBILI :</th>
    </tr>
   <%
if (operatori != null && !operatori.isEmpty()) {
    for (Operatore singoloOperatore : operatori) { 
    	System.out.println(singoloOperatore.getCf());
%>
    <tr>
        <td><%= singoloOperatore.getNome() %> <%= singoloOperatore.getCognome() %></td>
        <td><%= singoloOperatore.getCf() %></td>
        <td><input type="checkbox" name="operatoriSelezionati" value="<%= singoloOperatore.getCf() %>"></td>
    </tr>
<%
    }
} else { 
%>

    <tr>
        <td colspan="3">Nessun operatore disponibile</td>
    </tr>
<% } %>

</table>


<label for="date">Seleziona una data:</label>
<select name="selectedDate" id="date">
    <option value="2024-01-30">30 Gennaio 2024</option>
    <option value="2024-01-31">31 Gennaio 2024</option>
    <!-- Aggiungi altre date come opzioni qui -->
</select>
<br><br>

<label for="time">Seleziona un orario:</label>
<select name="selectedTime" id="time">
    <!-- Le opzioni verranno generate dinamicamente qui -->
</select>
<br><br>

<script>
    // Funzione per generare le opzioni del menu a tendina per una settimana
    function generateDropdownOptions() {
        var selectDate = document.getElementById("date");
        var selectTime = document.getElementById("time");
        selectDate.innerHTML = ""; // Pulisce le opzioni precedenti
        selectTime.innerHTML = ""; // Pulisce le opzioni precedenti

        // Ottiene la data di oggi
        var today = new Date();
        
        // Genera opzioni per una settimana (7 giorni)
        for (var i = 0; i < 7; i++) {
            var date = new Date(today);
            date.setDate(today.getDate() + i); // Incrementa la data di un giorno
            var optionDate = document.createElement("option");
            optionDate.value = formatDate(date);
            optionDate.textContent = formatDate(date, "dd MMMM yyyy");
            selectDate.appendChild(optionDate);
        }
        
        // Aggiunge un listener per l'evento "change" sul menu della data
        selectDate.addEventListener("change", function() {
            generateTimeOptions(this.value); // Genera le opzioni per l'orario quando viene selezionata una data
        });
        
        // Genera le opzioni iniziali per l'orario
        generateTimeOptions(selectDate.value);
    }

    // Funzione per generare le opzioni del menu a tendina per l'orario
    function generateTimeOptions(selectedDate) {
        var selectTime = document.getElementById("time");
        selectTime.innerHTML = ""; // Pulisce le opzioni precedenti

        // Genera opzioni per l'orario (esempio)
        var times = ["09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00"];
        times.forEach(function(time) {
            var optionTime = document.createElement("option");
            optionTime.value = time;
            optionTime.textContent = time;
            selectTime.appendChild(optionTime);
        });
    }

    // Funzione per formattare la data nel formato desiderato
    function formatDate(date, format) {
        var options = {
            year: "numeric",
            month: "2-digit",
            day: "2-digit"
        };

        // Formatta la data secondo il formato specificato
        return date.toISOString().slice(0, 10);
    }

    // Chiama la funzione al caricamento della pagina
    generateDropdownOptions();
</script>



   

    <!-- Conferma Prenotazione -->
    <input type="submit" name="submitButton" value="Conferma Prenotazione">

</form>

</body>
</html>
