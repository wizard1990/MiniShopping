package actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;

import DBModel.CategoriesDAO;
import DBModel.CustomerListElement;
import DBModel.HibernateSessionFactory;
import DBModel.ProductListElement;
import DBModel.Products;
import DBModel.Sales;
import DBModel.Users;

import com.opensymphony.xwork2.ActionSupport;

public class NextPageAction extends ActionSupport {
	private Integer rowPage;
	private Integer colPage;
	public String execute() throws Exception{
		Session session = null;
        boolean isSucc = false;
        CategoriesDAO cateDAO = new CategoriesDAO();
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession hSession = request.getSession();
        String age = (String)hSession.getAttribute("sAge"); 
        String state = (String)hSession.getAttribute("sState"); 
        String cid = (String)hSession.getAttribute("sCate");
        String rowType = (String)hSession.getAttribute("sRowtype");
        String stateFilter = "";
        if (state.length() > 0) {
        	stateFilter = String.format("u.state = '%s' ", state);
        }
        String ageFilter = "";
        
        if (age.length() > 0) {
        	Integer lb = Integer.parseInt(age);
            Integer ub = 0;
            switch (lb) {
            	case 12: ub = 18;
            			 break;
            	case 18: ub = 45;
            			 break;
            	case 45: ub = 65;
            			 break;
            	case 65: ub = 999;
            			 break;
            	default:
            			 break;
            }
        	ageFilter = String.format("u.age >= %d and u.age < %d ", lb, ub);
        }
        String categoryFilter = "";
        if (cid.length() > 0) {
        	categoryFilter = String.format("where p.categories.id = %d", Integer.parseInt(cid));
        }

        if (stateFilter.length() > 0) {
        	stateFilter = "where " + stateFilter;
        	if(ageFilter.length() > 0) {
        		ageFilter = "and " + ageFilter;
        	}
        }
        else if (ageFilter.length() > 0) {
        	ageFilter = "where " + ageFilter;
        }
        
        //String rowhql = "select u.id, u.name as uName, sum(t.quantity * p.price) from Users u, Sales t, Products p where t.uid = u.id and t.pid = p.id and t.finished = 't' " + stateFilter + ageFilter + "group by u.id order by sum(t.quantity * p.price) desc";
        String rowhql = "from Users u " + stateFilter + ageFilter + "order by u.name";
        //String colhql = "select p.id, p.name as pName, sum(t.quantity * p.price) from Transaction t, Product p where t.pid = p.id " + categoryFilter + "group by p.id order by sum(t.quantity * p.price) desc";
        String colhql = "from Products p " + categoryFilter + "order by p.name";
        try {
            session = HibernateSessionFactory.getSession();
            
            Query rowQuery = session.createQuery(rowhql);
            rowQuery.setMaxResults(20);
            rowQuery.setFirstResult((rowPage - 1) * 20);
            Query colQuery = session.createQuery(colhql);
            colQuery.setMaxResults(10);
            colQuery.setFirstResult((colPage - 1) * 20);
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
            List cateList = cateDAO.findAll();
            request.setAttribute("categories", cateList);
            
//			request.setAttribute("rowPage", 1);
//			Integer maxRowPage = rowList.size() / 10;
//			if (rowList.size() % 10 != 0) maxRowPage++;
//			request.setAttribute("maxRowPage", maxRowPage);
//			Integer rowLen = 10;
//			if (rowList.size() < 10) rowLen = rowList.size();
			request.setAttribute("rowlist", rowList);
			
//			request.setAttribute("colPage", 1);
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
	public Integer getRowPage() {
		return rowPage;
	}
	public void setRowPage(Integer rowPage) {
		this.rowPage = rowPage;
	}
	public Integer getColPage() {
		return colPage;
	}
	public void setColPage(Integer colPage) {
		this.colPage = colPage;
	}
}