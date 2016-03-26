package dynamaps.model.configuration;

import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "FLOOR")
public class Floor extends AbstractPersistable {

	/** Generated Serial Version UID. */
	private static final long	serialVersionUID	= 9197221728593377103L;

	@Column(name = "CODE", nullable = false)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String				code;

	@Column(name = "MAP", nullable = false)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String				map;

	@Column(name = "INACTIVE", nullable = false)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private boolean				inactive;

	/**
	 * Default Constructor.
	 */
	public Floor() {
		super();
	}


	public Floor(final String code, final String map, final boolean inactive) {
		this();
		this.code = code;
		this.map = map;
		this.inactive = inactive;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMap() {
		return map;
	}

	public void setMap(
		final String map) {
		this.map = map;
	}


	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(
		final boolean inactive) {
		this.inactive = inactive;
	}

}
