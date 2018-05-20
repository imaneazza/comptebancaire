package ma.enset.comptebancaire.dao;

import ma.enset.comptebancaire.entities.Compte;
import ma.enset.comptebancaire.entities.Operation;
import org.springframework.data.domain.Page;

public interface IBanqueMetier {
    public Compte consulterCompte(String code);
    public void verser(String compte, double montant);
    public void retirer(String compte, double montant);
    public void virement(String codecp1, String codecp2, double montant);
    public Page<Operation> listOperation(String code, int page, int size);


}
