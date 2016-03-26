package dynamaps.model.configuration;

import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
public class Person extends AbstractPersistable {

	/** Generated Serial Version UID. */
	private static final long	serialVersionUID	= 9197221728593377103L;

	/** The Name of the Configuration - Discriminator. */
	@Column(name = "NAME", nullable = false)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String				name;

	@Column(name = "EMAIL", nullable = true)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String				email;

	/** The Description of the Configuration - Discriminator. */
	@Column(name = "MAC", nullable = true)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String				mac;

	@Property(policy = PojomaticPolicy.TO_STRING)
	@OneToOne @MapsId
	private Desk desk;


	public Person() {
		super();
	}


	public String getName() {
		return name;
	}

	public void setName(
		final String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Desk getDesk() {
		return desk;
	}

	public void setDesk(Desk desk) {
		this.desk = desk;
	}
}
