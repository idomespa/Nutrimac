package mostrar;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.Utils;

public class ActualizarCita {


	public void actualizar(Conexion con, int id, String fecha, String hora, String ciudad, String direccion, int online) {
		
		
		String sql = "UPDATE citas SET fecha_cita = ?, hora_cita = ?, presencial = ?, direccion = ?, online = ?"
				+ " WHERE id_cita = ? ";
		PreparedStatement sentencia;
		
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			sentencia.setString(1, fecha);
			sentencia.setString(2, hora);
			sentencia.setString(3, ciudad);
			sentencia.setString(4, direccion);
			sentencia.setInt(5, online);
			sentencia.setInt(6, id);
			sentencia.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

		}
	}
public void actualizarObservaciones(Conexion con, int id,String observaciones) {
		
		
		String sql = "UPDATE citas SET observaciones = ?"
				+ " WHERE id_cita = ? ";
		PreparedStatement sentencia;
		
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			sentencia.setString(1, observaciones);
			sentencia.setInt(2, id);
			sentencia.executeUpdate();			
			
		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

		}
	}
}
