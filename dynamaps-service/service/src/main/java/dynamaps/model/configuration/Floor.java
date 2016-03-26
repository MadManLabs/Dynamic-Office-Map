package dynamaps.model.configuration;

import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "FLOOR")
public class Floor extends AbstractPersistable {

	/** Generated Serial Version UID. */
	private static final long	serialVersionUID	= 9197221728593377103L;

	@Column(name = "NAME", nullable = false)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String				name;

	@Column(name = "MAP", nullable = true)
	@Lob
	private String				map;

	@OneToMany(mappedBy="floor")
	private List<Zone> zones;

	/**
	 * Default Constructor.
	 */
	public Floor() {
		super();
	}


	public Floor(final String name, final String map) {
		this();
		this.name = name;
		this.map = map;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMap() {
		return map;
	}

	public void setMap(
		final String map) {
		this.map = map;
	}

	public List<Zone> getZones() {
		return zones;
	}

	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}
}
