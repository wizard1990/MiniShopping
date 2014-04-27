package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBModel.Category;
import DBModel.CategoryDAO;
import DBModel.Product;
import DBModel.ProductDAO;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteProductAction extends ActionSupport {
	private String sku;
	public String execute() throws Exception {
		ProductDAO proDAO = new ProductDAO();
		CategoryDAO cateDAO = new CategoryDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			System.out.println(sku);
			List l = proDAO.findBySku(sku.substring(0, sku.length() - 1));
			if (l.size() == 0) throw new RuntimeException("product not exists");
			Product prod = (Product) l.get(0);
			Category cate = cateDAO.findById(prod.getCid());
			proDAO.delete(prod);
			cate.setProducts(cate.getProducts() - 1);
			cateDAO.attachDirty(cate);
			request.setAttribute("isSucc", 1);
			return SUCCESS;
        } catch (RuntimeException re) {
        	System.out.println(re);
        	request.setAttribute("isSucc", 0);
            return ERROR;
        }
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
}
