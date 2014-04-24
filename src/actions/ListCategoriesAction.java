package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import DBModel.Category;
import DBModel.CategoryDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ListCategoriesAction extends ActionSupport {
	public String execute() throws Exception{
		CategoryDAO cateDAO = new CategoryDAO();
		List l = cateDAO.findAll();
		if (l.size() > 0) {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("categories", l);
			return SUCCESS;
		}
		else return "empty";
	}
}
