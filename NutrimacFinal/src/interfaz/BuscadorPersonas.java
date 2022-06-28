package interfaz;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import generadas.Clientes;
import mostrar.CargarClientes;
import mostrar.Conexion;
import mostrar.PrimeraMayuscula;
import util.Utils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class BuscadorPersonas extends JFrame {


	private static final long serialVersionUID = -994999386159873356L;
	private JPanel contentPane;
	private static JTable tabla;
	private static ArrayList<Clientes> listado = new ArrayList();
	private static ArrayList<Clientes> listadoClien;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton btnNewButton;


	public BuscadorPersonas(Conexion con) {
		setTitle("Buscar Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setSize(new Dimension(691, 282));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = tabla.getSelectedRow();
				String dni = "";
				if(DarCitaCliente.estaActivo()) {
					DarCitaCliente.rellenarDatos(tabla.getValueAt(row, 0).toString(),
							tabla.getValueAt(row, 1).toString(),
							tabla.getValueAt(row, 2).toString());
					dispose();
				}
				if(GestionarPacientes.activo()) {
					if(tabla.getValueAt(row, 3) != null) {
						GestionarPacientes.rellenar(tabla.getValueAt(row, 0).toString(),
								tabla.getValueAt(row, 1).toString(),
								tabla.getValueAt(row, 2).toString(),
								tabla.getValueAt(row, 3).toString());
						GestionarPacientes.realizarActivacionDesactivacion(false);
						
						
						dispose();
					}else {
						GestionarPacientes.rellenar(tabla.getValueAt(row, 0).toString(),
								tabla.getValueAt(row, 1).toString(),
								tabla.getValueAt(row, 2).toString(),
								dni);
						GestionarPacientes.realizarActivacionDesactivacion(true);
						dispose();
					}
				}
				
			}
		});
		tabla.setBounds(139, 261, 300, 83);
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(70, 96, 586, 105);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(tabla);
		
		textField = new JTextField();
		textField.setBounds(70, 58, 212, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setBounds(70, 44, 142, 16);
		contentPane.add(lblNewLabel);
		
		
		textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                campoBuscadorKeyReleased(evt);
            }
        });
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(WindowEvent winEvt) {
				con.Desconectar();
			}
		});
		
		rellenarTabla(con);

	}
	
	private void campoBuscadorKeyReleased(KeyEvent evt) {
        String cadenaInformativa = "";
        listadoClien = new ArrayList();
        String nombre = null, apellido = null,correo = null,nif = null;
        Date fecha = null;
        int telf = 0;
        for(int i=0; i<listado.size(); ++i) {
            if(listado.get(i).empiezaPor(textField.getText().toLowerCase())) {
            	nombre = listado.get(i).getNombre();
            	apellido = listado.get(i).getApellidos();
            	correo = listado.get(i).getEmailId();
            	nif = listado.get(i).getDniNif();
            	fecha= listado.get(i).getFechaNacimiento();
            	telf = listado.get(i).getTelefono();
            	listadoClien.add(new Clientes(correo,nombre,apellido,nif,fecha,telf));
            }

                
        }
        displayResult(listadoClien);
       
        
    }
	
	public static void displayResult(ArrayList<Clientes> resultList) {
		

		try {
			
			Vector<String> tableHeaders = new Vector<String>();
			Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
			tableHeaders.add("Nombre");
			tableHeaders.add("Apellido");
			tableHeaders.add("Correo");
			tableHeaders.add("DNI");
			

			resultList.forEach((l) -> {
				Vector<Object> oneRow = new Vector<Object>();
				oneRow.add(String.valueOf(PrimeraMayuscula.mayusculas(l.getNombre())));
				oneRow.add(String.valueOf(PrimeraMayuscula.mayusculas(l.getApellidos())));
				oneRow.add(l.getEmailId());
				oneRow.add(l.getDniNif());
				
				tableData.add(oneRow);
			});
			
			tabla.setModel(new DefaultTableModel(tableData, tableHeaders));
			
		} catch (Exception e) {
			e.printStackTrace();
			Utils.escribirLog("Error en la tabla buscar personas:" + e.getMessage());
		}
	}
	
	public static void rellenarTabla(Conexion con) {
		try {
			CargarClientes cc = new CargarClientes();
			listado = cc.listarCliente(con);
			con.Desconectar();
		} catch (Exception e) {
			e.printStackTrace();
			Utils.escribirLog("rellenar tabla buscar personas:" + e.getMessage());
		}
	}
}
