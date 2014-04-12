package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import DBModel.Usertable;
import DBModel.UsertableDAO;

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
		UsertableDAO userDAO = new UsertableDAO();
		List l = userDAO.findByName(name);
		if (l.size() == 1) {
			Usertable user = (Usertable) l.get(0);
			String r = user.getRole().toString();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("userrole", r);
			return SUCCESS;
		}
		else return ERROR;
	}
}
