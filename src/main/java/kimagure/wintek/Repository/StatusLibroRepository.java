package kimagure.wintek.repository;

import kimagure.wintek.domain.dto.LibroLecturaDTO;
import kimagure.wintek.domain.libros.StatusLibro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatusLibroRepository extends CrudRepository<StatusLibro, Long> {

    @Query("SELECT count(sl.id) FROM StatusLibro sl" +
            " WHERE sl.libro.id = :libroId")
    Integer contarCantidadLeido(@Param("libroId") Long libroId);

    @Query("SELECT new kimagure.wintek.domain.dto.LibroLecturaDTO(sl.libro.id, sl.libro.fantasia, sl.libro.anio, sl.libro.subgenero.subgenero," +
            " sl.libro.portadaUrl, sl.fechaInicio, sl.fechaFin, sl.estado)" +
            " FROM StatusLibro sl" +
            " ORDER BY sl.fechaInicio DESC")
    List<LibroLecturaDTO> obtenerTodos();
}
