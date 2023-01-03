package kimagure.wintek.service;

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
    StatusLibroRepository statusLibroRepository;


    @Override
    public List<LibroGrillaVO> getGrilla(Integer anio) {
        List<LibroGrillaVO> libroGrillaVOList = new ArrayList<>();

        List<LibroGrillaDTO> libroGrillaDTOList = randomRepository.obtenerNovelas(anio);

        obtenerPuntaje(anio, libroGrillaDTOList, Boolean.FALSE);

        libroGrillaVOList =  ParserUtil.obtenerLibroGrillaVO(libroGrillaDTOList);

        return libroGrillaVOList;
    }

    @Override
    public List<LibroGrillaVO> obtenerTop(Integer anio, Integer top) {
        List<LibroGrillaVO> libroGrillaVOList = new ArrayList<>();
        
        List<LibroGrillaDTO> libroGrillaDTOList = randomRepository.obtenerSoloNovelas(anio);
        libroGrillaDTOList.addAll(randomRepository.obtenerSinGenero(anio));
        //Pageable pageable = PageRequest.of(0, top);
        //List<LibroGrillaDTO> libroGrillaDTOList = randomRepository.obtenerNovelasTop(anio, pageable);

        obtenerPuntaje(anio, libroGrillaDTOList, Boolean.TRUE);

        //Ordena los libros
        Collections.sort(libroGrillaDTOList, Collections.reverseOrder());
        libroGrillaVOList =  ParserUtil.obtenerTopLibroGrillaVO(top, libroGrillaDTOList);

        return libroGrillaVOList;
    }

    private void obtenerPuntaje(Integer anio, List<LibroGrillaDTO> libroGrillaDTOList, Boolean omitirLeido) {

        List<String> numeros = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
        List<String> inicialesTitulos = obtenerInicialesDesafio(anio, "A-Z Título");
        inicialesTitulos.addAll(numeros);

        List<String> inicialesAutor = obtenerInicialesDesafio(anio, "A-Z Autor");

        List<Integer> todosAniosList = anioRepository.obtenerTodosAnios();
        todosAniosList.add(0);
        List<Integer> aniosNoValidos = obtenerAniosNoValidos(anio);

        List<String> subgenerosValidos = obtenerSubgenerosValidos(anio);

        for (LibroGrillaDTO libroGrillaDTO : libroGrillaDTOList) {

            Boolean leido = Boolean.FALSE;
            Boolean sagaValida = libroGrillaDTO.getNumSaga() == null || libroGrillaDTO.getNumSaga() == 1;
            Boolean azValido = Boolean.TRUE;
            Boolean ultSigloValido = Boolean.TRUE;

            Integer puntaje = 0;
            Integer ultSiglo = 0;
            Integer genero = 0;
            Integer cantTitulo = 0;
            Integer cantAutor = 0;

            libroGrillaDTO.setAutorDTO(autorService.obtenerAutoresPorLibro(libroGrillaDTO.getLibroId()));

            if(omitirLeido && !sagaValida) {
                libroGrillaDTO.setPuntaje(puntaje);
                continue;
            }

            //Status Lectura
            Integer cantLeido = statusLibroRepository.contarCantidadLeido(libroGrillaDTO.getLibroId());
            leido = cantLeido > 0 ? Boolean.TRUE : Boolean.FALSE;
            libroGrillaDTO.setLeido(leido);

            if(omitirLeido && leido) {
                libroGrillaDTO.setPuntaje(0);
                continue;
            }

            //A-Z
            String inicialTitulo = libroGrillaDTO.getTitulo().substring(0, 1);
            libroGrillaDTO.setAzTitulo( inicialesTitulos.contains(inicialTitulo));

            String inicialAutor = libroGrillaDTO.getAutorDTO().getAutor().substring(0, 1);
            libroGrillaDTO.setAzAutor( inicialesAutor.contains(inicialAutor));

            azValido = (libroGrillaDTO.getAzTitulo() && libroGrillaDTO.getAzAutor());

            if(omitirLeido && !azValido) {
                libroGrillaDTO.setPuntaje(0);
                continue;
            }

            //Ultimo Siglo
            Boolean containAnioNoValido = aniosNoValidos.contains(libroGrillaDTO.getAnio());
            ultSigloValido = containAnioNoValido ? Boolean.FALSE : ultSigloValido;

            libroGrillaDTO.setUltSiglo(ultSigloValido);
            ultSiglo = todosAniosList.contains(libroGrillaDTO.getAnio()) ? 1 : 0;

            puntaje = puntaje + ultSiglo;

            if(omitirLeido && !ultSigloValido) {
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
                cantAutor = cantAutor + obtenerCantidadAutor(autorId);
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
    private Integer obtenerCantidadAutor(Long autorId) {
        Integer cantAutor = 0;

        List<Long> idDesafioList = desafioLibroRepository.obtenerIdDesafioImportante();

        List<Long> libroAutorList = autorLibroRepository.obtenerLibroIdPorAutor(autorId);

        cantAutor = desafioLibroRepository.contarAutorEnDesafio(idDesafioList, libroAutorList);
        cantAutor = cantAutor + desafioAutorRepository.contarAutorEnDesafio(autorId);
        cantAutor = cantAutor + desafioPaisRepository.contarAutorEnDesafio(libroAutorList);

        return cantAutor;
    }
}
