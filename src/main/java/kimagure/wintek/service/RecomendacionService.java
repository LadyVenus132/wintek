package kimagure.wintek.service;

import kimagure.wintek.domain.vo.LibroGrillaVO;

import java.util.List;

public interface RecomendacionService {

    List<LibroGrillaVO> getGrilla(Integer anio);

    List<LibroGrillaVO> obtenerTop(Integer anio, Integer top);
}
