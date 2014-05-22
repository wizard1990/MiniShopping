package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBModel.Categories;
import DBModel.CategoriesDAO;
import DBModel.Products;
import DBModel.ProductsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ListProductsAction extends ActionSupport {
	public String execute() throws Exception{
		ProductsDAO proDAO = new ProductsDAO();
		List l = proDAO.findAll();
		CategoriesDAO cateDAO = new CategoriesDAO();
		List lc = cateDAO.findAll(); 
		HttpServletRequest request = ServletActionContext.getRequest();
		for (int i = 0; i < lc.size(); i++) {
			Categories cate = (Categories) lc.get(i);
			String name = cate.getName().replaceAll(" ", "&nbsp;");
			String desc = cate.getDescription().replaceAll(" ", "&nbsp;");
			cate.setName(name);
			cate.setDescription(desc);
		}
		request.setAttribute("categories", lc);
		if (l.size() > 0) {
			for (int i = 0; i < l.size(); i++) {
				Products prod = (Products) l.get(i);
				String name = prod.getName().replaceAll(" ", "&nbsp;");
				String sku = prod.getSku().replaceAll(" ", "&nbsp;");
				prod.setName(name);
				prod.setSku(sku);
			}
			request.setAttribute("products", l);
			return SUCCESS;
		}
		else {
			System.out.println("empty");
			return "empty";
		}
	}
}
