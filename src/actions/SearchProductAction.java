package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import DBModel.Categories;
import DBModel.CategoriesDAO;
import DBModel.ProductsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class SearchProductAction extends ActionSupport {
	private String cid;
	private String keyWord;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String execute() throws Exception{
		ProductsDAO proDAO = new ProductsDAO();
		CategoriesDAO cateDAO = new CategoriesDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			System.out.println(cid);
			System.out.println(keyWord);
			Integer cateId = -1;
			if (cid.length() > 0) {
				cateId = Integer.parseInt(cid);
			}
			List l = proDAO.searchProduct(cateId, keyWord);
			List lc = cateDAO.findAll();
			request.setAttribute("products", l);
			for (int i = 0; i < lc.size(); i++) {
				Categories cate = (Categories) lc.get(i);
				String name = cate.getName().replaceAll(" ", "&nbsp;");
				String desc = cate.getDescription().replaceAll(" ", "&nbsp;");
				cate.setName(name);
				cate.setDescription(desc);
			}
			request.setAttribute("categories", lc);
			HttpSession session = request.getSession();
			String userrole = (String) session.getAttribute("userrole");
			System.out.println(userrole);
			request.setAttribute("currentcid", cid);
			if (userrole.equals("owner")) return "admin_succ";
			else return "customer_succ";
		} catch (RuntimeException re) {
        	System.out.println(re);
            return ERROR;
        }
	}
}
