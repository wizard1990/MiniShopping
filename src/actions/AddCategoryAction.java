package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBModel.Category;
import DBModel.CategoryDAO;
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
		CategoryDAO cateDAO = new CategoryDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		List l = cateDAO.findByName(name);
		if (l.size() > 0) {
			request.setAttribute("isSucc", 0);
			return "duplicate";
		}
		try {
			if(name.length() > 20) throw null;
			else {
				Category cate = new Category(name, descrip);
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
