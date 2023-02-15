package kimagure.wintek.domain.dto;

import lombok.Data;

import java.util.Calendar;

@Data
public class LibroLecturaDTO {
    private Long libroId;

    private String titulo;
    private Integer anio;
    private String subgenero;
    private String portada;

    private Calendar fechaInicio;
    private Calendar fechaTermino;
    private String estado;

    public LibroLecturaDTO(Long libroId, String titulo, Integer anio, String subgenero, String portada, Calendar fechaInicio, Calendar fechaTermino, String estado) {
        this.libroId = libroId;
        this.titulo = titulo;
        this.anio = anio;
        this.subgenero = subgenero;
        this.portada = portada;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.estado = estado;
    }
}
