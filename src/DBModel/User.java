package DBModel;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
public class User extends AbstractUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String name, Integer role, Integer age, String state) {
		super(name, role, age, state);
	}

}
