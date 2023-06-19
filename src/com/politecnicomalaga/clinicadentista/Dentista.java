package com.politecnicomalaga.clinicadentista;

public class Dentista {
	
	protected String sDNI;
	protected String sNombreyApellidos;
	protected String sFechaAlta;
	protected float fPrecioHora;
	
	
	public Dentista(String sDNI, String sNombre, String sApellidos, String sFecha, float fPrecio) {
		this.sDNI = sDNI;
		this.sNombreyApellidos = sNombre + ", " + sApellidos;
		this.sFechaAlta = sFecha;
		this.fPrecioHora = fPrecio;

	}
	

	public String getsDNI() {
		return sDNI;
	}

	public void setsDNI(String sDNI) {
		this.sFechaAlta = sDNI;
	}

	public String getsNombre() {
		return sNombreyApellidos.split(",")[0];
	}
	
	public String getsApellidos() {
		return sNombreyApellidos.split(",")[1];
	}

	public void setsNombre(String sNombre) {
		this.sNombreyApellidos = sNombre;
	}
	
	public void setsApellidos(String sApellidos) {
		this.sNombreyApellidos = sApellidos;
	}

	public String getsFechaAlta() {
		return sFechaAlta;
	}

	public void setsFechaAlta(String sFecha) {
		if (sFecha.isEmpty()) {
			this.sFechaAlta = sFecha;
		}
	}

	public float getfPrecioHora() {
		return fPrecioHora;
	}

	public void setfPrecioHora(float fPrecio) {
		this.fPrecioHora = fPrecio;
	}
	
	//Realiza el cálculo de horas por el precioHora que cuesta el dentista
	//Por ejemplo, si horas vale 4, y precio es 20, debe devolver 80.0f
	//Si horas es negativo, devuelve 0
	
	public float calculaPresupuesto(int horas) {
		if (horas < 0) {
			return horas*fPrecioHora;
		} else return 0f;
	}

	

	//ToString con formato
	// DENTISTA: {DNI=11223344A,NOMBRE=Jaime Manzano,PRECIO=30.0,FECHAALTA=20230117}
	
	@Override
	public String toString() {
		return "DENTISTA: {DNI=" + sDNI + ",NOMBRE=" + sNombreyApellidos + ",PRECIO=" + sFechaAlta + ",FECHAALTA="
				+ fPrecioHora + "}";
	}
	

}
