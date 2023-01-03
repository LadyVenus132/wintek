package kimagure.wintek.repository;

import kimagure.wintek.domain.desafio.DesafioAbecedario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DesafioAbecedarioRepository extends CrudRepository<DesafioAbecedario, Long> {

    @Query("SELECT da.abecedario.letra FROM DesafioAbecedario da" +
            " WHERE da.estado is null" +
            " AND da.desafio.id in :tipoIdList")
    List<String> obtenerInicalesDisponibles(@Param("tipoIdList") List<Long> tipoIdList);
}
