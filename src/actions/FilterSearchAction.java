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
        String stateFilter = "";
        if (state.length() > 0) {
        	String.format("and u.state = '%s' ", state);
        }
        String ageFilter = "";
        if (age.length() > 0) {
        	ageFilter = String.format("and u.age >= %d and u.age < %d ", Integer.parseInt(age), Integer.parseInt(age) + 10);
        }
        String categoryFilter = "";
        if (cid.length() > 0) {
        	categoryFilter = String.format("and p.cid = %d", Integer.parseInt(cid));
        }
        String rowhql = "select u.id, u.name as uName, sum(t.quantity * p.price) from User u, Transaction t, Product p where t.uid = u.id and t.pid = p.id " + stateFilter + ageFilter + "group by u.id order by sum(t.quantity * p.price) desc";
        String colhql = "select p.id, p.name as pName, sum(t.quantity * p.price) from Transaction t, Product p where t.pid = p.id " + categoryFilter + "group by p.id order by sum(t.quantity * p.price) desc";
        try {
            session = HibernateSessionFactory.getSession();
            Query rowQuery = session.createQuery(rowhql);
            Query colQuery = session.createQuery(colhql);
            List rowlist = rowQuery.list();
            List<CustomerListElement> rowList = new ArrayList<CustomerListElement>(rowlist.size());
            for(Object obj:rowlist){
                Object[] objs = (Object[])obj;
                rowList.add(new CustomerListElement((Integer)objs[0], (String)objs[1], ((Long)objs[2]).intValue()));
            }
            List collist = colQuery.list();
            List<ProductListElement> colList = new ArrayList<ProductListElement>(collist.size());
            for(Object obj:collist){
                Object[] objs = (Object[])obj;
                colList.add(new ProductListElement((Integer)objs[0], (String)objs[1], ((Long)objs[2]).intValue()));
            }
            HttpServletRequest request = ServletActionContext.getRequest();
            
			request.setAttribute("rowPage", 1);
			Integer maxRowPage = rowList.size() / 10;
			if (rowList.size() % 10 != 0) maxRowPage++;
			request.setAttribute("maxRowPage", maxRowPage);
			Integer rowLen = 10;
			if (rowList.size() < 10) rowLen = rowList.size();
			request.setAttribute("rowlist", rowList.subList(0, rowLen));
			
			request.setAttribute("colPage", 1);
			Integer maxColPage = colList.size() / 10;
			if (colList.size() % 10 != 0) maxColPage++;
			request.setAttribute("maxColPage", maxColPage);
			Integer colLen = 10;
			if (colList.size() < 10) colLen = colList.size();
			List subList = colList.subList(0, colLen);
			System.out.println(subList);
			request.setAttribute("collist", colList.subList(0, colLen));
			
			List<Integer> resultList = new ArrayList<Integer>(rowLen * colLen);
			for (int i = 0; i < rowLen*colLen; i++) {
				Integer uid = (Integer)((Object[])rowlist.get(i / colLen))[0];
				Integer pid = (Integer)((Object[])collist.get(i % colLen))[0];
				System.out.println(uid+" "+pid);
				String hql = String.format("select sum(t.quantity) from Transaction t where t.uid = %d and t.pid = %d", uid, pid);
				Query q = session.createQuery(hql);
				List sales = q.list();
				if (sales.get(0) == null) resultList.add(0);
				else resultList.add(((Long)sales.get(0)).intValue());
				//System.out
				//System.out.println("result:"+count.size());
			}
			request.setAttribute("bigResult", resultList);
			System.out.println(resultList);
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
