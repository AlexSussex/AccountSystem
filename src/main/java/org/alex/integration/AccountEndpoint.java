package org.alex.integration;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.alex.service.IAccount;

@Path("/account")
public class AccountEndpoint {

	@Inject
	private IAccount data;

	@POST
	@Path("/json")
	@Produces({ "aplication/json" })
	public String addAccount(String account) {
		return data.addAccount(account);
	}

	@PUT
	@Path("/json")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String updateAccount(String accountToUpdate) {
		return data.updateAccount(accountToUpdate);
	}

	@DELETE
	@Path("/json/{id}")
	@Produces("application/json")
	public String removeAccount(@PathParam("id") int id) {
		return data.removeAccount(id);
	}
	
	@GET
	@Path("/json")
	@Produces("application/json")
	public String getAllAccounts() {
		return data.getAllAccounts();
	}
}
