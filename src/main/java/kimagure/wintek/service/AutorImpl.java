package kimagure.wintek.service;

import kimagure.wintek.Repository.AutorLibroRepository;
import kimagure.wintek.Repository.AutorRepository;
import kimagure.wintek.Repository.RandomRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Log
@Service("autorService")
@Transactional
public class AutorImpl implements AutorService{

    @Autowired
    AutorLibroRepository autorLibroRepository;

    @Override
    public String obtenerAutoresPorLibro(Long libroId) {
        String autorStr = "";

        List<String> autorList = autorLibroRepository.obtenerAutoresPorLibro(libroId);
        for (String autor: autorList) {
            autorStr =  autorStr + ", " + autor;
        }

        //remueve la coma al inicio
        autorStr = autorStr.substring(2);

        return autorStr;
    }
}
