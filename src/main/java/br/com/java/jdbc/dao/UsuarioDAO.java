package br.com.java.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.java.jdbc.conexao.SingleConnection;
import br.com.java.jdbc.model.Usuario;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() {
		connection = SingleConnection.getConnection();
	}

	public void insert(Usuario usuario) {
		try {
			String sql = "insert into usuariojava (id, nome, email) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(1, usuario.getId());
			insert.setString(2, usuario.getNome());
			insert.setString(3, usuario.getEmail());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<Usuario> ListAll() {
		List<Usuario> list = new ArrayList<Usuario>();

		String sql = "select * from usuariojava";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getLong("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setEmail(resultado.getString("email"));
				list.add(usuario);
			}

			return list;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return list;

	}

	public Usuario findById(long id) {
		Usuario usuario = new Usuario();

		String sql = "select * from usuariojava where id = " + id;

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				usuario.setId(resultado.getLong("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setEmail(resultado.getString("email"));
			}

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return usuario;

	}

	public void update(Usuario usuario) {

		String sql = "update usuariojava set nome = ? where id = " + usuario.getId();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getNome());

			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
