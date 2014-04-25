package actions;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import DBModel.CategoryDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ListCategoriesAction extends ActionSupport {
	public String execute() throws Exception{
		CategoryDAO cateDAO = new CategoryDAO();
		List l = cateDAO.findAll();
		HttpServletRequest request = ServletActionContext.getRequest();
		if (l.size() > 0) {
			request.setAttribute("categories", l);
			return SUCCESS;
		}
		else {
			return "empty";
		}
	}
}
