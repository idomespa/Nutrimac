package generadas;
// default package
// Generated 9 mar. 2022 19:42:08 by Hibernate Tools 5.6.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * NutriComentarios generated by hbm2java
 */
public class NutriComentarios implements java.io.Serializable {

	private Integer commentId;
	private Blog blog;
	private Long commentReply;
	private Date commentFecha;
	private String commentAutor;
	private Boolean aprovado;
	private String ipAutor;
	private String fotoAutor;
	private String commentTexto;
	private String autorEmail;
	private Set replyComentses = new HashSet(0);

	public NutriComentarios() {
	}
	public NutriComentarios(Integer commentId, String commentAutor, String commentTexto, String autorEmail, Boolean aprovado) {
		this.commentId = commentId;
		this.commentAutor = commentAutor;
		this.commentTexto = commentTexto;
		this.autorEmail = autorEmail;
		this.aprovado = aprovado;
	}

	public NutriComentarios(Blog blog, Date commentFecha, String commentAutor, Boolean aprovado, String commentTexto,
			String autorEmail) {
		this.blog = blog;
		this.commentFecha = commentFecha;
		this.commentAutor = commentAutor;
		this.aprovado = aprovado;
		this.commentTexto = commentTexto;
		this.autorEmail = autorEmail;
	}

	public NutriComentarios(Blog blog, Long commentReply, Date commentFecha, String commentAutor, Boolean aprovado,
			String ipAutor, String fotoAutor, String commentTexto, String autorEmail, Set replyComentses) {
		this.blog = blog;
		this.commentReply = commentReply;
		this.commentFecha = commentFecha;
		this.commentAutor = commentAutor;
		this.aprovado = aprovado;
		this.ipAutor = ipAutor;
		this.fotoAutor = fotoAutor;
		this.commentTexto = commentTexto;
		this.autorEmail = autorEmail;
		this.replyComentses = replyComentses;
	}

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Blog getBlog() {
		return this.blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Long getCommentReply() {
		return this.commentReply;
	}

	public void setCommentReply(Long commentReply) {
		this.commentReply = commentReply;
	}

	public Date getCommentFecha() {
		return this.commentFecha;
	}

	public void setCommentFecha(Date commentFecha) {
		this.commentFecha = commentFecha;
	}

	public String getCommentAutor() {
		return this.commentAutor;
	}

	public void setCommentAutor(String commentAutor) {
		this.commentAutor = commentAutor;
	}

	public boolean isAprovado() {
		return this.aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}
	public boolean getAprobado() {
		return aprovado;
	}

	public String getIpAutor() {
		return this.ipAutor;
	}

	public void setIpAutor(String ipAutor) {
		this.ipAutor = ipAutor;
	}

	public String getFotoAutor() {
		return this.fotoAutor;
	}

	public void setFotoAutor(String fotoAutor) {
		this.fotoAutor = fotoAutor;
	}

	public String getCommentTexto() {
		return this.commentTexto;
	}

	public void setCommentTexto(String commentTexto) {
		this.commentTexto = commentTexto;
	}

	public String getAutorEmail() {
		return this.autorEmail;
	}

	public void setAutorEmail(String autorEmail) {
		this.autorEmail = autorEmail;
	}

	public Set getReplyComentses() {
		return this.replyComentses;
	}

	public void setReplyComentses(Set replyComentses) {
		this.replyComentses = replyComentses;
	}
	

}
