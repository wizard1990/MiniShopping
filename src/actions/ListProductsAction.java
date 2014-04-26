package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBModel.Category;
import DBModel.CategoryDAO;
import DBModel.Product;
import DBModel.ProductDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ListProductsAction extends ActionSupport {
	public String execute() throws Exception{
		ProductDAO proDAO = new ProductDAO();
		List l = proDAO.findAll();
		CategoryDAO cateDAO = new CategoryDAO();
		List lc = cateDAO.findAll(); 
		HttpServletRequest request = ServletActionContext.getRequest();
		if (l.size() > 0) {
			for (int i = 0; i < lc.size(); i++) {
				Category cate = (Category) lc.get(i);
				String name = cate.getName().replaceAll(" ", "&nbsp;");
				String desc = cate.getDescrip().replaceAll(" ", "&nbsp;");
				cate.setName(name);
				cate.setDescrip(desc);
			}
			for (int i = 0; i < lc.size(); i++) {
				Product prod = (Product) l.get(i);
				String name = prod.getName().replaceAll(" ", "&nbsp;");
				String sku = prod.getSku().replaceAll(" ", "&nbsp;");
				prod.setName(name);
				prod.setSku(sku);
			}
			request.setAttribute("categories", lc);
			request.setAttribute("products", l);
			return SUCCESS;
		}
		else {
			return "empty";
		}
	}
}
