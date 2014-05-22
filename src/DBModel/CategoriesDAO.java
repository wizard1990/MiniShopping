package DBModel;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Categories entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see DBModel.Categories
 * @author MyEclipse Persistence Tools
 */

public class CategoriesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CategoriesDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	public void save(Categories transientInstance) {
		Session session = null;
        Transaction tran = null;
        log.debug("saving Category instance");
        try {
            session = getSession();
            tran = session.beginTransaction();
            session.save(transientInstance);
            tran.commit();
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            try{
                tran.rollback();
            }catch(RuntimeException rbe){
                log.error("Couldn’t roll back transaction", rbe);
                throw rbe;
            }
            throw re;
        } finally {
            if(session != null) getSession().close();
        }
	}

	public void delete(Categories persistentInstance) {
		log.debug("deleting Category instance");
        Session session = null;
        Transaction tran = null;
        try {
            session = getSession();
            tran = session.beginTransaction();
            session.delete(persistentInstance);
            tran.commit();
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            try{
                tran.rollback();
            }catch(RuntimeException rbe){
                log.error("Couldn’t roll back transaction", rbe);
                throw rbe;
            }
            throw re;
        } finally {
            if(session != null) getSession().close();
        }
	}

	public Categories findById(java.lang.Integer id) {
		log.debug("getting Categories instance with id: " + id);
		try {
			Categories instance = (Categories) getSession().get(
					"DBModel.Categories", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Categories instance) {
		log.debug("finding Categories instance by example");
		try {
			List results = getSession().createCriteria("DBModel.Categories")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Categories instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Categories as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all Categories instances");
		try {
			String queryString = "from Categories";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Categories merge(Categories detachedInstance) {
		log.debug("merging Categories instance");
		try {
			Categories result = (Categories) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Categories instance) {
		log.debug("attaching dirty Category instance");
        Session session = null;
        Transaction tran = null;
        try {
            session = getSession();
            tran = session.beginTransaction();
            session.saveOrUpdate(instance);
            tran.commit();
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            try{
                tran.rollback();
            }catch(RuntimeException rbe){
                log.error("Couldn’t roll back transaction", rbe);
                throw rbe;
            }
            throw re;
        } finally {
            if(session != null) getSession().close();
        }
	}

	public void attachClean(Categories instance) {
		log.debug("attaching clean Categories instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}