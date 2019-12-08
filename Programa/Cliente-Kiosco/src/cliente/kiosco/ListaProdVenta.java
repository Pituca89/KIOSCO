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

public class ListaProdVenta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JTextField codProducto;
	private JTable table;
	static Gson gson;
	static String data = "";
	static Stock[] stock;
	VentasDiarias ventas;
	
	public static ListaProdVenta instance;
	
	public static ListaProdVenta getInstance(VentasDiarias json) {
		if(instance == null) {
			instance = new ListaProdVenta(json);
		}
		instance.getCodProducto().setText("");
		DefaultTableModel model = new DefaultTableModel();
		
		model = (DefaultTableModel) instance.getTable().getModel();

		for (int i = instance.getTable().getRowCount() -1; i >= 0; i--){
			model.removeRow(i);
		}

		return instance;		
	}
	public ListaProdVenta(VentasDiarias ventas_1) {
		
		this.ventas = ventas_1;
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
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
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
				//buscarProducto();
				DefaultTableModel modal = new DefaultTableModel();
				DefaultTableModel modalPadre = new DefaultTableModel();
				modal = (DefaultTableModel) table.getModel();
				modalPadre = (DefaultTableModel) ventas.getTable().getModel();
				int sinstock = 0;
				ArrayList<String> nsinstock = new ArrayList<>();
				for (int i = 0; i < table.getRowCount(); i++){
					System.out.println("Valor boolean: " + table.getValueAt(i, 0));
					
					if(table.getValueAt(i, 0) != null) {
						
						if((Boolean)table.getValueAt(i, 0)) {
							if(Integer.parseInt(table.getValueAt(i, 4).toString()) <= 0) {
								sinstock = 1;
								nsinstock.add(table.getValueAt(i, 2).toString());
							}else {
								Object[] row = new Object[5];
								row[0] = table.getValueAt(i, 1);
								row[1] = table.getValueAt(i, 2);
								row[2] = 1;
								row[3] = table.getValueAt(i, 3);
								row[4] = table.getValueAt(i, 3);
								
								modalPadre.addRow(row);
							}
						}
					}					
					VentasDiarias.obtenerTotal();					
				}
				if(sinstock == 1) {
					sinstock = 0;
					String mensaje = "Los siguientes productos no tienen stock: ";
					for (String string : nsinstock) {
						mensaje += string + ", ";
						
					}
					int input = JOptionPane.showConfirmDialog(null, 
			                mensaje, "Atención!", 
			                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(input == 0) {
						VentasDiarias.foco();
						dispose();
					}
				}else {
					VentasDiarias.foco();
					dispose();
				}
				
			}
		});
		btnConfirmar.setBounds(373, 393, 135, 35);
		contentPanel.add(btnConfirmar);
		
		JButton btnNewButton = new JButton("CANCELAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentasDiarias.foco();
				dispose();
			}
		});
		btnNewButton.setBounds(150, 393, 135, 35);
		contentPanel.add(btnNewButton);
		contentPanel.requestFocus();
		this.setFocusableWindowState(true);
		this.setFocusable(true);
		this.setAutoRequestFocus(true);
		this.setLocationRelativeTo(null);
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
						//table.addMouseListener(mouseAdapter);
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
	
	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	public void setLblNewLabel(JLabel lblNewLabel) {
		this.lblNewLabel = lblNewLabel;
	}
	public JTextField getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(JTextField codProducto) {
		this.codProducto = codProducto;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public static Gson getGson() {
		return gson;
	}
	public static void setGson(Gson gson) {
		ListaProdVenta.gson = gson;
	}
	public static String getData() {
		return data;
	}
	public static void setData(String data) {
		ListaProdVenta.data = data;
	}
	public static Stock[] getStock() {
		return stock;
	}
	public static void setStock(Stock[] stock) {
		ListaProdVenta.stock = stock;
	}
	public VentasDiarias getVentas() {
		return ventas;
	}
	public void setVentas(VentasDiarias ventas) {
		this.ventas = ventas;
	}
	public static ListaProdVenta getInstance() {
		return instance;
	}
	public static void setInstance(ListaProdVenta instance) {
		ListaProdVenta.instance = instance;
	}
	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	
}
