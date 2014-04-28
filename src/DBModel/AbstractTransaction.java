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
	private Boolean finished;

	// Constructors

	/** default constructor */
	public AbstractTransaction() {
	}

	/** full constructor */
	public AbstractTransaction(Integer uid, Integer pid, String creditnum,
			Boolean finished) {
		this.uid = uid;
		this.pid = pid;
		this.creditnum = creditnum;
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

	public Boolean getFinished() {
		return this.finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

}