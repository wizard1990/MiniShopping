package DBModel;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */
public class Product extends AbstractProduct implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** full constructor */
	public Product(Category category, String name, String sku, Double price) {
		super(category, name, sku, price);
	}

}
