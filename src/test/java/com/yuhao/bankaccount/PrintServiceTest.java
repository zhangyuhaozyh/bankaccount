package com.yuhao.bankaccount;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yuhao.bankaccount.domain.Account;
import com.yuhao.bankaccount.domain.Statement;
import com.yuhao.bankaccount.serivce.PrintService;

/**
 * Unit test for PrintService.
 */
@RunWith(MockitoJUnitRunner.class)
public class PrintServiceTest {

	@Mock
	Account account;

	private PrintService printService = new PrintService();

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final String NOTRANSACTIONPRINT = "You have no transaction history.\r\n";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

	@Before
	public void setUp() {
		List<Statement> statements = new ArrayList<>();
		statements.add(new Statement("Deposit   ", ZonedDateTime.parse("2018-10-08 00:00:00", formatter),
				new BigDecimal("100.00"), new BigDecimal("100.00")));
		statements.add(new Statement("withdrawal", ZonedDateTime.parse("2018-10-08 00:01:00", formatter),
				new BigDecimal("50.00"), new BigDecimal("50.00")));
		statements.add(new Statement("withdrawal", ZonedDateTime.parse("2018-10-08 00:02:00", formatter),
				new BigDecimal("50.00"), new BigDecimal("0.00")));
		Mockito.when(account.getStatements()).thenReturn(statements);
		setUpStreams();
	}

	@Test
	public void statementPrintingTest() {
		printService.statementPrinting(account.getStatements());
		assertEquals("Operation  | Date                | Amount | Balance\r\n"
				+ "Deposit    | 2018-10-08 00:00:00 | 100.00 | 100.00\r\n"
				+ "withdrawal | 2018-10-08 00:01:00 | 50.00 | 50.00\r\n"
				+ "withdrawal | 2018-10-08 00:02:00 | 50.00 | 0.00\r\n", outContent.toString());
	}

	@Test
	public void whenNoStatement_thenNoStatementPrintingTest() {
		List<Statement> noStatements = new ArrayList<>();
		Mockito.when(account.getStatements()).thenReturn(noStatements);
		printService.statementPrinting(account.getStatements());
		assertEquals(NOTRANSACTIONPRINT, outContent.toString());
	}

	private void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

}
