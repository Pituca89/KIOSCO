package cliente.kiosco;
import java.awt.Component;
import java.io.*; 
import java.net.*;
import java.util.ArrayList;

import javax.swing.JLabel;

class HiloCliente extends Thread{ 
	static BufferedReader entradaDatos=null;
	
	public HiloCliente(BufferedReader entrada2){
		entradaDatos = entrada2;
		start();
	}

	public void run(){
		
   		String linea="";
   		/**Interpreto la respuesta del servidor para saber en que ventana mostrar los datos**/
		try{
			while( (linea = entradaDatos.readLine()) != null ){ //escucha mensajes del servidor
				
				//StockActual.ActualizarComponentes(linea);
				
				JLabel lbl = new JLabel();
				lbl.setText(linea);
				
				Canal canal = Canal.getInstance();
				canal.Push(lbl);
				
								
				if(linea.equals("6Usted a sido desconectado por el servidor."))	break;
				if(linea.equals("6Usted se ha desconectado correctamente.")) break;
			}
		}
		catch(Exception e){ 
   			//Cliente.salida(1,e.getMessage());
   			if(entradaDatos!=null){
   				try{ entradaDatos.close();}
   				catch(Exception er){}
   			}
   		}
	}
}
