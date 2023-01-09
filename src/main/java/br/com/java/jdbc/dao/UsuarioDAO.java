package br.com.java.jdbc.dao;

import java.sql.Connection;

import br.com.java.jdbc.conexao.SingleConnection;

public class UsuarioDAO {

	private Connection connection;
	
	public UsuarioDAO() {
		connection = SingleConnection.getConnection();
	}
}
