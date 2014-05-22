package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Transaction;

import DBModel.Categories;
import DBModel.CategoriesDAO;
import DBModel.Products;
import DBModel.ProductsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class InsertProductAction extends ActionSupport {
	private String sku;
	private Integer cid;
	private Integer price;
	private String name;
	
	public String execute() throws Exception{
		ProductsDAO proDAO = new ProductsDAO();
		CategoriesDAO cateDAO = new CategoriesDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		Transaction tran = null;
		if (sku.length() <= 0 || name.length() <= 0 || price <= 0) {
			request.setAttribute("isSucc", 2);
            return ERROR;
		}
		try {
			tran = proDAO.getSession().beginTransaction();
			List l = proDAO.findBySku(sku);
			Categories cate = cateDAO.findById(cid);
			if (cate == null) throw new RuntimeException("category not exists");
			if (l.size() > 0) {
				request.setAttribute("isSucc", 2);
				return "duplicate";
			}
			if(name == null || name.equals("") || name.length() > 20) throw new RuntimeException("illegal name");
			else {
				Products prod = new Products(name, sku, price);
				prod.setCategories(cate);
				proDAO.save(prod);
				request.setAttribute("isSucc", 1);
				request.setAttribute("inserted", prod);
				request.setAttribute("cname", cate.getName());
				proDAO.getSession().flush();
				tran.commit();
				return SUCCESS;
			}
        } catch (RuntimeException re) {
        	System.out.println(re);
        	request.setAttribute("isSucc", 2);
            try{
                tran.rollback();
            }catch(RuntimeException rbe){
                throw rbe;
            }
            return ERROR;
        } finally {
        	if (proDAO.getSession() != null) {
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
