package org.alex.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.alex.domain.Account;
import org.alex.util.JSONUtil;


public class AccountMapService {
	
	private JSONUtil util = new JSONUtil();
	
	private Map <Integer, Account> accounts = new HashMap<Integer, Account>();;

	public String addAccount(String jsonAccount) {
		Account newAccount = util.getObjectForJSON(jsonAccount, Account.class);
		if (accounts.values().stream().filter(account->account.getAccountNumber().equals(newAccount.getAccountNumber())).count()>0){
			return "{\"message\": \"The account is already stored in the list.\"}";
		}
		else {
			accounts.put(accounts.size()+1, newAccount);
			return ("{\"message\": \"The account has been sucessfully added to the list.\"}");
		}
		
	}

	public Map<Integer, Account> getAccounts() {
		return accounts;
	}
}
