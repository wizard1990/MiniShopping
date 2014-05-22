package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Transaction;

import DBModel.Products;
import DBModel.ProductsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteProductAction extends ActionSupport {
	private String sku;
	public String execute() throws Exception {
		ProductsDAO proDAO = new ProductsDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		Transaction tran = null;
		try {
			tran = proDAO.getSession().beginTransaction();
			List l = proDAO.findBySku(sku.substring(0, sku.length() - 1));
			if (l.size() == 0) throw new RuntimeException("product not exists");
			Products prod = (Products) l.get(0);
			proDAO.delete(prod);
			request.setAttribute("isSucc", 1);
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
        	if(proDAO.getSession() != null) {
        		proDAO.getSession().close();
        	}
        }
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
}
