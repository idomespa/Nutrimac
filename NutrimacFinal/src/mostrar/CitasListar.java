package mostrar;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import generadas.Citas;
import generadas.Clientes;
import util.Utils;


public class CitasListar {

	public ArrayList<Citas> listarCliente(Conexion con, String fecha) {
		ArrayList<Citas> listaCliente = new ArrayList<>();
		
		String sql = "select id_cita,nombre,apellidos,fecha_cita,hora_cita,activa,asistencia,confirmar,telefono,email_id,online,presencial,direccion,motivo,observaciones "
				+ "from citas, clientes "
				+ "WHERE activa=1 "
				+ "AND usuario = clientes.email_id "
				+ "AND fecha_cita ='"+fecha+"'"
				+ "ORDER BY hora_cita ASC";
		java.sql.PreparedStatement sentencia;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			ResultSet filas = sentencia.executeQuery();
			

			while (filas.next()) {
				Long idCita = filas.getLong(1);
				String nombre = filas.getString(2);
				String apellido = filas.getString(3);
				Date fechaCita = filas.getDate(4);
				Time horaCita = filas.getTime(5);
				int numero1 = filas.getInt(6);
				int numero2 = filas.getInt(7);
				int numero3 = filas.getInt(8);
				int telefono = filas.getInt(9);
				String correo = filas.getString(10);
				int numero4 = filas.getInt(11);
				String presencial = filas.getString(12);
				String direccion = filas.getString(13);
				String motivo = filas.getString(14);
				String observaciones = filas.getString(15);
				Boolean activa = false;
				Boolean asistencia = false;
				Boolean confirmar = false;
				Boolean online = false;
				
				if (numero1 == 1 ) {
					activa = true;
				}
				if (numero2 == 1 ) {
					asistencia = true;
				}
				if (numero3 == 1 ) {
					confirmar = true;
				}
				if (numero4 == 1 ) {
					online = true;
				
				}
				
				listaCliente.add(new Citas(idCita,new Clientes(correo,nombre, apellido,telefono),fechaCita,horaCita,activa,confirmar,asistencia,online,presencial,direccion,motivo,observaciones));
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
	
	public ArrayList<String> listarCitas(Conexion con, String fecha) {
		ArrayList<String> listaHoras = new ArrayList<>();
		
		
		String sql = "select hora_cita from citas WHERE activa=1 AND fecha_cita = ?";
		java.sql.PreparedStatement sentencia;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			sentencia.setString(1, fecha);
			ResultSet filas = sentencia.executeQuery();
			

			while (filas.next()) {
				
				String hora_cita = filas.getString(1);
				String[] hora = hora_cita.toString().split(":");
				
				listaHoras.add(hora[0]+":"+hora[1]);
			}
			
		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

		}

		return listaHoras;
	}
	
	public ArrayList<Citas> listarCitasId(Conexion con, int id) {
		ArrayList<Citas> listaClie = new ArrayList<>();
		
		
		String sql = "select id_cita,nombre,apellidos,fecha_cita,hora_cita,activa,asistencia,confirmar,telefono,email_id,online,presencial,direccion,motivo,observaciones "
				+ "from citas, clientes "
				+ "WHERE activa=1 "
				+ "AND id_cita = ? "
				+ "AND usuario = clientes.email_id ";
		java.sql.PreparedStatement sentencia;
		try {
			sentencia = con.Conectar().prepareStatement(sql);
			sentencia.setInt(1, id);
			ResultSet filas = sentencia.executeQuery();
			
			while (filas.next()) {
				Long idCita = filas.getLong(1);
				String nombre = filas.getString(2);
				String apellido = filas.getString(3);
				Date fechaCita = filas.getDate(4);
				Time horaCita = filas.getTime(5);
				int numero1 = filas.getInt(6);
				int numero2 = filas.getInt(7);
				int numero3 = filas.getInt(8);
				int telefono = filas.getInt(9);
				String correo = filas.getString(10);
				int numero4 = filas.getInt(11);
				String presnecial = filas.getString(12);
				String direccion = filas.getString(13);
				String motivo = filas.getString(14);
				String observaciones = filas.getString(15);
				Boolean activa = false;
				Boolean asistencia = false;
				Boolean confirmar = false;
				Boolean online = false;
				
				if (numero1 == 1 ) {
					activa = true;
				}
				if (numero2 == 1 ) {
					asistencia = true;
				}
				if (numero3 == 1 ) {
					confirmar = true;
				}
				if (numero4 == 1 ) {
					online = true;
				}
				
				
				
				listaClie.add(new Citas(idCita,new Clientes(correo,nombre, apellido,telefono),fechaCita,horaCita,activa,confirmar,asistencia,online,presnecial,direccion,motivo,observaciones));
			}
			
		} catch (SQLException e) {
			System.out.println("HA OCURRIDO UNA EXCEPCIÓNN:");
			System.out.println("Mensaje:    " + e.getMessage());
			System.out.println("SQL estado: " + e.getSQLState());
			System.out.println("Cód error:  " + e.getErrorCode());
			Utils.escribirLog("Mensaje :" + e.getMessage());

		}

		return listaClie;
	}
}
