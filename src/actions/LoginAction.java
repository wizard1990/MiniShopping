package actions;

import java.util.List;

import DBModel.Usertable;
import DBModel.UsertableDAO;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private String name;
	void setName(String name) {
		this.name = name;
	}
	String getName(String name) {
		return name;
	}
	
	public String execute() throws Exception{
		UsertableDAO userDAO = new UsertableDAO();
		List l = userDAO.findByName(name);
		if (l.size() > 0) {
			return "duplicate";
		}
		try {
            return SUCCESS;
        } catch (RuntimeException re) {
            return ERROR;
        }
	}
}
