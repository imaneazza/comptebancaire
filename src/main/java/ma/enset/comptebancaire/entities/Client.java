package ma.enset.comptebancaire.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Client implements Serializable{
    @Id @GeneratedValue
    private Long code;
    private String nom ;
    @OneToMany(mappedBy = "client" , fetch = FetchType.LAZY)
    private Collection<Compte> comptes;
   public Client(){

    }

    public Client(String nom) {
        this.nom = nom;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Collection<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(Collection<Compte> comptes) {
        this.comptes = comptes;
    }
}
