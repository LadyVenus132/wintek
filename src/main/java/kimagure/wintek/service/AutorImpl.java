package kimagure.wintek.service;

import kimagure.wintek.domain.autor.Autor;
import kimagure.wintek.domain.dto.AutorDTO;
import kimagure.wintek.repository.AutorLibroRepository;
import kimagure.wintek.repository.AutorRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Log
@Service("autorService")
@Transactional
public class AutorImpl implements AutorService{

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    AutorLibroRepository autorLibroRepository;

    @Override
    public AutorDTO obtenerAutoresPorLibro(Long libroId) {
        AutorDTO autorDTO = new AutorDTO();
        String autorStr = "";
        List<Long> autorIdList = new ArrayList<>();

        List<AutorDTO> autorList = autorLibroRepository.obtenerAutoresDTOPorLibro(libroId);

        for (AutorDTO autor: autorList) {
            autorStr =  autorStr + ", " + autor.getAutor();
            autorIdList.add(autor.getAutorId());
        }

        //remueve la coma al inicio
        autorStr = autorStr.substring(2);
        autorDTO.setAutor(autorStr);
        autorDTO.setAutorIdList(autorIdList);

        return autorDTO;
    }

}
