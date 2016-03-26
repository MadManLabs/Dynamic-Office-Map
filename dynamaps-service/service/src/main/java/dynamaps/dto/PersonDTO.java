package dynamaps.dto;

import java.io.Serializable;

/**
 * Created by caldea on 3/26/2016.
 */
public class PersonDTO implements Serializable {
    private static final long serialVersionUID	= -7639785294508068012L;

    private String name;
    private String email;
    private DeskDTO desk;
    private String mac;
    private Integer id;
    private ZoneDTO zone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DeskDTO getDesk() {
        return desk;
    }

    public void setDesk(DeskDTO desk) {
        this.desk = desk;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ZoneDTO getZone() {
        return zone;
    }

    public void setZone(ZoneDTO zone) {
        this.zone = zone;
    }
}
