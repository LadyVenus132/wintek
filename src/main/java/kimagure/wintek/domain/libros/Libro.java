package kimagure.wintek.domain.libros;

import kimagure.wintek.domain.Pais;
import kimagure.wintek.domain.genero.Subgenero;
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
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String nombre;

    @Column
    String fantasia;

    @Column
    Integer anio;

    @Column
    String idioma; //TODO cambiar a enum

    @Column
    String portadaUrl;

    @ManyToOne
    Pais paisAmbientacion;

    @ManyToOne
    Subgenero subgenero;

    @ManyToOne
    Saga saga;

    @Column
    Integer sagaNumero;


    /*public Champion(String name, String subname, Region region, Race race, Role role,
                    String releaseDate, String sex, String logo, String logoPositionH, String avatar) {
        this.name = name;
        this.keyName = getKey(name);
        this.subname = subname;
        this.region = region;
        this.race = race;
        this.role = role;
        this.releaseDate = getReleaseDate(releaseDate);
        this.sex = sex;
        this.logo = logo;
        this.logoPositionH = logoPositionH;
        this.avatar = avatar;
    }

    private final String getKey(String name){
        return name.trim().replace("'", "").toUpperCase();
    }

    private final Calendar getReleaseDate (String releaseDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            Date date = sdf.parse(releaseDate);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return cal;
    }*/
}
