package kimagure.wintek.service;

import kimagure.wintek.domain.vo.LibroGrillaVO;
import kimagure.wintek.domain.vo.LibroVO;

import java.util.List;

public interface RecomendacionService {
    LibroVO getTop();

    List<LibroGrillaVO> getGrilla();
}
