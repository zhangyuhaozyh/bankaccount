package com.yuhao.bankaccount;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {

	private BigDecimal balance;
	private List<Statement> statements = new ArrayList<>();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Account() {
		super();
	}

	public Account(BigDecimal balance) {
		super();
		this.balance = balance;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	public void deposit(BigDecimal amount) {
		this.setBalance(this.getBalance().add(amount));
		this.getStatements().add(new Statement("Deposit   ", ZonedDateTime.now(), amount, this.getBalance()));
	}

	public void withdrawal(BigDecimal amount) {
		this.setBalance(this.getBalance().subtract(amount));
		this.getStatements().add(new Statement("withdrawal", ZonedDateTime.now(), amount, this.getBalance()));
	}

	public void withdrawalAll() {
		BigDecimal amount = this.getBalance();
		this.setBalance(this.getBalance().subtract(this.getBalance()));
		this.getStatements().add(new Statement("withdrawal", ZonedDateTime.now(), amount, this.getBalance()));
	}

	public void statementPrinting() {
		List<String> operations = new ArrayList<>();
		operations.add(String.join(" | ", "Operation ", "Date               ", "Amount", "Balance"));
		this.statements.forEach(s -> {
			operations.add(String.join(" | ", s.getOperation(), s.getDate().format(formatter), s.getAmount().toString(),
					s.getBalance().toString()));
		});
		operations.forEach(System.out::println);
	}

}
