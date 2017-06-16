package tr.name.demir.apps.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tr.name.demir.apps.dao.UserDAO;
import tr.name.demir.apps.entity.User;

@ManagedBean
@ViewScoped
public class UserCreateBean implements Serializable {
	private User user = new User();
	private UserDAO userDAO = new UserDAO();

	public String save() {
		userDAO.insert(user);
		return "/private/user/list?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
