package actions;

import java.util.List;

import DBModel.Usertable;
import DBModel.UsertableDAO;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private String name;
	private Integer role;
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public String execute() throws Exception{
		UsertableDAO userDAO = new UsertableDAO();
		List l = userDAO.findByName(name);
		if (l.size() == 1) {
			Usertable user = (Usertable) l.get(0);
			setRole(user.getRole());
			return SUCCESS;
		}
		else return ERROR;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
}
