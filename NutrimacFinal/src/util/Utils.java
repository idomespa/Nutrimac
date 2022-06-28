package util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Utils {
	
	public static void escribirLog(String mensaje) {
		
		Logger logger = Logger.getLogger("MyLog");
	    FileHandler fh;
	    String rutaArchivo = "log.log";

	    try {

	        fh = new FileHandler(rutaArchivo, true);
	        logger.addHandler(fh);

	        SimpleFormatter formatter = new SimpleFormatter();

	        fh.setFormatter(formatter);

	        logger.info(mensaje);

	        fh.close();

	    } catch (SecurityException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}

}
