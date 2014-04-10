package DBModel;

/**
 * Usertable entity. @author MyEclipse Persistence Tools
 */
public class Usertable extends AbstractUsertable implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Usertable() {
	}

	/** minimal constructor */
	public Usertable(String name, Integer role) {
		super(name, role);
	}

	/** full constructor */
	public Usertable(String name, Integer role, Integer age,
			String state) {
		super( name, role, age, state);
	}

}
