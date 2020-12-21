package impl.tew.persistence;

import java.sql.*;
import java.util.*;
import com.tew.model.Publicacion;
import com.tew.persistence.PublicacionDao;
import com.tew.persistence.exception.*;
/**
 * 
 * @author Jácome y Miguel
 *
 */
public class PublicacionJdbcDao implements PublicacionDao {

	public List<Publicacion> getPublicacionesPropias(String email, String orderCol) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Publicacion> publicaciones = new ArrayList<Publicacion>();

		try {
			
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			
			ps = con.prepareStatement("select * from Publicacion where email = ? "
										+ "order by "+orderCol);
			ps.setString(1, email);
			
			
			rs = ps.executeQuery();

			while (rs.next()) {
				Publicacion publicacion = new Publicacion();
				publicacion.setId(rs.getInt("id"));
				publicacion.setEmail(rs.getString("email"));
				publicacion.setTitulo(rs.getString("titulo"));
				publicacion.setTexto(rs.getString("texto"));
				publicacion.setFecha(rs.getLong("fecha"));
				
				publicaciones.add(publicacion);
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
		
		return publicaciones;
	}
	
	public List<Publicacion> getPublicacionesAmigos(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Publicacion> publicaciones = new ArrayList<Publicacion>();

		try {
			// En una implemenntaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Publicacion where (Publicacion.email in (select email_amigo from amigos where email_usuario = ? and aceptada = 1) or publicacion.email in (select email_usuario from amigos where email_amigo = ? and aceptada = 1));");
			ps.setString(1, email);
			ps.setString(2, email);
			rs = ps.executeQuery();

			while (rs.next()) {
				Publicacion publicacion = new Publicacion();
				publicacion.setId(rs.getInt("id"));
				publicacion.setEmail(rs.getString("email"));
				publicacion.setTitulo(rs.getString("titulo"));
				publicacion.setTexto(rs.getString("texto"));
				publicacion.setFecha(rs.getLong("fecha"));
				
				publicaciones.add(publicacion);
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
		
		return publicaciones;
	}

	@Override
	public void delete(int id) throws NotPersistedException {
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
			ps = con.prepareStatement("delete from Publicacion where id = ?");
			
			ps.setInt(1, id);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Publicacion: " + id + " not found");
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
	public Publicacion findById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Publicacion publicacion = null;
		
		try {
			// En una implementaciï¿½ï¿½n mï¿½ï¿½s sofisticada estas constantes habrï¿½ï¿½a 
			// que sacarlas a un sistema de configuraciï¿½ï¿½n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexiï¿½ï¿½n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Publicacion where id = ?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				publicacion = new Publicacion();
				
				publicacion.setId(rs.getInt("id"));
				publicacion.setEmail(rs.getString("email"));
				publicacion.setTitulo(rs.getString("titulo"));
				publicacion.setTexto(rs.getString("texto"));
				publicacion.setFecha(rs.getLong("fecha"));
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
		
		return publicacion;
	}

	@Override
	public void save(Publicacion a) throws AlreadyPersistedException {
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
					"insert into Publicacion (id,email,titulo,texto,fecha) " +
					"values (?, ?, ?, ?,?)");
			System.out.println("ID: "+a.getId());
			System.out.println("EMAIL: "+a.getEmail());
			
			ps.setInt(1, a.getId());
			ps.setString(2, a.getEmail());
			ps.setString(3, a.getTitulo());
			ps.setString(4, a.getTexto());
			ps.setLong(5, a.getFecha());
				
			
			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Publicacion: " + a + " already persisted");
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
	public void update(Publicacion a) throws NotPersistedException {
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
					"update publicacion " +
					"email = ?, titulo = ?, texto = ?, fecha = ?" +
					"where id = ?");
			
			ps.setString(1, a.getEmail());
			ps.setString(2, a.getTitulo());
			ps.setString(3, a.getTexto());
			ps.setLong(4, a.getFecha());
			ps.setInt(5, a.getId());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Publicacion: " + a + " not found");
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

}
