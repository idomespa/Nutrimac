package mostrar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.Utils;

public class InsertarCategoria {
	public static boolean insertar(Conexion con, String nombre) {
		
		
		String sql = "INSERT INTO  categoria (categoria) VALUES "
				+ "(?)";
		boolean ret= false;
		
		PreparedStatement sentencia;
		
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			sentencia.setString(1, nombre);

			
			int updateCount=sentencia.executeUpdate();

			if(updateCount>0)
			{
				ret = true;
			}
			
		} catch (SQLException e) {
			Utils.escribirLog("Mensaje :" + e.getMessage());
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			ret=false;
		}
		return ret;
	}
}
