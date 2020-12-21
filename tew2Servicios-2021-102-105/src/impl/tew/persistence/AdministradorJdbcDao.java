package impl.tew.persistence;
import java.sql.*;
import java.util.*;

import com.tew.business.resteasy.AlmacenServidor;
import com.tew.persistence.AdministradorDao;
import com.tew.persistence.exception.*;

import org.json.simple.*;

import java.util.Iterator;


public class AdministradorJdbcDao implements AdministradorDao {	

	@Override
	public void borrarUsarios(List<String> a) throws NotPersistedException {
		
		if(!a.isEmpty()) {
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			PreparedStatement ps3 = null;
			Connection con = null;
			int rows = 0;
			String listaParaBorrar = "(";
			for(String s : a) {
				listaParaBorrar+= ("'"+s+"',");
			}
			listaParaBorrar = listaParaBorrar.substring(0, listaParaBorrar.length() - 1) + ")";
			
			try {
				
				String SQL_DRV = "org.hsqldb.jdbcDriver";
				String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
	
				
				
				Class.forName(SQL_DRV);
				con = DriverManager.getConnection(SQL_URL, "sa", "");
				ps1 = con.prepareStatement("DELETE FROM Amigos where email_usuario in "+listaParaBorrar+" OR email_amigo in "+listaParaBorrar);
				ps2 = con.prepareStatement("DELETE FROM Publicacion where email in "+listaParaBorrar);
				ps3 = con.prepareStatement("DELETE FROM Usuarios where email in "+listaParaBorrar);
				
				ps1.executeUpdate();
				ps2.executeUpdate();
				rows = ps3.executeUpdate();
				
				if (rows == 0) {
					throw new NotPersistedException("Users not found");
				} 
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new PersistenceException("Driver not found", e);
			} catch (SQLSyntaxErrorException  e) {
				e.printStackTrace();
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new PersistenceException("Invalid SQL or database schema", e);
			}
			
			finally  {
				if (ps1 != null || ps2 != null || ps3 != null) {try{ ps1.close();ps2.close();ps3.close(); } catch (Exception ex){}};
				if (con != null) {try{ con.close(); } catch (Exception ex){}};
			}
		}
		else {System.out.println("No se puede borrar ese usuario o No hay usuarios selecionados");}
	}

	@SuppressWarnings("resource")
	@Override
	public void reiniciarBD() {
		
		
		PreparedStatement ps = null;
		Connection con = null;

		try {
			 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			

			try {

				for(String s:DB_schema()) {
					ps=con.prepareStatement(s);
					ps.execute();
				}
				for(String s:DB_data()) {
					ps=con.prepareStatement(s);
					ps.execute();
				}

	        } catch (Exception e) {
	        	System.out.println(e);
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

	private List<String> DB_schema(){
		List<String> l = new ArrayList<String>();
		l.add("DROP TABLE Amigos;");
		l.add("DROP TABLE Publicacion;");
		l.add("DROP TABLE Usuarios;");
		l.add("CREATE TABLE Usuarios (email varchar(100) PRIMARY KEY, passwd varchar(100) NOT NULL,rol varchar(20) not null,nombre varchar(100) not null);");
		l.add("CREATE TABLE Publicacion (ID int PRIMARY KEY, email varchar(100) NOT NULL REFERENCES Usuarios (email),titulo varchar(20) not null,texto varchar(300) not null,fecha bigint not null);");
		l.add("CREATE TABLE Amigos (email_usuario varchar(100) REFERENCES Usuarios (email), email_amigo varchar(100) REFERENCES Usuarios (email), aceptada boolean not null);");
		l.add("INSERT INTO Usuarios (email, passwd, rol, nombre) VALUES ('admin@dominio.com', 'admin', 'admin', 'Administrador');");
		return l;
	}
	
	private List<String> DB_data(){
		List<String> l = new ArrayList<String>();
		
		try {
			Object obj = JSONValue.parse(data);
 
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray usuarios = (JSONArray) jsonObject.get("usuarios");
			JSONArray publicaciones = (JSONArray) jsonObject.get("publicaciones");
			JSONArray amigos = (JSONArray) jsonObject.get("amigos");
 
			Iterator<JSONObject> usu_iter = usuarios.iterator();
			while (usu_iter.hasNext()) {
				JSONObject usuario = usu_iter.next();
				l.add("INSERT INTO Usuarios (email, passwd, rol, nombre) VALUES ('"+usuario.get("email").toString()+"', '"+usuario.get("passwd").toString()+"', '"+usuario.get("rol").toString()+"', '"+usuario.get("nombre").toString()+"');");
			}
			
			int id = 0;
			Iterator<JSONObject> publi_iter = publicaciones.iterator();
			while (publi_iter.hasNext()) {
				JSONObject publicacion = publi_iter.next();
				l.add("INSERT INTO Publicacion (ID,email, titulo, texto, fecha) VALUES ('"+id+++"','"+publicacion.get("email").toString()+"', '"+publicacion.get("titulo").toString()+"', '"+publicacion.get("texto").toString()+"', "+publicacion.get("fecha").toString()+");");
			}
			
			Iterator<JSONObject> amigos_iter = amigos.iterator();
			while (amigos_iter.hasNext()) {
				JSONObject amigo = amigos_iter.next();
				l.add("INSERT INTO Amigos (email_usuario,email_amigo,aceptada) VALUES ('"+amigo.get("emailusuario").toString()+"','"+amigo.get("emailamigo").toString()+"',1);");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	
	private final String data = "{\"usuarios\": [\n" + 
			"  {\n" + 
			"    \"email\": \"user1@dominio.com\",\n" + 
			"    \"passwd\": \"user1\",\n" + 
			"    \"rol\": \"usuario\",\n" + 
			"    \"nombre\": \"user1\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user2@dominio.com\",\n" + 
			"    \"passwd\": \"user2\",\n" + 
			"    \"rol\": \"usuario\",\n" + 
			"    \"nombre\": \"user2\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user3@dominio.com\",\n" + 
			"    \"passwd\": \"user3\",\n" + 
			"    \"rol\": \"usuario\",\n" + 
			"    \"nombre\": \"user3\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user4@dominio.com\",\n" + 
			"    \"passwd\": \"user4\",\n" + 
			"    \"rol\": \"usuario\",\n" + 
			"    \"nombre\": \"user4\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user5@dominio.com\",\n" + 
			"    \"passwd\": \"user5\",\n" + 
			"    \"rol\": \"usuario\",\n" + 
			"    \"nombre\": \"user5\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user6@dominio.com\",\n" + 
			"    \"passwd\": \"user6\",\n" + 
			"    \"rol\": \"usuario\",\n" + 
			"    \"nombre\": \"user6\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user7@dominio.com\",\n" + 
			"    \"passwd\": \"user7\",\n" + 
			"    \"rol\": \"usuario\",\n" + 
			"    \"nombre\": \"user7\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user8@dominio.com\",\n" + 
			"    \"passwd\": \"user8\",\n" + 
			"    \"rol\": \"usuario\",\n" + 
			"    \"nombre\": \"user8\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user9@dominio.com\",\n" + 
			"    \"passwd\": \"user9\",\n" + 
			"    \"rol\": \"usuario\",\n" + 
			"    \"nombre\": \"user9\"\n" + 
			"  }\n" + 
			"  ],\n" + 
			"  \"publicaciones\": [\n" + 
			"  {\n" + 
			"    \"email\": \"user1@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 1 user 1\",\n" + 
			"    \"texto\": \"Texto publicacion 1 user1\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user1@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 2 user 1\",\n" + 
			"    \"texto\": \"Texto publicacion 2 user1\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user1@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 3 user 1\",\n" + 
			"    \"texto\": \"Texto publicacion 3 user1\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user2@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 1 user 2\",\n" + 
			"    \"texto\": \"Texto publicacion 1 user2\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user2@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 2 user 2\",\n" + 
			"    \"texto\": \"Texto publicacion 2 user 2\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user2@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 3 user 2\",\n" + 
			"    \"texto\": \"Texto publicacion 3 user 2\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user3@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 1 user 3\",\n" + 
			"    \"texto\": \"Texto publicacion 1 user 3\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"email\": \"user3@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 2 user 3\",\n" + 
			"    \"texto\": \"Texto publicacion 2 user 3\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"    {\n" + 
			"    \"email\": \"user3@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 3 user 3\",\n" + 
			"    \"texto\": \"Texto publicacion 3 user 3\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"    {\n" + 
			"    \"email\": \"user4@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 1 user 4\",\n" + 
			"    \"texto\": \"Texto publicacion 1 user 4\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"    {\n" + 
			"    \"email\": \"user4@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 2 user 4\",\n" + 
			"    \"texto\": \"Texto publicacion 2 user 4\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"    {\n" + 
			"    \"email\": \"user4@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 3 user 4\",\n" + 
			"    \"texto\": \"Texto publicacion 3 user 4\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"    {\n" + 
			"    \"email\": \"user5@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 1 user 5\",\n" + 
			"    \"texto\": \"Texto publicacion 1 user 5\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"    {\n" + 
			"    \"email\": \"user5@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 2 user 5\",\n" + 
			"    \"texto\": \"Texto publicacion 2 user 5\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"    {\n" + 
			"    \"email\": \"user5@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 3 user 5\",\n" + 
			"    \"texto\": \"Texto publicacion 3 user 5\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"email\": \"user6@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 1 user 6\",\n" + 
			"    \"texto\": \"Texto publicacion 1 user 6\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"email\": \"user6@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 2 user 6\",\n" + 
			"    \"texto\": \"Texto publicacion 2 user 6\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"email\": \"user6@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 3 user 6\",\n" + 
			"    \"texto\": \"Texto publicacion 3 user 6\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"email\": \"user7@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 1 user 7\",\n" + 
			"    \"texto\": \"Texto publicacion 1 user 7\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"email\": \"user7@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 2 user 7\",\n" + 
			"    \"texto\": \"Texto publicacion 2 user 7\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"email\": \"user7@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 3 user 7\",\n" + 
			"    \"texto\": \"Texto publicacion 3 user 7\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"email\": \"user8@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 1 user 8\",\n" + 
			"    \"texto\": \"Texto publicacion 1 user 8\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"email\": \"user8@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 2 user 8\",\n" + 
			"    \"texto\": \"Texto publicacion 2 user 8\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"email\": \"user8@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 3 user 8\",\n" + 
			"    \"texto\": \"Texto publicacion 3 user 8\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"email\": \"user9@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 1 user 9\",\n" + 
			"    \"texto\": \"Texto publicacion 1 user 9\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"email\": \"user9@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 2 user 9\",\n" + 
			"    \"texto\": \"Texto publicacion 2 user 9\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"email\": \"user9@dominio.com\",\n" + 
			"    \"titulo\": \"Publicacion 3 user 9\",\n" + 
			"    \"texto\": \"Texto publicacion 3 user 9\",\n" + 
			"    \"fecha\": \"1542116773\"\n" + 
			"  }\n" + 
			"  ],\n" + 
			" \"amigos\":  \n" + 
			" [\n" + 
			"  {\n" + 
			"    \"emailusuario\": \"user1@dominio.com\",\n" + 
			"    \"emailamigo\": \"user2@dominio.com\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"emailusuario\": \"user1@dominio.com\",\n" + 
			"    \"emailamigo\": \"user3@dominio.com\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"emailusuario\": \"user1@dominio.com\",\n" + 
			"    \"emailamigo\": \"user4@dominio.com\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"emailusuario\": \"user2@dominio.com\",\n" + 
			"    \"emailamigo\": \"user3@dominio.com\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"emailusuario\": \"user2@dominio.com\",\n" + 
			"    \"emailamigo\": \"user4@dominio.com\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"emailusuario\": \"user2@dominio.com\",\n" + 
			"    \"emailamigo\": \"user5@dominio.com\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"emailusuario\": \"user3@dominio.com\",\n" + 
			"    \"emailamigo\": \"user4@dominio.com\"\n" + 
			"  },\n" + 
			"  {\n" + 
			"    \"emailusuario\": \"user3@dominio.com\",\n" + 
			"    \"emailamigo\": \"user5@dominio.com\"\n" + 
			"  },\n" + 
			"    {\n" + 
			"    \"emailusuario\": \"user3@dominio.com\",\n" + 
			"    \"emailamigo\": \"user6@dominio.com\"\n" + 
			"  },\n" + 
			"    {\n" + 
			"    \"emailusuario\": \"user4@dominio.com\",\n" + 
			"    \"emailamigo\": \"user5@dominio.com\"\n" + 
			"  },\n" + 
			"    {\n" + 
			"    \"emailusuario\": \"user4@dominio.com\",\n" + 
			"    \"emailamigo\": \"user6@dominio.com\"\n" + 
			"  },\n" + 
			"    {\n" + 
			"    \"emailusuario\": \"user4@dominio.com\",\n" + 
			"    \"emailamigo\": \"user7@dominio.com\"\n" + 
			"  },\n" + 
			"    {\n" + 
			"    \"emailusuario\": \"user5@dominio.com\",\n" + 
			"    \"emailamigo\": \"user6@dominio.com\"\n" + 
			"  },\n" + 
			"    {\n" + 
			"    \"emailusuario\": \"user5@dominio.com\",\n" + 
			"    \"emailamigo\": \"user7@dominio.com\"\n" + 
			"  },\n" + 
			"    {\n" + 
			"    \"emailusuario\": \"user5@dominio.com\",\n" + 
			"    \"emailamigo\": \"user8@dominio.com\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"emailusuario\": \"user6@dominio.com\",\n" + 
			"    \"emailamigo\": \"user7@dominio.com\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"emailusuario\": \"user6@dominio.com\",\n" + 
			"    \"emailamigo\": \"user8@dominio.com\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"emailusuario\": \"user6@dominio.com\",\n" + 
			"    \"emailamigo\": \"user9@dominio.com\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"emailusuario\": \"user7@dominio.com\",\n" + 
			"    \"emailamigo\": \"user1@dominio.com\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"emailusuario\": \"user7@dominio.com\",\n" + 
			"    \"emailamigo\": \"user2@dominio.com\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"emailusuario\": \"user7@dominio.com\",\n" + 
			"    \"emailamigo\": \"user3@dominio.com\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"emailusuario\": \"user8@dominio.com\",\n" + 
			"    \"emailamigo\": \"user1@dominio.com\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"emailusuario\": \"user8@dominio.com\",\n" + 
			"    \"emailamigo\": \"user2@dominio.com\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"emailusuario\": \"user8@dominio.com\",\n" + 
			"    \"emailamigo\": \"user3@dominio.com\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"emailusuario\": \"user9@dominio.com\",\n" + 
			"    \"emailamigo\": \"user1@dominio.com\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"emailusuario\": \"user9@dominio.com\",\n" + 
			"    \"emailamigo\": \"user2@dominio.com\"\n" + 
			"  },\n" + 
			"      {\n" + 
			"    \"emailusuario\": \"user9@dominio.com\",\n" + 
			"    \"emailamigo\": \"user3@dominio.com\"\n" + 
			"  }\n" + 
			" ] \n" + 
			" }\n" + 
			"  \n" + 
			"";
	
}
