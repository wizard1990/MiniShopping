package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import DBModel.Category;
import DBModel.CategoryDAO;
import DBModel.ProductDAO;

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
		ProductDAO proDAO = new ProductDAO();
		CategoryDAO cateDAO = new CategoryDAO();
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
				Category cate = (Category) lc.get(i);
				String name = cate.getName().replaceAll(" ", "&nbsp;");
				String desc = cate.getDescrip().replaceAll(" ", "&nbsp;");
				cate.setName(name);
				cate.setDescrip(desc);
			}
			request.setAttribute("categories", lc);
			HttpSession session = request.getSession();
			String userrole = (String) session.getAttribute("userrole");
			System.out.println(userrole);
			request.setAttribute("currentcid", cid);
			if (userrole.equals("0")) return "admin_succ";
			else return "customer_succ";
		} catch (RuntimeException re) {
        	System.out.println(re);
            return ERROR;
        }
	}
}
