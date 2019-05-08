package cliente.kiosco;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.DefaultListModel;

public class ThreadCliente extends Thread{

	private Socket Socketcliente;
	private DataInputStream entrada;
	private Cliente cliente;
	private ObjectInputStream entradaObjeto;
	
	public ThreadCliente(Socket SocketCliente, Cliente cliente) {
		this.Socketcliente =  SocketCliente;
		this.cliente = cliente;
	}
	
	public void run() {
		while(true) {
			try {
				this.entrada =  new DataInputStream(this.Socketcliente.getInputStream());
				this.cliente.mensajeria(this.entrada.readUTF());
				
				this.entradaObjeto = new ObjectInputStream(this.Socketcliente.getInputStream());
				this.cliente.actualizarLista((DefaultListModel) this.entradaObjeto.readObject());
			} catch (Exception e) {
				// TODO: handle exception
			} 
		}
	}
}
