package kimagure.wintek.repository;

import kimagure.wintek.domain.desafio.DesafioAutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DesafioAutorRepository extends CrudRepository<DesafioAutor, Long> {

    @Query("SELECT count(da.id) FROM DesafioAutor da" +
            " WHERE da.autor.id = :autorId")
    Integer contarAutorEnDesafio(@Param("autorId") Long autorId);

    @Query("SELECT da from DesafioAutor da" +
            " WHERE da.desafio.id = :desafioId" +
            " AND da.autor.id = :autorId" +
            " AND da.estado = null")
    DesafioAutor obtenerDesafioNoCompletado(@Param("desafioId") Long desafioId,
                                            @Param("autorId") Long autorId);
}
