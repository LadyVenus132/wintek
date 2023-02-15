package kimagure.wintek.domain.recomendacion;

import kimagure.wintek.domain.dto.LibroGrillaDTO;
import kimagure.wintek.domain.vo.LibroGrillaVO;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Lazy
public class Recomendacion implements Serializable {
    private static final long serialVersionUID = 1L;

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    private String titulo;

    @Column
    private Long libroId;

    @Column
    private String autor;

    @Column
    private Integer anio;

    @Column
    private String subgenero;

    @Column
    private String portada;

    @Column
    private Integer puntaje;

    @Column
    private Boolean leido;

    @Column
    private Integer cantTitulo;

    @Column
    private Integer cantAutor;

    @Column
    private Integer random;

    @Column
    private Boolean azTitulo;

    @Column
    private Boolean azAutor;

    @Column
    private Boolean ultSiglo;

    public Recomendacion(LibroGrillaDTO libroGrillaDTO) {
        this.titulo = libroGrillaDTO.getTitulo();
        this.libroId = libroGrillaDTO.getLibroId();
        this.autor = libroGrillaDTO.getAutorDTO().getAutor();
        this.anio = libroGrillaDTO.getAnio();
        this.subgenero = libroGrillaDTO.getSubgenero();
        this.portada = libroGrillaDTO.getPortada();
        this.puntaje = libroGrillaDTO.getPuntaje();
        this.leido = libroGrillaDTO.getLeido();
        this.cantTitulo = libroGrillaDTO.getCantTitulo();
        this.cantAutor = libroGrillaDTO.getCantAutor();
        this.random = libroGrillaDTO.getRandom();
        this.azTitulo = libroGrillaDTO.getAzTitulo();
        this.azAutor = libroGrillaDTO.getAzAutor();
        this.ultSiglo = libroGrillaDTO.getUltSiglo();
    }

    public LibroGrillaVO obtenerVO() {
        LibroGrillaVO libroGrillaVO = new LibroGrillaVO();

        libroGrillaVO.setTitulo(this.titulo);
        libroGrillaVO.setAutor(this.autor);
        libroGrillaVO.setAnio(this.anio);
        libroGrillaVO.setSubgenero(this.subgenero);
        libroGrillaVO.setPortada(this.portada);
        libroGrillaVO.setPuntaje(this.puntaje);
        libroGrillaVO.setLeido(this.leido);
        libroGrillaVO.setCantTitulo(this.cantTitulo);
        libroGrillaVO.setCantAutor(this.cantAutor);
        libroGrillaVO.setRandom(this.random);
        libroGrillaVO.setAzTitulo(this.azTitulo);
        libroGrillaVO.setAzAutor(this.azAutor);
        libroGrillaVO.setUltSiglo(this.ultSiglo);

        return libroGrillaVO;
    }
}
