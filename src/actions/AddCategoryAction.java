package actions;

import java.util.List;

import DBModel.Category;
import DBModel.CategoryDAO;
import com.opensymphony.xwork2.ActionSupport;

public class AddCategoryAction extends ActionSupport {
	private String name;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescrption(String description) {
		this.description = description;
	}

	public String execute() throws Exception{
		CategoryDAO cateDAO = new CategoryDAO();
		List l = cateDAO.findByName(name);
		if (l.size() > 0) {
			return "duplicate";
		}
		try {
			if(name.length() > 20) throw null;
			else {
				Category cate = new Category(name, description);
				cateDAO.save(cate);
				return SUCCESS;
			}
        } catch (RuntimeException re) {
        	System.out.println(re);
            return ERROR;
        }
	}
}
