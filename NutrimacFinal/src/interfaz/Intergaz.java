package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mostrar.Conexion;
import util.Utils;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;

public class Intergaz extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7212579786893864692L;
	private JPanel contentPane;
	public static Conexion con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Intergaz frame = new Intergaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Intergaz() {
		setTitle("Gestion de Nutrimac");
		con = new Conexion();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 572, 453);
		Image iconoPropio = Toolkit.getDefaultToolkit().getImage(Intergaz.class.getResource("/imagenes/favicon.ico"));
		setIconImage(iconoPropio);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		/*Build the first menu.
		menu = new JMenu("Opciones");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);

		JCheckBoxMenuItem aiMode = new JCheckBoxMenuItem("Actualizaciones Automaticas");
		aiMode.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(aiMode.getSelectedObjects().toString() != null) {
					
				}else {
					
				}
				
			}
		});
		if(dato[1].equals("Si"))
			aiMode.setSelected(true);
		else
			aiMode.setSelected(false);
		
		menu.add(aiMode);*/

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Citas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalendariosClientes cl = new CalendariosClientes(con);
				cl.setVisible(true);
			}
		});
		btnNewButton.setBounds(140, 258, 96, 29);
		panel.add(btnNewButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 299, 558, 12);
		panel.add(separator);

		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setBounds(239, 230, 61, 16);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Personal");
		lblNewLabel_1.setBounds(239, 323, 61, 16);
		panel.add(lblNewLabel_1);

		JButton btnMisCitas = new JButton("Mis Citas");
		btnMisCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReservarmeCitas rCta= new ReservarmeCitas();
				rCta.setVisible(true);
			}
		});
		btnMisCitas.setBounds(140, 351, 117, 29);
		panel.add(btnMisCitas);

		JButton btnBlog = new JButton("Blog");
		btnBlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazBlog iB = new InterfazBlog(con);
				iB.setVisible(true);
				}
		});
		btnBlog.setBounds(281, 351, 117, 29);
		panel.add(btnBlog);


		JLabel jLabel1 = new JLabel();
		jLabel1.setIcon(new ImageIcon(Intergaz.class.getResource("/imagenes/logo.png")));
		jLabel1.setBounds(46, 0, 455, 218);
		panel.add(jLabel1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 212, 558, 12);
		panel.add(separator_1);
		
		JButton btnNewButton_1 = new JButton("Gestion de Pacientes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionarPacientes gP = new GestionarPacientes(con);
				gP.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(281, 258, 162, 29);
		panel.add(btnNewButton_1);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					con.Desconectar();

				}catch(Exception ec) {
					Utils.escribirLog("Error: " + ec.getMessage());
				}finally {
					cerrar();
				}

			}
		});

	}
	public void leerDesdeFichero() throws IOException {

		String cadena; 
		FileReader f = new FileReader("/Users/chori/eclipse-workspace/NutrimacFinal/src/config.ini"); 
		BufferedReader b = new BufferedReader(f);
		String[] arr = new String[4];
		int i = 0;
		while((cadena = b.readLine())!=null) { 
			arr[i] = cadena;
			i++;
		} 
		b.close();
		


	}


	public void cerrar(){
		Object [] opciones ={"Aceptar","Cancelar"};
		Icon icono = new ImageIcon(getClass().getResource("/imagenes/nuevologo.png"));
		int eleccion = JOptionPane.showOptionDialog(rootPane, "En realidad desea realizar cerrar la aplicacion", //contenido de la ventana
                "Mensaje de Confirmacion" , //titulo de la ventana
                JOptionPane.YES_NO_OPTION, //para 3 botones si/no/cancel
                JOptionPane.QUESTION_MESSAGE, //tipo de ícono
                icono,    // null para icono por defecto.
                new Object[] { "Aceptar"},//objeto para las opciones
                "SO");
		if (eleccion == JOptionPane.YES_OPTION)
		{
			System.exit(0);
		}
	}
}
