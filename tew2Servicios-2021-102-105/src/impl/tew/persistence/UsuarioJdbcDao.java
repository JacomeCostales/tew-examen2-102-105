package impl.tew.persistence;

import java.sql.*;
import java.util.*;
import com.tew.model.Usuario;
import com.tew.persistence.UsuarioDao;
import com.tew.persistence.exception.*;
/**
 * 
 * @author Jácome y Miguel
 *
 */
public class UsuarioJdbcDao implements UsuarioDao {

	public List<Usuario> getUsuario() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Usuarios");
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setEmail(rs.getString("email"));
				usuario.setPasswd(rs.getString("passwd"));
				usuario.setRol(rs.getString("rol"));
				usuario.setNombre(rs.getString("nombre"));
				
				usuarios.add(usuario);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return usuarios;
	}

	@Override
	public void delete(String email) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from Usuarios where email = ?");
			
			ps.setString(1, email);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Usuario: " + email + " No encontrado");
			} 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}

	@Override
	public Usuario findByEmail(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Usuario usuario = null;
		
		try {
			
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			
			String q = "select * from Usuarios where email = '"+email+"'";
			
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(q);
			
						
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				usuario = new Usuario();
				usuario.setEmail(rs.getString("email"));
				usuario.setPasswd(rs.getString("passwd"));
				usuario.setRol(rs.getString("rol"));
				usuario.setNombre(rs.getString("nombre"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return usuario;
	}

	@Override
	public void save(Usuario a) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into Usuarios (email,passwd,rol,nombre) " +
					"values (?, ?, ?, ?)");
			
			ps.setString(1, a.getEmail());
			ps.setString(2, a.getPasswd());
			ps.setString(3, a.getRol());
			ps.setString(4, a.getNombre());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Usuario: " + a.getEmail() + " ya esta guardado");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}

	@Override
	public void update(Usuario a) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"update Usuarios set passwd = ?, rol = ?, nombre = ?" +
					"where email = ?");
			
			ps.setString(1, a.getPasswd());
			ps.setString(2, a.getRol());
			ps.setString(3, a.getNombre());
			ps.setString(4, a.getEmail());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Usuario " + a + " no encontrado");
			} 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}

	@Override
	public List<Usuario> getUsuarioFiltrado(String email, String textoFiltro) 
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String filtro = "%" + textoFiltro + "%";
		System.out.println(filtro);
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Usuarios where Usuarios.email not in(select email_usuario from amigos where email_usuario = ? or email_amigo = ?) and Usuarios.email not in (select email_amigo from amigos where email_usuario = ? or email_amigo = ?) and Usuarios.rol = 'usuario' and (Usuarios.email like ? or Usuarios.nombre like ?)");
			//select * from Usuarios where Usuarios.email not in(select email_usuario from amigos where email_usuario = 'user1@email.es' or email_amigo = 'user1@email.es') and Usuarios.email not in (select email_amigo from amigos where email_usuario = 'user1@email.es' or email_amigo = 'user1@email.es') and Usuarios.rol = 'usuario'
			//select * from Usuarios inner join Amigos on Usuarios.email = Amigos.email_usuario where email like ? or nombre like ?
			ps.setString(1, email);
			ps.setString(2, email);
			ps.setString(3, email);
			ps.setString(4, email);
			ps.setString(5, filtro);
			ps.setString(6, filtro);
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setEmail(rs.getString("email"));
				usuario.setPasswd(rs.getString("passwd"));
			
				usuario.setRol(rs.getString("rol"));
				usuario.setNombre(rs.getString("nombre"));
				
				usuarios.add(usuario);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return usuarios;
	}

	@Override
	public List<Usuario> getListadoPeticiones(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select U2.* from (Amigos as A inner join Usuarios as U on A.email_amigo = U.email) inner join Usuarios as U2 on A.email_usuario = U2.email where U.email = ? and A.aceptada = ?");
			ps.setString(1, email);
			ps.setBoolean(2, false);
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setEmail(rs.getString("email"));
				usuario.setPasswd(rs.getString("passwd"));
			
				usuario.setRol(rs.getString("rol"));
				usuario.setNombre(rs.getString("nombre"));
				
				usuarios.add(usuario);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return usuarios;
	}

	@Override
	public List<Usuario> getListadoEnvios(String email) 
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Usuarios where Usuarios.email not in(select email_usuario from amigos where email_usuario = ? or email_amigo = ?) and Usuarios.email not in (select email_amigo from amigos where email_usuario = ? or email_amigo = ?) and Usuarios.rol = 'usuario'");
			//select * from Usuarios where Usuarios.email not in(select email_usuario from amigos where email_usuario = 'user1@email.es' or email_amigo = 'user1@email.es') and Usuarios.email not in (select email_amigo from amigos where email_usuario = 'user1@email.es' or email_amigo = 'user1@email.es') and Usuarios.rol = 'usuario'
			//select * from Usuarios inner join Amigos on Usuarios.email = Amigos.email_usuario where email like ? or nombre like ?
			ps.setString(1, email);
			ps.setString(2, email);
			ps.setString(3, email);
			ps.setString(4, email);
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setEmail(rs.getString("email"));
				usuario.setPasswd(rs.getString("passwd"));
			
				usuario.setRol(rs.getString("rol"));
				usuario.setNombre(rs.getString("nombre"));
				
				usuarios.add(usuario);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return usuarios;
	}

	@Override
	public List<Usuario> getInicioSesion(String login, String password) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Usuarios where email = ? and passwd = ?");
			ps.setString(1, login);
			ps.setString(2, password);
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setEmail(rs.getString("email"));
				usuario.setPasswd(rs.getString("passwd"));
			
				usuario.setRol(rs.getString("rol"));
				usuario.setNombre(rs.getString("nombre"));
				
				usuarios.add(usuario);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return usuarios;
		
	}

	@Override
	public String getRol(String login) 
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Usuario usuario = null;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Usuarios where email = ?");
			ps.setString(1, login);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setEmail(rs.getString("email"));
				usuario.setPasswd(rs.getString("passwd"));
				usuario.setRol(rs.getString("rol"));
				usuario.setNombre(rs.getString("nombre"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return usuario.getRol();
	}

}
