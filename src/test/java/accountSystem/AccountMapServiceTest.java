package accountSystem;

import static org.junit.Assert.*;
import org.alex.domain.Account;
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
		account2 = new Account("Tib", "Flat", "16542389");
		util = new JSONUtil();
	}
	
	@Test
	public void addAccountTest() {
		Assert.assertEquals("{\"message\": \"The account has been sucessfully added to the list.\"}", accounts.addAccount(util.getJSONForObject(account1)));
		Assert.assertEquals("{\"message\": \"The account has been sucessfully added to the list.\"}", accounts.addAccount(util.getJSONForObject(account2)));
	}
}
