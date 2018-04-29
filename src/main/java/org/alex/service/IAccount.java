package org.alex.service;

public interface IAccount {

	String addAccount(String account);
	String updateAccount(String accountToUpdate);
	String removeAccount (int id);
}
