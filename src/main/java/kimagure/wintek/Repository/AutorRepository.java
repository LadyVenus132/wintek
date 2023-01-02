package kimagure.wintek.Repository;

import kimagure.wintek.domain.autor.Autor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AutorRepository extends CrudRepository<Autor, Long> {

}
