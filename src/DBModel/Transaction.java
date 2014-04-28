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

	/** full constructor */
	public Transaction(Integer uid, Integer pid, String creditnum,
			Boolean finished) {
		super(uid, pid, creditnum, finished);
	}

}
