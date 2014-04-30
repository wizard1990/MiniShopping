package actions;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;



import DBModel.User;
import DBModel.UserDAO;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
	private String name;
	private Integer role;
	private Integer age;
	private String state;
	
	public String getName() {
    	return name;
	}
	public void setName(String userName) {
		this.name = userName;
	}
	
	public Integer getRole() {
    	return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	
	public Integer getAge() {
    	return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getState() {
    	return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String execute() throws Exception{
		UserDAO userDAO = new UserDAO();
		List l = userDAO.findByName(name);
		if (l.size() > 0) {
			return "duplicate";
		}
		try {
			if ((age != null && age < 0) || name.contains(" ") || name.length() < 1) throw new RuntimeException("illegal name");
			else {
				User user = new User(name, role, age, state);
				userDAO.save(user);
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession();
				session.setAttribute("userrole", role);
				session.setAttribute("userid", user.getId());
				return SUCCESS;
			}
        } catch (RuntimeException re) {
        	System.out.println(re);
            return ERROR;
        }
	}
}
