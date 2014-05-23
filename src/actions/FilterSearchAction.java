package actions;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import DBModel.HibernateSessionFactory;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;

import DBModel.ProductListElement;
import DBModel.CustomerListElement;
import DBModel.CategoriesDAO;
import DBModel.Products;
import DBModel.Sales;
import DBModel.Users;

public class FilterSearchAction extends ActionSupport {
	private String age;
	private String cid;
	private String state;
	private String rowType;
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRowType() {
		return rowType;
	}
	public void setRowType(String rowType) {
		this.rowType = rowType;
	}
	
	public String execute() throws Exception{
		Session session = null;
        boolean isSucc = false;
        CategoriesDAO cateDAO = new CategoriesDAO();
        String stateFilter = "";
        if (state.length() > 0) {
        	stateFilter = String.format("where u.state = '%s' ", state);
        }
        String ageFilter = "";
        if (age.length() > 0) {
        	ageFilter = String.format("and u.age >= %d and u.age < %d ", Integer.parseInt(age), Integer.parseInt(age) + 10);
        }
        String categoryFilter = "";
        if (cid.length() > 0) {
        	categoryFilter = String.format("where p.categories.id = %d", Integer.parseInt(cid));
        }
        //String rowhql = "select u.id, u.name as uName, sum(t.quantity * p.price) from Users u, Sales t, Products p where t.uid = u.id and t.pid = p.id and t.finished = 't' " + stateFilter + ageFilter + "group by u.id order by sum(t.quantity * p.price) desc";
        String rowhql = "from Users u " + /*stateFilter + ageFilter +*/ "order by u.name";
        //String colhql = "select p.id, p.name as pName, sum(t.quantity * p.price) from Transaction t, Product p where t.pid = p.id " + categoryFilter + "group by p.id order by sum(t.quantity * p.price) desc";
        String colhql = "from Products p " + categoryFilter + "order by p.name";
        try {
            session = HibernateSessionFactory.getSession();
            Query rowQuery = session.createQuery(rowhql);
            rowQuery.setMaxResults(20);
            Query colQuery = session.createQuery(colhql);
            colQuery.setMaxResults(10);
            List rowlist = rowQuery.list();
            List collist = colQuery.list();
            Integer rowLen = rowlist.size();
			Integer colLen = collist.size();
			
			List<Integer> resultList = new ArrayList<Integer>(rowLen * colLen);
            List<CustomerListElement> rowList = new ArrayList<CustomerListElement>(rowlist.size());
            
            for(Object obj:rowlist){
                Users user = (Users)obj;
                Integer cost = 0;
                for(Object sale: user.getSaleses()){  
                    Sales s = (Sales)sale;
                    cost += s.getPrice() * s.getQuantity();
                }
                for (int i = 0; i < colLen; i++) {
                	Integer pid = ((Products)(collist.get(i))).getId();
                	Integer purchase = 0;
                	for(Object sale: user.getSaleses()){
                        Sales s = (Sales)sale;
                        if (s.getProducts().getId().equals(pid)) {
                    		purchase += s.getPrice() * s.getQuantity();
                    	}
                    }
                	resultList.add(purchase);
                }
                rowList.add(new CustomerListElement(user.getId(), user.getName(), cost));
            }
            
            //System.out.println(rowlist.size());
            List<ProductListElement> colList = new ArrayList<ProductListElement>(collist.size());
            for(Object obj:collist){
                Products prod = (Products)obj;
                Integer profit = 0;
                for (Object sale: prod.getSaleses()) {
                	Sales s = (Sales)sale;
                	profit += s.getPrice() * s.getQuantity();
                }
                colList.add(new ProductListElement(prod.getId(), prod.getName(), profit));
            }
            HttpServletRequest request = ServletActionContext.getRequest();
            List cateList = cateDAO.findAll();
            request.setAttribute("categories", cateList);
            
			request.setAttribute("rowPage", 1);
//			Integer maxRowPage = rowList.size() / 10;
//			if (rowList.size() % 10 != 0) maxRowPage++;
//			request.setAttribute("maxRowPage", maxRowPage);
//			Integer rowLen = 10;
//			if (rowList.size() < 10) rowLen = rowList.size();
			request.setAttribute("rowlist", rowList);
			
			request.setAttribute("colPage", 1);
//			Integer maxColPage = colList.size() / 10;
//			if (colList.size() % 10 != 0) maxColPage++;
//			request.setAttribute("maxColPage", maxColPage);
//			Integer colLen = 10;
//			if (colList.size() < 10) colLen = colList.size();
			request.setAttribute("collist", colList);
//			for (int i = 0; i < rowLen*colLen; i++) {
//				Integer uid = rowList.get(i / colLen).getId();
//				Integer pid = colList.get(i % colLen).getId();
////				String hql = String.format("select sum(t.quantity) from Transaction t where t.uid = %d and t.pid = %d", uid, pid);
////				Query q = session.createQuery(hql);
//				//List sales = q.list();
//				if (sales.get(0) == null) resultList.add(0);
//				else resultList.add(((Long)sales.get(0)).intValue());
//				//System.out
//				//System.out.println("result:"+count.size());
//			}
			request.setAttribute("bigResult", resultList);
			System.out.println(rowList.size());
			System.out.println(rowList.get(19).getName());
			System.out.println(colList.size());
			System.out.println(resultList.size());
            isSucc = true;
        } catch (RuntimeException re) {
            System.out.println(re);
            isSucc = false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        if (isSucc) return SUCCESS;
        else return ERROR;
    }
}
