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
		setJMenuBar(menuBar);
		
		JMenu mnStock = new JMenu("Stock");
		menuBar.add(mnStock);
		
		JMenuItem mntmStockActual = new JMenuItem("Stock Actual");
		mnStock.add(mntmStockActual);
		
		JMenuItem mntmActualizarStock = new JMenuItem("Actualizar Stock");
		mnStock.add(mntmActualizarStock);
		
		JMenuItem mntmConsumoDeNegocio = new JMenuItem("Consumo de Negocio");
		mnStock.add(mntmConsumoDeNegocio);
		
		JMenuItem mntmDevolucion = new JMenuItem("Devoluci\u00F3n ");
		mnStock.add(mntmDevolucion);
		
		JMenu mnVentas = new JMenu("Ventas");
		menuBar.add(mnVentas);
		
		JMenuItem mntmVentaDelDia = new JMenuItem("Venta del Dia");
		mnVentas.add(mntmVentaDelDia);
		
		JMenu mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);
		
		JMenu mnInformes = new JMenu("Informes");
		menuBar.add(mnInformes);
		
		JMenu mnNegocio = new JMenu("Negocio");
		menuBar.add(mnNegocio);
		
		JMenuItem mntmCierreParcial = new JMenuItem("Cierre Parcial");
		mnNegocio.add(mntmCierreParcial);
		
		JMenuItem mntmCierreFinal = new JMenuItem("Cierre Final");
		mnNegocio.add(mntmCierreFinal);
		
		JMenuItem mntmConfiguracion = new JMenuItem("Configuracion");
		menuBar.add(mntmConfiguracion);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		VentasDiarias panel = new VentasDiarias();
		panelPrincipal = new JPanel();
		GroupLayout gl_panelPrincipal = new GroupLayout(panelPrincipal);
		gl_panelPrincipal.setHorizontalGroup(
			gl_panelPrincipal.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelPrincipal.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelPrincipal.setVerticalGroup(
			gl_panelPrincipal.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelPrincipal.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelPrincipal.setLayout(gl_panelPrincipal);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panelPrincipal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelPrincipal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(11))
		);
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
				Actualizar panel = new Actualizar();
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
    /*
    public static void salida(int objeto, String dato){
    	hora = new Date();
    	horaActual = hora.getHours()+":"+hora.getMinutes()+":"+hora.getSeconds()+" ";
    	
    	switch(objeto){
    		case 1: //algun tipo de error
    			try{
    				StyleConstants.setForeground(at,Color.red);
					StyleConstants.setBold(at,true);
					if(dato.equals("Connection reset")){
						dato="El servidor se ha caido.";
						us.removeAllElements(); //se elimina toda la lista de clientes
						botonEnviar.setEnabled(false);
						desconectarme.setEnabled(false);
						muestraIp.setEditable(true);
						puertoServ.setEditable(true);
						conectarme.setEnabled(true);
						InicioCliente.sckt.close(); //cerramos el socket
						InicioCliente.salidaC.close(); //cerramos el buffer salida
					}
					else if(dato.equals("Connection refused: connect")){
						dato="Conexion denegada: Corrija la IP y/o el puerto.";
					}
					else if(dato.equals("Connection timed out: connect")){
						dato="Tiempo de espera agotado: Corrija la IP y/o el puerto, o tal vez el servidor no esté activo.";
					}
					else if(dato.equals("No route to host: connect")){
						dato="No existe ruta al host: Corrija la IP y/o el puerto.";
					}
				
    				conversacion.getDocument().insertString(conversacion.getDocument().getLength(),horaActual+dato+"\n",at);
					conversacion.setCaretPosition(conversacion.getDocument().getLength());
				}
				catch (Exception e) {System.err.println(e);}
				break;
				
    		case 2: //mensajes entrantes
    			try{
					StyleConstants.setForeground(at,Color.blue.darker());
					StyleConstants.setBold(at,false);
					if(dato.charAt(0)=='1'){ // de quien recibimos el mensaje
						conversacion.getDocument().insertString(conversacion.getDocument().getLength(),horaActual+dato.substring(1)+"\n",at);
					}
					else if(dato.charAt(0)=='2'){ //mensaje q recibimos
						conversacion.getDocument().insertString(conversacion.getDocument().getLength(),dato.substring(1)+"\n",at);
					}
					else if(dato.charAt(0)=='3'){ //nos informan quien es el nuevo cliente
						StyleConstants.setForeground(at,Color.green.darker().darker());
						StyleConstants.setBold(at,true);
						conversacion.getDocument().insertString(conversacion.getDocument().getLength(),horaActual+dato.substring(1),at);
						us.addElement(dato.substring(1));
					}
					else if(dato.charAt(0)=='4'){ //nos informan de nuevo cliente
						StyleConstants.setForeground(at,Color.green.darker().darker());
						StyleConstants.setBold(at,true);
						conversacion.getDocument().insertString(conversacion.getDocument().getLength(),dato.substring(1)+"\n",at);
					}
					else if(dato.charAt(0)=='5'){ //nos informan q nos conectamos
						StyleConstants.setForeground(at,Color.green.darker().darker());
						StyleConstants.setBold(at,true);
						conversacion.getDocument().insertString(conversacion.getDocument().getLength(),horaActual+dato.substring(1)+"\n",at);
						us.addElement(InicioCliente.sckt.getInetAddress().getHostName()+" (Local).");
						conectarme.setEnabled(false);
    					muestraIp.setEditable(false);
    					puertoServ.setEditable(false);
    					desconectarme.setEnabled(true);
    					botonEnviar.setEnabled(true);
					}
					else if(dato.charAt(0)=='6'){ //nos desconecto el servidor o nos desconectamos
						StyleConstants.setForeground(at,Color.red);
						if(dato.equals("6Usted se ha desconectado correctamente.")){
							StyleConstants.setForeground(at,Color.green.darker().darker());
						}
						StyleConstants.setBold(at,true);
						conversacion.getDocument().insertString(conversacion.getDocument().getLength(),horaActual+dato.substring(1)+"\n",at);
						us.removeAllElements(); //se elimina toda la lista de clientes
						botonEnviar.setEnabled(false);
						desconectarme.setEnabled(false);
						muestraIp.setEditable(true);
						puertoServ.setEditable(true);
						conectarme.setEnabled(true);
						InicioCliente.sckt.close(); //cerramos el socket
						InicioCliente.salidaC.close(); //cerramos el buffer salida
					}
					else if(dato.charAt(0)=='7'){ //nos dicen quienes estan conectados
						us.addElement(dato.substring(1));
					}
					else if(dato.charAt(0)=='8'){ //nos informan quien ha sido desconectado
						StyleConstants.setForeground(at,Color.green.darker().darker());
						StyleConstants.setBold(at,true);
						conversacion.getDocument().insertString(conversacion.getDocument().getLength(),horaActual+dato.substring(1),at);
						int a=0;
						while(a<us.getSize()){ //para eliminarlo de la lista
							if(us.getElementAt(a).toString().equals(dato.substring(1))){
								us.removeElementAt(a);
								break;
							}
							else a++;
						}
					}
					else if(dato.charAt(0)=='9'){ //nos informan alguien ha sido desconectado
						StyleConstants.setForeground(at,Color.green.darker().darker());
						StyleConstants.setBold(at,true);
						conversacion.getDocument().insertString(conversacion.getDocument().getLength(),dato.substring(1)+"\n",at);
					}
					conversacion.setCaretPosition(conversacion.getDocument().getLength());
				}
				catch (Exception e) {System.err.println(e);}
				break;
				
			case 3: //mensajes salientes
    			try{
					StyleConstants.setForeground(at,Color.blue.darker().darker());
					StyleConstants.setBold(at,true);
    				conversacion.getDocument().insertString(conversacion.getDocument().getLength(),horaActual+dato+"\n",at);
					conversacion.setCaretPosition(conversacion.getDocument().getLength());
				}
				catch (BadLocationException e) {System.err.println(e);}
				break;
    	}    	
    }
    */
}