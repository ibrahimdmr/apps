package tr.name.demir.apps.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import tr.name.demir.apps.dao.AccountDAO;
import tr.name.demir.apps.entity.Account;

@ManagedBean
@RequestScoped
public class AccountListBean implements Serializable {
	private List<Account> accounts;
	private AccountDAO accountDAO = new AccountDAO();

	public AccountListBean() {
		accounts = accountDAO.getList();
		
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
