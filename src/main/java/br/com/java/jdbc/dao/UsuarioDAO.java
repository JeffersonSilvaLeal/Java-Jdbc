package br.com.java.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.java.jdbc.conexao.SingleConnection;
import br.com.java.jdbc.model.BeanUsuarioContato;
import br.com.java.jdbc.model.Contato;
import br.com.java.jdbc.model.Usuario;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() {
		connection = SingleConnection.getConnection();
	}

	public void insert(Usuario usuario) {
		try {
			String sql = "insert into usuariojava (nome, email) values (?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, usuario.getNome());
			insert.setString(2, usuario.getEmail());
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

	public void delete(Long id) {
		try {

			String sql = "delete from usuariojava where id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
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

	public void insertTell(Contato contato) {
		try {
			String sql = "insert into contato (numero, tipo, usuario) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, contato.getNumero());
			insert.setString(2, contato.getTipo());
			insert.setLong(3, contato.getUsuario());
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

	public List<BeanUsuarioContato> findUserFone(Long idUsuario) {

		List<BeanUsuarioContato> beanUsuarioContatos = new ArrayList<BeanUsuarioContato>();
		String sql = " select nome, numero, email from contato as cont";
			   sql += " inner join usuariojava as userjava";
			   sql += " on cont.usuario = userjava.id ";
			   sql += " where userjava.id = " + idUsuario;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				BeanUsuarioContato beanUsuarioContato = new BeanUsuarioContato();
				beanUsuarioContato.setEmail(resultSet.getString("email"));
				beanUsuarioContato.setNome(resultSet.getString("nome"));
				beanUsuarioContato.setNumero(resultSet.getString("numero"));
				
				beanUsuarioContatos.add(beanUsuarioContato);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return beanUsuarioContatos;
	}
	
	public void deleteContatosByUser(Long usuario) {
		
		try {
		String sqlContato = " delete from contato where usuario = " + usuario;		
		String sqlUsuario = " delete from usuariojava where id = " + usuario;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sqlContato);
		preparedStatement.executeUpdate();
		connection.commit();
		
		preparedStatement = connection.prepareStatement(sqlUsuario);
		preparedStatement.executeUpdate();
		connection.commit();
		
		}catch(Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
