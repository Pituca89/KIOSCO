package cliente.kiosco;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListProdDev extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JTextField codProducto;
	private JTable table;
	static Gson gson;
	static String data = "";
	static Stock[] stock;
	Recuperar devolucion;
	static MouseAdapter mouseAdapter;
	
	public static ListProdDev instance;
	
	public static ListProdDev getInstance(Recuperar json) {
		if(instance == null) {
			instance = new ListProdDev(json);
		}
		return instance;		
	}
	public ListProdDev(Recuperar dev) {
		
		this.devolucion = dev;
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaProdVenta.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		setBounds(100, 100, 683, 500);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 667, 428);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			lblNewLabel = new JLabel("Productos");
			lblNewLabel.setBounds(287, 16, 81, 20);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		}
		JLabel lblDescripcionDeProducto = new JLabel(" Descripcion de Producto");
		lblDescripcionDeProducto.setForeground(new Color(255, 255, 255));
		lblDescripcionDeProducto.setBackground(new Color(0, 153, 0));
		lblDescripcionDeProducto.setBounds(25, 71, 182, 24);
		lblDescripcionDeProducto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcionDeProducto.setOpaque(true);
		codProducto = new JTextField();
		codProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_ENTER) {
					buscarProducto(); 
				}
			}
		});
		codProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codProducto.setBounds(25, 96, 182, 24);
		codProducto.setColumns(10);
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.requestFocus(true);
		btnBuscar.setFocusable(true);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscarProducto(); 
		    	
			}
		});
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 153, 0));
		btnBuscar.setIcon(new ImageIcon("./imagen/binoculares.png"));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.setBounds(241, 71, 202, 49);
		btnBuscar.setFocusable(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 142, 618, 234);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				".", "C\u00F3digo", "Descripci\u00F3n", "Costo", "Stock"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class
			};
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		table.getColumnModel().getColumn(4).setPreferredWidth(15);
		table.getTableHeader().setBackground(new Color(0, 153, 0));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		contentPanel.setLayout(null);
		contentPanel.add(lblDescripcionDeProducto);
		contentPanel.add(codProducto);
		contentPanel.add(btnBuscar);
		contentPanel.add(scrollPane);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				devolucion.getLblCodigo().setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				devolucion.getDescProducto().setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				devolucion.getCantProducto().setText("1");
				devolucion.getCostoProducto().setText(table.getValueAt(table.getSelectedRow(), 3).toString());
						
				Recuperar.refreshMonto();				
				dispose();
				
			}
		});
		
		mouseAdapter = new MouseAdapter() {
			 public void mousePressed(MouseEvent Mouse_evt) {
				 JTable tbl =(JTable) Mouse_evt.getSource();
				 Point point = Mouse_evt.getPoint();
				 int row = tbl.rowAtPoint(point);
				 if (Mouse_evt.getClickCount() == 2) {
					devolucion.getLblCodigo().setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					devolucion.getDescProducto().setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					devolucion.getCantProducto().setText("1");
					devolucion.getCostoProducto().setText(table.getValueAt(table.getSelectedRow(), 3).toString());
							
					Recuperar.refreshMonto();				
					dispose();
				 }
			}
			 
		};
		btnConfirmar.setBounds(373, 393, 135, 35);
		contentPanel.add(btnConfirmar);
		
		JButton btnNewButton = new JButton("CANCELAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(150, 393, 135, 35);
		contentPanel.add(btnNewButton);
		contentPanel.requestFocus();
		
	}
	
	public void buscarProducto() {
		Runnable hilo1 = new Runnable() {
			
			@Override
			public void run() {
				try {
					JCheckBox check;
					gson = new Gson();
					data = HTTPRequest.ConsultasServidor(HTTPRequest.UN_STOCK, codProducto.getText());
					if(!data.isEmpty()) {
						DefaultTableModel modal = new DefaultTableModel();
						modal = (DefaultTableModel) table.getModel();
						for (int i = table.getRowCount() -1; i >= 0; i--){
							modal.removeRow(i);
						}
						stock =  gson.fromJson(data, Stock[].class);
						
						for(Stock s: stock) {
							
							Object[] row = new Object[5];
							
							row[1] = s.getProducto_id();
							row[2] = s.getNombre();
							row[3] = s.getPrecio_venta();
							row[4] = s.getStock_actual();
							
							
							modal.addRow(row);							
							
						}
						table.addMouseListener(mouseAdapter);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		
		Thread thread =  new Thread(hilo1);
		thread.start();
	}
}
