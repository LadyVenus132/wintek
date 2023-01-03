package kimagure.wintek.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class LibroGrillaDTO implements Comparable<LibroGrillaDTO>{
    //Inf General
    private String titulo;
    private String portada;
    private Integer numSaga;
    private Long libroId;
    private AutorDTO autorDTO;
    private Integer anio;
    private Long generoId;
    private String subgenero;

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

    public LibroGrillaDTO(String titulo, Long libroId, Integer anio, Integer numSaga, String subgenero, String portada, Integer random) {
        this.titulo = titulo;
        this.libroId = libroId;
        this.anio = anio;
        this.numSaga = numSaga;
        this.subgenero = subgenero;
        this.random = random;
        this.portada = portada;
    }

    public LibroGrillaDTO(String titulo, Long libroId, Integer anio, Integer numSaga, String portada, Integer random) {
        this.titulo = titulo;
        this.libroId = libroId;
        this.anio = anio;
        this.numSaga = numSaga;
        this.random = random;
        this.portada = portada;
    }

    @Override
    public int compareTo(LibroGrillaDTO l) {
        if (getPuntaje() == null || l.getPuntaje() == null) {
            return 0;
        }
        return getPuntaje().compareTo(l.getPuntaje());
    }
}
