package mostrar;

import java.sql.SQLException;

import util.Utils;


public class Asistencia {
	public Boolean Asistio(Conexion con, int numero,int asist) {
		
		String sql = "UPDATE citas SET asistencia= ?, activa = 0 WHERE id_cita = ?";
		java.sql.PreparedStatement sentencia;
		boolean resultado = false;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			sentencia.setInt(1, asist);
			sentencia.setInt(2, numero);
			int filas = sentencia.executeUpdate();
			if(filas > 0) {
				resultado = true;
			}
		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

		}

		return resultado;
	}

}
