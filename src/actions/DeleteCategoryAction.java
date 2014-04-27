package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import DBModel.Category;
import DBModel.CategoryDAO;
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
		CategoryDAO categoryDAO = new CategoryDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		List l = categoryDAO.findByName(name);
		if (l.size() != 1) {
			request.setAttribute("isSucc", 0);
			return ERROR;  
		}
		else {
			try {
				categoryDAO.delete((Category)l.get(0));
				request.setAttribute("isSucc", 1);
				return SUCCESS;		
			}catch (RuntimeException re) {	
	        	request.setAttribute("isSucc", 0);
	            return ERROR;
			}
		}
	}
	
}
