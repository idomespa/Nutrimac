package mostrar;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.Utils;

public class ConfirmarCita {
	public boolean confirmar(int id, Conexion con) {
		String sql = "UPDATE citas SET confirmar = 1 WHERE id_cita = ? ";
		PreparedStatement sentencia;
		
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			sentencia.setInt(1, id);
			sentencia.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());
			return false;
		}
		return true;
	}

}
