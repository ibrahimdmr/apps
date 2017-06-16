package tr.name.demir.apps.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tr.name.demir.apps.dao.AccountDAO;
import tr.name.demir.apps.entity.Account;
import tr.name.demir.apps.util.FacesUtil;

@ManagedBean
@ViewScoped
public class AccountEditBean implements Serializable {
	private Account account;
	private AccountDAO accountDAO = new AccountDAO();

	public AccountEditBean() {
		int id = Integer.parseInt(FacesUtil.getParamter("id"));
		account = accountDAO.findById(id);
	}

	public String save() {
		accountDAO.update(account);
		return "/private/account/list?faces-redirect=true";
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
