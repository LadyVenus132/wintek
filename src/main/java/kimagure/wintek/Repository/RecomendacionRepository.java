package kimagure.wintek.repository;

import kimagure.wintek.domain.recomendacion.Recomendacion;
import kimagure.wintek.domain.vo.LibroVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecomendacionRepository extends CrudRepository<Recomendacion, Long> {

    @Query("SELECT r from Recomendacion r" +
            " ORDER BY r.puntaje desc")
    List<Recomendacion> obtenerTop(Pageable pageable);

    @Query("SELECT new kimagure.wintek.domain.vo.LibroVO(r.libroId, r.fantasia, r.anio, r.autor, r.subgenero, r.portada)" +
            " FROM Recomendacion r" +
            " ORDER BY r.titulo ASC")
    List<LibroVO> obtenerTodosLibrosBasico();
}
