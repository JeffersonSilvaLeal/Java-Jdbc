package java_jdbc.java_jdbc;

import org.junit.Test;

import br.com.java.jdbc.conexao.SingleConnection;

public class TesteBancoJdbc {
	@Test
	public void initBanco() {
		SingleConnection.getConnection();
	}
}
