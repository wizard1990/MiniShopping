package actions;

import DBModel.User;
import DBModel.UserDAO;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteAction extends ActionSupport{
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
	
	public String execute() throws Exception {
		UserDAO userDAO = new UserDAO();
		
		try {
			User user = new User(name, role, age, state);
			userDAO.delete(user);
			return SUCCESS;		
		}catch (RuntimeException re) {	
        	System.out.println(re);
            return ERROR;  
		}
			
	}
	
}
