package mostrar;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import generadas.Blog;
import util.Utils;

public class DatosBlog {
	public static boolean insertarBlog(Conexion con,String autor,String titulo, String fecha,String nombreArch,String categoria) {


		String sql = "INSERT INTO  blog (autor_blog, titulo_blog, fecha_entrada, nombre_archivo, categoria, activo) VALUES "
				+ "(?, ?, ?, ?, ?, ?)";
		PreparedStatement sentencia;
		boolean aDevolver= false;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			sentencia.setString(1, autor);
			sentencia.setString(2,titulo);
			sentencia.setString(3,fecha);
			sentencia.setString(4,nombreArch);
			sentencia.setString(5,categoria);
			sentencia.setInt(6,1);
			int updateCount=sentencia.executeUpdate();

			if(updateCount>0)
			{
				aDevolver=true;
			}



		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

		}
		return aDevolver;
	}
	public static ArrayList<Blog> activarODesactivar(Conexion con,int numero) {
		ArrayList<Blog> listaBlog= new ArrayList<>();

		String sql = "SELECT id_blog, titulo_blog FROM blog WHERE activo = ?";
		java.sql.PreparedStatement sentencia;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			sentencia.setInt(1, numero);
			ResultSet filas = sentencia.executeQuery();
			

			while (filas.next()) {
				long id = filas.getLong(1);
				String nombre = filas.getString(2);
				
				listaBlog.add(new Blog(id,nombre));
			}
			


		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

		}
		return listaBlog;
		
	}
	public static boolean desctivarActivarBlog(Conexion con,int id, int num ) {


		String sql = "UPDATE blog SET activo = ?"
				+ " WHERE id_blog = ? ";
		PreparedStatement sentencia;
		boolean aDevolver= false;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			sentencia.setInt(1,id);
			sentencia.setInt(2,num);
			int updateCount=sentencia.executeUpdate();

			if(updateCount>0)
			{
				aDevolver=true;
			}



		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

		}
		return aDevolver;
	}
}
