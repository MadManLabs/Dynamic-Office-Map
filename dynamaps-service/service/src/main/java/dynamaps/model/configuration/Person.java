package dynamaps.model.configuration;

import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
public class Person extends AbstractPersistable {

	/** Generated Serial Version UID. */
	private static final long	serialVersionUID	= 9197221728593377103L;

	/** The Code of the Configuration - Discriminator. */
	@Column(name = "CODE", nullable = false)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String				code;

	/** The Name of the Configuration - Discriminator. */
	@Column(name = "NAME", nullable = false)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String				name;

	/** The Description of the Configuration - Discriminator. */
	@Column(name = "DESCRIPTION", nullable = false)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String				description;

	/** Boolean flag to indicate whether the Configuration - Discriminator is inactive or not. */
	@Column(name = "INACTIVE", nullable = false)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private boolean				inactive;

	/**
	 * Default Constructor.
	 */
	public Person() {
		super();
	}

	/**
	 * Parameterized Constructor.
	 * 
	 * @param code
	 *            The Code of the Configuration - Discriminator.
	 * @param name
	 *            The Name of the Configuration - Discriminator.
	 * @param description
	 *            The Description of the Configuration - Discriminator.
	 * @param inactive
	 *            Boolean flag to indicate whether the Configuration - Discriminator is inactive or not.
	 */
	public Person(final String code, final String name, final String description, final boolean inactive) {
		this();
		this.code = code;
		this.name = name;
		this.description = description;
		this.inactive = inactive;
	}

	public String getCode() {
		return code;
	}

	public void setCode(
		final String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(
		final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(
		final String description) {
		this.description = description;
	}

	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(
		final boolean inactive) {
		this.inactive = inactive;
	}

}
