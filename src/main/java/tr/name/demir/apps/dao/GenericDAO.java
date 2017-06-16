package tr.name.demir.apps.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class GenericDAO<T> implements Serializable {
	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public List<T> getList() {
		String query = String.format("Select %s From %s %s",
				persistentClass.getSimpleName().substring(0, 1).toLowerCase(), persistentClass.getSimpleName(),
				persistentClass.getSimpleName().substring(0, 1).toLowerCase());
		return this.getList(query);
	}

	public List<T> getList(String query) {
		return this.getList(query, null);
	}

	public List<T> getList(String query, Map<String, Object> parameters) {
		return this.getList(query, parameters, 0, 0);
	}

	@SuppressWarnings("unchecked")
	public List<T> getList(String query, Map<String, Object> parameters, int page, int rows) {
		try {
			Query q = EntityManagerHelper.getEntityManager().createQuery(query);
			if (parameters != null && !parameters.isEmpty()) {
				for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
					q.setParameter(parameter.getKey(), parameter.getValue());
				}
			}
			if (page > 0 && rows > 0) {
				int offset = ((page - 1) * rows) + 1;
				q.setFirstResult(offset);
				q.setMaxResults(rows);
			}
			return q.getResultList();
		} catch (PersistenceException e) {
			return new ArrayList<T>();
		}
	}

	public T findById(int id) {
		return EntityManagerHelper.getEntityManager().find(persistentClass, id);
	}

	public void insert(T entity) {
		EntityManagerHelper.getEntityManager().persist(entity);
	}

	public void update(T entity) {
		EntityManagerHelper.getEntityManager().merge(entity);
	}
}
