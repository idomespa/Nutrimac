package interfaz;

import java.awt.event.WindowEvent;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.optionalusertools.DateHighlightPolicy;
import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.zinternaltools.HighlightInformation;

import generadas.Citas;
import mostrar.ActualizarCita;
import mostrar.CitasListar;
import mostrar.Conexion;
import util.Utils;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JCheckBox;

public class ModificarCita extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5744608863403200484L;
	private JPanel contentPane;
	private static int id;
	public static Conexion con;
	private static ArrayList<Citas> cita;
	private static JTextField txtNombre;
	private static JTextField txtCorreo;
	private static JTextField txtApellidos;
	private static JTextField txtCiudad;
	private static JTextField txtDireccion;
	private static JTextField txtTelefono;
	private static JCheckBox chckbxNewCheckBox;
	
	private static String newDateString;
	private static String horaquehay;
	private static String fechaquehay;
	
	private static DatePicker dateP;
	private static JComboBox<String> timeP;
	private static ArrayList<String> listaHoras;
	private static ArrayList<String> listtiempo;
	private static ArrayList<String> listTratamiento;
	
	private static String[] dato;
	private static Date fecha1;
	private static Date fecha2;
	
	private static String horasMedias = "medias";

	/**
	 * Create the frame.
	 * @param tring 
	 */
	public ModificarCita(String dato, String ing) {
		fechaquehay = dato;
		horaquehay = ing;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 291);
		setTitle("Modificar Cita de Clientes");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listaHoras = new ArrayList<String>();

		if(horasMedias.equals("enteras")) {
			for (int i=9; i<=13; i++){
				if(i<10) {
					listaHoras.add("0"+i+":00"); 
				}else {
					listaHoras.add(i+":00"); 
				}
			}
			for (int i=17; i<=19; i++){
				listaHoras.add(i+":00"); 
			}
		}else{
			for (int i=9; i<=13; i++){
				if(i<10) {
					listaHoras.add("0"+i+":30"); 
				}else {
					listaHoras.add(i+":30"); 
				}
			}
			for (int i=17; i<=19; i++){
				listaHoras.add(i+":30"); 
			}
		}	
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(70, 46, 117, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setEditable(false);
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(475, 46, 197, 26);
		contentPane.add(txtCorreo);
		
		txtApellidos = new JTextField();
		txtApellidos.setEditable(false);
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(252, 46, 173, 26);
		contentPane.add(txtApellidos);
		
		timeP = new JComboBox<String>();
		timeP.setEnabled(false);
		timeP.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		timeP.setBounds(405, 119, 128, 26);
		contentPane.add(timeP);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(6, 51, 61, 16);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel);
		
		JLabel lblCorre = new JLabel("Correo:");
		lblCorre.setBounds(412, 51, 61, 16);
		lblCorre.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblCorre);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(349, 124, 44, 16);
		lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblHora);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(171, 51, 82, 16);
		lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblApellidos);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(6, 124, 70, 16);
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblFecha);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(218, 89, 61, 16);
		lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblCiudad);
		
		txtCiudad = new JTextField();
		txtCiudad.setColumns(10);
		txtCiudad.setBounds(279, 84, 141, 26);
		contentPane.add(txtCiudad);
		
		JLabel lblFecha_1_1 = new JLabel("Dirección:");
		lblFecha_1_1.setBounds(429, 91, 70, 16);
		lblFecha_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblFecha_1_1);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(504, 84, 168, 26);
		contentPane.add(txtDireccion);
		
		chckbxNewCheckBox = new JCheckBox("Online");
		chckbxNewCheckBox.setBounds(544, 120, 128, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JButton btnAceptar = new JButton("Modificar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String fechaHoy = dtf.format(LocalDateTime.now());
				if(fecha1.compareTo(fecha2) < 0) {
					JOptionPane.showOptionDialog(null, "No se aceptan fechas anteriores al dia de hoy", //contenido de la ventana
	                         "Aviso" , //titulo de la ventana
	                         JOptionPane.OK_OPTION, //para 3 botones si/no/cancel
	                         JOptionPane.ERROR_MESSAGE, //tipo de ícono
	                         null,    // null para icono por defecto.
	                         new Object[] { "Aceptar"},//objeto para las opciones
	                         //null para YES, NO y CANCEL
	                         "SO");					
				}else {
					if(chckbxNewCheckBox.isSelected()) {
						ActualizarCita actCi = new ActualizarCita();
						actCi.actualizar(con,id,newDateString,timeP.getSelectedItem().toString()+":00",
								null,null,1);
						con.Desconectar();
						CalendariosClientes.rellenarTabla(con, fechaHoy);
						dispose();
					}else {
						ActualizarCita actCi = new ActualizarCita();
						actCi.actualizar(con,id,newDateString,timeP.getSelectedItem().toString()+":00",
								txtCiudad.getText(),txtDireccion.getText(),0);
						con.Desconectar();
						CalendariosClientes.rellenarTabla(con, fechaHoy);
						dispose();
					}
					
				}
				
			}
		});
		btnAceptar.setBounds(269, 181, 117, 29);
		contentPane.add(btnAceptar);
		
		JLabel lblNewLabel_1 = new JLabel("Telefono:");
		lblNewLabel_1.setBounds(16, 89, 61, 16);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel_1);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setBounds(80, 84, 130, 26);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Modificar Cita");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(231, 6, 141, 24);
		contentPane.add(lblNewLabel_2);
		
		URL dateImageURL = DarCitaCliente.class.getResource("/images/datepickerbutton1.png");
	    Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
	    ImageIcon dateExampleIcon = new ImageIcon(dateExampleImage);
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateP = new DatePicker(dateSettings);
		dateP.addDateChangeListener(new SampleDateChangeListener());
		dateSettings.setHighlightPolicy(new SampleHighlightPolicy());
	    dateSettings.setVetoPolicy(new SampleDateVetoPolicy());
        dateSettings.setFormatForDatesBeforeCommonEra("uuuu-MM-dd");
	    JButton datePickerButton = dateP.getComponentToggleCalendarButton();
	    datePickerButton.setText("");
	    datePickerButton.setIcon(dateExampleIcon);
		dateP.setDateToToday();
		dateP.setBounds(88, 119, 250, 36);
		contentPane.add(dateP);
		
		
		 
         
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(WindowEvent winEvt) {
				con.Desconectar();
			}
		});
		
	}
	public void cargarDatos(int numero) {
		id=numero;
		con = new Conexion();
        CitasListar ctl = new CitasListar();
        cita =  ctl.listarCitasId(con, id);
        con.Desconectar();
        cita.forEach((l) ->{
        	txtNombre.setText(l.getClientes().getNombre());
        	txtApellidos.setText(l.getClientes().getApellidos());	
			String [] hora = l.getHoraCita().toString().split(":");
			horaquehay = hora[0]+":"+hora[1];
			fechaquehay = String.valueOf(l.getFechaCita());
			dateP.setText(String.valueOf(l.getFechaCita()));
        	txtCiudad.setText(l.getPresencial());
        	txtCorreo.setText(l.getClientes().getEmailId());
        	txtDireccion.setText(l.getDireccion());
        	txtTelefono.setText(String.valueOf(l.getClientes().getTelefono()));
        	chckbxNewCheckBox.setSelected(l.getOnline());
        });
	}
	

	
	private static class SampleHighlightPolicy implements DateHighlightPolicy {

	    @Override
	    public HighlightInformation getHighlightInformationOrNull(LocalDate date) {
	      // Highlight a chosen date, with a tooltip and a red background color.
	      // if (date.getDayOfMonth() == 25) {
	      //  return new HighlightInformation(Color.red, null, "It's the 25th!");
	      //}
	      // Highlight all Saturdays with a unique background and foreground color.
	      //if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
	      //  return new HighlightInformation(Color.orange, Color.yellow, "It's Saturday!");
	      //}
	      // Highlight all Sundays with default colors and a tooltip.
	      if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
	        return new HighlightInformation(null, null, "It's Sunday!");
	      }
	      // All other days should not be highlighted.
	      return null;
	    }
	  }
  
  private static class SampleDateVetoPolicy implements DateVetoPolicy {

	    @Override
	    public boolean isDateAllowed(LocalDate date) {
	      
	      if ((date.getDayOfWeek() == DayOfWeek.SUNDAY) /*&& ((date.getDayOfMonth() % 2) == 1)*/) {
	        return false;
	      }
	      // Allow all other days.
	      return true;
	    }
	  }
  
  private static class SampleDateChangeListener implements DateChangeListener {
        

		@Override
        public void dateChanged(DateChangeEvent event) {
			try {
	        	ArrayList<String> listaPaCombo = new ArrayList<String>();
	       	 	LocalDate newDate = event.getNewDate();
	            DateTimeFormatter dtfH = DateTimeFormatter.ofPattern("HH");
	            int horaHoy = Integer.parseInt(dtfH.format(LocalDateTime.now()));
	           
	       	 
	            newDateString = PickerUtilities.localDateToString(newDate, "(null)");
	            
	            con = new Conexion();
	            CitasListar ctl = new CitasListar();
	            listtiempo =  ctl.listarCitas(con, newDateString);
	            con.Desconectar();
	            
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	            dato = horaquehay.split(":");
	            
	           
				try {
					fecha1 = sdf.parse(newDateString);
					fecha2 = sdf.parse(fechaquehay);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	            listTratamiento = new ArrayList<String>();
	            timeP.removeAllItems();
	            listTratamiento.addAll(listtiempo);
	            if( fecha1.compareTo(fecha2) == 0 ){
	            	for(int i=9;i<=horaHoy; i++) {
	            		if(horasMedias.equals("enteras")) {
		            		if(i==9) {
		            			if(!listTratamiento.contains("0"+i+";00")) {
		            				listTratamiento.add("0"+i+":00");
		            			}
		            				
		
		            		}else if(i!=14 && i != 15 && i != 16) {
		            			boolean existe = listTratamiento.contains(i+";00");
		            			if(!existe) {
		            				listTratamiento.add(i+":00");
		            			}
		            			
		            		} 
	            		}else {
	            			if(i==9) {
		            			if(!listTratamiento.contains("0"+i+";30")) {
		            				listTratamiento.add("0"+i+":30");
		            			}
		            				
		
		            		}else if(i!=14 && i != 15 && i != 16) {
		            			boolean existe = listTratamiento.contains(i+";30");
		            			if(!existe) {
		            				listTratamiento.add(i+":30");
		            			}
		            			
		            		} 
	            		}
		             }
	            	for (String element : listaHoras) { 
	                    if (!listTratamiento.contains(element)) { 
	                        listaPaCombo.add(element); 
	                    } 
	                }
	            	listaPaCombo.add("-----");
	            	listaPaCombo.add(dato[0]+":"+dato[1]);
	            }else if (fecha1.compareTo(fecha2) > 0) {
	            	for (String element : listaHoras) { 
	                    if (!listtiempo.contains(element)) { 
	                        listaPaCombo.add(element); 
	                    } 
	                }
	            }
	             
	             
	             rellenarComboBox(listaPaCombo);
	             
	       		timeP.setEnabled(true);
			}catch(Exception ep) {
				Utils.escribirLog("Mod Cita Cambio Fecha :" + ep.getMessage());

			}
        	
        }
    }
  
	public static void rellenarComboBox(ArrayList<String> lista) {
		for (String eq : lista) {
			timeP.addItem(eq);
		}
		if(fecha1.compareTo(fecha2) == 0 ) 
			timeP.setSelectedItem(dato[0]+":"+dato[1]);
		
	}

}
