package tr.name.demir.apps.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tr.name.demir.apps.entity.User;

public class UserDAO extends GenericDAO<User> {

	public User getUserByUsername(String username) {
		String query = "Select u From User u Where u.username=:username";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("username", username);
		List<User> users = getList(query, parameters);
		if (users != null && users.size() > 0)
			return users.get(0);
		else
			return null;
	}

}
