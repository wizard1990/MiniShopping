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
		HttpServletRequest request = ServletActionContext.getRequest();
		if (l.size() > 0) {
			request.setAttribute("categories", l);
			request.setAttribute("isSucc", 1);
			return SUCCESS;
		}
		else {
			request.setAttribute("isSucc", 0);
			return "empty";
		}
	}
}
