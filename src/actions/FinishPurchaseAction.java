package actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Transaction;

import DBModel.Products;
import DBModel.ProductsDAO;
import DBModel.Carts;
import DBModel.CartsDAO;
import DBModel.Sales;
import DBModel.SalesDAO;
import DBModel.UsersDAO;

import com.opensymphony.xwork2.ActionSupport;

public class FinishPurchaseAction extends ActionSupport {
	private String creditCard;

	public String execute() throws Exception{
		if (creditCard.length() != 16 || !StringUtils.isNumeric(creditCard)) {
			return ERROR;
		}
		CartsDAO transDAO = new CartsDAO();
		ProductsDAO proDAO = new ProductsDAO();
		SalesDAO saleDAO = new SalesDAO();
		UsersDAO userDAO = new UsersDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		List<Carts> lc = new ArrayList<Carts>();
		List<Products> lp = new ArrayList<Products>();
		List<Sales> ls = new ArrayList<Sales>();
		Transaction tran = null;
		Integer userid = (Integer)session.getAttribute("userid");
		try {
			tran = transDAO.getSession().beginTransaction();
			boolean flag = true;
			List l = transDAO.findByProperty("users.id", userid);
			if (l.size() == 0) return ERROR;
			for (int i = 0; i < l.size(); i++) {
				Carts c = (Carts)l.get(i);
				if(c.getProducts() == null || proDAO.findById(c.getProducts().getId()) != null) {
					flag = false;
					transDAO.delete(c);
				}
				else {
					lc.add(c);
					lp.add(c.getProducts());
					ls.add(new Sales(userDAO.findById(userid), c.getProducts(), c.getQuantity(), c.getPrice()));
				}
			}
			if (!flag) {
				tran.commit();
				return ERROR;
			}
			else {
				for (int i = 0; i < l.size(); i++) {
					transDAO.delete(lc.get(i));
					saleDAO.save(ls.get(i));
				}
				request.setAttribute("transactions", lc);
				request.setAttribute("products", lp);
				tran.commit();
				return SUCCESS;
			}
        } catch (RuntimeException re) {
        	System.out.println(re);
            return ERROR;
        } finally {
        	if (transDAO.getSession() != null) {
        		transDAO.getSession().close();
        	}
        }
	}
	public String getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
}
