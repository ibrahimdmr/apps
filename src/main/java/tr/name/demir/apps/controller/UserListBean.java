package tr.name.demir.apps.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import tr.name.demir.apps.dao.UserDAO;
import tr.name.demir.apps.entity.User;

@ManagedBean
@RequestScoped
public class UserListBean implements Serializable {
	private List<User> users;
	private UserDAO userDAO = new UserDAO();

	public UserListBean() {
		users = userDAO.getList();
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
