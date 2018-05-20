package ma.enset.comptebancaire.dao;

import ma.enset.comptebancaire.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client , Long>{

}
