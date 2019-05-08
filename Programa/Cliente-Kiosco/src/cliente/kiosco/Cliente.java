package cliente.kiosco;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Cliente extends JFrame {

	private JPanel contentPane;
	private Socket cliente;
	private final int PUERTOC = 1000;
	private String host = "localhost";
	private DataOutputStream salida;
	private String nombre;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
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
	public Cliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		try {
			this.nombre = JOptionPane.showInputDialog("nombre");
			super.setTitle(super.getTitle() + this.nombre);
			this.cliente = new Socket(this.host,PUERTOC);
			
			DataOutputStream salida = new DataOutputStream(this.cliente.getOutputStream());
			this.salida.writeUTF(this.nombre);
			
			ThreadCliente hilo = new ThreadCliente(this.cliente, this);
			hilo.start();
		}catch (Exception e) {
			
		}
		
	}
	
	public void actualizarLista(DefaultListModel modelo) {
		System.out.println(modelo);
	}
	
	private void SalidaEvento(java.awt.event.ActionEvent evt) {
		try {
			this.salida = new DataOutputStream(this.cliente.getOutputStream());
			this.salida.writeUTF(this.nombre + " : ");
		}catch (Exception e) {
			
		}
	}
	
	public void mensajeria(String msg) {
		System.out.println(" " + msg + "\n");
	}
	
	

}
