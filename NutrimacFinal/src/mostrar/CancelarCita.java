package mostrar;

import java.sql.SQLException;

import util.Utils;

public class CancelarCita {

	public int Cancelar(Conexion con, int numero) {
		
		int filas = 0;
		String sql = "UPDATE citas SET activa= 0 WHERE id_cita = ?";
		java.sql.PreparedStatement sentencia;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			sentencia.setInt(1, numero);
			filas = sentencia.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

		}

		return filas;
	}
}
