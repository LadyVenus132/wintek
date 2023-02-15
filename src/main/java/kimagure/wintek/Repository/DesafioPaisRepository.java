package kimagure.wintek.repository;

import kimagure.wintek.domain.Pais;
import kimagure.wintek.domain.desafio.DesafioPais;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DesafioPaisRepository extends CrudRepository<DesafioPais, Long> {

    @Query("SELECT count (dp.id) FROM DesafioPais dp " +
            "WHERE dp.libroCompletado.id = :libroId")
    Integer contarTitulo(@Param("libroId") Long libroId);

    @Query("SELECT count(dp.id) FROM DesafioPais dp" +
            " WHERE dp.libroCompletado.id IN :libroAutorList")
    Integer contarAutorEnDesafio(@Param("libroAutorList") List<Long> libroAutorList);

    @Query("SELECT dp FROM DesafioPais dp" +
            " WHERE dp.desafio.id = :desafioId" +
            " AND dp.libroCompletado.id = :libroId" +
            " AND dp.estado = null")
    DesafioPais obtenerDesafioNoCompletado(@Param("desafioId") Long desafioId,
                                           @Param("libroId")Long libroId);
}
