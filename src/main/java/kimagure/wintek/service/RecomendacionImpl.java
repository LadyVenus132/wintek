package kimagure.wintek.service;

import kimagure.wintek.Repository.AutorRepository;
import kimagure.wintek.Repository.RandomRepository;
import kimagure.wintek.domain.vo.LibroGrillaVO;
import kimagure.wintek.domain.vo.LibroVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Log
@Service("recomendacionService")
@Transactional
public class RecomendacionImpl implements RecomendacionService{

    @Autowired
    RandomRepository randomRepository;

    @Autowired
    AutorService autorService;

    @Override
    public LibroVO getTop() {
        LibroVO libroVO = new LibroVO();

        libroVO.setNombre("título del libro");
        libroVO.setAutor("nombre Autor");
        libroVO.setPortadaUrl("https://template.canva.com/EADtutwov_Q/1/0/256w-nJr37FPJeZs.jpg");

        return libroVO;
    }

    @Override
    public List<LibroGrillaVO> getGrilla() {
        log.info("getGrilla[]");
        List<LibroGrillaVO> libroGrillaVOList = new ArrayList<>();

        Integer countNovelas = randomRepository.contarNovelas(2023);
        log.info("countNovelas :: " + countNovelas);
        libroGrillaVOList = randomRepository.obtenerNovelas(2023);

        for (LibroGrillaVO libroGrillaVO : libroGrillaVOList) {
            Integer puntaje = 0;
            Integer cantTitulo = 0;
            Integer cantAutor = 0;
            Integer azTitulo = 0;
            Integer azAutor = 0;
            Integer ultSiglo = 0;
            Integer genero = 0;

            libroGrillaVO.setAutor(autorService.obtenerAutoresPorLibro(libroGrillaVO.getLibroId()));

            cantTitulo = obtenerCantidadTitulo(libroGrillaVO.getLibroId());
            libroGrillaVO.setCantTitulo(cantTitulo);

            puntaje = cantTitulo + cantAutor + azTitulo + azAutor + ultSiglo + genero + libroGrillaVO.getRandom();
            libroGrillaVO.setPuntaje(puntaje);
        }

        //log.info("libroGrillaVOList SIZE :: " + libroGrillaVOList.size());
        return libroGrillaVOList;
    }

    private Integer obtenerCantidadTitulo(Long libroId) {
        Integer cantTitulo = 0;

        return cantTitulo;
    }
}
