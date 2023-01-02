package kimagure.wintek.domain.desafio;

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
public class Desafio implements Serializable{
    private static final long serialVersionUID = 1L;

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String nombre;

    @Column
    String tipo; //TODO cambiar a enum

    @Column
    Integer anio;
}

