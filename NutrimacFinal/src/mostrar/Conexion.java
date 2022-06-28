package mostrar;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.Utils;

public class Conexion {
	private Connection connex = null;
	private String ip;
	private String base;
	private String usuario;
	private String pass;

	public Connection Conectar() {
		
		try {
			/*try {
				leerDesdeFichero();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			String conexion="jdbc:mysql://sql587.main-hosting.eu/u105646651_nutrimac";
			connex =DriverManager.getConnection(conexion,"u105646651_estandar","ComiendoChurros1999@");
			//String conexion="jdbc:mysql://localhost:8889/nutrimac";
			//connex =DriverManager.getConnection(conexion,"estandar","Informacion2022");
		} catch (SQLException e) {
			Utils.escribirLog("Error de conexion"+e.getMessage());
			e.printStackTrace();
			System.err.println("Error de conexion");
		}

		return connex;
	}

	public void Desconectar() {
		try {
			connex.close();
		} catch (SQLException e) {
			Utils.escribirLog("Error de desconexion"+e.getMessage());
			e.printStackTrace();
			
		}
	}
public void leerDesdeFichero() throws IOException {
		
		String cadena; 
        FileReader f = new FileReader("/Users/chori/eclipse-workspace/NutrimacFinal/src/conexion.txt"); 
        BufferedReader b = new BufferedReader(f);
        String[] arr = new String[4];
		int i = 0;
        while((cadena = b.readLine())!=null) { 
        	arr[i] = cadena;
			i++;
        } 
        b.close();
        ip=arr[0];
		base=arr[1];
		usuario=arr[2];
		pass=arr[3];
		
	}

	

}
