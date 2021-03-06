package DBModel;

/**
 * AbstractSales entity provides the base persistence definition of the Sales
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSales implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Products products;
	private Integer quantity;
	private Integer price;

	// Constructors

	/** default constructor */
	public AbstractSales() {
	}

	/** minimal constructor */
	public AbstractSales(Integer quantity, Integer price) {
		this.quantity = quantity;
		this.price = price;
	}

	/** full constructor */
	public AbstractSales(Users users, Products products, Integer quantity,
			Integer price) {
		this.users = users;
		this.products = products;
		this.quantity = quantity;
		this.price = price;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Products getProducts() {
		return this.products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}