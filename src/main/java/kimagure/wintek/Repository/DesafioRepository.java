package kimagure.wintek.repository;

import kimagure.wintek.domain.desafio.Desafio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DesafioRepository extends CrudRepository<Desafio, Long> {

    @Query("SELECT d.id FROM Desafio d" +
            " WHERE d.anio IN :anioList" +
            " AND d.tipo = :tipo")
    List<Long> obtenerDesafioIdPorAnioTipo(@Param("anioList") List<Integer> anioList,
                                              @Param("tipo") String tipo);

    @Query("SELECT d FROM Desafio d" +
            " WHERE d.anio is null" +
            " OR d.anio = :anio")
    List<Desafio> obtenerDesafiosAnioPermanentes(@Param("anio") Integer anio);
}
