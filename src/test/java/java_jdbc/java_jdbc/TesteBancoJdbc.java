package java_jdbc.java_jdbc;

import java.util.List;

import org.junit.Test;

import br.com.java.jdbc.conexao.SingleConnection;
import br.com.java.jdbc.dao.UsuarioDAO;
import br.com.java.jdbc.model.Contato;
import br.com.java.jdbc.model.Usuario;

public class TesteBancoJdbc {

	@Test
	public void initBanco() {
		SingleConnection.getConnection();
	}

	@Test
	public void insert() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();

		usuario.setNome("Lucas");
		usuario.setEmail("Lucas@gmail.com");

		usuarioDAO.insert(usuario);
	}

	@Test
	public void listAll() {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			List<Usuario> list = dao.ListAll();

			for (Usuario usuario : list) {
				System.out.println(usuario);
				System.out.println("----------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void finById() {

		UsuarioDAO dao = new UsuarioDAO();

		try {
			Usuario usuario = dao.findById(3L);

			System.out.println(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void update() {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			Usuario usuario = dao.findById(1L);
			
			usuario.setNome("Arthur da silva leal");
			
			dao.update(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete() {
		
		try {
			
			UsuarioDAO dao = new UsuarioDAO();
			dao.delete(1L);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertTell() {
		Contato contato = new Contato();
		contato.setNumero("(55) 2555-4416");
		contato.setTipo("Residencial");
		contato.setUsuario(9L);
		
		UsuarioDAO dao = new UsuarioDAO();
		dao.insertTell(contato);
	}
}
