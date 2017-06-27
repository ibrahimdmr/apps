package tr.name.demir.apps.dao;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import tr.name.demir.apps.entity.User;
import tr.name.demir.apps.entity.UserRole;
import tr.name.demir.apps.util.Constants;

public class EntityManagerHelper {
	private static final EntityManagerFactory emf;
	private static final ThreadLocal<EntityManager> threadLocal;

	static {
		try {
			InitialContext initialContext = new InitialContext();
			Context environmentContext = (Context) initialContext.lookup("java:/comp/env");
			String objectdbPath = (String) environmentContext.lookup(Constants.DBPATH);
			System.setProperty("objectdb.home", objectdbPath);
			createUser();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		com.objectdb.Enhancer.enhance("tr.name.demir.apps.entity.*");
		emf = Persistence.createEntityManagerFactory("$objectdb/apps.odb");
		threadLocal = new ThreadLocal<EntityManager>();
	}

	@SuppressWarnings("unchecked")
	private static void createUser() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/apps.odb");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select u From User u");
		List<User> users;
		try {
			users = query.getResultList();
		} catch (PersistenceException e) {
			users = null;
		}
		if (users == null || users.size() == 0) {
			User user = new User();
			user.setUsername("ibrahim");
			user.setPassword("password");
			user.setFullname("ibrahim demir");
			user.setRole(UserRole.ADMIN);
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}
		em.close();
		emf.close();
	}

	public static EntityManager getEntityManager() {
		EntityManager em = threadLocal.get();

		if (em == null) {
			em = emf.createEntityManager();
			threadLocal.set(em);
		}
		return em;
	}

	public static void closeEntityManager() {
		EntityManager em = threadLocal.get();
		if (em != null) {
			em.close();
			threadLocal.set(null);
		}
	}

	public static void closeEntityManagerFactory() {
		emf.close();
	}

	public static void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}

	public static void rollback() {
		getEntityManager().getTransaction().rollback();
	}

	public static void commit() {
		getEntityManager().getTransaction().commit();
	}
}