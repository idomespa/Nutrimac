package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import generadas.Mediciones;
import mostrar.Conexion;
import mostrar.ListarMediciones;
import mostrar.PrimeraMayuscula;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GestionarPacientes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5921895944596452749L;
	private JPanel contentPane;
	private static JTextField textnombre;
	private static JTextField textapellido;
	private static JTextField textcorreo;
	private static JTextField textdni;
	private static boolean activo = false;
	private static ArrayList<Mediciones> medicion;
	private JTable table;
	private static JButton btnAltaPacientes;


	public GestionarPacientes(Conexion con) {
		activo = true;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1016, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 1005, 572);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(25, 18, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscadorPersonas bp= new BuscadorPersonas(con);
				bp.setVisible(true);
				
			}
		});
		btnNewButton.setEnabled(true);
		
		textnombre = new JTextField();
		textnombre.setBounds(74, 51, 142, 26);
		textnombre.setEditable(false);
		textnombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(6, 56, 62, 16);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido:");
		lblNewLabel_1.setBounds(6, 94, 62, 16);
		
		textapellido = new JTextField();
		textapellido.setBounds(74, 89, 240, 26);
		textapellido.setEditable(false);
		textapellido.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Correo:");
		lblNewLabel_1_1.setBounds(228, 56, 46, 16);
		
		textcorreo = new JTextField();
		textcorreo.setBounds(280, 51, 235, 26);
		textcorreo.setEditable(false);
		textcorreo.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("DNI:");
		lblNewLabel_1_1_1.setBounds(326, 94, 34, 16);
		
		
		
		JButton btnNewButton_1 = new JButton("Modificar Datos");
		btnNewButton_1.setBounds(846, 203, 144, 29);
		
		JButton btnNewButton_1_1 = new JButton("Nuevos Pliegues");
		btnNewButton_1_1.setBounds(846, 238, 144, 29);
		
		JButton btnNewButton_1_1_1 = new JButton("Activar Usuario");
		btnNewButton_1_1_1.setBounds(840, 443, 144, 29);
		btnNewButton_1_1_1.setEnabled(false);
		
		btnAltaPacientes = new JButton("Alta Paciente");
		btnAltaPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAltaPacientes.setBounds(846, 168, 144, 29);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(840, 150, 159, 12);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 133, 801, 432);
		
		textdni = new JTextField();
		textdni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textdni.getText().equals("")) {
					btnAltaPacientes.setEnabled(true);
				}
			}
		});
		textdni.setBounds(372, 89, 143, 26);
		textdni.setEditable(false);
		textdni.setColumns(10);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		panel.setLayout(null);
		panel.add(btnNewButton);
		panel.add(lblNewLabel);
		panel.add(textnombre);
		panel.add(lblNewLabel_1);
		panel.add(textapellido);
		panel.add(lblNewLabel_1_1);
		panel.add(textcorreo);
		panel.add(lblNewLabel_1_1_1);
		panel.add(textdni);
		panel.add(scrollPane);
		panel.add(btnNewButton_1_1_1);
		panel.add(btnAltaPacientes);
		panel.add(separator_1);
		panel.add(btnNewButton_1);
		panel.add(btnNewButton_1_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(840, 269, 159, 12);
		panel.add(separator_1_1);
		 
		btnAltaPacientes.setEnabled(false);
		
	}
	
	public static void rellenar(String uno,String dos, String tres, String cuatro) {
		textnombre.setText(String.valueOf(PrimeraMayuscula.mayusculas(uno)));
		textapellido.setText(String.valueOf(PrimeraMayuscula.mayusculas(dos)));
		textcorreo.setText(tres);
		textdni.setText(cuatro);
		
	}
	public static boolean activo() {
		return activo;
	}
	
	public static void rellenarTabla(Conexion con, String correo) {
		try {
			ListarMediciones lM= new ListarMediciones();
			medicion = lM.listarMedicionCliente(con, correo);
			con.Desconectar();
			displayResult(medicion);
			
		} catch (Exception e) {
		}
	}
	
	public static void displayResult(ArrayList<Mediciones> resultList) {
		try {
			
			/*Vector<String> tableHeaders = new Vector<String>();
			Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
			tableHeaders.add("Peso");
			tableHeaders.add("B");
			tableHeaders.add("Telefono");
			tableHeaders.add("Hora");
			tableHeaders.add("Asistencia");
			tableHeaders.add("Online");

			resultList.forEach((l) -> {
				Vector<Object> oneRow = new Vector<Object>();
				oneRow.add(l.getIdCita());
				oneRow.add(l.getClientes().getNombre()+" "+l.getClientes().getApellidos());
				oneRow.add(l.getClientes().getTelefono());
				String [] hora = l.getHoraCita().toString().split(":");
				oneRow.add(hora[0]+":"+hora[1]);
				String dato = null;
				String datoOnline = null;
				if(l.getAsistencia().equals(true)) {
					 dato = "Si";
				}else {
					dato = "No";
				}
				oneRow.add(dato);
				if(l.getOnline().equals(true)) {
					datoOnline = "Si";
				}else {
					datoOnline = "No";
				}
				oneRow.add(datoOnline);
				tableData.add(oneRow);
			});
			tabla.setModel(new DefaultTableModel(tableData, tableHeaders));
			tabla.getColumnModel().getColumn(1).setPreferredWidth(170);
			tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
			/*tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
			tabla.getColumnModel().getColumn(3).setPreferredWidth(25);
			tabla.getColumnModel().getColumn(4).setPreferredWidth(15);
			tabla.getColumnModel().getColumn(5).setPreferredWidth(15);
			tabla.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
			centrar.setHorizontalAlignment(JLabel.CENTER);
			tabla.getColumnModel().getColumn(0).setCellRenderer(centrar);
			tabla.getColumnModel().getColumn(4).setCellRenderer(centrar);
			tabla.getColumnModel().getColumn(5).setCellRenderer(centrar);
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void realizarActivacionDesactivacion(boolean dato) {
		btnAltaPacientes.setEnabled(dato);
	}
}
