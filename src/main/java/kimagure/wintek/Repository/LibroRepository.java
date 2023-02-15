package kimagure.wintek.repository;

import kimagure.wintek.domain.libros.Libro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LibroRepository extends CrudRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l" +
            " WHERE l.id = :id")
    Libro obtenerPorId(@Param("id") Long id);
}
