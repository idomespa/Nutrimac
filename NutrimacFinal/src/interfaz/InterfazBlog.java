package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mostrar.Conexion;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazBlog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -326486094529794341L;
	private JPanel contentPane;

	
	public InterfazBlog(Conexion con) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 438, 258);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion del Blog");
		lblNewLabel.setBounds(121, 6, 156, 25);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Que desea hacer");
		lblNewLabel_1.setBounds(146, 31, 103, 16);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Añadir un nuevo Blog");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearBlog creB = new CrearBlog(con);
				creB.setVisible(true);
			}
		});
		btnNewButton.setBounds(115, 69, 178, 29);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Gestionar los Blogs");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazGestionarBlog iGB = new InterfazGestionarBlog(con);
				iGB.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(121, 110, 178, 29);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Añadir Categorias");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearCategorias cc = new CrearCategorias(con);
				cc.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(121, 151, 172, 29);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Desactivar Comentarios");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DesactivarComentarios coment = new DesactivarComentarios(con);
				coment.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(121, 188, 178, 29);
		panel.add(btnNewButton_3);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(WindowEvent winEvt) {
			
			}
		});
	}

}






