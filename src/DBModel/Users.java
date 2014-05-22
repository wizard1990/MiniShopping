package DBModel;

import java.util.Set;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
public class Users extends AbstractUsers implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String name) {
		super(name);
	}

	/** full constructor */
	public Users(String name, String role, Integer age, String state,
			Set saleses, Set cartses) {
		super(name, role, age, state, saleses, cartses);
	}

}
