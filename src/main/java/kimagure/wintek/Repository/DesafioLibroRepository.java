package kimagure.wintek.repository;

import kimagure.wintek.domain.desafio.DesafioLibro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DesafioLibroRepository extends CrudRepository<DesafioLibro, Long> {

    @Query("SELECT dl.id FROM DesafioLibro dl" +
            " WHERE dl.desafio.tipo = 'Libro Importante'")
    List<Long> obtenerIdDesafioImportante();

    @Query("SELECT count (dl.id) FROM DesafioLibro dl" +
            " WHERE dl.libro.id = :libroId")
    Integer contarTitulo(@Param("libroId") Long libroId);

    @Query("SELECT count(dl.id) FROM DesafioLibro dl" +
            " WHERE dl.id IN :idDesafioList" +
            " AND dl.libro.id IN :libroAutorList")
    Integer contarAutorEnDesafio(@Param("idDesafioList") List<Long> idDesafioList,
                                 @Param("libroAutorList") List<Long> libroAutorList);
}
