package dynamaps.dto;

import java.io.Serializable;

/**
 * Created by caldea on 3/26/2016.
 */
public class PersonDTO implements Serializable {
    private static final long serialVersionUID	= -7639785294508068012L;

    private String name;
    private String code;
    private String description;
    private DeskDTO desk;
    private GeolocationDTO geolocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DeskDTO getDesk() {
        return desk;
    }

    public void setDesk(DeskDTO desk) {
        this.desk = desk;
    }

    public GeolocationDTO getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(GeolocationDTO geolocation) {
        this.geolocation = geolocation;
    }
}
