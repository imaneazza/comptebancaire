package ma.enset.comptebancaire.dao;

import ma.enset.comptebancaire.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private Operationrepository operationrepository;
    @Override
    public Compte consulterCompte(String code) {
        Compte cp=compteRepository.findById(code).get();
        if(cp==null)throw new RuntimeException("Compte Introuvable");
        return cp;
    }

    @Override
    public void verser(String compte, double montant) {
    Compte cp=consulterCompte(compte);
        Versement v=new Versement(new Date(),montant,cp);
        operationrepository.save(v);
        cp.setSolde(cp.getSolde()+montant);
        compteRepository.save(cp);

    }

    @Override
    public void retirer(String compte, double montant) {
        Compte cp=consulterCompte(compte);
        double facility=0;
        if(cp instanceof CompteCourant)
            facility=((CompteCourant) cp).getDecouvert();
        if(cp.getSolde()+facility<montant)
            throw new RuntimeException("Solde Insuffisant");
        Retrait r=new Retrait(new Date(),montant,cp);
        operationrepository.save(r);
        cp.setSolde(cp.getSolde()-montant);
        compteRepository.save(cp);

    }

    @Override
    public void virement(String codecp1, String codecp2, double montant) {
       if(codecp1.equals(codecp2))
           throw new RuntimeException("IMPOSSIBLE VIREMENT SUR LE MEME COMPTE ");
        retirer(codecp1,montant);
        verser(codecp2,montant);
    }

    @Override
    public Page<Operation> listOperation(String code, int page, int size) {
         return operationrepository.listOperation
                (code,new PageRequest(page,size));
    }
}
