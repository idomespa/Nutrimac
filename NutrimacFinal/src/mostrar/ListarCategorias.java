package mostrar;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import generadas.Categoria;
import generadas.Clientes;
import util.Utils;

public class ListarCategorias {
	public ArrayList<Categoria> listarCategorias(Conexion con) {
		ArrayList<Categoria> listaCategoria = new ArrayList<>();
		
		String sql = "select categoria from categoria";
		
		
		java.sql.PreparedStatement sentencia;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			ResultSet filas = sentencia.executeQuery();
			

			while (filas.next()) {
				String categoria = filas.getString(1);
								
				listaCategoria.add(new Categoria(categoria));
			}
			
		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

		}

		return listaCategoria;
	}

}
