package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Transaction;

import DBModel.Products;
import DBModel.ProductsDAO;
import DBModel.Carts;
import DBModel.CartsDAO;
import DBModel.Users;
import DBModel.UsersDAO;

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
		CartsDAO transDAO = new CartsDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		ProductsDAO proDAO = new ProductsDAO();
		Transaction tran = transDAO.getSession().beginTransaction();
		try {
			Products prod = proDAO.findById(pid);
			if (prod == null || quantity <= 0) {
				return ERROR;
			}
			UsersDAO usersDAO = new UsersDAO();
			Users user = usersDAO.findById((Integer)session.getAttribute("userid"));
			Carts cs = new Carts(user, prod, quantity, prod.getPrice());
			transDAO.save(cs);
			tran.commit();
			return SUCCESS;
        } catch (RuntimeException re) {
        	System.out.println(re);
        	request.setAttribute("isSucc", 0);
        	try{
                tran.rollback();
            }catch(RuntimeException rbe){
                throw rbe;
            }
            return ERROR;
        } finally {
        	if(transDAO.getSession() != null) {
        		transDAO.getSession().close();
        	}
        }
	}
}
