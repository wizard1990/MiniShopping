package DBModel;

/**
 * AbstractUsertable entity provides the base persistence definition of the
 * Usertable entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUsertable implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Integer role;
	private Integer age;
	private String state;

	// Constructors

	/** default constructor */
	public AbstractUsertable() {
	}

	/** minimal constructor */
	public AbstractUsertable(String name, Integer role) {
		this.name = name;
		this.role = role;
	}

	/** full constructor */
	public AbstractUsertable(String name, Integer role, Integer age,
			String state) {
		this.name = name;
		this.role = role;
		this.age = age;
		this.state = state;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}