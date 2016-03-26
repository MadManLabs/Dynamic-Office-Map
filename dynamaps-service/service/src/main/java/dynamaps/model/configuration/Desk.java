package dynamaps.model.configuration;

import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "DESK")
public class Desk extends AbstractPersistable {

	/** Generated Serial Version UID. */
	private static final long	serialVersionUID	= 9197221728593377103L;

	@Column(name = "CODE", nullable = false)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String				code;

	@Column(name = "XLAYOUT", nullable = false)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String xlayout;

	@Column(name = "YLAYOUT", nullable = false)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String				ylayout;

	@Column(name = "INACTIVE", nullable = false)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private boolean				inactive;

	/**
	 * Default Constructor.
	 */
	public Desk() {
		super();
	}


	public Desk(final String code, final String xlayout, final String ylayout, final boolean inactive) {
		this();
		this.code = code;
		this.xlayout = xlayout;
		this.ylayout = ylayout;
		this.inactive = inactive;
	}

	public String getCode() {
		return code;
	}

	public void setCode(
		final String code) {
		this.code = code;
	}

	public String getXlayout() {
		return xlayout;
	}

	public void setXlayout(
			final String xlayout) {
		this.xlayout = xlayout;
	}

	public String getYlayout() {
		return ylayout;
	}

	public void setYlayout(
		final String ylayout) {
		this.ylayout = ylayout;
	}

	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(
		final boolean inactive) {
		this.inactive = inactive;
	}

}
