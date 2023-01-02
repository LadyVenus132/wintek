package kimagure.wintek.Repository;

import kimagure.wintek.domain.autor.AutorLibro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorLibroRepository extends CrudRepository<AutorLibro, Long> {

    @Query("SELECT al.autor.nombre FROM AutorLibro al " +
            "WHERE al.libro.id = :libroId")
    List<String> obtenerAutoresPorLibro(@Param("libroId") Long libroId);
}
