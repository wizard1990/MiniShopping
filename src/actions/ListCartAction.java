package actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import DBModel.Product;
import DBModel.ProductDAO;
import DBModel.Transaction;
import DBModel.TransactionDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ListCartAction extends ActionSupport {
	
	public String execute() throws Exception{
		TransactionDAO transDAO = new TransactionDAO();
		ProductDAO proDAO = new ProductDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			List l = transDAO.findByUid(session.getAttribute("userid"));
			List<Transaction> lt = new ArrayList<Transaction>();
			List<Product> lp = new ArrayList<Product>();
			for (int i = 0; i < l.size(); i++) {
				Transaction trans = (Transaction)l.get(i);
				if(!trans.getFinished()) {
					Product prod = proDAO.findById(trans.getPid());
					lt.add(trans);
					lp.add(prod);
				}
			}
			request.setAttribute("transactions", lt);
			request.setAttribute("products", lp);
			return SUCCESS;
        } catch (RuntimeException re) {
        	System.out.println(re);
            return ERROR;
        }
	}
}
