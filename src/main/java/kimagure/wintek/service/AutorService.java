package kimagure.wintek.service;

import kimagure.wintek.domain.dto.AutorDTO;

import java.util.List;

public interface AutorService {
    public AutorDTO obtenerAutoresPorLibro(Long libroId);

}
