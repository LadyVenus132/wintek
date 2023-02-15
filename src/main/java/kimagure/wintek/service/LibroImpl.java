package kimagure.wintek.service;

import kimagure.wintek.domain.vo.LibroVO;
import kimagure.wintek.repository.RecomendacionRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Log
@Service("libroService")
@Transactional
public class LibroImpl implements LibroService {

    @Autowired
    RecomendacionRepository recomendacionRepository;

    @Override
    public List<LibroVO> getLibroBasico() {
        return recomendacionRepository.obtenerTodosLibrosBasico();
    }
}
