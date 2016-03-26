package dynamaps.model.configuration;

import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import javax.persistence.*;

@Entity
@Table(name = "FLOOR_DESK")
public class FloorDesk extends AbstractPersistable {


    @ManyToOne
    @JoinColumn(name = "FLOOR_ID")
    @Property(policy = PojomaticPolicy.TO_STRING)
    private Floor floor;


	@ManyToOne
    @JoinColumn(name = "DESK_ID")
    @Property(policy = PojomaticPolicy.TO_STRING)
    private Desk desk;

    @Column(name = "INACTIVE", nullable = false)
    @Property(policy = PojomaticPolicy.TO_STRING)
    private boolean				inactive;


    /**
     * Default Constructor.
     */
    public FloorDesk() {
        super();
    }


    public FloorDesk(final Floor floor, final Desk desk) {
        this();
        this.floor = floor;
        this.desk = desk;
    }


    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }
}
