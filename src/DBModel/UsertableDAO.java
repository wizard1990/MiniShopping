package DBModel;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Usertable entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see DBModel.Usertable
 * @author MyEclipse Persistence Tools
 */

public class UsertableDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UsertableDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String ROLE = "role";
	public static final String AGE = "age";
	public static final String STATE = "state";

	public void save(Usertable transientInstance) {
		log.debug("saving Usertable instance");
		Transaction tran=getSession().beginTransaction();
		try {
			getSession().save(transientInstance);
			tran.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		} finally {
			getSession().flush(); 
	        getSession().close();
		}
	}

	public void delete(Usertable persistentInstance) {
		log.debug("deleting Usertable instance");
		Transaction tran=getSession().beginTransaction();
		try {
			getSession().delete(persistentInstance);
			tran.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		} finally {
			getSession().flush(); 
	        getSession().close();
		}
	}

	public Usertable findById(java.lang.Integer id) {
		log.debug("getting Usertable instance with id: " + id);
		try {
			Usertable instance = (Usertable) getSession().get(
					"DBModel.Usertable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Usertable instance) {
		log.debug("finding Usertable instance by example");
		try {
			List results = getSession().createCriteria("DBModel.Usertable")
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
		log.debug("finding Usertable instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Usertable as model where model."
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

	public List findByRole(Object role) {
		return findByProperty(ROLE, role);
	}

	public List findByAge(Object age) {
		return findByProperty(AGE, age);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findAll() {
		log.debug("finding all Usertable instances");
		try {
			String queryString = "from Usertable";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Usertable merge(Usertable detachedInstance) {
		log.debug("merging Usertable instance");
		try {
			Usertable result = (Usertable) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Usertable instance) {
		log.debug("attaching dirty Usertable instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usertable instance) {
		log.debug("attaching clean Usertable instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}