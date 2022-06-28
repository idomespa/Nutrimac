package util;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import ftp.UploadTask;
import generadas.Categoria;
import interfaz.BuscadorPersonas;
import interfaz.CrearBlog;
import mostrar.Conexion;
import mostrar.CreacionXML;
import mostrar.DatosBlog;
import mostrar.LeerDesdeFichero;
import mostrar.ListarCategorias;

public class PruebaBlogCrear  extends JFrame{
	 
	private static final long serialVersionUID = -8998726492912282544L;
	private JPanel contentPane;
	private static JTextField txtTituloBlog;
	private static JTextField txtImgPrincipal;
	private static JTextField txtImgBlog;
	private static JTextField autortxt;
	private ArrayList<File> imagenesParaSubir;
	private JComboBox comboBox;
	private ArrayList<Categoria> listCategoria;
	private ArrayList<String> rutasParaSubir;
	private String imgPrincipal;
	private String imgBlog;
	private String imgAlmacen;
	private String imgTexto;
	private File imgAlmacenSubir;
	private String imgAlmaceBlogn;
	private File imgAlmacenSubirPrinci;
	private String archivoFinalPrincipal;
	private String archivoFinalBlog;
	private static JProgressBar progressBar = new JProgressBar(0,100);
	private static int eleccion;
	private static boolean devolver;
	private static String fechaArch;
	private static String fechaXml;
	private static JTextPane txtTexto;
	private static String fechaInsert;
	private Conexion con = new Conexion();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PruebaBlogCrear frame = new PruebaBlogCrear();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
 
    public PruebaBlogCrear() {
    	
        imagenesParaSubir = new ArrayList<File>();
        rutasParaSubir = new ArrayList<String>();
        
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 807, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtTituloBlog = new JTextField();
		txtTituloBlog.setBounds(119, 25, 273, 26);
		panel.add(txtTituloBlog);
		txtTituloBlog.setColumns(10);
        

		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("Titulo Blog:");
		lblNewJgoodiesTitle.setBounds(38, 30, 122, 16);
		panel.add(lblNewJgoodiesTitle);
		
		JLabel lblNewLabel = new JLabel("<html><body>Imagen Principal <br>&nbsp&nbsp Horizontal </body></html>");
		lblNewLabel.setBounds(6, 66, 111, 32);
		panel.add(lblNewLabel);
		
		txtImgPrincipal = new JTextField();
		txtImgPrincipal.setEditable(false);
		txtImgPrincipal.setBounds(119, 66, 170, 26);
		panel.add(txtImgPrincipal);
		txtImgPrincipal.setColumns(10);
		
		JButton btnFileImgPrincipal = new JButton("...");
		btnFileImgPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarImagen("principal");
			}
			
		});
		btnFileImgPrincipal.setBounds(289, 66, 48, 29);
		panel.add(btnFileImgPrincipal);
		
		JLabel lblImagenPagBlog = new JLabel("<html><body>Imagen Pag Blog <br>&nbsp&nbsp Vertical </body></html>");
		lblImagenPagBlog.setBounds(6, 107, 111, 40);
		panel.add(lblImagenPagBlog);
		
		txtImgBlog = new JTextField();
		txtImgBlog.setEditable(false);
		txtImgBlog.setColumns(10);
		txtImgBlog.setBounds(119, 106, 170, 26);
		panel.add(txtImgBlog);
		
		JButton bntImgPagBlog = new JButton("...");
		bntImgPagBlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarImagen("imgBlog");
			}
		});
		bntImgPagBlog.setBounds(289, 106, 48, 29);
		panel.add(bntImgPagBlog);
		
		JLabel lblNewLabel_2 = new JLabel("Categoria:");
		lblNewLabel_2.setBounds(419, 30, 72, 16);
		panel.add(lblNewLabel_2);
		
		comboBox = new JComboBox();
		comboBox.setBounds(493, 26, 184, 27);
		panel.add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Escritor Post:");
		lblNewLabel_3.setBounds(402, 66, 89, 16);
		panel.add(lblNewLabel_3);
		
		autortxt = new JTextField();
		autortxt.setBounds(493, 61, 184, 26);
		panel.add(autortxt);
		autortxt.setColumns(10);
		
		
		JButton Negrita = new JButton("");
		Negrita.setToolTipText("Negrita");
		Negrita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtTexto.replaceSelection("<b>;"+txtTexto.getSelectedText()+"</b>");
				
			}
		});
		Negrita.setIcon(new ImageIcon(CrearBlog.class.getResource("/imagenes/negrita.png")));
		Negrita.setBounds(60, 200, 40, 29);
		panel.add(Negrita);
		
		JButton Cursiva = new JButton("");
		Cursiva.setToolTipText("Cursiva");
		Cursiva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTexto.replaceSelection("<i>"+txtTexto.getSelectedText()+"</i>");
			}
		});
		Cursiva.setIcon(new ImageIcon(CrearBlog.class.getResource("/imagenes/cursiva.png")));
		Cursiva.setBounds(100, 200, 40, 29);
		panel.add(Cursiva);
		
		JButton subrayado = new JButton("");
		subrayado.setToolTipText("Subrayar");
		subrayado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTexto.replaceSelection("<u>"+txtTexto.getSelectedText()+"</u>");
			}
		});
		subrayado.setIcon(new ImageIcon(CrearBlog.class.getResource("/imagenes/subrayar.png")));
		subrayado.setBounds(142, 200, 40, 29);
		panel.add(subrayado);
		
		JButton parrafo = new JButton("");
		parrafo.setToolTipText("Poner Parrafo");
		parrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Desea Justificar Parrafo", "justificar", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					txtTexto.replaceSelection("<p class=\"text-justify\">"+txtTexto.getSelectedText()+"</p>");
				} else {
					txtTexto.replaceSelection("<p>"+txtTexto.getSelectedText()+"</p>");
				}
				
			}
		});
		parrafo.setIcon(new ImageIcon(CrearBlog.class.getResource("/imagenes/parrafo.png")));
		parrafo.setBounds(183, 200, 40, 29);
		panel.add(parrafo);
		
		JButton lista = new JButton("");
		lista.setToolTipText("Lista");
		lista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTexto.replaceSelection("<blockquote>"+txtTexto.getSelectedText()+"</blockquote>");
			}
		});
		lista.setIcon(new ImageIcon(CrearBlog.class.getResource("/imagenes/lista.png")));
		lista.setBounds(225, 200, 40, 29);
		panel.add(lista);
		
		JButton salto_linea = new JButton("");
		salto_linea.setToolTipText("Salto de linea: Solo para las listas");
		salto_linea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTexto.replaceSelection(txtTexto.getSelectedText()+"<br>");
			}
		});
		salto_linea.setIcon(new ImageIcon(CrearBlog.class.getResource("/imagenes/slinea.png")));
		salto_linea.setBounds(265, 200, 40, 29);
		panel.add(salto_linea);
		
		JButton img = new JButton("");
		img.setEnabled(false);
		img.setToolTipText("Insertar Imagen");
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarImagen("texto");
				
			}
		});
		img.setIcon(new ImageIcon(CrearBlog.class.getResource("/imagenes/imagen.png")));
		img.setBounds(307, 200, 40, 29);
		panel.add(img);
	
		
		JButton btnNewButton = new JButton("Publicar Post");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				buttonUploadActionPerformed(event,con);
			}
		});
		btnNewButton.setBounds(60, 496, 117, 29);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(60, 233, 681, 251);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollBar = new JScrollPane((Component) null);
		panel_1.add(scrollBar);
		
		txtTexto = new JTextPane();
		txtTexto.setContentType("text/html");
		scrollBar.setViewportView(txtTexto);
		

		cargarCombo(con);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(WindowEvent winEvt) {
				con.Desconectar();
			}
		});
 
        progressBar.setPreferredSize(new Dimension(200, 30));
        progressBar.setStringPainted(true);
        
        JButton btnNewButton_1 = new JButton("Cerrar");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnNewButton_1.setBounds(230, 496, 117, 29);
        panel.add(btnNewButton_1);
        fechas();
        
    }
 
    /**
     * handle click event of the Upload button
     */
    private void buttonUploadActionPerformed(ActionEvent event, Conexion con) {
    	try {
			progressBar.setValue(0);
			ArrayList<String> xml = new ArrayList<String>();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String[] nombreCorte = autortxt.getText().split(" ");
			Random rd = new Random();
			int numeroAleatorio = rd.nextInt(101);
			String nombreArch = dtf.format(LocalDateTime.now())+"_"+nombreCorte[0]+numeroAleatorio+".xml";
			
			
			
			xml.add(txtTituloBlog.getText());
			xml.add(fechaXml);
			xml.add(nombreArch);
			xml.add(archivoFinalPrincipal);
			xml.add(archivoFinalBlog);
			xml.add(comboBox.getSelectedItem().toString());
			xml.add(autortxt.getText());
			xml.add(txtTexto.getText().substring(0,117)+"...");
			xml.add(txtTexto.getText().substring(0,220)+"...");
			xml.add(txtTexto.getDocument().getText(0, txtTexto.getDocument().getLength()).toString());
			CreacionXML ejemploXML = new CreacionXML();
			if(ejemploXML.escribirXml(xml)>0) {
				ArrayList<String> list = new ArrayList<String>();
		        //list.addAll(LeerDesdeFichero.leerFichero());
		        fechas();
		        
		        
				//String host = list.get(0);
		        //int port = Integer.parseInt(list.get(3));
		        //String username = list.get(1);
		        //String password = list.get(2);
		       
		        File uploadFile = new File(nombreArch);
		        imagenesParaSubir.add(uploadFile);
		        rutasParaSubir.add("/xml/");
		        
		        //progressBar.setValue(0);
		        long tamanoArchivos=0;
		        for(int i = 0; i<imagenesParaSubir.size();i++) {
					tamanoArchivos += imagenesParaSubir.get(i).length();
					
				}

			}
		} catch (BadLocationException e1) {
			Utils.escribirLog("Error: " + e1.getMessage());
			e1.printStackTrace();
		}

    }
    
    public void cargarCombo(Conexion con) {
		ListarCategorias ctl = new ListarCategorias();
		listCategoria =  ctl.listarCategorias(con);
		con.Desconectar();
		
		for (Categoria eq : listCategoria) {
			comboBox.addItem(eq.getCategoria());
		}

	}
	private void buscarImagen(String dato) {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Imagenes jpg, png, jpeg", "jpg", "jpeg","png");
		fileChooser.setFileFilter(filter);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(fileChooser);
        try {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();                                        
            File f = new File(ruta);
            int aleatorio = aleatorio();
            String rutaMod= "/images/imgBlog/";
            
            if(dato.equals("principal")) {
            	imgPrincipal = rutaMod;
            	txtImgPrincipal.setText(f.getName());
            	if (imgAlmacen != null){
            		int indice = rutasParaSubir.indexOf(imgAlmacen);
            		if (indice != -1) {
            			rutasParaSubir.set(indice, imgPrincipal);
            			
            		}
            	}else {
            		rutasParaSubir.add(imgPrincipal);
            	}
            	
            	imgAlmacen = imgPrincipal;
            	
            	Files.copy(FileSystems.getDefault().getPath(f.getAbsolutePath()),
            		       FileSystems.getDefault().getPath(fechaArch+"_"+aleatorio+"."+extensionArchivo(f)),
            		       StandardCopyOption.REPLACE_EXISTING);
            	
            	File uploadFile = new File(fechaArch+"_"+aleatorio+"."+extensionArchivo(f));
            	if (imgAlmacenSubirPrinci != null){
            		int indice = imagenesParaSubir.indexOf(imgAlmacenSubirPrinci);
            		if (indice != -1) {
            			imagenesParaSubir.set(indice, uploadFile);
            			imgAlmacenSubirPrinci.delete();
            		}
            	}else {
            		imagenesParaSubir.add(uploadFile);
            	}
            	
            	imgAlmacenSubirPrinci = uploadFile;
            	
            	archivoFinalPrincipal= imgAlmacen+imgAlmacenSubirPrinci.getName();
            	
            }
            else if(dato.equals("imgBlog")) {
            	txtImgBlog.setText((f.getName()));
            	imgBlog = rutaMod;
            	if (imgAlmaceBlogn != null){
            		int indice = rutasParaSubir.indexOf(imgAlmaceBlogn);
            		if (indice != -1) {
            			rutasParaSubir.set(indice, imgBlog);
            		}
            	}else {
            		rutasParaSubir.add(imgBlog);
            	}
            	
            	imgAlmaceBlogn = imgBlog;
            	Files.copy(FileSystems.getDefault().getPath(f.getAbsolutePath()),
         		       FileSystems.getDefault().getPath(fechaArch+"_"+aleatorio+"."+extensionArchivo(f)),
         		       StandardCopyOption.REPLACE_EXISTING);
	         	
	         	File uploadFile = new File(fechaArch+"_"+aleatorio+"."+extensionArchivo(f));
	         	if (imgAlmacenSubir != null){
	         		int indice = imagenesParaSubir.indexOf(imgAlmacenSubir);
	         		if (indice != -1) {
	         			imagenesParaSubir.set(indice, uploadFile);
	         			imgAlmacenSubir.delete();
	         		}
	         	}else {
	         		imagenesParaSubir.add(uploadFile);
	         	}
	         	
	         	imgAlmacenSubir = uploadFile;
	         	archivoFinalBlog= imgAlmaceBlogn+imgAlmacenSubir.getName();
            	
            }
            /*else if(dato.equals("texto")) {
            	txtTexto.replaceSelection("&lt;p>&lt;img class='imgDentroBlog' src='images/imgBlog/'"+f.getName()+"> &lt;/p>");
            	imgTexto = rutaMod;
            	if (imgAlmacen != null){
            		int indice = rutasParaSubir.indexOf(imgAlmacen);
            		if (indice != -1) {
            			rutasParaSubir.set(indice, imgTexto);
            		}
            	}else {
            		rutasParaSubir.add(imgTexto);
            	}
            	
            	imgAlmacen = imgTexto;
            }*/
            
            
            
            
        } catch (NullPointerException ex) {
        	Utils.escribirLog("No se ha seleccionado ningún fichero");
            System.out.println("No se ha seleccionado ningún fichero");
        } catch (Exception ep) {
        	Utils.escribirLog(ep.getMessage());
            System.out.println(ep.getMessage());
        }
	}
	private static void limpiar() {
		txtTituloBlog.setText("");
		autortxt.setText("");
		txtTexto.setText("");
		txtImgPrincipal.setText("");
		txtImgBlog.setText("");
	}
	
	public static void devolver() {
		eleccion = JOptionPane.showOptionDialog(null, "Blog Publicado", //contenido de la ventana
                "Mensaje de Confirmacion" , //titulo de la ventana
                JOptionPane.OK_OPTION, //para 3 botones si/no/cancel
                JOptionPane.QUESTION_MESSAGE, //tipo de ícono
                null,    // null para icono por defecto.
                new Object[] { "Aceptar"},//objeto para las opciones
                "SO");
		if(eleccion == JOptionPane.OK_OPTION) {
			limpiar();
			progressBar.setValue(0);
		}
	}
	private static void fechas() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter format1 =
		DateTimeFormatter 
	      .ofPattern("dd 'de' MMMM yyyy")
	      .withLocale(new Locale("es", "ES"));
		
		fechaArch= dateTime.format(format);
		fechaXml = dateTime.format(format1);
		fechaInsert=dateTime.format(format2);
	}
	private static String extensionArchivo(File f) {
		String fe = "";
		int i = f.getName().lastIndexOf('.');
		if (i > 0) {
		    fe = f.getName().substring(i+1);
		}
		return fe;
	}
	private static int aleatorio() {
		Random rd = new Random();
		
		return rd.nextInt(1000001);
	}
}