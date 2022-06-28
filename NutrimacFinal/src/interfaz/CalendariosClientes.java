package interfaz;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import generadas.Citas;
import mostrar.Asistencia;
import mostrar.CancelarCita;
import mostrar.CitasListar;
import mostrar.Conexion;
import mostrar.FechaCita;
import util.Utils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CalendariosClientes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -775626222526061683L;
	private JPanel contentPane;
	private static JTable tabla;
	private static ArrayList<Citas> cita;
	private static String fecha = null;
	private JButton btnModificar;
	private static DatePicker dateP;
	private JButton btnDarCita;
	private JButton btnInformacion;
	

	
	public CalendariosClientes(Conexion con) {
		setTitle("Citas de Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setSize(new Dimension(1000,550));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		tabla = new JTable();
		tabla.setBounds(139, 261, 300, 83);
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(70, 163, 700, 250);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(tabla);
		
		URL dateImageURL = DarCitaCliente.class.getResource("/images/datepickerbutton1.png");
	    Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
	    ImageIcon dateExampleIcon = new ImageIcon(dateExampleImage);
		dateP = new DatePicker();
		
		dateP.setDateToToday();
		JButton datePickerButton = dateP.getComponentToggleCalendarButton();
        datePickerButton.setText("");
        datePickerButton.setIcon(dateExampleIcon);
        dateP.addDateChangeListener(new SampleDateChangeListener(con));
		dateP.setDateToToday();
		dateP.setBounds(60, 68, 250, 30);
		contentPane.add(dateP);
		
		JButton btnAsitencia = new JButton("Ha Asistido");
		btnAsitencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tabla.getSelectedRow() != -1) {
					int row = tabla.getSelectedRow();
					int id = Integer.parseInt(tabla.getValueAt(row, 0).toString());
					Asistencia asist = new Asistencia();
					int eleccion = JOptionPane.showOptionDialog(rootPane, "Confirmacion de Asistencia", //contenido de la ventana
			                "Asistencia" , //titulo de la ventana
			                JOptionPane.YES_NO_OPTION, //para 3 botones si/no/cancel
			                JOptionPane.QUESTION_MESSAGE, //tipo de ícono
			                null,    // null para icono por defecto.
			                new Object[] { "Si","No"},//objeto para las opciones
			                "SO");
					if (eleccion == JOptionPane.YES_OPTION)
					{
						if(asist.Asistio(con, id,1).equals(true)) {
							JOptionPane.showOptionDialog(rootPane, "Asistencia confirmada", //contenido de la ventana
					                "Asistencia" , //titulo de la ventana
					                JOptionPane.YES_NO_OPTION, //para 3 botones si/no/cancel
					                JOptionPane.INFORMATION_MESSAGE, //tipo de ícono
					                null,    // null para icono por defecto.
					                new Object[] { "Aceptar"},//objeto para las opciones
					                "SO");
							rellenarTabla(con, fecha);
						
						}
					}else {
						if(asist.Asistio(con, id,0).equals(true)) {
							JOptionPane.showOptionDialog(rootPane, "El cliente no asistio", //contenido de la ventana
					                "Asistencia" , //titulo de la ventana
					                JOptionPane.YES_NO_OPTION, //para 3 botones si/no/cancel
					                JOptionPane.INFORMATION_MESSAGE, //tipo de ícono
					                null,    // null para icono por defecto.
					                new Object[] { "Aceptar"},//objeto para las opciones
					                "SO");
							rellenarTabla(con, fecha);
						
						}
					}
					
					
					
				}else {
					mostrar();
				}
			}
			
		});
		btnAsitencia.setBounds(814, 193, 117, 29);
		contentPane.add(btnAsitencia);
		
		btnModificar= new JButton("Modificar Cita");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tabla.getSelectedRow() != -1) {
					int row = tabla.getSelectedRow();
					int id = Integer.parseInt(tabla.getValueAt(row, 0).toString());
					FechaCita fc = new FechaCita();
					ArrayList<String> hola = new ArrayList<String>();
					hola.addAll(fc.lfechaCita(con, id));
					ModificarCita modCita = new ModificarCita(hola.get(0),hola.get(1));
					modCita.cargarDatos(id);
					modCita.setVisible(true);
				}else {
					
					mostrar();
				}
			}
		});
		btnModificar.setBounds(496, 433, 117, 29);
		contentPane.add(btnModificar);
		
		JButton btnCancelar = new JButton("Cancelar Cita");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tabla.getSelectedRow() != -1) {
					int row = tabla.getSelectedRow();
					int id = Integer.parseInt(tabla.getValueAt(row, 0).toString());
					int reply = JOptionPane.showConfirmDialog(null, "Desea cancelar la cita?", "Cancelar Cita", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						CancelarCita cancelCt = new CancelarCita();
						if(cancelCt.Cancelar(con, id) > 0){
							JOptionPane.showConfirmDialog(null, "Cancelación Completada", "Aceptar",
									JOptionPane.CLOSED_OPTION);
							rellenarTabla(con, fecha);
							
							
						}	
					}
				}else {
					mostrar();
				}
			}
		});
		btnCancelar.setBounds(653, 433, 117, 29);
		contentPane.add(btnCancelar);
		
		btnDarCita = new JButton("Dar Cita");
		btnDarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DarCitaCliente dCC = new DarCitaCliente(con,null,null,null,false);
				dCC.setVisible(true);
			}
		});
		btnDarCita.setBounds(814, 228, 117, 29);
		contentPane.add(btnDarCita);
		
		btnInformacion = new JButton("Información");
		btnInformacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabla.getSelectedRow() != -1) {
					int row = tabla.getSelectedRow();
					int id = Integer.parseInt(tabla.getValueAt(row, 0).toString());
					
					DetallesCita detCitas = new DetallesCita();
					detCitas.crear(id);
					detCitas.setVisible(true);					
					
				}else {
					mostrar();
				}
			}
		});
		btnInformacion.setBounds(814, 160, 117, 29);
		contentPane.add(btnInformacion);
		
		fecha();
		rellenarTabla(con, fecha);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(WindowEvent winEvt) {
				con.Desconectar();
			}
		});
	}
	
	public static void displayResult(ArrayList<Citas> resultList) {
		

		try {
			
			Vector<String> tableHeaders = new Vector<String>();
			Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
			tableHeaders.add("Cita");
			tableHeaders.add("Cliente");
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
			tabla.getColumnModel().getColumn(3).setPreferredWidth(25);*/
			tabla.getColumnModel().getColumn(4).setPreferredWidth(15);
			tabla.getColumnModel().getColumn(5).setPreferredWidth(15);
			tabla.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
			centrar.setHorizontalAlignment(JLabel.CENTER);
			tabla.getColumnModel().getColumn(0).setCellRenderer(centrar);
			tabla.getColumnModel().getColumn(4).setCellRenderer(centrar);
			tabla.getColumnModel().getColumn(5).setCellRenderer(centrar);
			
		} catch (Exception e) {
			e.printStackTrace();
			Utils.escribirLog("Error en la tabla de gestion de citas:" + e.getMessage());
		}
	}
	public static void fecha() {
		fecha = dateP.getDate().toString();
		
	}
	
	public static void rellenarTabla(Conexion con, String fecha) {
		try {
			CitasListar ado= new CitasListar();
			cita = ado.listarCliente(con, fecha);
			con.Desconectar();
			displayResult(cita);
			
		} catch (Exception e) {
		}
	}
	
	  private static class SampleDateChangeListener implements DateChangeListener {
		  private Conexion conn;
	        public SampleDateChangeListener(Conexion con) {
	        	this.conn = con;
		}

			@Override
	        public void dateChanged(DateChangeEvent event) {
				fecha();
				rellenarTabla(conn, fecha);
	        }
	    }
	  private void mostrar() {
		  JOptionPane.showOptionDialog(null, "Debe seleccionar una fila", //contenido de la ventana
                  "Aviso" , //titulo de la ventana
                  JOptionPane.OK_OPTION, //para 3 botones si/no/cancel
                  JOptionPane.WARNING_MESSAGE, //tipo de ícono
                  null,    // null para icono por defecto.
                  new Object[] { "Aceptar"},//objeto para las opciones
                  //null para YES, NO y CANCEL
                  "SO");
	  }
}
