package dbService.testing;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.alex.service.AccountDBService;
import org.alex.util.JSONUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

	private static final String  MOCK_ARRAY= "[{\"firstName\":\"Alice\",\"secondName\":\"Taylor\",\"accountNumber\":\"1555\"}]";

	private static final String MOCK_OBJECT = "{\"firstName\":\"Alice\",\"secondName\":\"Taylor\",\"accountNumber\":\"1555\"}";

	@Before
	public void init() {
		dbOperations.setEm(entityManager);
		myJson = new JSONUtil();
		dbOperations.setJsonObject(myJson);
	}

	@Test
	public void testAddAccount() {
		String response = dbOperations.addAccount(MOCK_OBJECT);
		Assert.assertEquals("{\"message\": \"The account has been sucessfully added to the database.\"}", response);
	}

}
