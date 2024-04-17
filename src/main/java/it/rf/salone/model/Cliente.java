package it.rf.salone.model;


import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="cliente" )
public class Cliente {

    @Column
    private String nome;

    @Column
    private String cognome;

    @Id
    @Column
    private String cf;

    @Column
    private String ruolo;

    @Column
    private String username;

    @Column
    private String password;


    @OneToMany(mappedBy = "cliente")
    private List<Appuntamento> appuntamento    ;


        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCognome() {
            return cognome;
        }

        public void setCognome(String cognome) {
            this.cognome = cognome;
        }

        public String getCf() {
            return cf;
        }

        public void setCf(String cf) {
            this.cf = cf;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }



        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }

 public List<Appuntamento> getAppuntamento() {
    return appuntamento;
}
 
 public void setAppuntamento(List<Appuntamento> appuntamento) {
    this.appuntamento = appuntamento;
}
 
public String getRuolo() {
    return ruolo;
}
public void setRuolo(String ruolo) {
    this.ruolo = ruolo;
}




}