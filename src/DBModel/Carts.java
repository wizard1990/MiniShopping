package DBModel;



/**
 * Carts entity. @author MyEclipse Persistence Tools
 */
public class Carts extends AbstractCarts implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Carts() {
    }

	/** minimal constructor */
    public Carts(Integer quantity, Integer price) {
        super(quantity, price);        
    }
    
    /** full constructor */
    public Carts(Users users, Products products, Integer quantity, Integer price) {
        super(users, products, quantity, price);        
    }
   
}
