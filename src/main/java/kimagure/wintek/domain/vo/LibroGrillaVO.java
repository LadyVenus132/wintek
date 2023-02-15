package kimagure.wintek.domain.vo;

import kimagure.wintek.domain.dto.AutorDTO;
import lombok.Data;

@Data
public class LibroGrillaVO {
    private int id;

    //Inf General
    private String titulo;
    private String autor;
    private Integer anio;
    private String subgenero;
    private String portada;

    //Puntuaciones
    private Integer puntaje;
    private Boolean leido;
    private Integer cantTitulo;
    private Integer cantAutor;
    private Integer random;

    //Desafios
    private Boolean azTitulo;
    private Boolean azAutor;
    private Boolean ultSiglo;
    /*private Boolean nobel;
    private Boolean diversidad;
    private Boolean mustRead;
    private Boolean amazonMustRead;
    private Boolean goodreads;
    private Boolean porPais;
    private Boolean Pulitzer;
    private Boolean onMyOwn;*/

    public LibroGrillaVO() {

    }
}
