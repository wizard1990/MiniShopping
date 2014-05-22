package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBModel.Categories;
import DBModel.CategoriesDAO;
import DBModel.Category;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCategoryAction extends ActionSupport{
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
    
	
	public String execute() throws Exception {
		name = name.substring(0, name.length() - 1);
		CategoriesDAO categoriesDAO = new CategoriesDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		List l = categoriesDAO.findByName(name);
		if (l.size() != 1 || ((Categories)l.get(0)).getProductses().size() > 0) {
			request.setAttribute("isSucc", 0);
			return ERROR;
		}
		else {
			try {
				categoriesDAO.delete((Categories)l.get(0));
				request.setAttribute("isSucc", 1);
				return SUCCESS;		
			}catch (RuntimeException re) {	
	        	request.setAttribute("isSucc", 0);
	            return ERROR;
			}
		}
	}
}
