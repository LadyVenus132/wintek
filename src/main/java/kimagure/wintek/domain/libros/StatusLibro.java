package kimagure.wintek.domain.libros;

import kimagure.wintek.domain.libros.Libro;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@ToString
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Lazy
public class StatusLibro implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Libro libro;

    @Column
    Calendar fechaInicio;

    @Column
    Calendar fechaFin;

    @Column
    String estado; //TODO cambiar a enum
}
