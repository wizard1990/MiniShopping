package actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBModel.Category;
import DBModel.CategoryDAO;
import DBModel.Product;
import DBModel.ProductDAO;

import com.opensymphony.xwork2.ActionSupport;

public class UpdateProductAction extends ActionSupport {
	private String id;
	private String oldCid;
	private String cid;
	private String name;
	private String price;
	private String sku;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOldCid() {
		return oldCid;
	}
	public void setOldCid(String oldCid) {
		this.oldCid = oldCid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public String execute() throws Exception{
		CategoryDAO cateDAO = new CategoryDAO();
		ProductDAO proDAO = new ProductDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			System.out.println(oldCid.substring(0, oldCid.length() - 1));
			System.out.println(id);
			System.out.println(name);
			System.out.println(sku);
			Product prod = proDAO.findById(Integer.parseInt(id.substring(0, id.length() - 1)));
			prod.setCid(Integer.parseInt(cid));
			prod.setName(name);
			prod.setPrice(Integer.parseInt(price));
			prod.setSku(sku);
			proDAO.attachDirty(prod);
			if (!oldCid.substring(0, oldCid.length() - 1).equals(cid)) {
				Category oldCate = cateDAO.findById(Integer.parseInt(oldCid.substring(0, oldCid.length() - 1)));
				Category cate = cateDAO.findById(Integer.parseInt(cid));
				oldCate.setProducts(oldCate.getProducts() - 1);
				cate.setProducts(cate.getProducts() + 1);
				cateDAO.attachDirty(oldCate);
				cateDAO.attachDirty(cate);
			}
			request.setAttribute("isSucc", 1);
			return SUCCESS;
        } catch (RuntimeException re) {
        	System.out.println(re);
        	request.setAttribute("isSucc", 0);
            return ERROR;
        }
	}
}
