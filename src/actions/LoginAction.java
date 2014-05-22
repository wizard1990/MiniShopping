package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import DBModel.Users;
import DBModel.UsersDAO;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public String execute() throws Exception{
		UsersDAO usersDAO = new UsersDAO();
		List l = usersDAO.findByName(name);
		if (l.size() == 1) {
			Users user = (Users) l.get(0);
			String r = user.getRole();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("userrole", r);
			session.setAttribute("userid", user.getId());
			return SUCCESS;
		}
		else return ERROR;
	}
}
