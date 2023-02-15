package kimagure.wintek.repository;

import kimagure.wintek.domain.recomendacion.Random;
import kimagure.wintek.domain.dto.LibroGrillaDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RandomRepository extends CrudRepository<Random, Long> {

    @Query("SELECT new kimagure.wintek.domain.dto.LibroGrillaDTO(" +
            "r.libro.nombre, r.libro.id, r.libro.anio, r.libro.sagaNumero, r.libro.subgenero.subgenero, r.libro.portadaUrl, r.valor) " +
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
    List<LibroGrillaDTO> obtenerNovelas(@Param("anio") Integer anio);

    @Query("SELECT new kimagure.wintek.domain.dto.LibroGrillaDTO(" +
            "r.libro.nombre, r.libro.id, r.libro.anio, r.libro.sagaNumero, r.libro.portadaUrl, r.valor)" +
            " FROM Random r" +
            " WHERE r.anio = :anio" +
            " AND r.libro.subgenero is null")
    List<LibroGrillaDTO> obtenerSinGenero(@Param("anio") Integer anio);

    @Query("SELECT new kimagure.wintek.domain.dto.LibroGrillaDTO(" +
            "r.libro.nombre, r.libro.id, r.libro.anio, r.libro.sagaNumero, r.libro.subgenero.subgenero, r.libro.portadaUrl, r.valor)" +
            " FROM Random r" +
            " WHERE r.anio = :anio" +
            " AND r.libro.subgenero.genero.esLiterario = true")
    List<LibroGrillaDTO> obtenerSoloNovelas(@Param("anio") Integer anio);

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

    @Query("SELECT new kimagure.wintek.domain.dto.LibroGrillaDTO(" +
            "r.libro.nombre, r.libro.id, r.libro.anio, r.libro.sagaNumero, r.libro.subgenero.subgenero, r.libro.portadaUrl, r.valor) " +
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
    List<LibroGrillaDTO> obtenerNovelasTop(@Param("anio") Integer anio, Pageable top);
}
