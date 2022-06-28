package mostrar;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.Utils;

public class InsertarCliente {
	public boolean insertar(Conexion con, String usuario, String apellidos, String correo, int telefono) {
		
		
		String sql = "INSERT INTO  clientes (nombre, apellidos, email_id, telefono) VALUES "
				+ "(?, ?, ?, ?);";
		boolean ret= false;
		
		PreparedStatement sentencia;
		
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			sentencia.setString(1, usuario);
			sentencia.setString(2, apellidos);
			sentencia.setString(3, correo);
			sentencia.setInt(4, telefono);
			if (sentencia.executeUpdate() == 1) {
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
