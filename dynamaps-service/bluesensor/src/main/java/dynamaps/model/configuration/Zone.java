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

	@OneToMany(mappedBy="zone")
	private List<Device> devices;

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

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
}
