import java.io.*; 
import java.net.*;

class InicioCliente { 
	static Socket sckt=null;
	static PrintWriter salidaC=null;
	
  	public static void arrancarCliente(String dir, int puerto){
  		try{   
  			sckt = new Socket(dir,puerto);
  		}
  		catch(Exception e){ 
   			ClienteVentana.salida(1,e.getMessage());
   		}   		
   	}
   	
   	public static void procesarMensajes(){
   		BufferedReader entradaC;
   		try{
   			entradaC=new BufferedReader(new InputStreamReader(sckt.getInputStream()));
   			salidaC=new PrintWriter(sckt.getOutputStream(),true);
   			
   			new HiloCliente(entradaC);
		}		
   		catch(Exception e){
   			ClienteVentana.salida(1,e.getMessage());
   		}
   	}
	
	public static void main(String args[]){ 
		new ClienteVentana().setVisible(true);	
  	}
} 