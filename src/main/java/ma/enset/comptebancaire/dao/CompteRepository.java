package ma.enset.comptebancaire.dao;

import ma.enset.comptebancaire.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte , String> {

}
