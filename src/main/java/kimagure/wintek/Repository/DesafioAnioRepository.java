package kimagure.wintek.repository;

import kimagure.wintek.domain.desafio.DesafioAnio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DesafioAnioRepository extends CrudRepository<DesafioAnio, Long> {

    @Query("SELECT da.anio.anio FROM DesafioAnio da" +
            " WHERE da.estado = 'OK'" +
            " AND da.desafio.anio in :anioList")
    List<Integer> obtenerAniosNoValidos(@Param("anioList") List<Integer> anioList);
}
