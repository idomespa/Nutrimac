package generadas;
// default package
// Generated 9 mar. 2022 19:42:08 by Hibernate Tools 5.6.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * AntecedentesFamiliares generated by hbm2java
 */
public class AntecedentesFamiliares implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8041594012802699723L;
	private Integer idAntFami;
	private String nombreAntecedente;
	private Set antecedentesPertClientes = new HashSet(0);

	public AntecedentesFamiliares() {
	}

	public AntecedentesFamiliares(String nombreAntecedente) {
		this.nombreAntecedente = nombreAntecedente;
	}

	public AntecedentesFamiliares(String nombreAntecedente, Set antecedentesPertClientes) {
		this.nombreAntecedente = nombreAntecedente;
		this.antecedentesPertClientes = antecedentesPertClientes;
	}

	public Integer getIdAntFami() {
		return this.idAntFami;
	}

	public void setIdAntFami(Integer idAntFami) {
		this.idAntFami = idAntFami;
	}

	public String getNombreAntecedente() {
		return this.nombreAntecedente;
	}

	public void setNombreAntecedente(String nombreAntecedente) {
		this.nombreAntecedente = nombreAntecedente;
	}

	public Set getAntecedentesPertClientes() {
		return this.antecedentesPertClientes;
	}

	public void setAntecedentesPertClientes(Set antecedentesPertClientes) {
		this.antecedentesPertClientes = antecedentesPertClientes;
	}

}
