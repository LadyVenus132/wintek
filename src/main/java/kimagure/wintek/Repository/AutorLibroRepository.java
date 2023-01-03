package kimagure.wintek.repository;

import kimagure.wintek.domain.autor.Autor;
import kimagure.wintek.domain.autor.AutorLibro;
import kimagure.wintek.domain.dto.AutorDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorLibroRepository extends CrudRepository<AutorLibro, Long> {

    @Query("SELECT new kimagure.wintek.domain.dto.AutorDTO(al.autor.nombre, al.autor.id)" +
            " FROM AutorLibro al" +
            " WHERE al.libro.id = :libroId")
    List<AutorDTO> obtenerAutoresDTOPorLibro(@Param("libroId") Long libroId);

    @Query("SELECT al.autor.id FROM AutorLibro al" +
            " WHERE al.libro.id = :libroId")
    List<Long> obtenerAutoresIdPorLibro(@Param("libroId") Long libroId);

    @Query("SELECT al.libro.id FROM AutorLibro al" +
            " WHERE al.autor.id = :autorId")
    List<Long> obtenerLibroIdPorAutor(@Param("autorId") Long autorId);
}
