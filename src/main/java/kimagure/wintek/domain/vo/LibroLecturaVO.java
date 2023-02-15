package kimagure.wintek.domain.vo;

import kimagure.wintek.domain.dto.LibroLecturaDTO;
import kimagure.wintek.util.TiempoUtil;
import lombok.Data;

import java.io.Serializable;

@Data
public class LibroLecturaVO implements Serializable {
    private int id;

    private String titulo;
    private String autor;
    private Integer anio;
    private String subgenero;
    private String portada;

    private String fechaInicio;
    private String fechaTermino;
    private String estado;

    public LibroLecturaVO(LibroLecturaDTO libroLecturaDTO) {
        this.titulo = libroLecturaDTO.getTitulo();
        this.anio = libroLecturaDTO.getAnio();
        this.subgenero = libroLecturaDTO.getSubgenero();
        this.portada = libroLecturaDTO.getPortada();
        this.fechaInicio = TiempoUtil.formatearFechaStr(libroLecturaDTO.getFechaInicio());
        this.fechaTermino = TiempoUtil.formatearFechaStr(libroLecturaDTO.getFechaTermino());
        this.estado = libroLecturaDTO.getEstado();
    }

    public LibroLecturaVO() {
    }

    public LibroLecturaVO(int id, String titulo, String autor, Integer anio, String subgenero, String portada, String fechaInicio, String fechaTermino, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.subgenero = subgenero;
        this.portada = portada;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.estado = estado;
    }
}
