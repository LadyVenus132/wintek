package kimagure.wintek.repository;

import kimagure.wintek.domain.desafio.Anio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnioRepository extends CrudRepository<Anio, Long> {

    @Query("SELECT a.anio FROM Anio a")
    List<Integer> obtenerTodosAnios();

    @Query("SELECT a.id FROM Anio a" +
            " WHERE a.anio = :anio")
    Long obtenerAnioId(@Param("anio") Integer anio);
}
