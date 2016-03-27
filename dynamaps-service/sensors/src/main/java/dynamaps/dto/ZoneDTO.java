package dynamaps.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by caldea on 3/26/2016.
 */
public class ZoneDTO implements Serializable {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
