package com.yuhao.bankaccount.serivce;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.yuhao.bankaccount.domain.Statement;

public class PrintService {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public void statementPrinting(List<Statement> statements) {
		List<String> operations = new ArrayList<>();
		operations.add(String.join(" | ", "Operation ", "Date               ", "Amount", "Balance"));
		statements.forEach(s -> {
			operations.add(String.join(" | ", s.getOperation(), s.getDate().format(formatter), s.getAmount().toString(),
					s.getBalance().toString()));
		});
		operations.forEach(System.out::println);
	}
}
