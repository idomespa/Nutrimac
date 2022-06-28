package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mostrar.Conexion;
import mostrar.InsertarCategoria;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearCategorias extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8856080936889771654L;
	private JPanel contentPane;
	private JTextField textField;


	public CrearCategorias(Conexion con) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 336, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Escriba la categoria que desea insertar");
		lblNewLabel.setBounds(36, 32, 254, 35);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(70, 79, 180, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(InsertarCategoria.insertar(con,textField.getText())) {
					int eleccion = JOptionPane.showOptionDialog(null, "Se ha insertado correctamente\n la categoria:\n\n"+textField.getText(), //contenido de la ventana
			                "Mensaje de Confirmacion" , //titulo de la ventana
			                JOptionPane.YES_NO_OPTION, //para 3 botones si/no/cancel
			                JOptionPane.QUESTION_MESSAGE, //tipo de ícono
			                null,    // null para icono por defecto.
			                new Object[] { "Aceptar"},//objeto para las opciones
			                "SO");
					
					if(eleccion==JOptionPane.YES_OPTION) {
						dispose();
					}
				}
			}
		});
		btnNewButton.setBounds(91, 127, 117, 29);
		contentPane.add(btnNewButton);
	}
}
