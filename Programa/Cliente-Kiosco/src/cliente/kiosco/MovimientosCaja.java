package cliente.kiosco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.google.gson.Gson;

import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MovimientosCaja extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	JDateChooser fecha_desde;
	JDateChooser fecha_hasta;
	Gson gson;
	SimpleDateFormat formato;
	private Workbook excel;
	String data;
	public MovimientosCaja() {
		
		formato = new SimpleDateFormat("yyyy-MM-dd");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"NOMBRE","RUBRO","FECHA","HORA","STOCK ATERIOR","CANTIDAD INGRESADA","STOCK ACTUAL","TIPO MOVIMIENTO"
			}
		));
		table.getTableHeader().setBackground(new Color(0, 153, 0));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
		scrollPane.setViewportView(table);
		table.setRowHeight(25);
		
		fecha_hasta = new JDateChooser();
		fecha_hasta.setDateFormatString("yyyy-MM-dd");
		java.util.Date fecha = new java.util.Date();
		fecha_hasta.setDate(fecha);
		
		fecha_desde = new JDateChooser();
		fecha_desde.setDateFormatString("yyyy-MM-dd");
		
		JLabel lblNewLabel_1 = new JLabel("     FECHA DESDE");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(0, 153, 0));
		lblNewLabel_1.setOpaque(true);
		
		JLabel lblFechaHasta = new JLabel("     FECHA HASTA");
		lblFechaHasta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaHasta.setForeground(new Color(255, 255, 255));
		lblFechaHasta.setBackground(new Color(0, 153, 0));
		lblFechaHasta.setOpaque(true);
		
		JButton btnNewButton = new JButton("BUSCAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RefreshTable();
				cierreParcial();
			}
		});
		btnNewButton.setBackground(new Color(0, 153, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnDescargar = new JButton("DESCARGAR");
		btnDescargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DescargarExcel();
			}
		});
		btnDescargar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDescargar.setForeground(new Color(255, 255, 255));
		btnDescargar.setBackground(new Color(0, 153, 0));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1329, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(152)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addComponent(fecha_desde, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFechaHasta, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addComponent(fecha_hasta, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(btnDescargar)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnDescargar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(fecha_desde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(lblFechaHasta, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(fecha_hasta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		setExtendedState(MAXIMIZED_BOTH);
		int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(ancho,alto);
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                //exitForm(evt);
                dispose();
            }
        });
	}
	
	public void cierreParcial() {
		Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {
					gson = new Gson();
					DefaultTableModel modal = new DefaultTableModel();
					modal = (DefaultTableModel) table.getModel();
					ArrayList<Fecha> arrayfecha = new ArrayList<>();
					Fecha fecha = new Fecha(formato.format(fecha_desde.getDate()), formato.format(fecha_hasta.getDate()));
					arrayfecha.add(fecha);
					String json = gson.toJson(fecha);
					System.out.println(json);
					String data = HTTPRequest.ConsultasServidor(HTTPRequest.LOG, json);
					System.out.println(data);
					if(!data.isEmpty()) {
						Gson gson = new Gson();
						Log [] log =  gson.fromJson(data, Log[].class);
						
						for(Log s: log) {
							Object[] row = new Object[8];
							row[0] = s.getNOMBRE();
							row[1] = s.getRUBRO();
							row[2] = s.getFECHA();
							row[3] = s.getHORA();
							row[4] = s.getCANTIDAD_ANTERIOR();
							row[5] = s.getDIFERENCIA();
							row[6] = s.getCANTIDAD_ANTERIOR() + s.getDIFERENCIA();
							row[7] = s.getTIPO_MOVIMIENTO();
							modal.addRow(row);
							//modal.addRow(new Object()[s.getProducto_id(), s.getNombre(),s.getStock_minimo(),s.getStock_actual(),s.getPrecio_lista(),s.getPrecio_venta(),s.getFecha(),s.getFecha()]);
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		

		Thread thread =  new Thread(hilo);
		thread.start();
					
	}
	
	public static void RefreshTable() {
		
		DefaultTableModel model = new DefaultTableModel();
		model = (DefaultTableModel) table.getModel();

		for (int i = table.getRowCount() -1; i >= 0; i--){
			model.removeRow(i);
		}	
	}
	
	
	public void DescargarExcel() {

        /**Capturo los mensajes que el ThreadCliente deja en el buffer **/
        Runnable hilo = new Runnable() {
			
			@Override
			public void run() {
				try {
					excel = new HSSFWorkbook();
					Sheet hoja = excel.createSheet("Stock");
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
					java.util.Date fechaf = new java.util.Date();
					String fechaactual = formato.format(fechaf);
					String file = "C:\\Descargas\\MOVIMIENTOS_DE_CAJA_" + fechaactual + ".xls";
					int i = 0;
					//"FECHA VENTA","HORA VENTA", "CODIGO PRODUCTO", "NOMBRE PRODUCTO", "CANTIDAD DISPONIBLE", "CANTIDAD VENDIDA", "VENTA PARCIAL"
					Row fila = hoja.createRow(i);
					Cell celda = fila.createCell(0);
					celda.setCellValue("NOMBRE");
					celda = fila.createCell(1);
					celda.setCellValue("RUBRO");
					celda = fila.createCell(2);
					celda.setCellValue("FECHA");
					celda = fila.createCell(3);
					celda.setCellValue("HORA");
					celda = fila.createCell(4);
					celda.setCellValue("CANTIDAD ANTERIOR");
					celda = fila.createCell(5);
					celda.setCellValue("CANTIDAD CARGADA");
					celda = fila.createCell(6);
					celda.setCellValue("STOCK ACTUAL");
					celda = fila.createCell(7);
					celda.setCellValue("TIPO MOVIMIENTO");
					i++;
					ArrayList<Fecha> arrayfecha = new ArrayList<>();
					Fecha fecha = new Fecha(formato.format(fecha_desde.getDate()), formato.format(fecha_hasta.getDate()));
					arrayfecha.add(fecha);
					String json = gson.toJson(fecha);
					System.out.println(json);
					String data = HTTPRequest.ConsultasServidor(HTTPRequest.LOG, json);
					if(!data.isEmpty()) {
						Gson gson = new Gson();
						Log [] log =  gson.fromJson(data, Log[].class);
						
						for(Log s: log) {
							fila = hoja.createRow(i);
							celda = fila.createCell(0);
							celda.setCellValue(s.getNOMBRE());
							celda = fila.createCell(1);
							celda.setCellValue(s.getRUBRO());
							celda = fila.createCell(2);
							celda.setCellValue(s.getFECHA());
							celda = fila.createCell(3);
							celda.setCellValue(s.getHORA());
							celda = fila.createCell(4);
							celda.setCellValue(s.getCANTIDAD_ANTERIOR());
							celda = fila.createCell(5);
							celda.setCellValue(s.getDIFERENCIA());
							celda = fila.createCell(6);
							celda.setCellValue(s.getCANTIDAD_ANTERIOR() + s.getDIFERENCIA());
							celda = fila.createCell(7);
							celda.setCellValue(s.getTIPO_MOVIMIENTO());
							i++;
						}
						FileOutputStream salida = new FileOutputStream(file);
						excel.write(salida);
						salida.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		};
					
		Thread thread =  new Thread(hilo);
		thread.start();
	}
	
}
