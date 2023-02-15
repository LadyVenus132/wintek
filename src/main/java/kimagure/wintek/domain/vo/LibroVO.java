package kimagure.wintek.domain.vo;

import lombok.Data;

@Data
public class LibroVO {
    private Long id;

    private String titulo;
    private Integer anio;
    private String autor;
    private String subgenero;
    private String portada;

    public LibroVO(Long id, String titulo, Integer anio, String autor, String subgenero, String portada) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.autor = autor;
        this.subgenero = subgenero;
        this.portada = portada;
    }
}
