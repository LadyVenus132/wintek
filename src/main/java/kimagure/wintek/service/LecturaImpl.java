package kimagure.wintek.service;

import kimagure.wintek.domain.ResponseVO;
import kimagure.wintek.domain.autor.Autor;
import kimagure.wintek.domain.desafio.*;
import kimagure.wintek.domain.dto.AutorDTO;
import kimagure.wintek.domain.dto.LibroLecturaDTO;
import kimagure.wintek.domain.libros.Libro;
import kimagure.wintek.domain.libros.StatusLibro;
import kimagure.wintek.domain.vo.LibroLecturaVO;
import kimagure.wintek.repository.*;
import kimagure.wintek.util.TiempoUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Log
@Service("lecturaService")
@Transactional
public class LecturaImpl implements LecturaService{

    @Autowired
    AutorService autorService;

    @Autowired
    AbecedarioRepository abecedarioRepository;

    @Autowired
    AnioRepository anioRepository;

    @Autowired
    AutorLibroRepository autorLibroRepository;

    @Autowired
    DesafioRepository desafioRepository;

    @Autowired
    DesafioAbecedarioRepository desafioAbecedarioRepository;

    @Autowired
    DesafioAnioRepository desafioAnioRepository;

    @Autowired
    DesafioAutorRepository desafioAutorRepository;

    @Autowired
    DesafioLibroRepository desafioLibroRepository;

    @Autowired
    DesafioPaisRepository desafioPaisRepository;

    @Autowired
    DesafioSubgeneroRepository desafioSubgeneroRepository;

    @Autowired
    StatusLibroRepository statusLibroRepository;

    @Autowired
    LibroRepository libroRepository;

    @Override
    public List<LibroLecturaVO> getLeido() {
        List<LibroLecturaVO> libroLecturaVOList = new ArrayList<>();

        List<LibroLecturaDTO> libroLecturaDTOList = statusLibroRepository.obtenerTodos();

        int i = 0;
        for(LibroLecturaDTO libroLecturaDTO : libroLecturaDTOList){
            LibroLecturaVO libroLecturaVO = new LibroLecturaVO(libroLecturaDTO);

            AutorDTO autorDTO = autorService.obtenerAutoresPorLibro(libroLecturaDTO.getLibroId());
            libroLecturaVO.setAutor(autorDTO.getAutor());

            libroLecturaVO.setId(i);
            i++;
            libroLecturaVOList.add(libroLecturaVO);
        }

        return libroLecturaVOList;
    }

    @Override
    public ResponseVO postLeido(LibroLecturaVO lecturaVO) {
        StatusLibro statusLibro = new StatusLibro();

        Libro libro = libroRepository.obtenerPorId(Long.valueOf(lecturaVO.getId()));
        statusLibro.setLibro(libro);
        statusLibro.setEstado(lecturaVO.getEstado());
        statusLibro.setFechaFin(TiempoUtil.generarFecha(lecturaVO.getFechaTermino()));
        statusLibro.setFechaInicio(TiempoUtil.generarFecha(lecturaVO.getFechaInicio()));

        statusLibroRepository.save(statusLibro);

        List<Autor> autorList = autorLibroRepository.obtenerAutoresPorLibro(Long.valueOf(lecturaVO.getId()));

        Integer anio = Integer.valueOf(lecturaVO.getFechaTermino().substring(0, 4));

        //Obtener todos los desafios del año o permanentes
        List<Desafio> desafioIdList = desafioRepository.obtenerDesafiosAnioPermanentes(anio);

        for(Desafio desafio : desafioIdList){
            //log.info("desafio :: " + desafio);
            switch (desafio.getTipo()){
                case "GENERO":
                    DesafioSubgenero desafioSubgenero =
                            desafioSubgeneroRepository.obtenerDesafioNoCompletado(desafio.getId(), libro.getSubgenero().getId());
                    if(null != desafioSubgenero) {
                        desafioSubgenero.setLibroCompletado(libro);
                        desafioSubgenero.setEstado("OK");
                        desafioSubgeneroRepository.save(desafioSubgenero);
                    }
                    break;
                case "A-Z TITULO":
                    char letra = libro.getNombre().substring(0, 1).toCharArray()[0];
                    Long abeceId = abecedarioRepository.obtenerAbecedarioId(letra);

                    DesafioAbecedario desafioAbecedario = desafioAbecedarioRepository.obtenerDesafioNoCompletado(desafio.getId(), abeceId);
                    if(null != desafioAbecedario) {
                        desafioAbecedario.setLibroCompletado(libro);
                        desafioAbecedario.setEstado("OK");
                        desafioAbecedarioRepository.save(desafioAbecedario);
                    }
                    break;
                case "A-Z AUTOR":
                    boolean insAutor = false;
                    for(Autor autor : autorList){
                        if(!insAutor) {
                            char letraAutor = autor.getNombre().substring(0, 1).toCharArray()[0];
                            Long abeceAutorId = abecedarioRepository.obtenerAbecedarioId(letraAutor);

                            DesafioAbecedario desafioAbecedarioAutor = desafioAbecedarioRepository.obtenerDesafioNoCompletado(desafio.getId(), abeceAutorId);
                            if (null != desafioAbecedarioAutor) {
                                desafioAbecedarioAutor.setLibroCompletado(libro);
                                desafioAbecedarioAutor.setEstado("OK");
                                desafioAbecedarioRepository.save(desafioAbecedarioAutor);
                                insAutor = true;
                            }
                        }
                    }
                    break;
                case "ANIO" :
                    Long anioId = anioRepository.obtenerAnioId(libro.getAnio());

                    DesafioAnio desafioAnio = desafioAnioRepository.obtenerDesafioNoCompletado(desafio.getId(), anioId);
                    if(null != desafioAnio){
                        desafioAnio.setLibroCompletado(libro);
                        desafioAnio.setEstado("OK");
                        desafioAnioRepository.save(desafioAnio);
                    }

                    break;
                case "LISTA AUTOR":
                    for(Autor autor : autorList){
                        DesafioAutor desafioAutor = desafioAutorRepository.obtenerDesafioNoCompletado(desafio.getId(), autor.getId());

                        if(null != desafioAutor){
                            desafioAutor.setLibroCompletado(libro);
                            desafioAutor.setEstado("OK");
                            desafioAutorRepository.save(desafioAutor);
                        }
                    }
                    break;
                case "LISTA LIBRO":
                    DesafioLibro desafioLibro = desafioLibroRepository.obtenerDesafioNoCompletado(desafio.getId(), libro.getId());

                    if(null != desafioLibro){
                        desafioLibro.setLibro(libro);
                        desafioLibro.setEstado("OK");
                        desafioLibroRepository.save(desafioLibro);
                    }
                    break;
                case "PAIS":
                    DesafioPais desafioPais = desafioPaisRepository.obtenerDesafioNoCompletado(desafio.getId(), libro.getId());

                    if(null != desafioPais){
                        desafioPais.setLibroCompletado(libro);
                        desafioPais.setEstado("OK");
                        desafioPaisRepository.save(desafioPais);
                    }
                    break;
                default:
                    //log.info("el desafio id :: " + desafio.getId() + " tipo :: " + desafio.getTipo() + " no es de ningun tipo :S");
            }
        }

        return new ResponseVO(200, libro.getFantasia() + " agregado a lectura");
    }
}
