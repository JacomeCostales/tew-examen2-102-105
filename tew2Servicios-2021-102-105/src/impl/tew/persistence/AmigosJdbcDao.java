package impl.tew.persistence;

import java.sql.*;
import java.util.*;
import com.tew.model.Amigos;
import com.tew.persistence.AmigosDao;
import com.tew.persistence.exception.*;
/**
 * 
 * @author Jácome y Miguel
 *
 */
public class AmigosJdbcDao implements AmigosDao {

	public List<Amigos> getAmigos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Amigos> amigosLista = new ArrayList<Amigos>();

		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Amigos");
			rs = ps.executeQuery();

			while (rs.next()) {
				Amigos amigos = new Amigos();
				amigos.setEmail_usuario(rs.getString("email_usuario"));
				amigos.setEmail_amigo(rs.getString("email_amigo"));
				amigos.setAceptada(rs.getBoolean("aceptada"));
				
				amigosLista.add(amigos);

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
		
		return amigosLista;
	}

	@Override
	public void delete(Amigos a) throws NotPersistedException {
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
			ps = con.prepareStatement("delete from Amigos where email_usuario = ? and email_amigo = ?");
			
			ps.setString(1, a.getEmail_usuario());
			ps.setString(2, a.getEmail_amigo());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Amigos-> " + a + " no encontrados");
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
	public Amigos find(Amigos a) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Amigos amigos = null;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Amigos where email_usuario = ? and email_amigo = ?");
			ps.setString(1, a.getEmail_usuario());
			ps.setString(2, a.getEmail_amigo());
			
			rs = ps.executeQuery();
			if (rs.next()) {
				amigos = new Amigos();
				amigos.setEmail_usuario(rs.getString("email_usuario"));
				amigos.setEmail_amigo(rs.getString("email_amigo"));
				amigos.setAceptada(rs.getBoolean("aceptada"));
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
		
		return amigos;
	}

	@Override
	public void save(Amigos a) throws AlreadyPersistedException {
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
			System.out.println(a.getEmail_usuario());
			System.out.println(a.getEmail_amigo());
			System.out.println(a.isAceptada());
			
			ps = con.prepareStatement("insert into Amigos (email_usuario,email_amigo,aceptada) values (?, ?, ?)");
			
			ps.setString(1, a.getEmail_usuario());
			ps.setString(2, a.getEmail_amigo());
			ps.setBoolean(3, a.isAceptada());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Amigos-> " + a + " ya está guardado");
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
	public void update(Amigos a) throws NotPersistedException {
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
			ps = con.prepareStatement("update Amigos set aceptada = ? where email_usuario = ? and email_amigo = ?");
			System.out.println(a.isAceptada());
			System.out.println(a.getEmail_usuario());
			System.out.println(a.getEmail_amigo());
			ps.setBoolean(1, a.isAceptada());
			ps.setString(2, a.getEmail_usuario());
			ps.setString(3, a.getEmail_amigo());
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Amigos-> " + a + " no encontrado");
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
	public List<Amigos> getAmigosPeticiones(String email) 
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Amigos> amigosLista = new ArrayList<Amigos>();

		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Amigos where email_amigo = ? and aceptada = ?");
			ps.setString(1, email);
			ps.setBoolean(2, false);
			rs = ps.executeQuery();

			while (rs.next()) {
				Amigos amigos = new Amigos();
				amigos.setEmail_usuario(rs.getString("email_usuario"));
				amigos.setEmail_amigo(rs.getString("email_amigo"));
				amigos.setAceptada(rs.getBoolean("aceptada"));
				
				amigosLista.add(amigos);

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
		
		return amigosLista;
	}

}
