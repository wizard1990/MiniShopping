package DBModel;

/**
 * Transaction entity. @author MyEclipse Persistence Tools
 */
public class Transaction extends AbstractTransaction implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Transaction() {
	}

	/** minimal constructor */
	public Transaction(Integer uid, Integer pid, Integer quantity,
			Boolean finished) {
		super(uid, pid, quantity, finished);
	}
	
	/** full constructor */
	public Transaction(Integer uid, Integer pid, String creditNum, Integer quantity,
			Boolean finished) {
		super(uid, pid, quantity, finished);
	}
}
