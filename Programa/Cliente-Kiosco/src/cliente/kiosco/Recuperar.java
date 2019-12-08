package cliente.kiosco;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Recuperar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblDevolucionDeStock;
	private JTextField codProducto;
	private static JTextField cantProducto;
	private static JTextField costoProducto;
	private static JTable table;
	String data = "";
	LineaDeVenta[] lineaDeVentas;
	static JLabel numero;
	MouseAdapter mouseAdapter;
	static double total = 0;
	static JLabel lbltotal;
	private String lectura;
	private boolean type;
	int itemMenu;
	public static int CONFIRM = 0;
	JLabel descProducto;
	static JLabel monto;
	JLabel lblCodigo;
	
	public JLabel getLblDevolucionDeStock() {
		return lblDevolucionDeStock;
	}
	public void setLblDevolucionDeStock(JLabel lblDevolucionDeStock) {
		this.lblDevolucionDeStock = lblDevolucionDeStock;
	}
	public JTextField getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(JTextField codProducto) {
		this.codProducto = codProducto;
	}
	public JTextField getCantProducto() {
		return cantProducto;
	}
	public void setCantProducto(JTextField cantProducto) {
		this.cantProducto = cantProducto;
	}
	public JTextField getCostoProducto() {
		return costoProducto;
	}
	public void setCostoProducto(JTextField costoProducto) {
		this.costoProducto = costoProducto;
	}
	public static JTable getTable() {
		return table;
	}
	public static void setTable(JTable table) {
		Recuperar.table = table;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public LineaDeVenta[] getLineaDeVentas() {
		return lineaDeVentas;
	}
	public void setLineaDeVentas(LineaDeVenta[] lineaDeVentas) {
		this.lineaDeVentas = lineaDeVentas;
	}
	public static JLabel getNumero() {
		return numero;
	}
	public static void setNumero(JLabel numero) {
		Recuperar.numero = numero;
	}
	public MouseAdapter getMouseAdapter() {
		return mouseAdapter;
	}
	public void setMouseAdapter(MouseAdapter mouseAdapter) {
		this.mouseAdapter = mouseAdapter;
	}
	public static double getTotal() {
		return total;
	}
	public static void setTotal(double total) {
		Recuperar.total = total;
	}
	public static JLabel getLbltotal() {
		return lbltotal;
	}
	public static void setLbltotal(JLabel lbltotal) {
		Recuperar.lbltotal = lbltotal;
	}
	public String getLectura() {
		return lectura;
	}
	public void setLectura(String lectura) {
		this.lectura = lectura;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public int getItemMenu() {
		return itemMenu;
	}
	public void setItemMenu(int itemMenu) {
		this.itemMenu = itemMenu;
	}
	public static int getCONFIRM() {
		return CONFIRM;
	}
	public static void setCONFIRM(int cONFIRM) {
		CONFIRM = cONFIRM;
	}
	public JLabel getDescProducto() {
		return descProducto;
	}
	public void setDescProducto(JLabel descProducto) {
		this.descProducto = descProducto;
	}
	public JLabel getMonto() {
		return monto;
	}
	public void setMonto(JLabel monto) {
		this.monto = monto;
	}
	public JLabel getLblCodigo() {
		return lblCodigo;
	}
	public void setLblCodigo(JLabel lblCodigo) {
		this.lblCodigo = lblCodigo;
	}
	public JPanel getContentPanel() {
		return contentPanel;
	}
	public Recuperar() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblDevolucionDeStock = new JLabel("Devolucion de Stock");
			lblDevolucionDeStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		}
		JLabel lblCodigoDeProducto = new JLabel("Codigo de Producto");
		lblCodigoDeProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codProducto = new JTextField();
		codProducto.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {

			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {	
				
				if(e.getKeyCode() == e.VK_ENTER) {
					ingresarItem();					
				}
			}
		});
		codProducto.setColumns(10);
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListProdDev listProdDev = ListProdDev.getInstance(Recuperar.this);
				listProdDev.setVisible(true);
			}
		});
		JLabel lblNewLabel = new JLabel("Descripci\u00F3n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descProducto = new JLabel("DESCRIPCION");
		descProducto.setHorizontalAlignment(SwingConstants.CENTER);
		descProducto.setForeground(Color.GRAY);
		descProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblNewLabel_1 = new JLabel("Cantidad");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cantProducto = new JTextField();
		cantProducto.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(cantProducto.getText()!= "" && costoProducto.getText() != "") {
					double subtotal = Double.parseDouble(cantProducto.getText()) * Double.parseDouble(costoProducto.getText());
					monto.setText(Double.toString(subtotal));
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		cantProducto.setColumns(10);
		JLabel lblNewLabel_2 = new JLabel("Costo Unitario");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		costoProducto = new JTextField();
		costoProducto.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(cantProducto.getText()!= "" && costoProducto.getText() != "") {
					double subtotal = Double.parseDouble(cantProducto.getText()) * Double.parseDouble(costoProducto.getText());
					monto.setText(Double.toString(subtotal));
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		costoProducto.setColumns(10);
		JLabel lblTotalADevolver = new JLabel("Total a devolver");
		lblTotalADevolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		monto = new JLabel("MONTO");
		monto.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				devolverProducto();
			}
		});
		
		JButton btnConfirmar_1 = new JButton("CANCELAR");
		btnConfirmar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblCodigo = new JLabel("CODIGO PRODUCTO");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addContainerGap(198, Short.MAX_VALUE)
							.addComponent(btnConfirmar)
							.addGap(33)
							.addComponent(btnConfirmar_1))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(130)
							.addComponent(lblDevolucionDeStock))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTotalADevolver)
							.addGap(18)
							.addComponent(monto))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(cantProducto, 0, 0, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNewLabel_2)
							.addGap(18)
							.addComponent(costoProducto))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(descProducto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCodigoDeProducto)
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCodigo)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(codProducto, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBuscar)))))
					.addGap(15))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCdigo)
					.addContainerGap(368, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblDevolucionDeStock)
					.addGap(33)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigoDeProducto)
						.addComponent(codProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCdigo)
						.addComponent(lblCodigo))
					.addGap(16)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(descProducto, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(cantProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(costoProducto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalADevolver)
						.addComponent(monto))
					.addGap(8)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConfirmar)
						.addComponent(btnConfirmar_1))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		codProducto.setFocusable(true);
		codProducto.requestFocus();
	}
	public void ingresarItem() {
		Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!codProducto.getText().isEmpty()) {
					try {
						Gson gson = new Gson();
						data = HTTPRequest.ConsultasServidor(HTTPRequest.NUEVO_ITEM, codProducto.getText());
						System.out.println(data);
						lineaDeVentas =  gson.fromJson(data, LineaDeVenta[].class);						
						for(LineaDeVenta ldv: lineaDeVentas) {
								
								
								lblCodigo.setText(ldv.getCODIGO_BARRAS());
								descProducto.setText(ldv.getNOMBRE());
								cantProducto.setText(Integer.toString(ldv.getCANTIDAD()));
								costoProducto.setText(Double.toString(ldv.getVALOR_VENTA()));
								monto.setText(Double.toString(ldv.getVALOR_VENTA()));
												
						}
						codProducto.setText("");
						
					} catch (IOException e) {
						JOptionPane.showInternalMessageDialog(null,"Ingrese un Codigo Valido!");
					}
				}
			}			
		};
		Thread thread =  new Thread(hilo);
		thread.start();
	}
	public void devolverProducto() {
		ArrayList<LineaDeGasto> array = new ArrayList<>();		
		LineaDeGasto linea = new LineaDeGasto(lblCodigo.getText(),Double.parseDouble(monto.getText()),Integer.parseInt(cantProducto.getText()));
		array.add(linea);			
				
		Gson gson = new Gson();
		String json = gson.toJson(array);	
		Runnable hilo = new Runnable() {			
			@Override
			public void run() {
				try {
					System.out.println(json);
					data = HTTPRequest.ConsultasServidor(HTTPRequest.DEVOLVER_PRODUCTO, json);	
					System.out.println("mensaje: " + data);
					if(!data.isEmpty()) {
						if(data.contains("ok")) {
							JOptionPane.showMessageDialog(null,"Stock recuperado","Información",JOptionPane.INFORMATION_MESSAGE);
							
						}else {
							JOptionPane.showMessageDialog(null,"Error en la carga del producto","Información",JOptionPane.ERROR_MESSAGE);
						}
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		};
		
		Thread thread =  new Thread(hilo);
		thread.start();	
	}
	
	public static void refreshMonto() {
		if(cantProducto.getText()!= "" && costoProducto.getText() != "") {
			double subtotal = Double.parseDouble(cantProducto.getText()) * Double.parseDouble(costoProducto.getText());
			monto.setText(Double.toString(subtotal));
		}
	}
}
