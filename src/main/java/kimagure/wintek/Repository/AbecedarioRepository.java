package kimagure.wintek.repository;

import kimagure.wintek.domain.desafio.Abecedario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AbecedarioRepository extends CrudRepository<Abecedario, Long> {

    @Query("SELECT a.id from Abecedario a" +
            " WHERE a.letra = :letra")
    Long obtenerAbecedarioId(@Param("letra")char letra);
}
