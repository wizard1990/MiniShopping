package DBModel;

/**
 * Sales entity. @author MyEclipse Persistence Tools
 */
public class Sales extends AbstractSales implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Sales() {
	}

	/** minimal constructor */
	public Sales(Integer quantity, Integer price) {
		super(quantity, price);
	}

	/** full constructor */
	public Sales(Users users, Products products, Integer quantity, Integer price) {
		super(users, products, quantity, price);
	}
}
