package interfaz;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import mostrar.ComentariosPorBlog;
import mostrar.Conexion;

import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetallesComentario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8529317225055553106L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private int id;
	private JTextPane textArea;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetallesComentario frame = new DetallesComentario(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param contentPane2 
	 */
	public DetallesComentario(Conexion con, JPanel contentPane2) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 632, 292);
		setTitle("Modificar Cita de Clientes");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		txtNombre = new JTextField();
		txtNombre.setBounds(70, 46, 207, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(372, 46, 251, 26);
		contentPane.add(txtCorreo);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(6, 51, 61, 16);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel);
		
		JLabel lblCorre = new JLabel("Correo:");
		lblCorre.setBounds(299, 51, 61, 16);
		lblCorre.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblCorre);
		
		textArea = new JTextPane();
		textArea.setBounds(70, 94, 474, 103);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Desactivar Comentario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComentariosPorBlog cp = new ComentariosPorBlog();
				boolean datosDevultos;
				datosDevultos = cp.cancelarComentario(con, id);
				con.Desconectar();
				if (datosDevultos == true) {
					JOptionPane.showConfirmDialog(null, "Comentario cancelado con exito", "Aceptar",
							JOptionPane.CLOSED_OPTION);
					dispose();
					contentPane2.revalidate();
					contentPane2.repaint();
					
			}else {
				JOptionPane.showConfirmDialog(null, "No se pudo cancelar el comentario", "Aceptar",
						JOptionPane.CLOSED_OPTION);
			}
			}
		});
		btnNewButton.setBounds(344, 215, 174, 29);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(215, 215, 117, 29);
		contentPane.add(btnNewButton_1);
		
	}
	
	public void cargarDatos(int numero, String autor, String texto, String email) {
		id = numero;
		txtNombre.setText(autor);
		textArea.setText(texto);
		txtCorreo.setText(email);
		
	}
}
