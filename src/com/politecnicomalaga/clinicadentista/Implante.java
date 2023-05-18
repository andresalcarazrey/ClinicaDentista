
package com.politecnicomalaga.clinicadentista;


public class Implante {
	protected String sCod;
	protected String sDescripcion;
	protected String sFecha;
	protected float fPrecio;
	protected boolean bCobrado;
	
	public Implante(String sCod, String sDescripcion, String sFecha, float fPrecio) {
		this.sCod = sCod;
		this.sDescripcion = sDescripcion;
		this.sFecha = sFecha;
		this.fPrecio = fPrecio;
		this.bCobrado = false;
	}
	
	public Implante(String sCSV) {
		
		String[] atr = sCSV.split(":");
		
		this.sCod = atr[0];
		this.sDescripcion = atr[1];
		this.sFecha = atr[2];
		this.fPrecio = Float.parseFloat(atr[3]);
		this.bCobrado = Boolean.parseBoolean(atr[4]);
		
	}
	public String getsCod() {
		return sCod;
	}
	public void setsCod(String sCod) {
		this.sCod = sCod;
	}
	public String getsDescripcion() {
		return sDescripcion;
	}
	public void setsDescripcion(String sDescripcion) {
		this.sDescripcion = sDescripcion;
	}
	public String getsFecha() {
		return sFecha;
	}
	public void setsFecha(String sFecha) {
		this.sFecha = sFecha;
	}
	public float getfPrecio() {
		return fPrecio;
	}
	public void setfPrecio(float fPrecio) {
		this.fPrecio = fPrecio;
	}
	public boolean isbCobrado() {
		return bCobrado;
	}
	public void setbCobrado(boolean bCobrado) {
		this.bCobrado = bCobrado;
	}
	@Override
	public String toString() {
		return "IMPLANTE;" + sCod + ";" + sDescripcion + ";" + sFecha + ";"
				+ fPrecio + ";" + bCobrado;
	}
	
}




