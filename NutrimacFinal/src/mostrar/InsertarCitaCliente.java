package mostrar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.Utils;

public class InsertarCitaCliente {
	public long insertar(Conexion con, String fecha, String hora ,String usuario, int online, String present, String direccion) {
		
		
		String sql = "INSERT INTO  citas (fecha_cita, hora_cita, usuario, online, presencial, direccion) VALUES "
				+ "(?, ?, ?, ?, ?, ?);";
		boolean ret= false;
		long pk=0;
		
		PreparedStatement sentencia;
		
		try {
			sentencia = con.Conectar().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, fecha);
			sentencia.setString(2, hora);
			sentencia.setString(3, usuario);
			sentencia.setInt(4, online);
			sentencia.setString(5, present);
			sentencia.setString(6, direccion);
			
			if (sentencia.executeUpdate() == 1) {
				ret = true;
			}
			ResultSet rs = sentencia.getGeneratedKeys();
			rs.next();
			pk = rs.getLong(1);
			
		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

			ret=false;
		}
		return pk;
	}
}
