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

	@Test()
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

}
