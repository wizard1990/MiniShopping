package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import DBModel.User;
import DBModel.UserDAO;

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
		UserDAO userDAO = new UserDAO();
		List l = userDAO.findByName(name);
		if (l.size() == 1) {
			User user = (User) l.get(0);
			String r = user.getRole().toString();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("alllist", userDAO.findAll());
			HttpSession session = request.getSession();
			session.setAttribute("userrole", r);
			return SUCCESS;
		}
		else return ERROR;
	}
}
