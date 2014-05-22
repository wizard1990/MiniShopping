package DBModel;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractProducts entity provides the base persistence definition of the
 * Products entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractProducts implements java.io.Serializable {

	// Fields

	private Integer id;
	private Categories categories;
	private String name;
	private String sku;
	private Integer price;
	private Set saleses = new HashSet(0);
	private Set cartses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractProducts() {
	}

	/** minimal constructor */
	public AbstractProducts(String name, String sku, Integer price) {
		this.name = name;
		this.sku = sku;
		this.price = price;
	}

	/** full constructor */
	public AbstractProducts(Categories categories, String name, String sku,
			Integer price, Set saleses, Set cartses) {
		this.categories = categories;
		this.name = name;
		this.sku = sku;
		this.price = price;
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

	public Categories getCategories() {
		return this.categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return this.sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
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