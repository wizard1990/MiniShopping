package DBModel;

/**
 * AbstractCategory entity provides the base persistence definition of the
 * Category entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCategory implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String descrip;
	private Integer products;

	// Constructors

	/** default constructor */
	public AbstractCategory() {
	}

	/** full constructor */
	public AbstractCategory(String name, String descrip, Integer products) {
		this.name = name;
		this.descrip = descrip;
		this.products = products;
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

	public String getDescrip() {
		return this.descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public Integer getProducts() {
		return this.products;
	}

	public void setProducts(Integer products) {
		this.products = products;
	}

}