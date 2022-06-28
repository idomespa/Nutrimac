package interfaz;

import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import generadas.Citas;
import mostrar.ActualizarCita;
import mostrar.CancelarCita;
import mostrar.CitasListar;
import mostrar.Conexion;
import mostrar.ConfirmarCita;
import util.Utils;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;

public class DetallesCita extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2456090247414174806L;
	private JPanel contentPane;
	private int id;
	public static Conexion con;
	private static ArrayList<Citas> cita;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JTextField txtHora;
	private JTextField txtApellidos;
	private JTextField txtFecha;
	private JTextField txtConfirmacion;
	private JTextField txtCiudad;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextArea textAreaMotivo;
	private JTextArea textAreaObservaciones;
	private JScrollPane scrollpane1;
	private JLabel lblNewLabel_3;
	

	public DetallesCita() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Detalles");
		setBounds(100, 100, 558, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(109, 46, 130, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setEditable(false);
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(251, 104, 252, 26);
		contentPane.add(txtCorreo);
		
		txtHora = new JTextField();
		txtHora.setEditable(false);
		txtHora.setColumns(10);
		txtHora.setBounds(271, 214, 53, 26);
		contentPane.add(txtHora);
		
		txtApellidos = new JTextField();
		txtApellidos.setEditable(false);
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(321, 46, 182, 26);
		contentPane.add(txtApellidos);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(109, 104, 92, 26);
		contentPane.add(txtFecha);
		
		txtConfirmacion = new JTextField();
		txtConfirmacion.setEditable(false);
		txtConfirmacion.setColumns(10);
		txtConfirmacion.setBounds(428, 214, 33, 26);
		contentPane.add(txtConfirmacion);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(36, 51, 61, 16);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel);
		
		JLabel lblCorre = new JLabel("Correo:");
		lblCorre.setBounds(188, 109, 61, 16);
		lblCorre.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblCorre);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(208, 219, 61, 16);
		lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblHora);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(231, 51, 82, 16);
		lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblApellidos);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(27, 109, 70, 16);
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblFecha);
		
		JLabel lblFecha_1 = new JLabel("Confirmación:");
		lblFecha_1.setBounds(321, 219, 105, 16);
		lblFecha_1.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblFecha_1);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(36, 219, 61, 16);
		lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblCiudad);
		
		txtCiudad = new JTextField();
		txtCiudad.setEditable(false);
		txtCiudad.setColumns(10);
		txtCiudad.setBounds(109, 214, 120, 26);
		contentPane.add(txtCiudad);
		
		JLabel lblFecha_1_1 = new JLabel("Dirección:");
		lblFecha_1_1.setBounds(221, 168, 70, 16);
		lblFecha_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblFecha_1_1);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(303, 163, 200, 26);
		contentPane.add(txtDireccion);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = null;
				if(textAreaObservaciones.getText().equals("")) 
					texto = null;
				else
					texto = textAreaObservaciones.getText();
				ActualizarCita ac = new ActualizarCita();
				ac.actualizarObservaciones(con, id, texto);
				dispose();
			}
		});
		btnAceptar.setBounds(188, 396, 117, 29);
		contentPane.add(btnAceptar);
		
		JLabel lblNewLabel_1 = new JLabel("Telefono:");
		lblNewLabel_1.setBounds(36, 168, 61, 16);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel_1);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setBounds(99, 163, 120, 26);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Motivo de la consulta");
		lblNewLabel_2.setBounds(36, 262, 143, 16);
		contentPane.add(lblNewLabel_2);
		
		
		textAreaMotivo=new JTextArea();
		textAreaMotivo.setFont(new Font("Courier New", Font.PLAIN, 16));
		textAreaMotivo.setEditable(false);
		textAreaMotivo.setLineWrap(true); 
	    scrollpane1=new JScrollPane(textAreaMotivo);
	    scrollpane1.setBounds(36,285,200,100);
	    getContentPane().add(scrollpane1);
	    
	    lblNewLabel_3 = new JLabel("Observaciones de la Consulta");
	    lblNewLabel_3.setBounds(281, 262, 206, 16);
	    contentPane.add(lblNewLabel_3);
	    
	    JScrollPane scrollpane1_1 = new JScrollPane((Component) null);
	    scrollpane1_1.setBounds(281, 285, 222, 100);
	    contentPane.add(scrollpane1_1);
	    
	    textAreaObservaciones = new JTextArea();
	    textAreaObservaciones.setFont(new Font("Courier New", Font.PLAIN, 16));
	    textAreaObservaciones.setLineWrap(true);
	    scrollpane1_1.setViewportView(textAreaObservaciones);
	    
	    JButton btnNewButton = new JButton("✓");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					int reply = JOptionPane.showConfirmDialog(null, "Desea confirmar la cita?", "Confirmar Cita", JOptionPane.YES_NO_OPTION);
		    		if (reply == JOptionPane.YES_OPTION) {
		    			boolean confirmado= false;
						ConfirmarCita cC = new ConfirmarCita();
						confirmado = cC.confirmar(id, con);
						con.Desconectar();
						if (confirmado == true) {
							JOptionPane.showOptionDialog(null, "Cita confirmada", //contenido de la ventana
					                  "Confirmacion" , //titulo de la ventana
					                  JOptionPane.OK_OPTION, //para 3 botones si/no/cancel
					                  JOptionPane.INFORMATION_MESSAGE, //tipo de ícono
					                  null,    // null para icono por defecto.
					                  new Object[] { "Aceptar"},//objeto para las opciones
					                  //null para YES, NO y CANCEL
					                  "SO");
							txtConfirmacion.setText("Si");
						}
					}
	    		}catch(Exception ep) {
	    			Utils.escribirLog("Error: "+ ep.getMessage());
	    		}
	    	}
	    });
	    btnNewButton.setBounds(473, 214, 25, 25);
	    contentPane.add(btnNewButton);
		 
         
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(WindowEvent winEvt) {
				con.Desconectar();
			}
		});
		
	}
	public void crear(int numero) {
		try {
			this.id=numero;
			con = new Conexion();
	        CitasListar ctl = new CitasListar();
	        cita =  ctl.listarCitasId(con, id);
	        con.Desconectar();
	        cita.forEach((l) ->{
	        	txtNombre.setText(l.getClientes().getNombre());
	        	txtApellidos.setText(l.getClientes().getApellidos());	
				String [] hora = l.getHoraCita().toString().split(":");
	        	txtFecha.setText(new SimpleDateFormat("dd MMMM").format(l.getFechaCita()));
	        	txtHora.setText(hora[0]+":"+hora[1]);
	        	if (l.getPresencial()== null) {
	        		txtCiudad.setText("");
	        	}else {
	        		txtCiudad.setText(l.getPresencial());
	        	}
	        	txtCorreo.setText(l.getClientes().getEmailId());
	        	if(l.getConfirmar() == true) {
	        		txtConfirmacion.setText("Si");
	        	}else {
	        		txtConfirmacion.setText("No");
	        	}
	        	txtDireccion.setText(l.getDireccion());
	        	txtTelefono.setText(String.valueOf(l.getClientes().getTelefono()));
	        	textAreaMotivo.setText(l.getMotivo());
	        	textAreaObservaciones.setText(l.getObservaciones());
	        });
		}catch(Exception e) {
			Utils.escribirLog("error en detalles cita :" + e.getMessage());

		}
	}
}
