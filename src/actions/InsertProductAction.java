package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import DBModel.Category;
import DBModel.CategoryDAO;
import DBModel.Product;
import DBModel.ProductDAO;

import com.opensymphony.xwork2.ActionSupport;

public class InsertProductAction extends ActionSupport {
	private String sku;
	private Integer cid;
	private Integer price;
	private String name;
	
	public String execute() throws Exception{
		ProductDAO proDAO = new ProductDAO();
		CategoryDAO cateDAO = new CategoryDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		if (sku.length() <= 0 || name.length() <= 0) {
			request.setAttribute("isSucc", 0);
            return ERROR;
		}
		try {
			System.out.println(sku);
			System.out.println(cid);
			List l = proDAO.findBySku(sku);
			Category cate = cateDAO.findById(cid);
			if (cate == null) throw new RuntimeException("category not exists");
			if (l.size() > 0) {
				request.setAttribute("isSucc", 0);
				return "duplicate";
			}
			if(name == null || name.equals("") || name.length() > 20) throw new RuntimeException("illegal name");
			else {
				Product prod = new Product(name, sku, cid, price);
				System.out.println(prod.getName());
				System.out.println(prod.getSku());
				System.out.println(prod.getCid());
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

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
