package servidor.kiosco;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.DefaultListModel;

public class ThreadServidor extends Thread {

	private DataInputStream entrada;
	private DataOutputStream salida;
	private Servidor server;
	private Socket cliente;
	public static Vector<ThreadServidor> usuarioActivo = new Vector();
	private String nombre;
	private ObjectOutputStream salidaObjeto;
	
	public ThreadServidor(Socket socketCliente, String nombre, Servidor server) throws IOException {
		this.cliente = socketCliente;
		this.server = server;
		this.nombre = nombre;
		this.usuarioActivo.add(this);
		
		for(int i = 0; i < this.usuarioActivo.size() ; i++) {
			this.usuarioActivo.get(i).enviosMensajes(nombre + " Se ha conectado...");
		}
	}
	
	public void run() {
		String mensaje = " ";
		while(true) {
			try {
				this.entrada = new DataInputStream(this.cliente.getInputStream());
				mensaje =  entrada.readUTF();
				
				for(int i = 0 ; i < this.usuarioActivo.size(); i++) {
					this.usuarioActivo.get(i).enviosMensajes(mensaje);
					this.server.mensajeria("Mensaje enviado...");
				}
			}catch(Exception e) {
				break;
			}
		}
		
		this.usuarioActivo.removeElement(this);
		this.server.mensajeria("El usuario se ha desconectado...");
		
		try {
			this.cliente.close();
		}catch (Exception e) {
			
		}
	}
	
	private void enviosMensajes(String msg) throws IOException {
		
		this.salida = new DataOutputStream(cliente.getOutputStream());
		this.salida.writeUTF(msg);
		DefaultListModel modelo = new DefaultListModel();
		
		for(int i = 0; i < this.usuarioActivo.size(); i++) {
			modelo.addElement(this.usuarioActivo.get(i).nombre);
		}
		
		this.salidaObjeto = new ObjectOutputStream(cliente.getOutputStream());
		this.salidaObjeto.writeObject(modelo);
	}
	
}
