package DBModel;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for Carts entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see DBModel.Carts
  * @author MyEclipse Persistence Tools 
 */

public class CartsDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(CartsDAO.class);
		//property constants
	public static final String QUANTITY = "quantity";
	public static final String PRICE = "price";



    
    public void save(Carts transientInstance) {
        log.debug("saving Carts instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Carts persistentInstance) {
        log.debug("deleting Carts instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Carts findById( java.lang.Integer id) {
        log.debug("getting Carts instance with id: " + id);
        try {
            Carts instance = (Carts) getSession()
                    .get("DBModel.Carts", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Carts instance) {
        log.debug("finding Carts instance by example");
        try {
            List results = getSession()
                    .createCriteria("DBModel.Carts")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Carts instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Carts as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByQuantity(Object quantity
	) {
		return findByProperty(QUANTITY, quantity
		);
	}
	
	public List findByPrice(Object price
	) {
		return findByProperty(PRICE, price
		);
	}
	

	public List findAll() {
		log.debug("finding all Carts instances");
		try {
			String queryString = "from Carts";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Carts merge(Carts detachedInstance) {
        log.debug("merging Carts instance");
        try {
            Carts result = (Carts) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Carts instance) {
        log.debug("attaching dirty Carts instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Carts instance) {
        log.debug("attaching clean Carts instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}