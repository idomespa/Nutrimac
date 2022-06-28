package interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import mostrar.ReservarCitas;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import mostrar.Direcciones;

public class ReservarmeCitas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2565862071799952514L;
	public static Conexion con;
	private JPanel contentPane;
	private JLabel lblFecha;
	private JLabel lblHora;
	private static DatePicker dateP;
	private static JComboBox<String> timeP;
	private JLabel lblHastaLaHora;
	private static JComboBox<String> timeP_1;
	private static ArrayList<String> listaHoras;
	private static ArrayList<String> listtiempo;
	private static ArrayList<String> listaComboFinal;
	private static ArrayList<String> listaHorasSabado;
	private JButton btnNewButton;
	private static String newDateString;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservarmeCitas frame = new ReservarmeCitas();
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
	public ReservarmeCitas() {
		setTitle("Mis Citas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listaHoras = new ArrayList<String>();
		listaHorasSabado = new ArrayList<String>();

		if (Direcciones.enterosOYMedia.equals("medias")) {
			for (int i = 9; i <= 13; i++) {
				if (i < 10) {
					listaHoras.add("0" + i + ":30");
				} else {
					listaHoras.add(i + ":30");
				}
			}
			for (int i = 17; i <= 19; i++) {
				listaHoras.add(i + ":30");
			}
		} else {
			for (int i = 9; i <= 13; i++) {
				if (i < 10) {
					listaHoras.add("0" + i + ":00");
				} else {
					listaHoras.add(i + ":00");
				}
			}
			for (int i = 17; i <= 19; i++) {
				listaHoras.add(i + ":00");
			}
		}
		for (int i=9; i<=13; i++){
			if(i<10) {
				listaHorasSabado.add("0"+i+":00"); 
			}else {
				listaHorasSabado.add(i+":00"); 
			}
		}
		lblHora = new JLabel("Desde la Hora");
		lblHora.setBounds(70, 70, 95, 16);
		contentPane.add(lblHora);

		lblHastaLaHora = new JLabel("Hasta la Hora");
		lblHastaLaHora.setBounds(70, 127, 95, 16);
		contentPane.add(lblHastaLaHora);

		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(29, 16, 36, 16);
		contentPane.add(lblFecha);

		timeP = new JComboBox<String>();
		timeP.setEnabled(false);
		timeP.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		timeP.setBounds(169, 65, 102, 27);

		timeP_1 = new JComboBox<String>();
		timeP_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		timeP_1.setEnabled(false);
		timeP_1.setBounds(169, 122, 102, 27);
		contentPane.add(timeP_1);

		DatePickerSettings dateSettings = new DatePickerSettings();
		dateP = new DatePicker(dateSettings);
		dateSettings.setHighlightPolicy(new SampleHighlightPolicy());
		dateSettings.setVetoPolicy(new SampleDateVetoPolicy());
		dateP.addDateChangeListener(new SampleDateChangeListener());
		dateP.setDateToToday();
		dateP.setBounds(70, 10, 258, 29);
		contentPane.add(dateP);

		/*
		timeP.addItemListener(new ItemListener() { public void
		itemStateChanged(ItemEvent e) { cararCombo(listaComboFinal,
		timeP.getSelectedItem().toString()); } });
		*/

		contentPane.add(timeP);

		btnNewButton = new JButton("Reservarme");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] hora = timeP.getSelectedItem().toString().split(":");
				String[] hora1 = timeP_1.getSelectedItem().toString().split(":");

				int numero1 = Integer.parseInt(hora[0]);
				int numero2 = Integer.parseInt(hora1[0]);
				ReservarCitas rCita = new ReservarCitas();
				if (rCita.InsertarMisCitas(con, numero1, numero2, newDateString)) {
					JOptionPane.showConfirmDialog(null, "Citas Reservadas", "Aceptar", JOptionPane.CLOSED_OPTION);
					contentPane.revalidate();
					contentPane.repaint();
				} else {
					JOptionPane.showConfirmDialog(null, "Fallo en la reserva, ponte en contacto con el administrador",
							"Aceptar", JOptionPane.CLOSED_OPTION);
					contentPane.revalidate();
					contentPane.repaint();
				}
			}
		});
		btnNewButton.setToolTipText("Las horas seleccionadas son de 10 a 11 por ejemplo si marcamos la hora 10");
		btnNewButton.setBounds(154, 195, 117, 29);
		contentPane.add(btnNewButton);

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(WindowEvent winEvt) {
				con.Desconectar();
			}
		});
	}

	public static void rellenarComboBox(ArrayList<String> lista) {
		for (String eq : lista) {
			timeP.addItem(eq);
		}

	}

	public void cararCombo(ArrayList<String> lista, String dato) {
		timeP_1.removeAllItems();
		String[] numeroExtra = dato.split(":");

		for (String eq : lista) {
			String[] datos = eq.split(":");
			if (Integer.parseInt(datos[0]) >= Integer.parseInt(numeroExtra[0])) {
				timeP_1.addItem(eq);
			}
		}

	}

	private static class SampleHighlightPolicy implements DateHighlightPolicy {

		@Override
		public HighlightInformation getHighlightInformationOrNull(LocalDate date) {
			// Highlight a chosen date, with a tooltip and a red background color.
			// if (date.getDayOfMonth() == 25) {
			// return new HighlightInformation(Color.red, null, "It's the 25th!");
			// }
			// Highlight all Saturdays with a unique background and foreground color.
			// if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
			// return new HighlightInformation(Color.orange, Color.yellow, "It's
			// Saturday!");
			// }
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

			if ((date.getDayOfWeek() == DayOfWeek.SUNDAY) /* && ((date.getDayOfMonth() % 2) == 1) */) {
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
			listaComboFinal = new ArrayList<String>();

			LocalDate newDate = event.getNewDate();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter dtfH = DateTimeFormatter.ofPattern("HH");
			DateTimeFormatter dtfmin = DateTimeFormatter.ofPattern("mm");
			String fechaHoy = dtf.format(LocalDateTime.now());
			int horaHoy = Integer.parseInt(dtfH.format(LocalDateTime.now()));
			int minHoy = Integer.parseInt(dtfmin.format(LocalDateTime.now()));

			String fechaCambio = newDate.toString();
			newDateString = PickerUtilities.localDateToString(newDate, "(null)");
			con = new Conexion();
			CitasListar ctl = new CitasListar();
			listtiempo = ctl.listarCitas(con, newDateString);
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

			if (newDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
				if (fecha1.compareTo(fecha2) == -1) {

					for (int i = 9; i <= 13; i++) {
						if (i <= 9) {
							listtiempo.add("0" + i + ":00");
						} else {
							listtiempo.add(i + ":00");
						}

					}

				} else {

					if (fechaCambio.equals(fechaHoy) && horaHoy >= 9 && minHoy >= 0) {
						for (int i = 9; i <= horaHoy; i++) {
							if (i <= 9) {
								listtiempo.add("0" + i + ":00");
							} else {
								listtiempo.add(i + ":00");
							}

						}

					}
				}
				for (String elemento : listaHorasSabado) {
					if (!listtiempo.contains(elemento)) {
						listaPaCombo.add(elemento);
					}
				}
				timeP.removeAllItems();
				if (listtiempo == null || listtiempo.size() == 0) {

					rellenarComboBox(listaHorasSabado);
				} else {

					rellenarComboBox(listaPaCombo);
				}
				timeP.setEnabled(true);

			} else {
				if (fecha1.compareTo(fecha2) == -1) {

					for (int i = 9; i <= 20; i++) {
						if (i <= 9) {
							listtiempo.add("0" + i + ":30");
						} else {
							listtiempo.add(i + ":30");
						}

					}

				} else {

					if (fechaCambio.equals(fechaHoy) && horaHoy >= 9 && minHoy >= 0) {
						for (int i = 9; i <= horaHoy; i++) {
							if (i <= 9) {
								listtiempo.add("0" + i + ":30");
							} else {
								listtiempo.add(i + ":30");
							}

						}

					}
				}
				for (String elemento : listaHoras) {
					if (!listtiempo.contains(elemento)) {
						listaPaCombo.add(elemento);
					}
				}
				timeP.removeAllItems();
				if (listtiempo == null || listtiempo.size() == 0) {

					rellenarComboBox(listaHoras);
				} else {

					rellenarComboBox(listaPaCombo);
				}
				timeP.setEnabled(true);
			}
			
			/*
			 * for(String elemento : listaHoras){ if(!listtiempo.contains(elemento)){
			 * listaPaCombo.add(elemento); } }
			 * 
			 * 
			 * timeP.removeAllItems(); if(listtiempo == null || listtiempo.size() == 0) {
			 * listaComboFinal.clear(); for(String elemento : listaHoras)
			 * listaComboFinal.add(elemento); rellenarComboBox(listaHoras);
			 * 
			 * 
			 * }else { listaComboFinal.clear();
			 * 
			 * for(String elemento : listaPaCombo) listaComboFinal.add(elemento);
			 * rellenarComboBox(listaPaCombo);
			 * 
			 * }
			 */
			timeP.setEnabled(true);
			timeP_1.setEnabled(true);

		}
	}
}
