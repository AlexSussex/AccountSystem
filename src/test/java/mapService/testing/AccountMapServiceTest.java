package mapService.testing;

import org.alex.domain.Account;
import org.alex.service.AccountMapService;
import org.alex.util.JSONUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountMapServiceTest {

	AccountMapService accounts;

	private Account account1;
	private Account account2;
	private JSONUtil util;

	@Before
	public void init() {
		accounts = new AccountMapService();
		account1 = new Account("Alex", "Developer", "12750589");
		account2 = new Account("Andrew", "Stevens", "16542389");
		util = new JSONUtil();
	}

	@Test
	public void addAndRemoveAccountTest() {
		String expected = "{\"message\": \"The account has been sucessfully added to the list.\"}";
		String actual1 = accounts.addAccount(util.getJSONForObject(account1));
		Assert.assertEquals("{\"message\": \"The account has been removed.\"}", accounts.removeAccount(1));
		Assert.assertEquals(accounts.getAccounts().size(), 0);
		Assert.assertEquals(expected, actual1);
		String actual2 = accounts.addAccount(util.getJSONForObject(account2));
		Assert.assertEquals(accounts.getAccounts().size(), 1);
		Assert.assertEquals(expected, actual2);
		Assert.assertEquals("{\"message\": \"The account is already stored in the list.\"}",
				accounts.addAccount(util.getJSONForObject(account2)));
	}

	@Test
	public void testUpdateAccount() {
		Assert.assertEquals("{\"message\": \"The account has been sucessfully updated.\"}", accounts.updateAccount(
				"{\"id\":1,\"firstName\":\"Andrew\",\"lastName\":\"Developer\",\"accountNumber\":\"12750589\"}"));
	}

	@Test
	public void testGetAllAccounts() {
		accounts.addAccount(util.getJSONForObject(account1));
		accounts.addAccount(util.getJSONForObject(account2));
		Assert.assertEquals("{\"1\":{\"firstName\":\"Alex\",\"secondName\":\"Developer\","
				+ "\"accountNumber\":\"12750589\"},\"2\":{\"firstName\":\"Andrew\","
				+ "\"secondName\":\"Stevens\",\"accountNumber\":\"16542389\"}}", accounts.getAllAccounts());
	}
}