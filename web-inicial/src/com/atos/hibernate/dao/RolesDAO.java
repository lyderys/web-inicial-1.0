package com.atos.hibernate.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.Roles;

/**
 * A data access object (DAO) providing persistence and search support for Roles
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.atos.hibernate.Roles
 * @author MyEclipse Persistence Tools
 */
@Repository("roles_dao")
@Scope("prototype")
public class RolesDAO {
	private static final Logger log = LoggerFactory.getLogger(RolesDAO.class);
	// property constants
	public static final String DESCRIPCION_ROL = "descripcionRol";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Roles transientInstance) {
		log.debug("saving Roles instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Roles persistentInstance) {
		log.debug("deleting Roles instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Roles findById(java.lang.Byte id) {
		log.debug("getting Roles instance with id: " + id);
		try {
			Roles instance = (Roles) getCurrentSession().get(
					"com.atos.hibernate.Roles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Roles> findByExample(Roles instance) {
		log.debug("finding Roles instance by example");
		try {
			List<Roles> results = (List<Roles>) getCurrentSession()
					.createCriteria("com.atrium.hibernate.Roles")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Roles instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Roles as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Roles> findByDescripcionRol(Object descripcionRol) {
		return findByProperty(DESCRIPCION_ROL, descripcionRol);
	}

	public List findAll() {
		log.debug("finding all Roles instances");
		try {
			String queryString = "from Roles";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Roles merge(Roles detachedInstance) {
		log.debug("merging Roles instance");
		try {
			Roles result = (Roles) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Roles instance) {
		log.debug("attaching dirty Roles instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Roles instance) {
		log.debug("attaching clean Roles instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RolesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RolesDAO) ctx.getBean("RolesDAO");
	}
}