package DBModel;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractUsers entity provides the base persistence definition of the Users
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUsers implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String role;
	private Integer age;
	private String state;
	private Set saleses = new HashSet(0);
	private Set cartses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractUsers() {
	}

	/** minimal constructor */
	public AbstractUsers(String name) {
		this.name = name;
	}

	/** full constructor */
	public AbstractUsers(String name, String role, Integer age, String state,
			Set saleses, Set cartses) {
		this.name = name;
		this.role = role;
		this.age = age;
		this.state = state;
		this.saleses = saleses;
		this.cartses = cartses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
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

	public Set getSaleses() {
		return this.saleses;
	}

	public void setSaleses(Set saleses) {
		this.saleses = saleses;
	}

	public Set getCartses() {
		return this.cartses;
	}

	public void setCartses(Set cartses) {
		this.cartses = cartses;
	}

}