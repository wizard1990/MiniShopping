package actions;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import DBModel.StateListElement;
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
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession hSession = request.getSession();
        hSession.setAttribute("sAge", age); 
        hSession.setAttribute("sState", state); 
        hSession.setAttribute("sCate", cid);
        hSession.setAttribute("sRowtype", rowType);
        String stateFilter = "";
        if (state.length() > 0) {
        	stateFilter = String.format("u.state = '%s' ", state);
        }
        String ageFilter = "";
        Integer lb = -1;
        Integer ub = 999;
        if (age.length() > 0) {
        	lb = Integer.parseInt(age);
            ub = 0;
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
        	categoryFilter = String.format("where p.categories.id = %d ", Integer.parseInt(cid));
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
        if (rowType.equals("customer")) {
        	//String rowhql = "select u.id, u.name as uName, sum(t.quantity * p.price) from Users u, Sales t, Products p where t.uid = u.id and t.pid = p.id and t.finished = 't' " + stateFilter + ageFilter + "group by u.id order by sum(t.quantity * p.price) desc";
            String rowhql = "from Users u " + stateFilter + ageFilter + "order by u.name";
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
                        if (cid.length() == 0 || s.getProducts().getCategories().getId().equals(Integer.parseInt(cid))) {
                        	cost += s.getPrice() * s.getQuantity();
                        }
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
                    	if (s.getUsers().getAge() >= lb && s.getUsers().getAge() < ub && (state.length() == 0 || s.getUsers().getState().equals(state))) {
                    		profit += s.getPrice() * s.getQuantity();
                    	}
                    }
                    colList.add(new ProductListElement(prod.getId(), prod.getName(), profit));
                }
                List cateList = cateDAO.findAll();
                request.setAttribute("categories", cateList);
                
    			request.setAttribute("rowPage", 1);
//    			Integer maxRowPage = rowList.size() / 10;
//    			if (rowList.size() % 10 != 0) maxRowPage++;
//    			request.setAttribute("maxRowPage", maxRowPage);
//    			Integer rowLen = 10;
//    			if (rowList.size() < 10) rowLen = rowList.size();
    			request.setAttribute("rowlist", rowList);
    			
    			request.setAttribute("colPage", 1);
//    			Integer maxColPage = colList.size() / 10;
//    			if (colList.size() % 10 != 0) maxColPage++;
//    			request.setAttribute("maxColPage", maxColPage);
//    			Integer colLen = 10;
//    			if (colList.size() < 10) colLen = colList.size();
    			request.setAttribute("collist", colList);
//    			for (int i = 0; i < rowLen*colLen; i++) {
//    				Integer uid = rowList.get(i / colLen).getId();
//    				Integer pid = colList.get(i % colLen).getId();
////    				String hql = String.format("select sum(t.quantity) from Transaction t where t.uid = %d and t.pid = %d", uid, pid);
////    				Query q = session.createQuery(hql);
//    				//List sales = q.list();
//    				if (sales.get(0) == null) resultList.add(0);
//    				else resultList.add(((Long)sales.get(0)).intValue());
//    				//System.out
//    				//System.out.println("result:"+count.size());
//    			}
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
        }
        else {
        	String stateAgeFilter = "";
        	if (lb > 0) {
        		stateAgeFilter = String.format("and s.users.age >= %d and s.users.age < %d ", lb, ub);
        	}
        	String stateStateFilter = "";
        	if (state.length() > 0) {
        		stateStateFilter = String.format("and s.users.state = '%s' ", state);
        	}
        	String stateCategoryFilter = "";
        	if (cid.length() > 0) {
        		stateCategoryFilter = String.format("and s.products.categories.id = %d ", Integer.parseInt(cid));
        	}
        	String rowhql = "select s.users.state, sum(s.price * s.quantity) from Sales s where 1 = 1 " + stateAgeFilter + stateStateFilter + stateCategoryFilter + "group by s.users.state order by s.users.state";
        	String colhql = "from Products p " + categoryFilter + "order by p.name";
        	try {
        		session = HibernateSessionFactory.getSession();
        		Query rowQuery = session.createQuery(rowhql);
        		rowQuery.setMaxResults(20);
        		List rowlist = rowQuery.list();
        		
        		Query colQuery = session.createQuery(colhql);
                colQuery.setMaxResults(10);
                List collist = colQuery.list();
                Integer rowLen = rowlist.size();
                if (rowLen == 0) rowLen = 1;
    			Integer colLen = collist.size();
        		
    			String[] stateList = {"Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"};
    			List<Integer> resultList = new ArrayList<Integer>(rowLen * colLen);
                List<StateListElement> rowList = new ArrayList<StateListElement>(20);
                if (stateStateFilter.length() > 0) {
                	if (rowlist.size() > 0) {
                		Object[] ele = (Object[])rowlist.get(0);
                		rowList.add(new StateListElement((String)ele[0], ((Long)ele[1]).intValue()));
                	}
                	else {
                		rowList.add(new StateListElement(state, 0));
                	}
                }
                else {
                	for (int i = 0; i < 20; i++) {
                    	rowList.add(new StateListElement(stateList[i], 0));
                    }
                }
        		
                for (Object obj:rowlist) {
                	Object[] stateElement = (Object[])obj;
                	Integer cost = ((Long)stateElement[1]).intValue();
                	for (Object element:rowList) {
                		StateListElement ele = (StateListElement)element;
                		if(ele.getName().equals(stateElement[0])) {
                			ele.setCost(cost);
                			break;
                		}
                	}
                }
                
                List<ProductListElement> colList = new ArrayList<ProductListElement>(collist.size());
                for(Object obj:collist){
                    Products prod = (Products)obj;
                    Integer profit = 0;
                    for (Object sale: prod.getSaleses()) {
                    	Sales s = (Sales)sale;
                    	if (s.getUsers().getAge() >= lb && s.getUsers().getAge() < ub && (state.length() == 0 || s.getUsers().getState().equals(state))) {
                    		profit += s.getPrice() * s.getQuantity();
                    	}
                    }
                    colList.add(new ProductListElement(prod.getId(), prod.getName(), profit));
                }
                
                for (int i = 0; i < rowLen*colLen; i++) {
    				String stateName = rowList.get(i / colLen).getName();
    				Integer pid = colList.get(i % colLen).getId();
    				String hql = String.format("select sum(s.price*s.quantity) from Sales s where s.users.state = '%s' and s.products.id = %d and s.users.age >= %d and s.users.age < %d", stateName, pid, lb.intValue(), ub.intValue());
    				Query q = session.createQuery(hql);
    				List purchase = q.list();
    				if (purchase.get(0) == null) resultList.add(0);
    				else resultList.add(((Long)purchase.get(0)).intValue());
    				System.out.println(resultList.get(resultList.size() - 1));
    			}
                
                List cateList = cateDAO.findAll();
                request.setAttribute("categories", cateList);
                
                request.setAttribute("rowPage", 1);
    			request.setAttribute("rowlist", rowList);	
    			request.setAttribute("colPage", 1);
    			request.setAttribute("collist", colList);
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
        }
        
        if (isSucc) return SUCCESS;
        else return ERROR;
    }
}
