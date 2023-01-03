package kimagure.wintek.util;

import kimagure.wintek.domain.dto.LibroGrillaDTO;
import kimagure.wintek.domain.vo.LibroGrillaVO;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Log
public final class ParserUtil {

    public static final List<LibroGrillaVO> obtenerLibroGrillaVO(List<LibroGrillaDTO> libroGrillaDTOList){
        List<LibroGrillaVO> libroGrillaVOList = new ArrayList<>();

        for(LibroGrillaDTO libroDTO: libroGrillaDTOList){
            libroGrillaVOList.add(libroDTOaLibroVO(libroDTO));
        }

        return libroGrillaVOList;
    }

    public static List<LibroGrillaVO> obtenerTopLibroGrillaVO(Integer top, List<LibroGrillaDTO> libroGrillaDTOList) {
        List<LibroGrillaVO> libroGrillaVOList = new ArrayList<>();

        top = top > libroGrillaDTOList.size() ? libroGrillaDTOList.size() : top;

        for(int i=0; i<top; i++){
            LibroGrillaDTO libroDTO = libroGrillaDTOList.get(i);
            libroGrillaVOList.add(libroDTOaLibroVO(libroDTO));
        }

        return libroGrillaVOList;
    }

    private static LibroGrillaVO libroDTOaLibroVO(LibroGrillaDTO libroDTO){
        return new LibroGrillaVO(libroDTO.getTitulo(), libroDTO.getLibroId(), libroDTO.getAutorDTO().getAutor(), libroDTO.getAnio(), libroDTO.getSubgenero(), libroDTO.getPortada(),
                libroDTO.getPuntaje(), libroDTO.getCantTitulo(), libroDTO.getCantAutor(), libroDTO.getRandom(),
                libroDTO.getLeido(), libroDTO.getAzTitulo(), libroDTO.getAzAutor(), libroDTO.getUltSiglo());
    }
}
