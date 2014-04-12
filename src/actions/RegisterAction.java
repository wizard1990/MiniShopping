package actions;


import java.util.List;

import DBModel.Usertable;
import DBModel.UsertableDAO;


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
		UsertableDAO userDAO = new UsertableDAO();
		List l = userDAO.findByName(name);
		if (l.size() > 0) {
			return "duplicate";
		}
		try {
			if (age != null && age < 0) throw null;
			if (state.length() > 20) throw null;
			else {
				Usertable user = new Usertable(name, role, age, state);
				userDAO.save(user);
				return SUCCESS;
			}
        } catch (RuntimeException re) {
            return ERROR;
        }
	}
}
