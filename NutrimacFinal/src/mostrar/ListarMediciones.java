package mostrar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import generadas.Clientes;
import generadas.Mediciones;
import util.Utils;

public class ListarMediciones {

	public ArrayList<Mediciones> listarMedicionCliente(Conexion con, String correo) {
		ArrayList<Mediciones> med = new ArrayList<>();
		String sql = "SELECT * FROM mediciones WHERE id_cliente=? ORDER BY fecha_medicion ASC";
		
		PreparedStatement sentencia;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			sentencia.setString(1, correo);
			ResultSet filas = sentencia.executeQuery();
			
			while (filas.next()) {
			med.add(new Mediciones(
					filas.getString(1),
					filas.getFloat(2),
					filas.getFloat(3),
					filas.getFloat(4),
					filas.getFloat(5),
					filas.getFloat(6),
					filas.getFloat(7),
					filas.getFloat(8),
					filas.getFloat(9),
					filas.getFloat(10),
					filas.getFloat(11),
					filas.getFloat(12),
					filas.getFloat(13),
					filas.getFloat(14),
					filas.getFloat(15),
					filas.getFloat(16),
					filas.getFloat(17),
					filas.getFloat(18),
					filas.getDate(19)));

			}
			
			
		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

		}
		return med;
	}

}
