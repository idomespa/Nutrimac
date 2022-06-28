package mostrar;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import generadas.Blog;
import generadas.NutriComentarios;
import util.Utils;

public class ComentariosPorBlog {
	
	public ArrayList<Blog> listarBlog(Conexion con) {
		ArrayList<Blog> listaBlogs = new ArrayList<>();
		
		String sql = "select id_blog, autor_blog, titulo_blog from blog";
		java.sql.PreparedStatement sentencia;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			ResultSet filas = sentencia.executeQuery();
			

			while (filas.next()) {
				Long id = filas.getLong(1);
				String nombre = filas.getString(2);
				String titulo = filas.getString(3);
				
				listaBlogs.add(new Blog(id,nombre,titulo));
			}
			
			
		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

		}

		return listaBlogs;
	}
	
	public ArrayList<NutriComentarios> listarComentarios(Conexion con, int numero) {
		ArrayList<NutriComentarios> listaBlogs = new ArrayList<>();
		
		String sql = "select comment_id, comment_autor, comment_texto, autor_email, aprovado from nutri_comentarios";
		java.sql.PreparedStatement sentencia;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			ResultSet filas = sentencia.executeQuery();
			

			while (filas.next()) {
				int id = filas.getInt(1);
				String nombre = filas.getString(2);
				String texto = filas.getString(3);
				String correo = filas.getString(4);
				boolean activo = filas.getBoolean(5);
				
				listaBlogs.add(new NutriComentarios(id, nombre, texto, correo,activo));
			}
			
			
		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

		}

		return listaBlogs;
	}
public boolean cancelarComentario(Conexion con, int id) {
		
		
		String sql = "UPDATE nutri_comentarios SET aprovado = 0 WHERE comment_id = ? ";
		PreparedStatement sentencia;
		boolean aDevolver= false;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			sentencia.setInt(1, id);
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
