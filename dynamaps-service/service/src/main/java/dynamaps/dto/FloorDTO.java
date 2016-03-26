package dynamaps.dto;

import java.io.Serializable;

/**
 * Created by caldea on 3/26/2016.
 */
public class FloorDTO implements Serializable {
    private static final long serialVersionUID	= -6507501740566566265L;

    private String code;
    private String map;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
