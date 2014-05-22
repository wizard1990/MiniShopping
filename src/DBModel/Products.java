package DBModel;

import java.util.Set;

/**
 * Products entity. @author MyEclipse Persistence Tools
 */
public class Products extends AbstractProducts implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Products() {
	}

	/** minimal constructor */
	public Products(String name, String sku, Integer price) {
		super(name, sku, price);
	}

	/** full constructor */
	public Products(Categories categories, String name, String sku,
			Integer price, Set saleses, Set cartses) {
		super(categories, name, sku, price, saleses, cartses);
	}
}
