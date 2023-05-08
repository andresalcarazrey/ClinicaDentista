package com.politecnicomalaga.clinicadentista;

import java.util.Comparator;

public class PacienteComparadorDni implements Comparator<Paciente>{

	@Override
	public int compare(Paciente o1, Paciente o2) {
		// TODO Auto-generated method stub
		
		return o1.getsDni().compareTo(o2.getsDni());
	}
	

}
