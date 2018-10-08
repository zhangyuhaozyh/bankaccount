package com.yuhao.bankaccount;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.yuhao.bankaccount.domain.Account;

/**
 * Unit test for BankAccount.
 */
public class BankAccountTest {

	Account account = new Account(new BigDecimal("0.00"));

	@Test
	public void depositTest() {
		account.deposit(new BigDecimal("100.00"));
		assertEquals(new BigDecimal("100.00"), account.getBalance());
	}

	@Test
	public void withdrawalTest() {
		account = new Account(new BigDecimal("99.00"));
		account.withdrawal(new BigDecimal("50.00"));
		assertEquals(new BigDecimal("49.00"), account.getBalance());
	}

	@Test
	public void withdrawalAllTest() {
		account = new Account(new BigDecimal("99.00"));
		account.withdrawalAll();
		assertEquals(new BigDecimal("0.00"), account.getBalance());
	}

}
