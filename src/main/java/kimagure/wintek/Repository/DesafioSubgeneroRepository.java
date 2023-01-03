package kimagure.wintek.repository;

import kimagure.wintek.domain.desafio.DesafioSubgenero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DesafioSubgeneroRepository extends CrudRepository<DesafioSubgenero, Long> {

    @Query("SELECT ds.subgenero.subgenero FROM DesafioSubgenero ds" +
            " WHERE ds.estado is null" +
            " AND ds.desafio.anio in :anioList")
    List<String> obtenerSubgenerosValidos(@Param("anioList")List<Integer> anioList);
}
