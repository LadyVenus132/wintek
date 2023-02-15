package kimagure.wintek.service;

import kimagure.wintek.domain.ResponseVO;
import kimagure.wintek.domain.vo.LibroLecturaVO;

import java.util.List;

public interface LecturaService {
    public List<LibroLecturaVO> getLeido();

    ResponseVO postLeido(LibroLecturaVO lecturaVO);
}
