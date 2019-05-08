package servidor.kiosco;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Servidor extends JFrame {

	private JPanel contentPane;
	private ServerSocket server;
	private final int PUERTOH = 1000;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servidor frame = new Servidor();
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
	public Servidor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		try {
			this.server = new ServerSocket(PUERTOH);
			this.mensajeria(" Servidor con Conexión\n ");
						
			while(true) {
				Socket cliente = server.accept();
				this.mensajeria("Cliente conectado desde la direccion: " + cliente.getInetAddress().getHostAddress());
				
				DataInputStream entrada = new DataInputStream(cliente.getInputStream());
				
				ThreadServidor hilo = new ThreadServidor(cliente, entrada.readUTF(), this);
				hilo.start();
			}
			
		}catch (Exception e) {
			
		}
	}
	
	
	public void mensajeria(String msg) {
		System.out.println(" " + msg + "\n");
	}

}
