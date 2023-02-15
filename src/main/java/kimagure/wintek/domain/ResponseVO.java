package kimagure.wintek.domain;

import lombok.Data;
import java.io.Serializable;

@Data
public class ResponseVO implements Serializable {
    private Integer codigo;
    private String msje;

    public ResponseVO(Integer codigo, String msje) {
        this.codigo = codigo;
        this.msje = msje;
    }
}
