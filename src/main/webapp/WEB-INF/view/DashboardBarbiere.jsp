<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="it.rf.salone.model.Appuntamento" %>
<%@ page import="it.rf.salone.model.Operatore" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Barbiere</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <!-- Includi i tuoi file CSS o JavaScript personalizzati se necessario -->
</head>
<body>
<div class="container">
    <h2 class="mt-5">Benvenuto nel dashboard, Barbiere!</h2>

    <a href="/creaServizio">Crea nuovi servizi</a>
    &nbsp; &nbsp; &nbsp; &nbsp;
    <a href="/registrazioneOperatore">Registra un nuovo barbiere</a>
    <!-- Tabella per gli appuntamenti raggruppati per data -->
    <h3 class="mt-4">Appuntamenti</h3>
    <table class="table">
        <!-- Aggiungi gli header della tabella in base alla struttura dei tuoi dati -->
        <thead>
        <tr>
            <th>Data</th>
            <th>Ora</th>
            <th>Operatore</th>
            <th>Cliente</th>
            <!-- Aggiungi più header se necessario -->
        </tr>
        </thead>
        <tbody>
        <% 
            // Ottieni la lista degli appuntamenti dal modello
            List<Appuntamento> appuntamenti = (List<Appuntamento>) request.getAttribute("appuntamenti");
            
            // Ordina gli appuntamenti per data
            appuntamenti.sort((a1, a2) -> a1.getDataAppuntamento().compareTo(a2.getDataAppuntamento()));
            
            // Inizializza la variabile per la data precedente
            LocalDate dataPrecedente = null;

            // Itera attraverso la lista ordinata degli appuntamenti e popola le righe della tabella
            for (Appuntamento appuntamento : appuntamenti) {
                // Ottieni la data dell'appuntamento
                LocalDate dataAppuntamento = appuntamento.getDataAppuntamento();

                // Verifica se la data è diversa dalla data precedente
                if (!dataAppuntamento.equals(dataPrecedente)) {
                    // Se la data è diversa, stampa una nuova riga per la data
        %>
        <tr>
            <td><strong><%= dataAppuntamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) %></strong></td>
            <td><%= appuntamento.getOrario() %></td>
            <td><%= appuntamento.getOperatore() != null ? appuntamento.getOperatore().getNome() + " " + appuntamento.getOperatore().getCognome() : "" %></td>
            <td><%= appuntamento.getCliente() != null ? appuntamento.getCliente().getNome() + " " + appuntamento.getCliente().getCognome() : "" %></td>
            <!-- Aggiungi più colonne con i dati dell'appuntamento -->
        </tr>
        <% 
            // Aggiorna la data precedente
            dataPrecedente = dataAppuntamento;
            }
            else {
        %>
        <tr>
            <td></td> <!-- Cellula vuota per la data -->
            <td><%= appuntamento.getOrario() %></td>
            <td><%= appuntamento.getOperatore() != null ? appuntamento.getOperatore().getNome() + " " + appuntamento.getOperatore().getCognome() : "" %></td>
            <td><%= appuntamento.getCliente() != null ? appuntamento.getCliente().getNome() + " " + appuntamento.getCliente().getCognome() : "" %></td>
            <!-- Aggiungi più colonne con i dati dell'appuntamento -->
        </tr>
        <% 
            }
            } 
        %>
        <!-- Aggiungi più righe in base ai tuoi dati -->
        </tbody>
    </table>

    <a href="/login" class="btn btn-danger mt-3">Logout</a>
</div>
</body>
</html>
