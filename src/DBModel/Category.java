package DBModel;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */
public class Category extends AbstractCategory implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** full constructor */
	public Category(String name, String descrip, Integer products) {
		super(name, descrip, products);
	}
}
