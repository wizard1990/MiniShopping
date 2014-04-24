package actions;

import DBModel.Category;
import DBModel.CategoryDAO;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateCategoryAction extends ActionSupport {
	private String id;
	private String name;
	private String descrip;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	
	public String execute() throws Exception{
		CategoryDAO cateDAO = new CategoryDAO();
		try {
			Category cate = cateDAO.findById(Integer.parseInt(id.substring(0, id.length() - 1)));
			cate.setName(name);
			cate.setDescrip(descrip);
			cateDAO.attachDirty(cate);
			return SUCCESS;
        } catch (RuntimeException re) {
        	System.out.println(re);
            return ERROR;
        }
	}
}
