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
 * Transaction entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see DBModel.Transaction
 * @author MyEclipse Persistence Tools
 */

public class TransactionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TransactionDAO.class);
	// property constants
	public static final String UID = "uid";
	public static final String PID = "pid";
	public static final String CREDITNUM = "creditnum";
	public static final String QUANTITY = "quantity";
	public static final String FINISHED = "finished";

	public void save(DBModel.Transaction transientInstance) {
		log.debug("saving Transaction instance");
		Session session = null;
        Transaction tran = null;
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
            if (session != null) {
                getSession().close();
            }
        }
	}

	public void delete(DBModel.Transaction persistentInstance) {
		log.debug("deleting Transaction instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DBModel.Transaction findById(java.lang.Integer id) {
		log.debug("getting Transaction instance with id: " + id);
		try {
			DBModel.Transaction instance = (DBModel.Transaction) getSession().get(
					"DBModel.Transaction", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DBModel.Transaction instance) {
		log.debug("finding Transaction instance by example");
		try {
			List results = getSession().createCriteria("DBModel.Transaction")
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
		log.debug("finding Transaction instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Transaction as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUid(Object uid) {
		return findByProperty(UID, uid);
	}

	public List findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public List findByCreditnum(Object creditnum) {
		return findByProperty(CREDITNUM, creditnum);
	}

	public List findByQuantity(Object quantity) {
		return findByProperty(QUANTITY, quantity);
	}

	public List findByFinished(Object finished) {
		return findByProperty(FINISHED, finished);
	}

	public List findAll() {
		log.debug("finding all Transaction instances");
		try {
			String queryString = "from Transaction";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DBModel.Transaction merge(DBModel.Transaction detachedInstance) {
		log.debug("merging Transaction instance");
		try {
			DBModel.Transaction result = (DBModel.Transaction) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DBModel.Transaction instance) {
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
            if(session != null) session.close();
        }
	}

	public void attachClean(DBModel.Transaction instance) {
		log.debug("attaching clean Transaction instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}