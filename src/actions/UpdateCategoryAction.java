package actions;

import javax.servlet.http.HttpServletRequest;
import DBModel.Category;
import org.apache.struts2.ServletActionContext;
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
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			Category cate = cateDAO.findById(Integer.parseInt(id.substring(0, id.length() - 1)));
			if(cate != null) {
				cate.setName(name);
				cate.setDescrip(descrip);
				cateDAO.attachDirty(cate);
				request.setAttribute("isSucc", 1);
				return SUCCESS;
			}
			else {
				request.setAttribute("isSucc", 0);
				return ERROR;
			}
        } catch (RuntimeException re) {
        	System.out.println(re);
        	request.setAttribute("isSucc", 0);
            return ERROR;
        }
	}
}
