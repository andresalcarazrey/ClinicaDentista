package com.politecnicomalaga.clinicadentista;

import java.util.Comparator;

public class PacienteComparadorFnac implements Comparator<Paciente>{

	@Override
	public int compare(Paciente o1, Paciente o2) {
		// TODO Auto-generated method stub
		return o1.getsFNac().compareTo(o2.getsFNac());
	}
	

}
