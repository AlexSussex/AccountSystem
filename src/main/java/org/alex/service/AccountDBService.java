package org.alex.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.alex.domain.Account;
import org.alex.util.JSONUtil;

public class AccountDBService {

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
	private Account findAccount(int id) {
		return manager.find(Account.class, id);
	}
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}
}
