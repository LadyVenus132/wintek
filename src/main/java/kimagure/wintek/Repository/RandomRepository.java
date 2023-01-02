package kimagure.wintek.Repository;

import kimagure.wintek.domain.Random;
import kimagure.wintek.domain.vo.LibroGrillaVO;
import kimagure.wintek.domain.vo.LibroVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RandomRepository extends CrudRepository<Random, Long> {

    //String titulo, Integer anio, String subgenero, Integer random
    @Query("SELECT new kimagure.wintek.domain.vo.LibroGrillaVO(" +
            "r.libro.nombre, r.libro.id, r.libro.anio, r.libro.subgenero.subgenero, r.valor) " +
            "FROM Random r " +
                    "WHERE r.anio = :anio " +
                    "AND (r.id in (" +
                    "   SELECT ra.id " +
                    "   FROM Random ra " +
                    "   WHERE ra.libro.subgenero is null)" +
                    "OR r.id in (" +
                    "   SELECT rb.id " +
                    "   FROM Random rb " +
                    "   WHERE rb.libro.subgenero.genero.esLiterario = true))")
    List<LibroGrillaVO> obtenerNovelas(@Param("anio") Integer anio);

    @Query("SELECT count (r.id) FROM Random r " +
            "WHERE r.anio = :anio " +
            "AND (r.id in (" +
            "   SELECT ra.id " +
            "   FROM Random ra " +
            "   WHERE ra.libro.subgenero is null)" +
            "OR r.id in (" +
            "   SELECT rb.id " +
            "   FROM Random rb " +
            "   WHERE rb.libro.subgenero.genero.esLiterario = true))")
    Integer contarNovelas(@Param("anio") Integer anio);
}
