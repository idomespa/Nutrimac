package interfaz;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import generadas.Blog;
import mostrar.Conexion;
import mostrar.DatosBlog;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class InterfazGestionarBlog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3872909664306105969L;
	private JPanel contentPane;
	protected JButton activar;
	protected JButton desactivar;
	private static JTable tabla;


	public InterfazGestionarBlog(Conexion con) {
		setTitle("Gestion de Blogs");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 454, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnActivo = new JButton("Desactivar Blog");
		btnActivo.setBounds(46, 30, 132, 29);
		contentPane.add(btnActivo);

		JButton btnDesactivar = new JButton("Activar Blog");
		btnDesactivar.setBounds(261, 30, 132, 29);
		contentPane.add(btnDesactivar);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 71, 443, 12);
		contentPane.add(separator);

		tabla = new JTable();
		tabla.setBounds(20, 90, 410, 110);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 90, 410, 110);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(tabla);

		

		
		btnActivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(activar.isDisplayable()) {
						contentPane.revalidate();
						contentPane.repaint();
						contentPane.remove(activar);
						contentPane.revalidate();
						contentPane.repaint();
					}
				}catch (Exception ex) {
					
				}
				rellenarTabla(con, true);
				
				desactivar = new JButton("Desactivar");
				desactivar.setBounds(166, 212, 117, 29);
				contentPane.add(desactivar);
				desactivar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int row = tabla.getSelectedRow();
						int id = Integer.parseInt(tabla.getValueAt(row, 0).toString());
						if(DatosBlog.desctivarActivarBlog(con, 0,id)) {
							JOptionPane.showConfirmDialog(null, "Se ha desactivado correctamente", "Aceptar",
									JOptionPane.CLOSED_OPTION);
							
							rellenarTabla(con, true);
							con.Desconectar();
						}
						
					}
				});
			}
		});
		
		btnDesactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(desactivar.isDisplayable()) {
						contentPane.revalidate();
						contentPane.repaint();
						contentPane.remove(desactivar);
						contentPane.revalidate();
						contentPane.repaint();
					}
					
				}catch (Exception ex) {
					
				}
				rellenarTabla(con, false);
				activar = new JButton("Activar");
				activar.setBounds(166, 212, 117, 29);
				contentPane.add(activar);
				activar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int row = tabla.getSelectedRow();
						int id = Integer.parseInt(tabla.getValueAt(row, 0).toString());
						if(DatosBlog.desctivarActivarBlog(con, 1,id)) {
							JOptionPane.showConfirmDialog(null, "Se ha activado correctamente", "Aceptar",
									JOptionPane.CLOSED_OPTION);
							
							rellenarTabla(con, true);
							con.Desconectar();
						}
						
					}
				});
			}
		});
	}
	public static void displayResult(ArrayList<Blog> resultList) {


		try {

			Vector<String> tableHeaders = new Vector<String>();
			Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
			tableHeaders.add("Blog");
			tableHeaders.add("Titulo");


			resultList.forEach((l) -> {
				Vector<Object> oneRow = new Vector<Object>();
				oneRow.add(l.getIdBlog());
				oneRow.add(l.getTituloBlog());

				tableData.add(oneRow);
			});
			tabla.setModel(new DefaultTableModel(tableData, tableHeaders));
			tabla.getColumnModel().getColumn(1).setPreferredWidth(320);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void rellenarTabla(Conexion con, boolean dato) {
		try {
			if(dato) {
				int numero = 1;
				displayResult(DatosBlog.activarODesactivar(con, numero));
				con.Desconectar();
			}else {
				int numero = 0;
				displayResult(DatosBlog.activarODesactivar(con, numero));
				con.Desconectar();
			}

		} catch (Exception e) {
		}
	}
}
