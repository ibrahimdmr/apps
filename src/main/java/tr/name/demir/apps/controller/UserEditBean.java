package tr.name.demir.apps.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tr.name.demir.apps.dao.UserDAO;
import tr.name.demir.apps.entity.User;
import tr.name.demir.apps.util.FacesUtil;

@ManagedBean
@ViewScoped
public class UserEditBean implements Serializable {
	private User user;
	private UserDAO userDAO = new UserDAO();

	public UserEditBean() {
		int id = Integer.parseInt(FacesUtil.getParamter("id"));
		user = userDAO.findById(id);
	}

	public String save() {
		userDAO.update(user);
		return "/admin/user/list?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
