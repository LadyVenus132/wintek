package kimagure.wintek.repository;

import kimagure.wintek.domain.libros.StatusLibro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StatusLibroRepository extends CrudRepository<StatusLibro, Long> {

    @Query("SELECT count(sl.id) FROM StatusLibro sl" +
            " WHERE sl.libro.id = :libroId")
    Integer contarCantidadLeido(@Param("libroId") Long libroId);
}
