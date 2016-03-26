package dynamaps.model.configuration;

import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "ZONE")
public class Zone extends AbstractPersistable {

	/** Generated Serial Version UID. */
	private static final long	serialVersionUID	= 9197221728593377103L;

	@Column(name = "NAME", nullable = true)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String				name;

	@Property(policy = PojomaticPolicy.TO_STRING)
	@ManyToOne
	private Floor floor;

	@OneToMany(mappedBy="zone")
	private List<Desk> desks;

	/**
	 * Default Constructor.
	 */
	public Zone() {
		super();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public List<Desk> getDesks() {
		return desks;
	}

	public void setDesks(List<Desk> desks) {
		this.desks = desks;
	}
}
