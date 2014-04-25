package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import DBModel.Category;
import DBModel.CategoryDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ListCategoriesAction extends ActionSupport {
	public String execute() throws Exception{
		CategoryDAO cateDAO = new CategoryDAO();
		List l = cateDAO.findAll();
		HttpServletRequest request = ServletActionContext.getRequest();
		if (l.size() > 0) {
			for (int i = 0; i < l.size(); i++) {
				Category cate = (Category) l.get(i);
				String name = cate.getName().replaceAll(" ", "&nbsp;");
				String desc = cate.getDescrip().replaceAll(" ", "&nbsp;");
				cate.setName(name);
				cate.setDescrip(desc);
			}
			request.setAttribute("categories", l);
			return SUCCESS;
		}
		else {
			return "empty";
		}
	}
}
