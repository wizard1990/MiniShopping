package actions;

import com.opensymphony.xwork2.ActionSupport;

public class InsertTransactionAction extends ActionSupport {
	private Integer uid;
	private Integer pid;
	private Integer quantity;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
