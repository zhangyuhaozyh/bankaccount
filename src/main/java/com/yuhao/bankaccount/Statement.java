package com.yuhao.bankaccount;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Statement {
	private ZonedDateTime date;
	private BigDecimal amount;
	private BigDecimal balance;

	public Statement() {
		super();
	}

	public Statement(ZonedDateTime date, BigDecimal amount, BigDecimal balance) {
		super();
		this.date = date;
		this.amount = amount;
		this.balance = balance;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
