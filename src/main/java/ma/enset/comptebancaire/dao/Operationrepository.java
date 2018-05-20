package ma.enset.comptebancaire.dao;

import ma.enset.comptebancaire.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Operationrepository extends JpaRepository<Operation ,Long> {
    @Query("SELECT o from Operation o where o.compte.codeCompte=:x order by o.dateOperation desc ")
    public Page<Operation> listOperation(@Param("x") String code, Pageable pageable);

}
