package mostrar;

import java.sql.SQLException;

import util.Utils;

public class ReservarCitas {
	public boolean InsertarMisCitas(Conexion con, int numero1, int numero2,String fecha) {
		
		String hora = null;
		int filas = 0;
		String usuario= "nutrimac@nutrimac.es";
		String sql = "INSERT INTO citas (fecha_cita, hora_cita, usuario, activa, confirmar, asistencia) VALUES (?, ?, ?, ?, ?, ?)";
		java.sql.PreparedStatement sentencia;
		boolean resultado = false;
		try {
			for(int i = numero1; i <= numero2; i++) {
				if(i != 14 && i != 15 && i != 16 ) {
					if (i <= 9) {
						hora= "0"+i+":00";
					}else {
						hora= i+":00";
					}
					sentencia = con.Conectar().prepareStatement(sql);
					sentencia.setString(1, fecha);
					sentencia.setString(2, hora);
					sentencia.setString(3, usuario);
					sentencia.setInt(4, 1);
					sentencia.setInt(5, 1);
					sentencia.setInt(6, 1);
					filas += sentencia.executeUpdate();
					con.Desconectar();
				}
			}
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
