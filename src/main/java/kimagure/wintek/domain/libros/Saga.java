package kimagure.wintek.domain.libros;

import lombok.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString
@Data
@Lazy
public class Saga implements Serializable {
    private static final long serialVersionUID = 1L;

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String nombre;
}
