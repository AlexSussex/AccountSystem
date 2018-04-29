package org.alex.integration;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.alex.service.IAccount;

@Path("/account")
public class AccountEndpoint {

	@Inject
	private IAccount data;
	
	@POST
	@Path("/json")
	@Produces ({"aplication/json"})
	public String addAccount(String account) {
		return data.addAccount(account);
	}	
}
