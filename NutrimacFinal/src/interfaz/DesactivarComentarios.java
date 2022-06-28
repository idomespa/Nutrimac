package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import generadas.Blog;
import generadas.NutriComentarios;
import mostrar.ComentariosPorBlog;
import mostrar.Conexion;
import javax.swing.JComboBox;

public class DesactivarComentarios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3030080668121381063L;
	private JPanel contentPane;
	private static JTable tabla;
	private static ArrayList<Blog> listBlog;
	private static ArrayList<NutriComentarios> listComent;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DesactivarComentarios frame = new DesactivarComentarios(null);
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
	public DesactivarComentarios(Conexion con) {
		setTitle("Desactivar Comentarios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setSize(new Dimension(903, 506));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		tabla.setBounds(139, 261, 300, 83);
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(59, 105, 700, 250);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(tabla);
		
		JButton btnCancelar = new JButton("Desactivar Comentario");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tabla.getSelectedRow() != -1) {
					int row = tabla.getSelectedRow();
					int id = Integer.parseInt(tabla.getValueAt(row, 0).toString());
					String autor = tabla.getValueAt(row, 1).toString();
					String texto = tabla.getValueAt(row, 2).toString();
					String email = tabla.getValueAt(row, 3).toString();
					
					DetallesComentario detComen = new DetallesComentario(con,contentPane);
					detComen.cargarDatos(id,autor,texto,email);
					detComen.setVisible(true);					
					
				}else {
					JOptionPane.showConfirmDialog(null, "Debe Seleccionar una fila", "Aceptar",
							JOptionPane.CLOSED_OPTION);
				}
			}
		});
		btnCancelar.setBounds(546, 396, 193, 29);
		contentPane.add(btnCancelar);
		
		comboBox = new JComboBox();
		comboBox.setBounds(59, 44, 336, 27);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Titulo Blog");
		lblNewLabel.setBounds(60, 16, 100, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rellenarTabla(con, comboBox.getSelectedIndex()+1);
			}
		});
		btnNewButton.setBounds(407, 43, 117, 29);
		contentPane.add(btnNewButton);
		
		
		rellenarComboBox(con);
		rellenarTabla(con,comboBox.getSelectedIndex()+1);
		con.Desconectar();
		
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(WindowEvent winEvt) {
				con.Desconectar();
			}
		});
	}
	
	public void rellenarComboBox(Conexion con) {
		ComentariosPorBlog cm = new ComentariosPorBlog();
		listBlog = cm.listarBlog(con);
		
		for(Blog objeto : listBlog) {
			comboBox.addItem(objeto.getTituloBlog());
		}

	}
	
	public static void displayResult(ArrayList<NutriComentarios> resultList) {
		

		try {
			
			Vector<String> tableHeaders = new Vector<String>();
			Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
			tableHeaders.add("Comentario");
			tableHeaders.add("Autor");
			tableHeaders.add("Texto");
			tableHeaders.add("Email");
			tableHeaders.add("Activo");

			resultList.forEach((l) -> {
				Vector<Object> oneRow = new Vector<Object>();
				oneRow.add(l.getCommentId());
				oneRow.add(l.getCommentAutor());
				oneRow.add(l.getCommentTexto());
				oneRow.add(l.getAutorEmail());
				String dato;
				if(l.getAprobado() == true) {
					 dato = "Si";
				}else {
					dato = "No";
				}
				oneRow.add(dato);
				
				
				tableData.add(oneRow);
			});
			tabla.setModel(new DefaultTableModel(tableData, tableHeaders));
			tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rellenarTabla(Conexion con, int numero) {
		try {
			ComentariosPorBlog cpb= new ComentariosPorBlog();
			listComent = cpb.listarComentarios(con, numero);
			displayResult(listComent);
		} catch (Exception e) {
		}
	}
}
