package DBModel;

/**
 * AbstractTransaction entity provides the base persistence definition of the
 * Transaction entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTransaction implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Integer pid;
	private String creditnum;
	private Integer quantity;
	private Boolean finished;

	// Constructors

	/** default constructor */
	public AbstractTransaction() {
	}

	/** minimal constructor */
	public AbstractTransaction(Integer uid, Integer pid, Integer quantity,
			Boolean finished) {
		this.uid = uid;
		this.pid = pid;
		this.quantity = quantity;
		this.finished = finished;
	}

	/** full constructor */
	public AbstractTransaction(Integer uid, Integer pid, String creditnum,
			Integer quantity, Boolean finished) {
		this.uid = uid;
		this.pid = pid;
		this.creditnum = creditnum;
		this.quantity = quantity;
		this.finished = finished;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getCreditnum() {
		return this.creditnum;
	}

	public void setCreditnum(String creditnum) {
		this.creditnum = creditnum;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getFinished() {
		return this.finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

}