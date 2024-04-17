package it.rf.salone.controller;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.rf.salone.model.Appuntamento;
import it.rf.salone.model.Cliente;
import it.rf.salone.model.Operatore;
import it.rf.salone.model.Servizio;
import it.rf.salone.service.AppuntamentoService;
import it.rf.salone.service.ClienteService;
import it.rf.salone.service.OperatoreService;
import it.rf.salone.service.ServizioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {


	
	@Autowired
	private ClienteService service4;
	@Autowired
	private OperatoreService operatoreService;
	@Autowired
	private ServizioService servizioService;
	@Autowired
	private AppuntamentoService appuntamentoservice;

	
	
	@GetMapping("/login")
    public ModelAndView showLoginForm(Model model) {
        model.addAttribute("error", ""); // Inizializzo l'attributo "error" nel model
        return new ModelAndView("login");
    }

	
	
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
	    Operatore operatore = operatoreService.getOperatoreByUsernameAndPassword(username, password);

	    if (operatore != null) {
	        // Autenticazione riuscita come operatore
	        session.setAttribute("username", username);
	        session.setAttribute("ruolo", "operatore");
	        return "redirect:/DashboardBarbiere";
	    } else if ("admin".equalsIgnoreCase(username) && "admin".equals(password)) {
	        // Utente "admin", reindirizza alla dashboard operatore
	        session.setAttribute("username", username);
	        session.setAttribute("ruolo", "operatore");
	        return "redirect:/DashboardBarbiere";
	    } else {
	        
	        Cliente cliente = service4.getClienteByUsernameAndPassword(username, password);

	        if (cliente != null) {
	            // Autenticazione riuscita come cliente
	            session.setAttribute("username", username);
	            session.setAttribute("ruolo", "cliente");
	            session.setAttribute("cliente", cliente); // Aggiungi il cliente alla sessione
	            return "redirect:/DashboardCliente";
	        } else {
	            // Autenticazione fallita
	            model.addAttribute("error", "Credenziali non valide");
	            return "login";
	        }

	        }
	    }
	

	
	
	@PostMapping("/conferma-prenotazione")
	public String confermaPrenotazione(
	        @RequestParam Operatore operatoriSelezionati,
	        @RequestParam Servizio serviziSelezionati,
	        @RequestParam LocalDate selectedDate,
	        @RequestParam LocalTime selectedTime,
	        HttpSession session,
	        RedirectAttributes redirectAttributes) {

	    // Ottieni il cliente dalla sessione
	    Cliente cliente = (Cliente) session.getAttribute("cliente");
	    
	    // Controlla se il cliente è presente nella sessione
	    if (cliente == null) {
	        // Gestisci l'errore o reindirizza a una pagina di accesso
	        // ...
	    }

	    // Costruisci l'oggetto Appuntamento
	    Appuntamento appuntamento = new Appuntamento();
	    // Imposta i valori dell'appuntamento con i dati ottenuti dai parametri della richiesta
	    appuntamento.setCodiceOrdine(UUID.randomUUID().toString());
	    appuntamento.setDataAppuntamento(selectedDate);
	    appuntamento.setOperatore(operatoriSelezionati);
	    appuntamento.setServizio(serviziSelezionati);
	    appuntamento.setOrario(selectedTime);
	    
	    appuntamento.setCodiceFiscaleCliente(cliente.getCf());

	    // Salva l'appuntamento nel database utilizzando il servizio AppuntamentoService
	    appuntamentoservice.salvaAppuntamento(appuntamento, cliente);

	    // Aggiorna la vista o esegui altre operazioni necessarie
	    redirectAttributes.addFlashAttribute("messaggio", "Prenotazione confermata con successo!");

	    return "redirect:/conferma-prenotazione";
	}


	@GetMapping("/conferma-prenotazione")
	public String mostraConfermaPrenotazione(Model model) {
	    // Puoi aggiungere eventuali dati necessari alla vista utilizzando l'oggetto Model
	    // Ad esempio, se hai bisogno di visualizzare ulteriori informazioni sulla prenotazione, puoi passarle qui

	    return "conferma-prenotazione"; // Restituisci il nome della vista da visualizzare
	}


	
	
	@GetMapping("/DashboardBarbiere")
	public String mostraDashboardBarbiere(Model model) {
	    // Aggiunge lista operatori presenti
	    List<Operatore> operatori = operatoreService.getOperatori();
	    
	    
	    model.addAttribute("operatori", operatori);
	    
	    // visualizza appuntamenti nella settimana corrente 
	    LocalDate oggi = LocalDate.now();
	    LocalDate inizioSettimana = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
	    LocalDate fineSettimana = inizioSettimana.plusDays(6);
	    List<Appuntamento> appuntamenti = appuntamentoservice.getAppuntamentiPerSettimana(inizioSettimana, fineSettimana);
	    
	    
	    
	    model.addAttribute("appuntamenti", appuntamenti);

	    // Restituisci la pagina del dashboard del barbiere
	    return "DashboardBarbiere";
	}



   
    
	
	private String determineUserRole(String username) {
        // controllo se l'utente sia un operatore o un cliente
        // Restituisco "operatore" o "cliente" a seconda della logica che ho applicato

        if (username.startsWith("admin")) {
            return "operatore";
        } else {
            return "cliente";
        }
    }
	
	
	
    
    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        

        // Termina la sessione invalidando tutti gli attributi di sessione
        sessionStatus.setComplete();

        // Reindirizza alla pagina di login
        return "redirect:/login";

    }
    
    
    
    
    @PostMapping(value = "/registrazione")
    public String insertCliente(@ModelAttribute Cliente c, HttpSession session) {
        try {
            // Controlla se l'username è "admin"
            if ("admin".equalsIgnoreCase(c.getUsername())) {
                // Controlla se l'username "admin" è già associato a un operatore
                Operatore operatore = operatoreService.getOperatoreByUsername("admin");
                if (operatore != null) {
                    // L'username "admin" è già associato a un operatore, quindi imposta il ruolo a "operatore"
                    c.setRuolo("operatore");
                } else {
                    // L'username "admin" non è ancora registrato come operatore, registra un nuovo operatore
                    Operatore nuovoOperatore = new Operatore();
                    nuovoOperatore.setUsername("admin");
                    nuovoOperatore.setRuolo("operatore");

                    // Puoi implementare la logica per settare altri attributi dell'operatore se necessario
                    // Ad esempio, nuovoOperatore.setPassword("password");

                    // Salva il nuovo operatore
                    operatoreService.salvaOperatore(nuovoOperatore);

                    // Imposta il ruolo del cliente a "operatore"
                    c.setRuolo("operatore");
                }
            } else {
                // L'username non è "admin", quindi gestisci la logica di registrazione come cliente
                c.setRuolo("cliente");
            }

            // Altri passaggi di registrazione e salvataggio del cliente...
            Integer a = (Integer) this.service4.insertCliente(c);

            ArrayList<Cliente> l = (ArrayList<Cliente>) this.service4.creaLista();
            session.setAttribute("lista", l);
            session.setAttribute("verifica", a);

            return "registrazione";
        } catch (Exception e) {
            // Gestione dell'eccezione, ad esempio, redirect a una pagina di errore
            return "errore";
        }
    }
    
    
    
    

 
	
	@GetMapping(path = "/registrazione")
    public String  nuovoCliente( HttpSession session)
    {
		ArrayList <Cliente> l =(ArrayList <Cliente>) this.service4.creaLista();
        session.setAttribute("lista", l);
        return "registrazione";
    }
	
	
	
	
	

 
    
    
    
    @RequestMapping("/Prenotazione")
    public String visualizzaServiziOperatori(Model model) {
        List<Servizio> servizi = servizioService.creaLista();
        List<Operatore> operatori = operatoreService.getOperatori();

        model.addAttribute("servizi", servizi);
        model.addAttribute("operatori", operatori);

        return "Prenotazione";
    }
    
    
    
  

    
	
    
    @GetMapping("/DashboardCliente")
    public String showDashboardCliente(Model model, HttpSession session) {
        // Ottieni il cliente dalla sessione
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        // Ottieni le prenotazioni del cliente dal service
        List<Appuntamento> prenotazioni = appuntamentoservice.getPrenotazioniByCliente(cliente);
        // Passa le prenotazioni al modello
        model.addAttribute("prenotazioni", prenotazioni);
        // Ritorna il nome della vista JSP
        return "DashboardCliente";
    }

  
    
    
    @GetMapping("/creaServizio")
    public String visualizzaPagina(Model model) {
        List<Servizio> servizi = servizioService.creaLista();
        model.addAttribute("servizi", servizi);
        return "creaServizio";
    }


    @PostMapping("/creaServizio")
    public String creaServizio(@RequestParam("nomeServizio") String nomeServizio,
                              
                               @RequestParam("prezzo") float prezzo,
                               Model model) {
        Servizio nuovoServizio = new Servizio();
        nuovoServizio.setNomeServizio(nomeServizio);
       
        nuovoServizio.setPrezzo(prezzo);
        servizioService.createServizio(nuovoServizio);
   
        // Puoi decidere dove reindirizzare dopo aver inserito il servizio
        return "redirect:/creaServizio";
    }
    

    @GetMapping
    public String home() {
        return "/login";
    }
    
    
  @GetMapping("/creaOrdine")
    public String mostraFormCreazioneOrdine(Model model) {
        List<Operatore> operatori = operatoreService.getOperatoriByRuolo("operatore");
        // Aggiungi la lista degli operatori
        model.addAttribute("operatori", operatori);
        return "creaOrdine";
    }



    @PostMapping(value = "/registrazioneOperatore")
    public String insertOperatore(@ModelAttribute Operatore o, HttpSession session) {
        Integer a;
        a=(Integer)this.operatoreService.insertOperatore(o);
        ArrayList <Operatore> li =(ArrayList <Operatore>) this.operatoreService.creaLista();
        session.setAttribute("lista", li);
        session.setAttribute("verifica", a);
              return "registrazioneOperatore";
            }

 

    @GetMapping(path = "/registrazioneOperatore")
    public String  nuovoOperatore( HttpSession session)
    {
        ArrayList <Operatore> li =(ArrayList <Operatore>) this.operatoreService.creaLista();
        session.setAttribute("lista", li);
        return "registrazioneOperatore";
    }
    
}
