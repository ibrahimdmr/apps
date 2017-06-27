package tr.name.demir.apps.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tr.name.demir.apps.entity.Todo;
import tr.name.demir.apps.entity.TodoStatus;
import tr.name.demir.apps.entity.User;

public class TodoDAO extends GenericDAO<Todo> {

	public List<Todo> getTodoByStatus(TodoStatus status) {
		String query = "Select t From Todo t Where t.status=:status";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("status", status);
		return getList(query, parameters);
	}

	public List<Todo> getActiveTodoList() {
		String query = "Select t From Todo t Where  (t.status = :statusNEW ro t.status = :statusINPROGESS) Order By t.targetDate";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("statusNEW", TodoStatus.NEW);
		parameters.put("statusINPROGESS", TodoStatus.INPROGRESS);
		return getList(query, parameters);
	}

	public List<Todo> getListByUser(User user, TodoStatus status) {
		String query = "Select t From Todo t Where  t.createdBy = :user And t.status = :status";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("user", user);
		parameters.put("status", status);
		return getList(query, parameters);
	}

}
