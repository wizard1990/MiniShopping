package actions;


import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;



import DBModel.Users;
import DBModel.UsersDAO;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
	private String name;
	private String role;
	private Integer age;
	private String state;
	
	public String getName() {
    	return name;
	}
	public void setName(String userName) {
		this.name = userName;
	}
	
	public String getRole() {
    	return role;
	}
	public void setRole(String role) {
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
		UsersDAO usersDAO = new UsersDAO();
		List l = usersDAO.findByName(name);
		if (l.size() > 0) {
			return "duplicate";
		}
		try {
			if ((age != null && age < 0) || name.contains(" ") || name.length() < 1) throw new RuntimeException("illegal name");
			else {
				Users user = new Users(name, role, age, state, new HashSet(0), new HashSet(0));
				usersDAO.save(user);
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
