package mostrar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import util.Utils;

public class MandarCorreo {
	public static String send(String name, String email,String ape,
			String fecha, String hora,String citaid,long id) throws IOException {
		
		String variableM = "";
		try {
			
		
			String fechaMod = fecha.replace(" de ", " "); 
			
			String nuevaF = String.valueOf(PrimeraMayuscula.mayusculas(fechaMod));
			
	        URL url = new URL(Direcciones.direccionEmail);
	        
	        Map<String, Object> params = new LinkedHashMap<>();
	 
	        
	        params.put("name", name);
	        params.put("email", email);
	        params.put("apell", ape);
	        params.put("fecha", nuevaF);
	        params.put("hora", hora);
	        params.put("cita", citaid);
	        params.put("citaid", id);
	        
	 
	        StringBuilder postData = new StringBuilder();
	        for (Map.Entry<String, Object> param : params.entrySet()) {
	            if (postData.length() != 0)
	                postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
	                    "UTF-8"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	 
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type",
	                "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length",
	                String.valueOf(postDataBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);
	 
	        Reader in = new BufferedReader(new InputStreamReader(
	                conn.getInputStream(), "UTF-8"));
	        
	        for (int c = in.read(); c != -1; c = in.read())
	        	variableM += (char) c;
            
		}catch (Exception e) {
			Utils.escribirLog("Mensaje :" + e.getMessage());
		}
        return variableM;
    }   
}
