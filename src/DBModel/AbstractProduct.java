package DBModel;

/**
 * AbstractProduct entity provides the base persistence definition of the
 * Product entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractProduct implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String sku;
	private Integer cid;
	private Integer price;

	// Constructors

	/** default constructor */
	public AbstractProduct() {
	}

	/** full constructor */
	public AbstractProduct(String name, String sku, Integer cid, Integer price) {
		this.name = name;
		this.sku = sku;
		this.cid = cid;
		this.price = price;
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

	public String getSku() {
		return this.sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid2) {
		this.cid = cid2;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}