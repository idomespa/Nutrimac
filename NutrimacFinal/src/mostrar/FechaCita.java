package mostrar;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import generadas.Clientes;
import util.Utils;

public class FechaCita {
	public ArrayList<String> lfechaCita(Conexion con,int num) {
		ArrayList<String> ra = new ArrayList<String>();
		
		String sql = "select fecha_cita,hora_cita from citas where id_cita= "+num;
		String fecha = null;
		
		java.sql.PreparedStatement sentencia;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			ResultSet filas = sentencia.executeQuery();
			

			while (filas.next()) {
				ra.add(filas.getString(1));
				ra.add(filas.getString(2));
				
			}
			System.out.println(fecha);
		} catch (SQLException e) {
			Utils.escribirLog("Mensaje :" + e.getMessage());
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
		}

		return ra;
	}
}
