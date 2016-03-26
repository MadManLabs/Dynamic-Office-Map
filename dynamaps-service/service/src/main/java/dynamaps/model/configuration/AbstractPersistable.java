package dynamaps.model.configuration;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.proxy.HibernateProxy;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class AbstractPersistable implements java.io.Serializable, Persistable<String> {

	private static final long serialVersionUID = 2535090450811888936L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID")
	@Property(policy=PojomaticPolicy.TO_STRING)
	private String id;
	
	@Version
	@Column(name="VERSION_NUMBER")
	@NotNull
	@Property(policy=PojomaticPolicy.TO_STRING)
	private long versionNumber = 1;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Persistable#getId()
     */
	public String getId() {

        return this.id;
    }


    /**
     * Sets the id of the entity.
     * 
     * @param id the id to set
     */
	public void setId(final String id) {

        this.id = id;
    }

    public long getVersionNumber() {
		return this.versionNumber;
	}


	public void setVersionNumber(final long versionNumber) {
		this.versionNumber = versionNumber;
	}
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Persistable#isNew()
     */
    public boolean isNew() {

        return null == getId();
    }

    /**
     * Don't override. Use Pojomatic annotations instead.
     */
	@Override
	public final String toString() {
		return Pojomatic.toString(getEntity());
	}
	
    /*
     * Method is final because derived classes should use Pojomatic annotations.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(final Object obj) {
    	return Pojomatic.equals(getEntity(), obj);
    }

    /*
     * Method is final because derived classes should use Pojomatic annotations.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode() {
    	return Pojomatic.hashCode(getEntity());
    }
    
    private Object getEntity(){
    	if (this instanceof HibernateProxy){
    		return HibernateProxy.class.cast(this).getHibernateLazyInitializer().getImplementation();
    	} else {
    		return this;
    	}
   	
    }
}
