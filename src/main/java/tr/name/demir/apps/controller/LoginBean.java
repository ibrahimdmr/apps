package tr.name.demir.apps.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tr.name.demir.apps.dao.UserDAO;
import tr.name.demir.apps.entity.User;
import tr.name.demir.apps.util.FacesUtil;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	private String username;
	private String password;
	private User user;
	private UserDAO userDAO = new UserDAO();

	public String doLogin() {
		if (!validate()) {
			return null;
		}
		user = userDAO.getUserByUsername(username);
		return "/private/home?faces-redirect=true";

	}

	private boolean validate() {
		if (username == null || username.trim().length() == 0) {
			FacesUtil.addError("Kullanıcı adı boş olamaz...");
			return false;
		}
		if (password == null || password.trim().length() == 0) {
			FacesUtil.addError("Şifre boş olamaz...");
			return false;
		}
		User dbUser = userDAO.getUserByUsername(username);
		if (dbUser == null || !dbUser.getPassword().equals(password)) {
			FacesUtil.addError("Kullanıcı adı yada şifre hatalı");
			return false;
		}
		return true;
	}

	public String doLogout() {
		this.user = null;
		return "/login?faces-redirect=true";

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

}
