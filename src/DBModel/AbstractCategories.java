package DBModel;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractCategories entity provides the base persistence definition of the
 * Categories entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCategories implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String description;
	private Set productses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractCategories() {
	}

	/** minimal constructor */
	public AbstractCategories(String name) {
		this.name = name;
	}

	/** full constructor */
	public AbstractCategories(String name, String description, Set productses) {
		this.name = name;
		this.description = description;
		this.productses = productses;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getProductses() {
		return this.productses;
	}

	public void setProductses(Set productses) {
		this.productses = productses;
	}

}