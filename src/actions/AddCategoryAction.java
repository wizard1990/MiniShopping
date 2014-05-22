package actions;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBModel.Categories;
import DBModel.CategoriesDAO;
import com.opensymphony.xwork2.ActionSupport;

public class AddCategoryAction extends ActionSupport {
	private String name;
	private String descrip;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String execute() throws Exception{
		CategoriesDAO cateDAO = new CategoriesDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		if (name.length() <= 0 || descrip.length() <= 0) {
			request.setAttribute("isSucc", 0);
            return ERROR;
		}
		List l = cateDAO.findByName(name);
		if (l.size() > 0) {
			request.setAttribute("isSucc", 0);
			return "duplicate";
		}
		try {
			if(name == null || name.equals("") || name.length() > 20) throw new RuntimeException("illegal name");
			else {
				Categories cate = new Categories(name, descrip, new HashSet(0));
				cateDAO.save(cate);
				request.setAttribute("isSucc", 1);
				return SUCCESS;
			}
        } catch (RuntimeException re) {
        	System.out.println(re);
        	request.setAttribute("isSucc", 0);
            return ERROR;
        }
	}
}
