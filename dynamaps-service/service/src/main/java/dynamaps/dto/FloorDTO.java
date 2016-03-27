package dynamaps.dto;

import java.io.Serializable;

/**
 * Created by caldea on 3/26/2016.
 */
public class FloorDTO implements Serializable {
    private static final long serialVersionUID	= -6507501740566566265L;

    private String name;
    private String map;
    private Integer id;

    private Integer nrPersons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNrPersons() {
        return nrPersons;
    }

    public void setNrPersons(Integer nrPersons) {
        this.nrPersons = nrPersons;
    }
}
