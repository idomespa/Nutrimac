package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

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

import mostrar.CitasListar;
import mostrar.Conexion;
import mostrar.Direcciones;
import mostrar.InsertarCitaCliente;
import mostrar.InsertarCliente;
import mostrar.MandarCorreo;
import util.Utils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;


public class DarCitaCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5358198151882694231L;
	private JPanel contentPane;
	public static Conexion con;
	private static JTextField txtNombre;
	private JTextField txtApel;
	private static JTextField txtCorreo;
	private JTextField txtTelef;
	private JComboBox<String> comboModalidad;
	private JComboBox<String> comboCiudad;
	private JTextField txtdireccion;
	private JLabel lblmodalidad;
	private JLabel lbldireccion;
	private JLabel lblciudad;
	private JLabel lblTelefono;
	private JLabel lblCorreo;
	private JLabel lblApellido;
	private JLabel lblNombre;
	private JLabel lblFecha;
	private JLabel lblHora;
	private JLabel lblPuebloOtro;
	private JTextField txtotro;
	private JButton btnBuscar;
	
	//private static DarCitaCliente frame;
	private static DatePicker dateP;
	private static JComboBox<String> timeP;
	private static ArrayList<String> listaHoras;
	private static ArrayList<String> listaHorasSabado;
	private static ArrayList<String> listtiempo;
	private ArrayList<String> lisrciudades;
	
	private static String apellido;
	private static String nombre;
	private static boolean activo= false;
	

	public DarCitaCliente(Conexion con,String dato1, String dato2, String dato3, boolean info) {
		activo = true;
		this.setTitle("Dar Citas a Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		this.setSize(new Dimension(550,500));
		
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		listaHoras = new ArrayList<String>();
		listaHorasSabado = new ArrayList<String>();
		
		if(Direcciones.enterosOYMedia.equals("medias")) {
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
		}else {
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
		}
		for (int i=9; i<=13; i++){
			if(i<10) {
				listaHorasSabado.add("0"+i+":00"); 
			}else {
				listaHorasSabado.add(i+":00"); 
			}
		}
		
		lisrciudades = new ArrayList<String>();
		
		lisrciudades.add("Peralta");
		lisrciudades.add("Funes");
		lisrciudades.add("Marcilla");
		lisrciudades.add("Falces");
		lisrciudades.add("Villafranca");
		lisrciudades.add("San Adrian");
		lisrciudades.add("Caparroso");
		lisrciudades.add("Calahorra");
		lisrciudades.add("Otros");
		
		
		JLabel lblNewLabel = new JLabel("Citas a los Clientes");
		lblNewLabel.setBounds(205, 21, 121, 16);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Nuevo Cliente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					panel.revalidate();
					panel.repaint();
					panel.remove(txtNombre);
					panel.remove(lblNombre);

					panel.remove(txtCorreo);
					panel.remove(lblCorreo);

					panel.remove(comboModalidad);
					panel.remove(lblmodalidad);

					panel.remove(comboCiudad);
					panel.remove(lblciudad);

					panel.remove(txtdireccion);
					panel.remove(lbldireccion);
					
					panel.remove(lblFecha);
					panel.remove(dateP);

					panel.remove(lblHora);
					panel.remove(timeP);
					
					panel.remove(lblPuebloOtro);
					panel.remove(txtotro);
					
					panel.remove(btnBuscar);
					panel.revalidate();
					panel.repaint();
				}catch (Exception ex) {
					Utils.escribirLog("Citas clientes error nuevo cliente :" + ex.getMessage());
				}
				
				lblNombre = new JLabel("Nombre:");
				lblNombre.setBounds(39, 105, 71, 16);
				lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblNombre);
				
				txtNombre = new JTextField();
				txtNombre.setBounds(122, 100, 309, 26);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
				
				lblApellido = new JLabel("Apellidos:");
				lblApellido.setBounds(23, 143, 87, 16);
				lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblApellido);
				
				lblCorreo = new JLabel("Correo:");
				lblCorreo.setBounds(23, 181, 87, 16);
				lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblCorreo);
				
				txtApel = new JTextField();
				txtApel.setColumns(10);
				txtApel.setBounds(122, 138, 312, 26);
				panel.add(txtApel);
				
				txtCorreo = new JTextField();
				txtCorreo.setColumns(10);
				txtCorreo.setBounds(122, 176, 312, 26);
				panel.add(txtCorreo);
				
				lblTelefono = new JLabel("Telefono:");
				lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
				lblTelefono.setBounds(23, 214, 87, 16);
				panel.add(lblTelefono);
				
				txtTelef = new JTextField();
				txtTelef.setColumns(10);
				txtTelef.setBounds(122, 209, 312, 26);
				panel.add(txtTelef);
				
				lblHora = new JLabel("Hora");
				lblHora.setBounds(306, 354, 87, 16);
				panel.add(lblHora);
				
				
				timeP = new JComboBox<String>();
				timeP.setEnabled(false);
				timeP.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
				timeP.setBounds(300, 372, 100, 30);
				panel.add(timeP);
				
				lblFecha = new JLabel("Fecha");
				lblFecha.setBounds(23, 354, 87, 16);
				panel.add(lblFecha);
				
				 URL dateImageURL = DarCitaCliente.class.getResource("/images/datepickerbutton1.png");
			     Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
			     ImageIcon dateExampleIcon = new ImageIcon(dateExampleImage);
				
				DatePickerSettings dateSettings = new DatePickerSettings();
				dateP = new DatePicker(dateSettings);
				dateP.addDateChangeListener(new SampleDateChangeListener());
				dateSettings.setHighlightPolicy(new SampleHighlightPolicy());
			    dateSettings.setVetoPolicy(new SampleDateVetoPolicy());
			    JButton datePickerButton = dateP.getComponentToggleCalendarButton();
		        datePickerButton.setText("");
		        datePickerButton.setIcon(dateExampleIcon);
				dateP.setDateToToday();
				dateP.setBounds(23, 372, 250, 30);
				panel.add(dateP);
				
				lbldireccion = new JLabel("Direccion:");
				lbldireccion.setBounds(23, 321, 87, 16);
				lbldireccion.setHorizontalAlignment(SwingConstants.RIGHT);
				lbldireccion.setVisible(false);
				panel.add(lbldireccion);
				
				txtdireccion = new JTextField();
				txtdireccion.setColumns(10);
				txtdireccion.setBounds(122, 316, 318, 26);
				txtdireccion.setVisible(false);
				panel.add(txtdireccion);
				
				lblmodalidad = new JLabel("Modalidad:");
				lblmodalidad.setBounds(44, 242, 77, 16);
				panel.add(lblmodalidad);
				
				lblciudad = new JLabel("Pueblos:");
				lblciudad.setBounds(57, 282, 53, 16);
				panel.add(lblciudad);
				
				lblPuebloOtro = new JLabel("Otros:");
				lblPuebloOtro.setBounds(254, 282, 61, 16);
				panel.add(lblPuebloOtro);
				
				txtotro = new JTextField();
				txtotro.setBounds(300, 278, 134, 26);
				panel.add(txtotro);
				txtotro.setColumns(10);
				
				comboCiudad = new JComboBox<String>();
				comboCiudad.setBounds(122, 278, 134, 26);
				comboCiudad.setVisible(false);
				comboCiudad.addActionListener (new ActionListener () {
				    public void actionPerformed(ActionEvent e) {
				    	if (comboCiudad.getSelectedItem().equals("Otros")) {
				    		txtotro.setVisible(true);
				    		lblPuebloOtro.setVisible(true);
				    	}else {
				    		txtotro.setVisible(false);
				    		lblPuebloOtro.setVisible(false);
				    		txtotro.setText("");
				    	}
				        
				    }
				});
				panel.add(comboCiudad);
				cargarComboCiudad(lisrciudades);
				
				comboModalidad = new JComboBox<String>();
				comboModalidad.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
				comboModalidad.setBounds(122, 240, 312, 26);
				comboModalidad.addActionListener (new ActionListener () {
				    public void actionPerformed(ActionEvent e) {
				        if(comboModalidad.getSelectedItem().equals("Domicilio")) {
				        	comboCiudad.setVisible(true);
				        	lblciudad.setVisible(true);
				        	txtdireccion.setVisible(true);
				        	lbldireccion.setVisible(true);
				        }else {
				        	comboCiudad.setVisible(false);
				        	lblPuebloOtro.setVisible(false);
				        	lbldireccion.setVisible(false);
				        	lblciudad.setVisible(false);
				        	txtotro.setVisible(false);
				        	txtotro.setText("");
				        	txtdireccion.setVisible(false);
				        	txtdireccion.setText("");
				        }
				    }
				});
				panel.add(comboModalidad);
				comboModalidad.addItem("Online");
				comboModalidad.addItem("Domicilio");
				
				JButton btnRegistrarCita = new JButton("Registrar");
				btnRegistrarCita.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						InsertarCliente inClien = new InsertarCliente();
						InsertarCitaCliente icc = new InsertarCitaCliente();
						int online = 0;
						String present = null;
						if(!txtCorreo.getText().equals("")) {
							if(comboModalidad.getSelectedItem().equals("Online")) {
								online = 1;
							}else {
								if(comboCiudad.getSelectedItem().equals("Otros")) {
									present=txtotro.getText();
								}else {
									present=comboCiudad.getSelectedItem().toString();

								}
							}
							
							boolean usuario = inClien.insertar(con, txtNombre.getText(), txtApel.getText(),
									txtCorreo.getText(), Integer.parseInt(txtTelef.getText()));
							long numero = icc.insertar(con, dateP.getDate().toString(),timeP.getSelectedItem().toString(),
									txtCorreo.getText(),online,present,txtdireccion.getText());
							con.Desconectar();
							if( numero > 0 && usuario == true) {
								
								con.Desconectar();
								JOptionPane.showConfirmDialog(null, "Se ha registrado correctamente \n la cita de "+txtNombre.getText(), "Aceptar",
										JOptionPane.CLOSED_OPTION);
								try {
									if(MandarCorreo.send(txtNombre.getText(), txtCorreo.getText(),
											txtApel.getText(), dateP.getText(),
											timeP.getSelectedItem().toString(), "reserva", numero).contains("ok")) {
										JOptionPane.showConfirmDialog(null, "Mensaje enviado Correctamente", "Aceptar",
												JOptionPane.CLOSED_OPTION);
										CalendariosClientes.rellenarTabla(con, dateP.getDate().toString());
									}
								
								} catch (IOException e1) {
									
									e1.printStackTrace();
									Utils.escribirLog("error en registro de cliente :" + e1.getMessage());

								}
								comboCiudad.setVisible(false);
					        	lblPuebloOtro.setVisible(false);
					        	txtotro.setVisible(false);
					        	txtdireccion.setVisible(false);
					        	
					        	txtNombre.setText("");
					        	txtApel.setText("");
					        	txtCorreo.setText("");
					        	txtTelef.setText("");
					        	txtdireccion.setText("");
					        	txtotro.setText("");
					        	
					        	
					        	
								
							}else {
								JOptionPane.showConfirmDialog(null, "No se pudo Insertar", "Aceptar",
										JOptionPane.CLOSED_OPTION);
							}
						}else {
							JOptionPane.showConfirmDialog(null, "El correo esta vacio", "Aceptar",
									JOptionPane.CANCEL_OPTION);
						}
					}
				});
				btnRegistrarCita.setBounds(130, 416, 117, 29);
				panel.add(btnRegistrarCita);
				
				JButton btnCancelarInsert = new JButton("Cancelar");
				btnCancelarInsert.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelarInsert.setBounds(275, 416, 117, 29);
				panel.add(btnCancelarInsert);
				
				
				
			}
		});
		btnNewButton.setBounds(80, 59, 134, 29);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cliente Existente");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					panel.revalidate();
					panel.repaint();
					panel.remove(txtNombre);
					panel.remove(txtApel);
					panel.remove(txtCorreo);
					panel.remove(txtTelef);
					panel.remove(comboModalidad);
					panel.remove(comboCiudad);
					panel.remove(txtdireccion);
					panel.remove(lblmodalidad);
					panel.remove(lbldireccion);
					panel.remove(lblciudad);
					panel.remove(lblTelefono);
					panel.remove(lblCorreo);
					panel.remove(lblApellido);
					panel.remove(lblNombre);
					panel.remove(lblFecha);
					panel.remove(lblHora);
					panel.remove(lblPuebloOtro);
					panel.remove(txtotro);
					panel.remove(dateP);
					panel.remove(timeP);
					
					panel.revalidate();
					panel.repaint();
					
				}catch(Exception ep) {
					Utils.escribirLog("error en registro de cliente existente:" + ep.getMessage());

				}
				
				
				lblNombre = new JLabel("Nombre:");
				lblNombre.setBounds(23, 163, 87, 16);
				lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblNombre);
				
				txtNombre = new JTextField();
				txtNombre.setEditable(false);
				txtNombre.setBounds(122, 158, 279, 26);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
				
				btnBuscar = new JButton("->");
				btnBuscar.setBounds(413, 158, 27, 29);
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						BuscadorPersonas bp= new BuscadorPersonas(con);
						bp.setVisible(true);
						
						
					}
				});
				panel.add(btnBuscar);
				
				
				lblCorreo = new JLabel("Correo:");
				lblCorreo.setBounds(23, 191, 87, 16);
				lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblCorreo);
				
				txtCorreo = new JTextField();
				txtCorreo.setColumns(10);
				txtCorreo.setEditable(false);
				txtCorreo.setBounds(122, 186, 279, 26);
				panel.add(txtCorreo);
				
				lblmodalidad = new JLabel("Modalidad:");
				lblmodalidad.setBounds(23, 219, 87, 16);
				lblmodalidad.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblmodalidad);
				
				
				lblciudad = new JLabel("Ciudad:");
				lblciudad.setBounds(23, 247, 87, 16);
				lblciudad.setHorizontalAlignment(SwingConstants.RIGHT);
				lblciudad.setVisible(false);
				panel.add(lblciudad);
				
				JSeparator separator = new JSeparator();
				separator.setBounds(6, 90, 528, 12);
				panel.add(separator);
				
				txtotro = new JTextField();
				txtotro.setBounds(386, 241, 130, 26);
				txtotro.setVisible(false);
				panel.add(txtotro);
				txtotro.setColumns(10);
				
				lblPuebloOtro = new JLabel("Pueblo:");
				lblPuebloOtro.setBounds(313, 246, 61, 16);
				lblPuebloOtro.setVisible(false);
				panel.add(lblPuebloOtro);
				
				lbldireccion = new JLabel("Direccion:");
				lbldireccion.setBounds(23, 275, 87, 16);
				lbldireccion.setHorizontalAlignment(SwingConstants.RIGHT);
				lbldireccion.setVisible(false);
				panel.add(lbldireccion);
				
				txtdireccion = new JTextField();
				txtdireccion.setColumns(10);
				txtdireccion.setBounds(122, 270, 279, 26);
				txtdireccion.setVisible(false);
				panel.add(txtdireccion);
				
				lblHora = new JLabel("Hora");
				lblHora.setBounds(300, 303, 87, 16);
				panel.add(lblHora);
				
				timeP = new JComboBox<String>();
				timeP.setEnabled(false);
				timeP.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
				timeP.setBounds(300, 320, 100, 30);
				panel.add(timeP);
				
				comboCiudad = new JComboBox<String>();
				comboCiudad.setBounds(122, 242, 179, 26);
				comboCiudad.setVisible(false);
				comboCiudad.addActionListener (new ActionListener () {
				    public void actionPerformed(ActionEvent e) {
				    	if (comboCiudad.getSelectedItem().equals("Otros")) {
				    		txtotro.setVisible(true);
				    		lblPuebloOtro.setVisible(true);
				    	}else {
				    		txtotro.setVisible(false);
				    		lblPuebloOtro.setVisible(false);
				    		txtotro.setText("");
				    	}
				        
				    }
				});
				panel.add(comboCiudad);
				cargarComboCiudad(lisrciudades);
				
				comboModalidad = new JComboBox<String>();
				comboModalidad.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
				comboModalidad.setBounds(122, 214, 279, 26);
				comboModalidad.addActionListener (new ActionListener () {
				    public void actionPerformed(ActionEvent e) {
				        if(comboModalidad.getSelectedItem().equals("Domicilio")) {
				        	comboCiudad.setVisible(true);
				        	txtdireccion.setVisible(true);
				        	lbldireccion.setVisible(true);
				        	lblciudad.setVisible(true);
				        }else {
				        	comboCiudad.setVisible(false);
				        	lblPuebloOtro.setVisible(false);
				        	lbldireccion.setVisible(false);
				        	lblciudad.setVisible(false);
				        	txtotro.setVisible(false);
				        	txtotro.setText("");
				        	txtdireccion.setVisible(false);
				        	txtdireccion.setText("");
				        }
				    }
				});
				panel.add(comboModalidad);
				comboModalidad.addItem("Online");
				comboModalidad.addItem("Domicilio");
				
				lblFecha = new JLabel("Fecha");
				lblFecha.setBounds(23, 303, 87, 16);
				panel.add(lblFecha);
				
				
				URL dateImageURL = DarCitaCliente.class.getResource("/images/datepickerbutton1.png");
			    Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
			    ImageIcon dateExampleIcon = new ImageIcon(dateExampleImage);
				
				DatePickerSettings dateSettings = new DatePickerSettings();
				dateP = new DatePicker(dateSettings);
				dateP.addDateChangeListener(new SampleDateChangeListener());
				dateSettings.setHighlightPolicy(new SampleHighlightPolicy());
			    dateSettings.setVetoPolicy(new SampleDateVetoPolicy());
			    JButton datePickerButton = dateP.getComponentToggleCalendarButton();
		        datePickerButton.setText("");
		        datePickerButton.setIcon(dateExampleIcon);
				dateP.setDateToToday();
				dateP.setBounds(23, 320, 250, 30);
				panel.add(dateP);
				
				JButton btnRegistrarCita = new JButton("Registrar");
				btnRegistrarCita.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						InsertarCitaCliente icc = new InsertarCitaCliente();
						int online = 0;
						String present = "";
						if(!txtCorreo.getText().equals("")) {
							if(comboModalidad.getSelectedItem().equals("Online")) {
								online = 1;
							}else {
								if(comboCiudad.getSelectedItem().equals("Otros")) {
									present=txtotro.getText();
								}else {
									present=comboCiudad.getSelectedItem().toString();
	
								}
							}
							long numero = icc.insertar(con,dateP.getDate().toString(),timeP.getSelectedItem().toString(),txtCorreo.getText(),online,present,txtdireccion.getText());
							if(numero > 0) {
								JOptionPane.showConfirmDialog(null, "Se ha registrado correctamente \n la cita de "+txtNombre.getText(), "Aceptar",
										JOptionPane.CLOSED_OPTION);
								try {
									if(MandarCorreo.send(nombre, txtCorreo.getText(),
											apellido, dateP.getText(),
											timeP.getSelectedItem().toString(), "reserva", numero).contains("ok")) {
										JOptionPane.showConfirmDialog(null, "Mensaje enviado Correctamente", "Aceptar",
												JOptionPane.CLOSED_OPTION);
										
										CalendariosClientes.rellenarTabla(con, dateP.getDate().toString());

									}
								
								} catch (IOException e1) {
									Utils.escribirLog("error en registro de cliente :" + e1.getMessage());
									e1.printStackTrace();
								}
								txtNombre.setText("");
								txtCorreo.setText("");
								comboCiudad.setVisible(false);
					        	lblPuebloOtro.setVisible(false);
					        	txtotro.setVisible(false);
					        	txtotro.setText("");
					        	txtdireccion.setVisible(false);
					        	txtdireccion.setText("");
								
							}else {
								JOptionPane.showConfirmDialog(null, "No se pudo Insertar", "Aceptar",
										JOptionPane.CLOSED_OPTION);
							}
						}else {
							JOptionPane.showConfirmDialog(null, "El correo esta vacio", "Aceptar",
									JOptionPane.CANCEL_OPTION);
						}
					}
				});
				btnRegistrarCita.setBounds(130, 416, 117, 29);
				panel.add(btnRegistrarCita);
				
				JButton btnCancelarInsert = new JButton("Cancelar");
				btnCancelarInsert.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelarInsert.setBounds(275, 416, 117, 29);
				panel.add(btnCancelarInsert);

				
			}
		});
		btnNewButton_1.setBounds(306, 59, 134, 29);
		panel.add(btnNewButton_1);
		
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(WindowEvent winEvt) {
				con.Desconectar();
			}
		});
	}

		
	
	public void cargarComboCiudad(ArrayList<String> lista) {
		for (String eq : lista) {
			comboCiudad.addItem(eq);
		}

	}
	
	public static void rellenarComboBox(ArrayList<String> lista) {
		for (String eq : lista) {
			timeP.addItem(eq);
		}

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
	        	 ArrayList<String> listaPaCombo = new ArrayList<String>();
	        	 LocalDate newDate = event.getNewDate();
	        	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	             DateTimeFormatter dtfH = DateTimeFormatter.ofPattern("HH");
	             DateTimeFormatter dtfmin = DateTimeFormatter.ofPattern("mm");
	             String fechaHoy = dtf.format(LocalDateTime.now());
	             int horaHoy = Integer.parseInt(dtfH.format(LocalDateTime.now()));
	             int minHoy = Integer.parseInt(dtfmin.format(LocalDateTime.now()));

	             String fechaCambio = newDate.toString();
	        	 
	             String newDateString = PickerUtilities.localDateToString(newDate, "(null)");
	             con = new Conexion();
	             CitasListar ctl = new CitasListar();
	             listtiempo =  ctl.listarCitas(con, newDateString);
	             con.Desconectar();
	             
	             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	             
	             Date fecha1 = null;
	             Date fecha2 = null;
				try {
					fecha1 = sdf.parse(fechaCambio);
					fecha2 = sdf.parse(fechaHoy);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	             if(newDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
	            	 if( fecha1.compareTo(fecha2) == -1 ) {
		            	 
			           	 for(int i=9;i<=13; i++) {
			           		 if(i<=9) {
			           			 listtiempo.add("0"+i+":00");
			           		 }else {
			           			 listtiempo.add(i+":00");
			           		 }
			            		 
			            }
			            	 
			             
		             }else {
			             
			             if(fechaCambio.equals(fechaHoy) && horaHoy >= 9 && minHoy >= 0 ) {
			            	 for(int i=9;i<=horaHoy; i++) {
			            		 if(i<=9) {
			            			 listtiempo.add("0"+i+":00");
			            		 }else {
			            			 listtiempo.add(i+":00");
			            		 }
			            		 
			            	 }
			            	 
			             }
		             }
		             for(String elemento : listaHorasSabado){
		                 if(!listtiempo.contains(elemento)){
		                     listaPaCombo.add(elemento);
		                 }
		             }
		             timeP.removeAllItems();
			        	if(listtiempo == null || listtiempo.size() == 0) {
			        			
			            	 rellenarComboBox(listaHorasSabado);
			             }else {
			            	 
			            	 rellenarComboBox(listaPaCombo);
			             }
			       		timeP.setEnabled(true);
	            	 
	             }else {
		             if( fecha1.compareTo(fecha2) == -1 ) {
		            	 
			           	 for(int i=9;i<=20; i++) {
			           		 if(i<=9) {
			           			 listtiempo.add("0"+i+":30");
			           		 }else {
			           			 listtiempo.add(i+":30");
			           		 }
			            		 
			            }
			            	 
			             
		             }else {
			             
			             if(fechaCambio.equals(fechaHoy) && horaHoy >= 9 && minHoy >= 0 ) {
			            	 for(int i=9;i<=horaHoy; i++) {
			            		 if(i<=9) {
			            			 listtiempo.add("0"+i+":30");
			            		 }else {
			            			 listtiempo.add(i+":30");
			            		 }
			            		 
			            	 }
			            	 
			             }
		             }
		             for(String elemento : listaHoras){
		                 if(!listtiempo.contains(elemento)){
		                     listaPaCombo.add(elemento);
		                 }
		             }
		             timeP.removeAllItems();
			        	if(listtiempo == null || listtiempo.size() == 0) {
			        			
			            	 rellenarComboBox(listaHoras);
			             }else {
			            	 
			            	 rellenarComboBox(listaPaCombo);
			             }
			       		timeP.setEnabled(true);
	             }
	             
	        	
	        	
	        }
	  }
	  public static void rellenarDatos(String dato1, String dato2, String dato3) {
		  txtNombre.setText(dato1+" "+dato2);
		  nombre = dato1;
		  apellido = dato2;
		  txtCorreo.setText(dato3);
		  
	  }
	  public static boolean estaActivo() {
		  
		  return activo;
		  
	  }
}
