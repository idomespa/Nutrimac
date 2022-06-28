import java.io.IOException;

import mostrar.MandarCorreo;

public class Llamada {

	public static void main(String[] args) {
		MandarCorreo mc = new MandarCorreo();
		try {
			mc.send("Ana Fernanda", "anafipyomisma@gmail.com", "Ibañez", "11 Mayo 2022", "19:00", "reserva", 94);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
