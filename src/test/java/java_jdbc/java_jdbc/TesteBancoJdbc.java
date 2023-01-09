package java_jdbc.java_jdbc;

import org.junit.Test;

import br.com.java.jdbc.conexao.SingleConnection;
import br.com.java.jdbc.dao.UsuarioDAO;
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
		
		usuario.setId(3L);
		usuario.setNome("Arthur");
		usuario.setEmail("Arthur@gmail.com");
		
		usuarioDAO.insert(usuario);	
	}
}
