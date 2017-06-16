package tr.name.demir.apps.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tr.name.demir.apps.dao.AccountDAO;
import tr.name.demir.apps.entity.Account;

@ManagedBean
@ViewScoped
public class AccountCreateBean implements Serializable {
	private Account account = new Account();
	private AccountDAO accountDAO = new AccountDAO();
	

	public String save() {
		accountDAO.insert(account);
		return "/private/account/list?faces-redirect=true";
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
