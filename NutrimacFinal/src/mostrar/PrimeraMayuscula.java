package mostrar;

public class PrimeraMayuscula {
	public static char[] mayusculas(String dato) {
		char[] caracteres = dato.toCharArray();
		caracteres[0] = Character.toUpperCase(caracteres[0]);
		for (int i = 0; i < dato.length()- 2; i++) 
		    // Es 'palabra'
		    if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',')
		      // Reemplazamos
		      caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
		return caracteres;
	}

}
