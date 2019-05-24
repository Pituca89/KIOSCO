package cliente.kiosco;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;

import com.github.cliftonlabs.json_simple.JsonArray;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.net.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import net.miginfocom.swing.MigLayout;

public class Cliente extends JFrame {
	static SimpleAttributeSet at = new SimpleAttributeSet();
	static DefaultListModel us =new DefaultListModel();
	static Date hora;
	static String horaActual;
	String datoEscrito;
	static JButton botonEnviar;
	static JButton desconectarme;
	static JButton conectarme;
	static Component comp;
    public JLabel jLabel1;
    public JLabel jLabel2;
    public JLabel jLabel3;
    public JLabel jLabel4;
    public JLabel jLabel5;
    public JList usuarios;
    public JScrollPane scConversacion;
    public JScrollPane scUsuarios;
    public JScrollPane scEnvioDatos;
    static JTextField muestraIp;
    static JTextField puertoServ;
    static JTextPane conversacion;
    static JTextPane envioDatos;
    private JPanel contentPane;
    static final String STOCK_ACTUAL = "STOCK_ACTUAL";
    static final String ACTUALIZAR_STOCK = "ACTUALIZAR_STOCK";
    private JPanel panelPrincipal;
    public Cliente() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Cliente.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
        initComponents();
        //setSize(171,73);       
    }

    private void initComponents() {
    	
        setTitle("GESTION DE STOCK");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
                
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(0, 0, MAXIMIZED_VERT, MAXIMIZED_HORIZ);
		setExtendedState(MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuBar);
		
		JMenu mnStock = new JMenu("Stock");
		mnStock.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnStock.setBackground(new Color(255, 255, 255));
		menuBar.add(mnStock);
		
		JMenuItem mntmStockActual = new JMenuItem("Stock Actual");
		mntmStockActual.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmStockActual.setBackground(new Color(255, 255, 255));
		mnStock.add(mntmStockActual);
		
		JMenuItem mntmActualizarStock = new JMenuItem("Reposición rapida");
		mntmActualizarStock.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmActualizarStock.setBackground(new Color(255, 255, 255));
		mnStock.add(mntmActualizarStock);
		
		JMenuItem mntmConsumoDeNegocio = new JMenuItem("Consumo de Negocio");
		mntmConsumoDeNegocio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmConsumoDeNegocio.setBackground(new Color(255, 255, 255));
		mnStock.add(mntmConsumoDeNegocio);
		
		JMenuItem mntmDevolucion = new JMenuItem("Devoluci\u00F3n ");
		mntmDevolucion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmDevolucion.setBackground(new Color(255, 255, 255));
		mnStock.add(mntmDevolucion);
		
		JMenu mnVentas = new JMenu("Ventas");
		mnVentas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnVentas.setBackground(new Color(255, 255, 255));
		menuBar.add(mnVentas);
		
		JMenuItem mntmVentaDelDia = new JMenuItem("Venta del Dia");
		mntmVentaDelDia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmVentaDelDia.setBackground(new Color(255, 255, 255));
		mnVentas.add(mntmVentaDelDia);
		
		JMenu mnProductos = new JMenu("Productos");
		mnProductos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnProductos.setBackground(new Color(255, 255, 255));
		menuBar.add(mnProductos);
		
		JMenuItem mntmActualizarProductos = new JMenuItem("Actualizar Productos");
		mntmActualizarProductos.setBackground(new Color(255, 255, 255));
		mntmActualizarProductos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnProductos.add(mntmActualizarProductos);
		
		JMenu mnInformes = new JMenu("Informes");
		mnInformes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnInformes.setBackground(new Color(255, 255, 255));
		menuBar.add(mnInformes);
		
		JMenu mnNegocio = new JMenu("Negocio");
		mnNegocio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNegocio.setBackground(new Color(255, 255, 255));
		menuBar.add(mnNegocio);
		
		JMenuItem mntmCierreParcial = new JMenuItem("Cierre Parcial");
		mntmCierreParcial.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmCierreParcial.setBackground(new Color(255, 255, 255));
		mnNegocio.add(mntmCierreParcial);
		
		JMenuItem mntmCierreFinal = new JMenuItem("Cierre Final");
		mntmCierreFinal.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmCierreFinal.setBackground(new Color(255, 255, 255));
		mnNegocio.add(mntmCierreFinal);
		
		JMenuItem mntmConfiguracion = new JMenuItem("Configuracion");
		mntmConfiguracion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmConfiguracion.setBackground(new Color(255, 255, 255));
		menuBar.add(mntmConfiguracion);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		VentasDiarias panel = new VentasDiarias();
		panelPrincipal = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelPrincipal, GroupLayout.DEFAULT_SIZE, 1344, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelPrincipal, GroupLayout.PREFERRED_SIZE, 657, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		GroupLayout gl_panelPrincipal = new GroupLayout(panelPrincipal);
		gl_panelPrincipal.setHorizontalGroup(
			gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrincipal.createSequentialGroup()
					.addGap(7)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelPrincipal.setVerticalGroup(
			gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrincipal.createSequentialGroup()
					.addGap(8)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelPrincipal.setLayout(gl_panelPrincipal);
		contentPane.setLayout(gl_contentPane);
				
		mntmStockActual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StockActual panel = new StockActual();
							
				panel.setSize(contentPane.getSize());				
				panelPrincipal.removeAll();
				panelPrincipal.add(panel);
				panelPrincipal.revalidate();
				panelPrincipal.repaint();
				
			}
		});
		
		mntmActualizarStock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Actualizar panel = new Actualizar(1);
				panel.setVisible(true);	
			}
		});
		
		mntmConsumoDeNegocio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Descontar panel = new Descontar();
				panel.setVisible(true);
			}
		});
		
		mntmDevolucion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Recuperar panel = new Recuperar();
				panel.setVisible(true);
				
			}
		});
		
		mntmVentaDelDia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentasDiarias panel = new VentasDiarias();
				panel.setSize(contentPane.getSize());
				panelPrincipal.removeAll();
				panelPrincipal.add(panel);
				panelPrincipal.revalidate();
				panelPrincipal.repaint();
			}
		});
		
		mntmConfiguracion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Configuracion panel = new Configuracion();
				panel.setSize(contentPane.getSize());
				panelPrincipal.removeAll();
				panelPrincipal.add(panel);
				panelPrincipal.revalidate();
				panelPrincipal.repaint();
				
			}
		});
        pack();
        try {
			if(HTTPRequest.VerificarServidor()) {
				System.out.println("conexion exitosa");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error de verificacion");
		}

    }
        
    private void exitForm(WindowEvent evt) {
    	try{
    		InicioCliente.salidaC.println("2"); //"2" significa q me desconécte
    	}
    	catch (Exception e) {
    		//salida(1,e.getMessage());
    	}
    	
    	if(InicioCliente.sckt != null){ //cerramos el socket
    		try{ InicioCliente.sckt.close(); }
    		catch(Exception e){InicioCliente.sckt=null;}
    	}
    	if(InicioCliente.salidaC != null){ //cerramos buffer de salida
    		try{ InicioCliente.salidaC.close(); }
    		catch(Exception e){InicioCliente.salidaC=null;}
    	}
        System.exit(0);
    }
    
    /**Utilizarlo para cerrar la caja**/
    private void desconectarmeActionPerformed(ActionEvent evt) {
    	int resp=2;
    	resp=JOptionPane.showConfirmDialog(null,"Esta seguro que desea abandonar el chat?","Confirmación",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
    	if(resp==0){
    		try{
    			InicioCliente.salidaC.println("2"); //"2" significa q me desconécte
    		}
    		catch (Exception e) {
    			//salida(1,e.getMessage());
    		}
    	}    	
    }
    
    private void conectarmeActionPerformed() {
    	//InicioCliente.arrancarCliente(muestraIp.getText(),Integer.parseInt(puertoServ.getText()));    
    	InicioCliente.arrancarCliente("10.245.75.97",7777);
    	InicioCliente.procesarMensajes();
    }
    

    
    private void botonEnviarActionPerformed(ActionEvent evt) {
    	datoEscrito=envioDatos.getText().toString(); //capturamos lo q se escribio
    	/**Defino los mensajes que envio al servidor dependiendo de las consultas que necesito**/
    	if( datoEscrito.equals("") == false){ //se envia si escribe algo
    		try{
    			InicioCliente.salidaC.println("1"+datoEscrito);
    			//salida(3,InetAddress.getLocalHost().getHostName()+" dice:\n"+datoEscrito);
    		}
    		catch (Exception e) {
    			//salida(1,e.getMessage());
    		}
    	}
    	envioDatos.setText("");
    	envioDatos.requestFocus();
    }
    

	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	public void setPanelPrincipal(JPanel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
		
	}
}