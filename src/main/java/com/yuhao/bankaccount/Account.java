package com.yuhao.bankaccount;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {

	private BigDecimal balance;
	private List<Statement> statements = new ArrayList<>();

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
		this.getStatements().add(new Statement(ZonedDateTime.now(), amount, this.getBalance()));
	}

	public void withdrawal(BigDecimal amount) {
		// TODO Auto-generated method stub
	}

	public void withdrawalAll() {
		// TODO Auto-generated method stub
	}

	public void checkOperations() {
		// TODO Auto-generated method stub
	}

}
