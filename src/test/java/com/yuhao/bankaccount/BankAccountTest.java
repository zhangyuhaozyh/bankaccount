package com.yuhao.bankaccount;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * Unit test for BankAccount.
 */
public class BankAccountTest {

	Account account = new Account(new BigDecimal("0.00"));

	/**
	 * US 1 : deposit some money
	 */
	@Test
	public void depositTest() {
		account.deposit(new BigDecimal("100.00"));
		assertEquals(new BigDecimal("100.00"), account.getBalance());
	}

	/**
	 * US 2 : retrieve some money
	 */
	@Test
	public void withdrawalTest() {
		account = new Account(new BigDecimal("99.00"));
		account.withdrawal(new BigDecimal("50.00"));
		assertEquals(new BigDecimal("49.00"), account.getBalance());
	}

	/**
	 * US 2 : retrieve all money
	 */
	@Test
	public void withdrawalAllTest() {
		account = new Account(new BigDecimal("99.00"));
		account.withdrawalAll();
		assertEquals(new BigDecimal("0"), account.getBalance());
	}

	/**
	 * US 3 : check my operations
	 */
	@Test
	public void checkOperationsTest() {
		account.checkOperations();
		//TODO
	}
}
