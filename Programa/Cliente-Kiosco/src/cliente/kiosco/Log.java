package cliente.kiosco;

public class Log {
	//NOMBRE;RUBRO;FECHA;HORA;CANTIDAD_INGRESADA;CANTIDAD_ANTERIOR
	String NOMBRE,RUBRO,FECHA,HORA,TIPO_MOVIMIENTO;
	int CANTIDAD_INGRESADA,CANTIDAD_ANTERIOR,DIFERENCIA,STOCK_ACTUAL,RESTAN;
	
	public int getSTOCK_ACTUAL() {
		return STOCK_ACTUAL;
	}
	public void setSTOCK_ACTUAL(int sTOCK_ACTUAL) {
		STOCK_ACTUAL = sTOCK_ACTUAL;
	}
	public int getRESTAN() {
		return RESTAN;
	}
	public void setRESTAN(int rESTAN) {
		RESTAN = rESTAN;
	}
	public int getDIFERENCIA() {
		return DIFERENCIA;
	}
	public void setDIFERENCIA(int dIFERENCIA) {
		DIFERENCIA = dIFERENCIA;
	}
	public String getTIPO_MOVIMIENTO() {
		return TIPO_MOVIMIENTO;
	}
	public void setTIPO_MOVIMIENTO(String tIPO_MOVIMIENTO) {
		TIPO_MOVIMIENTO = tIPO_MOVIMIENTO;
	}
	public String getNOMBRE() {
		return NOMBRE;
	}
	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}
	public String getRUBRO() {
		return RUBRO;
	}
	public void setRUBRO(String rUBRO) {
		RUBRO = rUBRO;
	}
	public String getFECHA() {
		return FECHA;
	}
	public void setFECHA(String fECHA) {
		FECHA = fECHA;
	}
	public String getHORA() {
		return HORA;
	}
	public void setHORA(String hORA) {
		HORA = hORA;
	}
	public int getCANTIDAD_INGRESADA() {
		return CANTIDAD_INGRESADA;
	}
	public void setCANTIDAD_INGRESADA(int cANTIDAD_INGRESADA) {
		CANTIDAD_INGRESADA = cANTIDAD_INGRESADA;
	}
	public int getCANTIDAD_ANTERIOR() {
		return CANTIDAD_ANTERIOR;
	}
	public void setCANTIDAD_ANTERIOR(int cANTIDAD_ANTERIOR) {
		CANTIDAD_ANTERIOR = cANTIDAD_ANTERIOR;
	}
}
