package mostrar;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import generadas.Clientes;
import util.Utils;

public class CargarClientes {

	public ArrayList<Clientes> listarCliente(Conexion con) {
		ArrayList<Clientes> listaCliente = new ArrayList<>();
		
		String sql = "select email_id,nombre,apellidos,dni_nif,fecha_nacimiento,telefono "
				+ "from clientes ";
		
		
		java.sql.PreparedStatement sentencia;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			ResultSet filas = sentencia.executeQuery();
			

			while (filas.next()) {
				String correo = filas.getString(1).toLowerCase();
				String nombre = filas.getString(2).toLowerCase();
				String apellido = filas.getString(3).toLowerCase();
				String dniNif = filas.getString(4);
				Date fecha = filas.getDate(5);
				int telefono = filas.getInt(6);
								
				listaCliente.add(new Clientes(correo,nombre, apellido,dniNif,fecha,telefono));
			}
			
		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

		}

		return listaCliente;
	}
}
