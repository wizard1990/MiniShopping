package actions;

import java.util.List;

import DBModel.Category;
import DBModel.CategoryDAO;
import DBModel.User;
import DBModel.UserDAO;

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
		List l = categoryDAO.findByName(name);
		if (l.size() != 1) {
			return ERROR;  
		}
		else {
			try {
				categoryDAO.delete((Category)l.get(0));
				return SUCCESS;		
			}catch (RuntimeException re) {	
	        	System.out.println(re);
	            return ERROR;
			}
		}
			
	}
	
}
