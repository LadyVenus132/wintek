package kimagure.wintek.domain.desafio;

import kimagure.wintek.domain.libros.Libro;
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
public class DesafioAnio implements Serializable {
    private static final long serialVersionUID = 1L;

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Desafio desafio;

    @ManyToOne
    Anio anio;

    @ManyToOne
    Libro libroCompletado;

    @Column
    String estado; //TODO cambiar a enum
}
