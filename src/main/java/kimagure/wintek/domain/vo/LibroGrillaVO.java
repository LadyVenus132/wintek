package kimagure.wintek.domain.vo;

import kimagure.wintek.domain.dto.AutorDTO;
import lombok.Data;

@Data
public class LibroGrillaVO {
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

    public LibroGrillaVO(String titulo, String autor, Integer anio, String subgenero, String portada,
                         Integer puntaje, Integer cantTitulo, Integer cantAutor, Integer random, Boolean leido, Boolean azTitulo, Boolean azAutor) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.subgenero = subgenero;
        this.portada = portada;

        this.puntaje = puntaje;
        this.cantTitulo = cantTitulo;
        this.cantAutor = cantAutor;
        this.random = random;
        this.leido = leido;
    }

    private Long libroId;
    public LibroGrillaVO(String titulo, Long libroId, String autor, Integer anio, String subgenero, String portada,
                         Integer puntaje, Integer cantTitulo, Integer cantAutor, Integer random,
                         Boolean leido, Boolean azTitulo, Boolean azAutor, Boolean ultSiglo) {
        this.titulo = titulo;
        this.libroId = libroId;
        this.autor = autor;
        this.anio = anio;
        this.subgenero = subgenero;
        this.portada = portada;

        this.puntaje = puntaje;
        this.cantTitulo = cantTitulo;
        this.cantAutor = cantAutor;
        this.random = random;

        this.leido = leido;
        this.azTitulo = azTitulo;
        this.azAutor = azAutor;
        this.ultSiglo = ultSiglo;
    }
}
