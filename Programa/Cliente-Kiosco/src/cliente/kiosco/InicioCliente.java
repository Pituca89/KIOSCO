package cliente.kiosco;

import java.io.*; 
import java.net.*;
import java.rmi.server.Skeleton;

class InicioCliente { 

	public static void main(String args[]){ 
		Cliente cliente = new Cliente();
		cliente.setVisible(true);	
  	}
} 
