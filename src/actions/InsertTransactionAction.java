package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import DBModel.ProductDAO;
import DBModel.Transaction;
import DBModel.TransactionDAO;

import com.opensymphony.xwork2.ActionSupport;

public class InsertTransactionAction extends ActionSupport {
	private Integer pid;
	private Integer quantity;
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
	
	public String execute() throws Exception{
		TransactionDAO transDAO = new TransactionDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		ProductDAO proDAO = new ProductDAO();
		if (proDAO.findById(pid) == null) {
			return ERROR;
		}
		try {
			Transaction tran = new Transaction((Integer) session.getAttribute("userid"), pid, quantity, false);
			transDAO.save(tran);
			return SUCCESS;
        } catch (RuntimeException re) {
        	System.out.println(re);
        	request.setAttribute("isSucc", 0);
            return ERROR;
        }
	}
}
