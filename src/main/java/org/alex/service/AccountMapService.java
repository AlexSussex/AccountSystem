package org.alex.service;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.alex.domain.Account;
import org.alex.util.JSONUtil;

@Alternative
public class AccountMapService implements IAccount {

	private JSONUtil util = new JSONUtil();

	private Map<Integer, Account> accounts = new HashMap<Integer, Account>();;

	public String addAccount(String jsonAccount) {
		Account newAccount = util.getObjectForJSON(jsonAccount, Account.class);
		if (accounts.values().stream()
				.filter(account -> account.getAccountNumber().equals(newAccount.getAccountNumber())).count() > 0) {
			return "{\"message\": \"The account is already stored in the list.\"}";
		} else {
			accounts.put(accounts.size() + 1, newAccount);
			return ("{\"message\": \"The account has been sucessfully added to the list.\"}");
		}
	}

	public Map<Integer, Account> getAccounts() {
		return accounts;
	}

	public String removeAccount(int key) {
		if (accounts.containsKey(key)) {
			accounts.remove(key);
			return "{\"message\": \"The account has been removed.\"}";
		} else {
			return "{\"message\": \"The account could not be found in our list.\"}";
		}
	}

	public String updateAccount(String accountToUpdate) {
		Account anAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		if (accounts.values().stream().filter(account -> account.getId() == anAccount.getId()) != null) {
			accounts.forEach((key, value) -> {
				if (value.getId() == anAccount.getId()) {
					accounts.replace(key, anAccount);
				}
			});
			return "{\"message\": \"The account has been sucessfully updated.\"}";
		} else {
			return "{\"message\": \"This account cannot be updated.\"}";
		}
	}

	@Override
	public String getAllAccounts() {
		return util.getJSONForObject(accounts);
	}

}
