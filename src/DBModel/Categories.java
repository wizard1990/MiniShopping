package DBModel;

import java.util.Set;

/**
 * Categories entity. @author MyEclipse Persistence Tools
 */
public class Categories extends AbstractCategories implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Categories() {
	}

	/** minimal constructor */
	public Categories(String name) {
		super(name);
	}

	/** full constructor */
	public Categories(String name, String description, Set productses) {
		super(name, description, productses);
	}

}
