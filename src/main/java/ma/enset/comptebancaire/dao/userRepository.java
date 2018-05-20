package ma.enset.comptebancaire.dao;

import ma.enset.comptebancaire.entities.Compte;
import ma.enset.comptebancaire.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<user, String> {

}
