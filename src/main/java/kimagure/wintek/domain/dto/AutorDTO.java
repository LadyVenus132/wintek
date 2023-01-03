package kimagure.wintek.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class AutorDTO {
    private String autor;
    private List<Long> autorIdList;
    private Long autorId;

    public AutorDTO() {
    }

    public AutorDTO(String autor, Long autorId) {
        this.autor = autor;
        this.autorId = autorId;
    }
}
