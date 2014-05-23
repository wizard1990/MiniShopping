package actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import DBModel.Products;
import DBModel.ProductsDAO;
import DBModel.Carts;
import DBModel.CartsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ListCartAction extends ActionSupport {
	private String po;
	public String execute() throws Exception{
		CartsDAO cartDAO = new CartsDAO();
		//ProductsDAO proDAO = new ProductsDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			Integer userid = (Integer)session.getAttribute("userid");
			List l = cartDAO.findByProperty("users.id", userid);
			List<Carts> lt = new ArrayList<Carts>();
			for (int i = 0; i < l.size(); i++) {
				Carts trans = (Carts)l.get(i);
				lt.add(trans);
			}
			request.setAttribute("transactions", lt);
			if (po != null && po.equals("1")) {
				return "order";
			}
			return SUCCESS;
        } catch (RuntimeException re) {
        	System.out.println(re);
            return ERROR;
        }
	}
	public String getPo() {
		return po;
	}
	public void setPo(String po) {
		this.po = po;
	}
}
