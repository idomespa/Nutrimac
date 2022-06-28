package mostrar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeerDesdeFichero {
	public static ArrayList<String> leerFichero() throws IOException {
		ArrayList<String> lista = new ArrayList<String>();

		String cadena; 
		FileReader f = new FileReader(Direcciones.direccionlecturaFtp); 
		BufferedReader b = new BufferedReader(f);
		String[] arr = new String[4];
		int i = 0;
		while((cadena = b.readLine())!=null) { 
			arr[i] = cadena;
			i++;
		} 
		b.close();
		lista.add(arr[0]);
		lista.add(arr[1]);
		lista.add(arr[2]);
		lista.add(arr[3]);
		return lista;

	}
}
