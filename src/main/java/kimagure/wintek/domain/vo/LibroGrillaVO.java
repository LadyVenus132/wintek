package kimagure.wintek.domain.vo;

import lombok.Data;

@Data
public class LibroGrillaVO {
    //Inf General
    private String titulo;
    private Long libroId;
    private String autor;
    private Integer anio;
    private String subgenero;

    //Puntuaciones
    private Integer puntaje;
    //private Boolean leido;
    private Integer cantTitulo;
    //private Integer cantAutor;
    private Integer random;

    //Desafios
    /*private Boolean azTitulo;
    private Boolean azAutor;
    private Boolean ultSiglo;
    private Boolean generoLiterario;
    private Boolean nobel;
    private Boolean diversidad;
    private Boolean mustRead;
    private Boolean amazonMustRead;
    private Boolean goodreads;
    private Boolean porPais;
    private Boolean Pulitzer;
    private Boolean onMyOwn;*/

    public LibroGrillaVO(String titulo, Long libroId, Integer anio, String subgenero, Integer random) {
        this.titulo = titulo;
        this.libroId = libroId;
        this.anio = anio;
        this.subgenero = subgenero;
        this.random = random;
    }
}
