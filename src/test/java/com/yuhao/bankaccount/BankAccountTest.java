package com.yuhao.bankaccount;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for BankAccount.
 */
public class BankAccountTest {

	Account account = new Account(new BigDecimal("0.00"));
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

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

	@Test
	public void statementPrintingTest() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
				.withZone(ZoneId.systemDefault());
		account.getStatements().add(new Statement("Deposit   ", ZonedDateTime.parse("2018-10-08 00:00:00", formatter),
				new BigDecimal("100.00"), new BigDecimal("100.00")));
		account.getStatements().add(new Statement("withdrawal", ZonedDateTime.parse("2018-10-08 00:01:00", formatter),
				new BigDecimal("50.00"), new BigDecimal("50.00")));
		account.getStatements().add(new Statement("withdrawal", ZonedDateTime.parse("2018-10-08 00:02:00", formatter),
				new BigDecimal("50.00"), new BigDecimal("0.00")));
		account.statementPrinting();
		assertEquals("Operation  | Date                | Amount | Balance\r\n"
				+ "Deposit    | 2018-10-08 00:00:00 | 100.00 | 100.00\r\n"
				+ "withdrawal | 2018-10-08 00:01:00 | 50.00 | 50.00\r\n"
				+ "withdrawal | 2018-10-08 00:02:00 | 50.00 | 0.00\r\n", outContent.toString());
	}
}
