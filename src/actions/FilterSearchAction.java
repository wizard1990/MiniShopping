package actions;

import java.util.List;

import DBModel.HibernateSessionFactory;

import com.opensymphony.xwork2.ActionSupport;
import org.hibernate.Query;
import org.hibernate.Session;

public class FilterSearchAction extends ActionSupport {
	private String quarter;
	private String age;
	private String cid;
	private String state;
	private String rowType;
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
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
        String hql = "select u.name uName, sum(t.amount * p.price) tSum from User u, Transaction t, Product p where t.uid = u.id and t.pid = p.id group by u.id order by tSum";
        try {
            session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(hql);
            List<Object> objlist = query.list();
            for(Object obj:objlist){
                Object[] objs = (Object[])obj;
                System.out.println("将object拆分为objs数组后的长度："+objs.length);
                System.out.println("将object拆分为objs数组后的打印："+objs[0]);
                System.out.println("将object拆分为objs数组后的打印："+objs[1]);
            }
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
