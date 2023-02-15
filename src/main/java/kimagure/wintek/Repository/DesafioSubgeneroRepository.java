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

    @Query("SELECT ds FROM DesafioSubgenero ds" +
            " WHERE ds.desafio.id = :desafioId" +
            " AND ds.subgenero.id = :subgeneroId" +
            " AND ds.estado = null")
    DesafioSubgenero obtenerDesafioNoCompletado(@Param("desafioId")Long desafioId,
                                                @Param("subgeneroId")Long subgeneroId);
}
