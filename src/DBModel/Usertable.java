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
	public Usertable(Long id, String name, Integer role) {
		super(id, name, role);
	}

	/** full constructor */
	public Usertable(Long id, String name, Integer role, Integer age,
			String state) {
		super(id, name, role, age, state);
	}

}
