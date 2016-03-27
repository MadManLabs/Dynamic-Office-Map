package dynamaps.model.configuration;

import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import javax.persistence.*;


@Entity
@Table(name = "MAC")
public class Mac extends AbstractPersistable {

	/** Generated Serial Version UID. */
	private static final long	serialVersionUID	= 9197221728593377303L;

	@Column(name = "VALUE", nullable = true)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String				value;

	@Property(policy = PojomaticPolicy.TO_STRING)
	@ManyToOne
	private Zone zone;

	/**
	 * Default Constructor.
	 */
	public Mac() {
		super();
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}
}
