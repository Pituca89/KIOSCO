package servidor.kiosco;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author volt
 */
public class BaseMySQL {

	private static Connection conectar = null;
	private static BaseMySQL mysql;
	
	public BaseMySQL() {
		try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sch_kiosco", "root", "1234");
            JOptionPane.showMessageDialog(null, "Conexion Establecida","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException | SQLException error) {
            JOptionPane.showMessageDialog(null, "Error al Conectarse"+"\n"+error,"Mensaje Error",JOptionPane.ERROR_MESSAGE);
        }
	}
   
	public static BaseMySQL getInstance() {
		if(mysql == null) {
			mysql =  new BaseMySQL();
		}
		return mysql;
	}
	
}
