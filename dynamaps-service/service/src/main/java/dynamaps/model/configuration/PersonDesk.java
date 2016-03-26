package dynamaps.model.configuration;

import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import javax.persistence.*;

@Entity
@Table(name = "PERSON_DESK")
public class PersonDesk extends AbstractPersistable {


    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    @Property(policy = PojomaticPolicy.TO_STRING)
    private Person person;


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
    public PersonDesk() {
        super();
    }


    public PersonDesk(final Person person, final Desk desk) {
        this();
        this.person = person;
        this.desk = desk;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
