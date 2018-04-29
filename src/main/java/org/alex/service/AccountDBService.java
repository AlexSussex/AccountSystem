package org.alex.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.alex.domain.Account;
import org.alex.util.JSONUtil;

public class AccountDBService implements IAccount {

	@Inject
	private JSONUtil util;
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Transactional(Transactional.TxType.REQUIRED)
	public String addAccount(String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		if (manager.contains(anAccount)) {
			return ("{\"message\": \"The account already exists in the database.\"}");
		}
		else {
			manager.persist(anAccount);      
			return "{\"message\": \"The account has been sucessfully added to the database.\"}";
		}
		
	}
	
	public String removeAccount (int id) {
		Account account = findAccount(id);
		
		if (account !=null) {
			manager.remove(account);
			return "{\"message\": \"The account has been removed from the database.\"}";
		}
		else {
			return "{\"message\": \"The account cannot be found in the database.\"}";
		}
	}
	public Account findAccount(int id) {
		return manager.find(Account.class, id);
	}
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	public String updateAccount(String jsonString) {
		Account anAccount = util.getObjectForJSON(jsonString, Account.class);
		manager.merge(anAccount);
		return "{\"message\": \"The account has been sucessfully updated.\"}";
	}

	@Override
	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM Account a");
		Collection <Account> accounts = query.getResultList();
		return util.getJSONForObject(accounts);
	}

}
