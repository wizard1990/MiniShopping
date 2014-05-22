package actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Transaction;

import DBModel.Categories;
import DBModel.CategoriesDAO;
import DBModel.Products;
import DBModel.ProductsDAO;

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
		CategoriesDAO cateDAO = new CategoriesDAO();
		ProductsDAO proDAO = new ProductsDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		if (sku.length() <= 0 || name.length() <= 0 || Integer.parseInt(price) <= 0 ) {
			request.setAttribute("isSucc", 0);
            return ERROR;
		}
		Transaction tran = null;
		try {
			tran = proDAO.getSession().beginTransaction();
			Products prod = proDAO.findById(Integer.parseInt(id.substring(0, id.length() - 1)));
			if (!oldCid.substring(0, oldCid.length() - 1).equals(cid)) {
				Categories cate = cateDAO.findById(Integer.parseInt(cid));
				prod.setCategories(cate);
			}
			prod.setName(name);
			prod.setPrice(Integer.parseInt(price));
			prod.setSku(sku);
			proDAO.attachDirty(prod);
			request.setAttribute("isSucc", 1);
			tran.commit();
			return SUCCESS;
        } catch (RuntimeException re) {
        	System.out.println(re);
            try{
                tran.rollback();
            }catch(RuntimeException rbe){
                throw rbe;
            }
        	request.setAttribute("isSucc", 0);
            return ERROR;
        } finally {
        	if(proDAO.getSession() != null) {
        		proDAO.getSession().close();
        	}
        }
	}
}
