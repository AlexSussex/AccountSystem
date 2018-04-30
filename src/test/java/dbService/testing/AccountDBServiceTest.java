package dbService.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.alex.domain.Account;
import org.alex.service.AccountDBService;
import org.alex.util.JSONUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountDBServiceTest {

	@InjectMocks
	private AccountDBService dbOperations;

	@Mock
	private EntityManager entityManager;

	@Mock
	private Query query;

	private JSONUtil myJson;

	private static final String  MOCK_ARRAY= "[{\"id\":1,\"firstName\":\"Alice\",\"secondName\":\"Taylor\",\"accountNumber\":\"1555\"}]";

	private static final String MOCK_OBJECT = "{\"firstName\":\"Alice\",\"secondName\":\"Taylor\",\"accountNumber\":\"1555\"}";

	@Before
	public void init() {
		dbOperations.setManager(entityManager);
		myJson = new JSONUtil();
		dbOperations.setUtil(myJson);
	}

	@Test
	public void testAddAccount() {
		String response = dbOperations.addAccount(MOCK_OBJECT);
		Assert.assertEquals("{\"message\": \"The account has been sucessfully added to the database.\"}", response);
	}
	
	@Test
	public void testRemoveAccount() {
		Mockito.when(dbOperations.findAccount(1)).thenReturn(myJson.getObjectForJSON(MOCK_OBJECT, Account.class));
		String response = dbOperations.removeAccount(1);
		Assert.assertEquals("{\"message\": \"The account has been removed from the database.\"}", response);
		String response2 = dbOperations.removeAccount(5);
		Assert.assertEquals("{\"message\": \"The account cannot be found in the database.\"}", response2);
	}
	
	@Test
	public void testUpdateAccount() {
		Mockito.when(dbOperations.findAccount(1)).thenReturn(myJson.getObjectForJSON(MOCK_OBJECT, Account.class));
		String response = dbOperations.updateAccount("{\"id\":1,\"firstName\":\"Alexandrew\",\"secondName\":\"Stevens\",\"accountNumber\":\"78945623\"}");
		Assert.assertEquals("{\"message\": \"The account has been sucessfully updated.\"}", response);
	}
	
	@Test
	public void testGetAllAccounts() {
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts.add(myJson.getObjectForJSON("{\"id\":1,\"firstName\":\"Alice\",\"secondName\":\"Taylor\",\"accountNumber\":\"1555\"}", Account.class));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		Assert.assertEquals(MOCK_ARRAY, dbOperations.getAllAccounts());
	}

}
