package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.AccountManagement;

class AccountManagementTests {

	private AccountManagement accountManagement;

	@BeforeEach
	void setUp() {
		this.accountManagement = new AccountManagement();
	}

	@Test
	void testInsertAccount() {
		String accountName = "TestAccount";

		accountManagement.insertAccount(accountName);

		assertTrue(accountManagement.getAllAccounts().containsKey(accountName));
	}

	@Test
	public void testInsertDuplicateAccount() {
		String accountName = "TestAccount";

		accountManagement.insertAccount(accountName);

		try {
			accountManagement.insertAccount(accountName);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testInsertAccountWithPassword() {
		String accountName = "TestAccount";
		String password = "test-password";

		accountManagement.insertAccountWithPassword(accountName, password);

		assertTrue(accountManagement.getAllAccounts().containsKey(accountName));
	}

	@Test
	public void testInsertDuplicateAccountWithPassword() {
		String accountName = "TestAccount";
		String password = "test-password";

		accountManagement.insertAccount(accountName);

		try {
			accountManagement.insertAccountWithPassword(accountName, password);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testSwitchAccount() {
		String accountName = "TestAccount";

		accountManagement.insertAccount(accountName);
		accountManagement.switchAccount(accountName);

		assertEquals(accountManagement.getCurrentAccount(), accountManagement.getAllAccounts().get(accountName));
	}

	@Test
	public void testSwitchAccountNonExistent() {
		String accountName = "TestAccount";

		try {
			accountManagement.switchAccount(accountName);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testSwitchAccountWithPassword() {
		String accountName = "TestAccount";
		String password = "test-password";

		accountManagement.insertAccountWithPassword(accountName, password);
		accountManagement.switchAccountWithPassword(accountName, password);

		assertEquals(accountManagement.getCurrentAccount(), accountManagement.getAllAccounts().get(accountName));
	}

	@Test
	public void testSwitchAccountNonExistentWithPassword() {
		String accountName = "TestAccount";
		String password = "test-password";

		try {
			accountManagement.switchAccountWithPassword(accountName, password);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testSwitchAccountWrongPassword() {
		String accountName = "TestAccount";
		String password = "test-password";

		accountManagement.insertAccountWithPassword(accountName, password);

		try {
			accountManagement.switchAccountWithPassword(accountName, "wrong-password");
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testAddAccountPassword() {
		String accountName = "TestAccount";

		accountManagement.insertAccount(accountName);
		accountManagement.switchAccount(accountName);
		accountManagement.changeAccountPassword("", "new-password");

		assertEquals(accountManagement.getCurrentAccount().getPassword(), "new-password");
	}

	@Test
	public void testRemoveAccountPassword() {
		String accountName = "TestAccount";
		String password = "test-password";

		accountManagement.insertAccountWithPassword(accountName, password);
		accountManagement.switchAccountWithPassword(accountName, password);
		accountManagement.changeAccountPassword(password, "");

		assertTrue(accountManagement.getCurrentAccount().getPassword().isEmpty());
	}

	@Test
	public void testChangeAccountPassword() {
		String accountName = "TestAccount";
		String password = "test-password";

		accountManagement.insertAccountWithPassword(accountName, password);
		accountManagement.switchAccountWithPassword(accountName, password);
		accountManagement.changeAccountPassword(password, "new-password");

		assertEquals(accountManagement.getCurrentAccount().getPassword(), "new-password");
	}

	@Test
	public void testChangeAccountWrongOldPassword() {
		String accountName = "TestAccount";
		String password = "test-password";

		accountManagement.insertAccountWithPassword(accountName, password);
		accountManagement.switchAccountWithPassword(accountName, password);
		try {
			accountManagement.changeAccountPassword("wrong-password", "new-password");
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

}
