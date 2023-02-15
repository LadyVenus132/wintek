package kimagure.wintek.service;

import kimagure.wintek.domain.recomendacion.Recomendacion;
import kimagure.wintek.domain.vo.LibroGrillaVO;
import kimagure.wintek.repository.*;
import kimagure.wintek.domain.dto.LibroGrillaDTO;
import kimagure.wintek.util.ParserUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Log
@Service("recomendacionService")
@Transactional
public class RecomendacionImpl implements RecomendacionService{

    @Autowired
    AutorService autorService;

    @Autowired
    AnioRepository anioRepository;

    @Autowired
    AutorLibroRepository autorLibroRepository;

    @Autowired
    DesafioAnioRepository desafioAnioRepository;

    @Autowired
    DesafioRepository desafioRepository;

    @Autowired
    DesafioAbecedarioRepository desafioAbecedarioRepository;

    @Autowired
    DesafioAutorRepository desafioAutorRepository;

    @Autowired
    DesafioLibroRepository desafioLibroRepository;

    @Autowired
    DesafioPaisRepository desafioPaisRepository;

    @Autowired
    DesafioSubgeneroRepository desafioSubgeneroRepository;

    @Autowired
    RandomRepository randomRepository;

    @Autowired
    RecomendacionRepository recomendacionRepository;

    @Autowired
    StatusLibroRepository statusLibroRepository;


    @Override
    public List<LibroGrillaVO> getGrilla(Integer anio) {
        List<LibroGrillaVO> libroGrillaVOList = new ArrayList<>();

        List<Recomendacion> recomendacionList = (List<Recomendacion>) recomendacionRepository.findAll();

        log.info("recomendacionList SIZE :: " + recomendacionList.size());

        int i = 1;
        for(Recomendacion recomendacion : recomendacionList){
            LibroGrillaVO libroGrillaVO = recomendacion.obtenerVO();
            libroGrillaVO.setId(i);
            libroGrillaVOList.add(libroGrillaVO);

            i++;
        }

        log.info("libroGrillaVOList SIZE :: " + libroGrillaVOList.size());
        return libroGrillaVOList;
    }

    @Override
    public List<LibroGrillaVO> obtenerTop(Integer anio, Integer top) {
        log.info("obtenerTop[] TOP :: " + top);

        List<LibroGrillaVO> libroGrillaVOList = new ArrayList<>();

        Pageable pageable = PageRequest.of(0, top);
        List<Recomendacion> recomendacionList = recomendacionRepository.obtenerTop(pageable);

        int i = 1;
        for(Recomendacion recomendacion : recomendacionList){
            LibroGrillaVO libroGrillaVO = recomendacion.obtenerVO();
            libroGrillaVO.setId(i);
            libroGrillaVOList.add(libroGrillaVO);

            i++;
        }

        return libroGrillaVOList;
    }

    @Override
    public void postEliminarRecomendacion() {
        recomendacionRepository.deleteAll();
    }

    @Override
    public void postCalcular(Integer anio) {
        List<LibroGrillaDTO> libroGrillaDTOList = randomRepository.obtenerSoloNovelas(anio);
        libroGrillaDTOList.addAll(randomRepository.obtenerSinGenero(anio));

        obtenerPuntaje(anio, libroGrillaDTOList);

        insertarRecomendacion(libroGrillaDTOList);
    }

    private void insertarRecomendacion(List<LibroGrillaDTO> libroGrillaDTOList) {
        for(LibroGrillaDTO libroGrillaDTO: libroGrillaDTOList){
            Recomendacion recomendacion = new Recomendacion(libroGrillaDTO);

            recomendacionRepository.save(recomendacion);
        }
    }

    private void obtenerPuntaje(Integer anio, List<LibroGrillaDTO> libroGrillaDTOList) {
        List<String> numeros = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
        List<String> inicialesTitulos = obtenerInicialesDesafio(anio, "A-Z TITULO");
        inicialesTitulos.addAll(numeros);

        List<String> inicialesAutor = obtenerInicialesDesafio(anio, "A-Z AUTOR");

        List<Integer> todosAniosList = anioRepository.obtenerTodosAnios();
        todosAniosList.add(0);
        List<Integer> aniosNoValidos = obtenerAniosNoValidos(anio);

        List<String> subgenerosValidos = obtenerSubgenerosValidos(anio);

        List<Long> desafiosAutorIdList = desafioLibroRepository.obtenerIdDesafioImportante();

        for (LibroGrillaDTO libroGrillaDTO : libroGrillaDTOList) {

            boolean leido = Boolean.FALSE;
            boolean sagaValida = libroGrillaDTO.getNumSaga() == null || libroGrillaDTO.getNumSaga() == 1;
            boolean azValido = Boolean.TRUE;
            boolean ultSigloValido = Boolean.TRUE;

            int puntaje = 0;
            int ultSiglo = 0;
            int genero = 0;
            int cantTitulo = 0;
            int cantAutor = 0;

            libroGrillaDTO.setAutorDTO(autorService.obtenerAutoresPorLibro(libroGrillaDTO.getLibroId()));

            //Status Lectura
            Integer cantLeido = statusLibroRepository.contarCantidadLeido(libroGrillaDTO.getLibroId());
            leido = cantLeido > 0 ? Boolean.TRUE : Boolean.FALSE;
            libroGrillaDTO.setLeido(leido);

            if(leido) {
                libroGrillaDTO.setPuntaje(0);
                continue;
            }

            //A-Z
            String inicialTitulo = libroGrillaDTO.getTitulo().substring(0, 1);
            libroGrillaDTO.setAzTitulo( inicialesTitulos.contains(inicialTitulo));

            String inicialAutor = libroGrillaDTO.getAutorDTO().getAutor().substring(0, 1);
            libroGrillaDTO.setAzAutor( inicialesAutor.contains(inicialAutor));

            azValido = (libroGrillaDTO.getAzTitulo() && libroGrillaDTO.getAzAutor());

            if(!azValido) {
                libroGrillaDTO.setPuntaje(0);
                continue;
            }

            //Ultimo Siglo
            ultSigloValido = aniosNoValidos.contains(libroGrillaDTO.getAnio()) ? Boolean.FALSE : ultSigloValido;

            libroGrillaDTO.setUltSiglo(ultSigloValido);
            ultSiglo = todosAniosList.contains(libroGrillaDTO.getAnio()) ? 1 : 0;

            puntaje = puntaje + ultSiglo;

            if(!ultSigloValido) {
                libroGrillaDTO.setPuntaje(0);
                continue;
            }

            //Subgenero
            genero = (null == libroGrillaDTO.getSubgenero() || subgenerosValidos.contains(libroGrillaDTO.getSubgenero())) ? 1 : 0;
            puntaje = puntaje + genero;

            //Cantidad Título
            cantTitulo = obtenerCantidadTitulo(libroGrillaDTO.getLibroId());
            libroGrillaDTO.setCantTitulo(cantTitulo);

            //Cantidad Autor
            for (Long autorId : libroGrillaDTO.getAutorDTO().getAutorIdList()) {
                cantAutor = cantAutor + obtenerCantidadAutor(autorId, desafiosAutorIdList);
            }
            libroGrillaDTO.setCantAutor(cantAutor);

            puntaje = puntaje + libroGrillaDTO.getRandom() + cantTitulo + cantAutor;

            //Puntaje
            puntaje = (leido || !sagaValida || !azValido || !ultSigloValido) ? 0 : puntaje;
            libroGrillaDTO.setPuntaje(puntaje);
        }
    }

    private List<String> obtenerSubgenerosValidos(Integer anio) {
        List<Integer> anioList = new ArrayList<>();
        anioList.add(anio);
        anioList.add(anio -1);

        return desafioSubgeneroRepository.obtenerSubgenerosValidos(anioList);
    }

    private List<Integer> obtenerAniosNoValidos(Integer anio) {
        List<Integer> anioList = new ArrayList<>();
        anioList.add(anio);
        anioList.add(anio -1);

        return desafioAnioRepository.obtenerAniosNoValidos(anioList);
    }

    private List<String> obtenerInicialesDesafio(Integer anio, String tipo) {
        List<Integer> anioList = new ArrayList<>();
        anioList.add(anio);
        anioList.add(anio -1);

        List<Long> tipoIdList = desafioRepository.obtenerDesafioIdPorAnioTipo(anioList, tipo);

        return desafioAbecedarioRepository.obtenerInicalesDisponibles(tipoIdList);
    }


    //Obtiene la cantidad de veces que aparece el título en desafios
    private Integer obtenerCantidadTitulo(Long libroId) {
        Integer cantTitulo = 0;

        cantTitulo = desafioLibroRepository.contarTitulo(libroId);
        cantTitulo = cantTitulo + desafioPaisRepository.contarTitulo(libroId);

        return cantTitulo;
    }

    //Obtiene la cantidad de veces que aparece el autor en desafios
    private Integer obtenerCantidadAutor(Long autorId, List<Long> idDesafioList) {
        Integer cantAutor = 0;

        List<Long> libroAutorList = autorLibroRepository.obtenerLibroIdPorAutor(autorId);

        cantAutor = desafioLibroRepository.contarAutorEnDesafio(idDesafioList, libroAutorList);
        cantAutor = cantAutor + desafioAutorRepository.contarAutorEnDesafio(autorId);
        cantAutor = cantAutor + desafioPaisRepository.contarAutorEnDesafio(libroAutorList);

        return cantAutor;
    }
}
