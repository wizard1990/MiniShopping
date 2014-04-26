package DBModel;

/**
 * AbstractProduct entity provides the base persistence definition of the
 * Product entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractProduct implements java.io.Serializable {

	// Fields

	private Integer id;
	private Category category;
	private String name;
	private String sku;
	private Double price;

	// Constructors

	/** default constructor */
	public AbstractProduct() {
	}

	/** full constructor */
	public AbstractProduct(Category category, String name, String sku,
			Double price) {
		this.category = category;
		this.name = name;
		this.sku = sku;
		this.price = price;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}