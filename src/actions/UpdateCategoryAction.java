package actions;

import javax.servlet.http.HttpServletRequest;
import DBModel.Categories;
import org.apache.struts2.ServletActionContext;
import DBModel.CategoriesDAO;
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
		CategoriesDAO cateDAO = new CategoriesDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			Categories cate = cateDAO.findById(Integer.parseInt(id.substring(0, id.length() - 1)));
			if(cate != null) {
				if(name.equals("") || descrip.equals(""))
				{
					request.setAttribute("isSucc", 0);
					return ERROR;
				}
				name = name.replaceAll("&nbsp;", " ");
				descrip = descrip.replaceAll("&nbsp;", " ");
				cate.setName(name);
				cate.setDescription(descrip);
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
