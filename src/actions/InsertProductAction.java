package actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import DBModel.Category;
import DBModel.CategoryDAO;
import DBModel.Product;
import DBModel.ProductDAO;

import com.opensymphony.xwork2.ActionSupport;

public class InsertProductAction extends ActionSupport {
	private String sku;
	private String cname;
	private double price;
	private String name;
	
	public String execute() throws Exception{
		ProductDAO proDAO = new ProductDAO();
		CategoryDAO cateDAO = new CategoryDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			List l = proDAO.findBySku(sku);
			List lc = cateDAO.findByName(cname);
			if (lc.size() == 0) throw new RuntimeException("category not exists");
			Category cate = (Category) lc.get(0);
			if (l.size() > 0) {
				request.setAttribute("isSucc", 0);
				return "duplicate";
			}
			if(name == null || name.equals("") || name.length() > 20) throw new RuntimeException("illegal name");
			else {
				Product prod = new Product(cate, name, sku, price);
				System.out.println(prod.getName());
				System.out.println(prod.getSku());
				System.out.println(prod.getPrice());
				System.out.println(prod.getCategory().getId());
				proDAO.save(prod);
				cate.setProducts(cate.getProducts() + 1);
				cateDAO.attachDirty(cate);
				request.setAttribute("isSucc", 1);
				return SUCCESS;
			}
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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
