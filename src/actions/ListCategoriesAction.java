package actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import DBModel.Categories;
import DBModel.CategoriesDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ListCategoriesAction extends ActionSupport {
	public String execute() throws Exception{
		CategoriesDAO cateDAO = new CategoriesDAO();
		List l = cateDAO.findAll();
		HttpServletRequest request = ServletActionContext.getRequest();
		if (l.size() > 0) {
			for (int i = 0; i < l.size(); i++) {
				Categories cate = (Categories) l.get(i);
				String name = cate.getName().replaceAll(" ", "&nbsp;");
				String desc = cate.getDescription().replaceAll(" ", "&nbsp;");
				cate.setName(name);
				cate.setDescription(desc);
			}
			request.setAttribute("categories", l);
			return SUCCESS;
		}
		else {
			return "empty";
		}
	}
}
