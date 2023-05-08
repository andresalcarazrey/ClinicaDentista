package com.politecnicomalaga.clinicadentista;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.google.gson.Gson;

public class MainLauncher {

	private static Clinica miClinica;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		boolean seguir = true;
		int opcion;

		do {
			// menú principal

			// mostrar menú
			mostrarMenuPrincipal();
			opcion = leerIntTeclado(sc);
			if (opcion > 1 && opcion < 8 && MainLauncher.miClinica == null) {
				System.out.println(ProjectStrings.CLINICANULL);
			} else {
				switch (opcion) {
				case 1:
					generarClinica(sc);
					break;

				case 2:
					altaPaciente(sc);

					break;
				case 3:
					eliminaPaciente(sc);

					break;
				case 4:
					modificaPaciente(sc);

					break;
				case 5:
					buscaPacientes(sc);
					break;
				case 6:
					listaPacientes();
					break;
				case 7:
					saveClinica();
					break;
				case 8:
					loadClinica(sc);
					break;
				case -1: // Opción de entrada errónea
					System.out.println(ProjectStrings.OPCION_NO_VALIDA);
					break;
				default:
					seguir = false;
				}
			} // Else clinica is not null
		} while (seguir);
		System.out.println("Fin de la aplicación");

	}

	private static void mostrarMenuPrincipal() {
		System.out.println("\n\n\nMenú Opciones Proyecto Clínica Dentista");
		System.out.println("-------------------------------------------------\n");
		System.out.println("1. Dar de alta/modificar datos de la Clínica Dentista");
		System.out.println("2. Alta de paciente");
		System.out.println("3. Eliminar paciente");
		System.out.println("4. Modificar datos de paciente");
		System.out.println("5. Buscar paciente/s");
		System.out.println("6. Listar paciente/s");
		System.out.println("7. Grabar a disco");
		System.out.println("8. Leer de disco");

		System.out.println("Cualquier otra opción: Salir");

	}

	// Recogemos un número de teclado, si nos dan algo que no es un número, ponemos
	// -1 para repetir entrada
	private static int leerIntTeclado(Scanner sc) {
		int iOpcion;
		try {
			iOpcion = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			sc.nextLine();
			return -1;
		}
		return iOpcion;
	}

	// Recogemos un número float de teclado, si nos dan algo que no es un número
	// float, ponemos -1f
	private static float leerFloatTeclado(Scanner sc) {
		float fOpcion;
		try {
			fOpcion = sc.nextFloat();
			sc.nextLine();
		} catch (InputMismatchException e) {
			sc.nextLine();
			return -1f;
		}
		return fOpcion;
	}

	// Recoger un string de teclado
	private static String leerStringTeclado(Scanner sc) {

		String sEntrada;
		try {
			sEntrada = sc.nextLine();
		} catch (InputMismatchException e) {
			return "";
		}
		return sEntrada;
	}

	private static void generarClinica(Scanner sc) {
		// Recoger datos clínica
		String sNombre, sDireccion, sTelefono, sEmail, sCIF;

		System.out.println("Nombre de la clínica:");
		sNombre = leerStringTeclado(sc);
		System.out.println("Dirección de la clínica:");
		sDireccion = leerStringTeclado(sc);
		System.out.println("Teléfono de la clínica:");
		sTelefono = leerStringTeclado(sc);
		System.out.println("Email de la clínica:");
		sEmail = leerStringTeclado(sc);
		System.out.println("CIF de la clínica:");
		sCIF = leerStringTeclado(sc);

		// Crear/modificar clínica
		if (miClinica == null) {
			miClinica = new Clinica(sNombre, sDireccion, sTelefono, sEmail, sCIF);
		} else {
			// Setters
			miClinica.setsNombre(sNombre);
			miClinica.setsDireccion(sDireccion);
			miClinica.setsTelefono(sTelefono);
			miClinica.setsEmail(sEmail);
			miClinica.setsCIF(sCIF);
		}
	}

	// Submenú / Interfaz texto para gestionar pacientes
	private static void altaPaciente(Scanner sc) {

		// Mostrar submenú
		System.out.println("SubMenú Proyecto Clínica Dentista");
		System.out.println("-------------------------------------------------\n");
		System.out.println("ALTA PACIENTE\n");
		// Recoger datos Paciente
		String nombre, apellidos, dni, fNac, email, tfno;

		System.out.println("Nombre");
		nombre = leerStringTeclado(sc);
		System.out.println("Apellidos");
		apellidos = leerStringTeclado(sc);
		System.out.println("Dni");
		dni = leerStringTeclado(sc);
		System.out.println("Fecha de Nacimiento DD/MM/AAAA");
		fNac = leerStringTeclado(sc);
		System.out.println("Email");
		email = leerStringTeclado(sc);
		System.out.println("Teléfono");
		tfno = leerStringTeclado(sc);

		// Crear paciente
		if (miClinica.nuevoPaciente(nombre, apellidos, dni, fNac, email, tfno)) {
			System.out.println(ProjectStrings.ALTAPACIENTE_OK);
		} else {
			System.out.println(ProjectStrings.ALTAPACIENTE_ERROR);
		}

	}

	private static void modificaPaciente(Scanner sc) {
		String dni, campo;
		Paciente.AtributosPaciente atributoAModificar;
		int opcion;

		System.out.println("SubMenú Proyecto Clínica Dentista");
		System.out.println("-------------------------------------------------\n");
		System.out.println("MODIFICAR PACIENTE\n");
		System.out.println("Escriba el dni del paciente:");
		dni = leerStringTeclado(sc);
		System.out.println("Escoja el campo a modificar:");
		System.out.println("1. Nombre");
		System.out.println("2. Apellidos");
		System.out.println("3. Dni");
		System.out.println("4. Fecha Nacimiento");
		System.out.println("5. Teléfono");
		System.out.println("Cualquier otra cosa. Email");
		opcion = leerIntTeclado(sc);
		switch (opcion) {
		case 1:
			atributoAModificar = Paciente.AtributosPaciente.NOMBRE;
			break;
		case 2:
			atributoAModificar = Paciente.AtributosPaciente.APELLIDOS;
			break;
		case 3:
			atributoAModificar = Paciente.AtributosPaciente.DNI;
			break;
		case 4:
			atributoAModificar = Paciente.AtributosPaciente.FNAC;
			break;
		case 5:
			atributoAModificar = Paciente.AtributosPaciente.TFNO;
			break;
		default:
			atributoAModificar = Paciente.AtributosPaciente.EMAIL;
		}
		System.out.println("Ahora introduzca el nuevo valor del campo seleccionado:");
		campo = leerStringTeclado(sc);
		if (miClinica.actualizaPaciente(dni, campo, atributoAModificar)) {
			System.out.println(ProjectStrings.UPDATEPACIENTE_OK);
		} else {
			System.out.println(ProjectStrings.ALTAPACIENTE_ERROR + dni);
		}
	}

	private static void eliminaPaciente(Scanner sc) {
		// Mostrar submenú
		System.out.println("SubMenú Proyecto Clínica Dentista");
		System.out.println("-------------------------------------------------\n");
		System.out.println("ELIMINAR PACIENTE\n");
		// Recoger datos Paciente
		String dni;

		System.out.println("Dni");
		dni = leerStringTeclado(sc);

		// Eliminar paciente
		if (miClinica.eliminaPaciente(dni)) {
			System.out.println(ProjectStrings.ELIMINAPACIENTE_OK);
		} else {
			System.out.println(ProjectStrings.ELIMINAPACIENTE_ERROR);
		}
	}

	private static void buscaPacientes(Scanner sc) {
		Paciente[] lista;
		String campo;
		Paciente.AtributosPaciente atributoBusqueda;
		int opcion;

		System.out.println("SubMenú Proyecto Clínica Dentista");
		System.out.println("-------------------------------------------------\n");
		System.out.println("BUSCAR PACIENTE/S\n");
		System.out.println("Escoja el campo a buscar:");
		System.out.println("1. Nombre");
		System.out.println("2. Apellidos");
		System.out.println("3. Dni");
		System.out.println("4. Fecha Nacimiento");
		System.out.println("5. Teléfono");
		System.out.println("Cualquier otra cosa. Email");
		opcion = leerIntTeclado(sc);
		switch (opcion) {
		case 1:
			atributoBusqueda = Paciente.AtributosPaciente.NOMBRE;
			break;
		case 2:
			atributoBusqueda = Paciente.AtributosPaciente.APELLIDOS;
			break;
		case 3:
			atributoBusqueda = Paciente.AtributosPaciente.DNI;
			break;
		case 4:
			atributoBusqueda = Paciente.AtributosPaciente.FNAC;
			break;
		case 5:
			atributoBusqueda = Paciente.AtributosPaciente.TFNO;
			break;
		default:
			atributoBusqueda = Paciente.AtributosPaciente.EMAIL;
		}
		System.out.println("Ahora introduzca el valor a buscar del campo seleccionado:");
		campo = leerStringTeclado(sc);
		lista = miClinica.buscaPacientes(campo, atributoBusqueda);
		if (lista != null && lista.length > 0) {
			System.out.println("Pacientes encontrados:");
			mostrarListaPacientes(lista);
			System.out.println("Si desea consultar/gestionar tratamientos de un Paciente, teclee su dni");
			System.out.println("En otro caso pulse Intro para volver al menú principal");
			String dni = leerStringTeclado(sc);
			if (dni.length() > 0) {
				Paciente[] unPaciente = miClinica.buscaPacientes(dni, Paciente.AtributosPaciente.DNI);
				if (unPaciente != null && unPaciente.length == 1) {
					subMenuTratamientos(unPaciente[0], sc);
				} else {
					System.out.println(ProjectStrings.BUSQUEDA_NO_ENCUENTRA);
				}

			}

		} else {
			System.out.println(ProjectStrings.BUSQUEDA_NO_ENCUENTRA);
		}
	}

	private static void listaPacientes() {
		mostrarListaPacientes(miClinica.todosPacientes());

	}

	private static void mostrarListaPacientes(Paciente[] lista) {
		if (lista != null) {
			System.out.println("Lista de pacientes:");
			for (Paciente p : lista) {
				System.out.println(p.toString());
			}
		} else {
			System.out.println("0 pacientes");
		}
	}

	// Interfaz - Texto para tratamientos
	// Submenú para gestión de los tratamientos.
	public static void subMenuTratamientos(Paciente p, Scanner sc) {
		Tratamiento[] lista;
		String campo;
		Tratamiento.AtributosTratamiento atributoBusqueda;
		int opcion;
		boolean seguir = true;

		do {
			System.out.println("SubMenú Proyecto Clínica Dentista");
			System.out.println("-------------------------------------------------\n");
			System.out.println("Tratamientos del paciente:\n");
			System.out.println(p.toString());
			System.out.println("Escoja la opción a realizar:");
			System.out.println("1. Poner nuevo tratamiento");
			System.out.println("2. Eliminar tratamiento");
			System.out.println("3. Cobrar tratamiento");
			System.out.println("4. Buscar tratamiento/s");
			System.out.println("5. Listar todos los tratamientos");
			System.out.println("Cualquier otra opción: volver al menú principal");
			opcion = leerIntTeclado(sc);
			switch (opcion) {
			case 1:
				ponerTratamiento(p, sc);
				break;
			case 2:
				eliminarTratamiento(p, sc);
				break;
			case 3:
				cobrarTratamiento(p, sc);
				break;
			case 4:
				buscarTratamientos(p, sc);
				break;
			case 5:
				listarTratamientos(p);
				break;
			default:
				seguir = false;
			}
		} while (seguir);
		System.out.println("Saliendo del submenú de Tratamientos...\n\n\n");

	}

	private static void ponerTratamiento(Paciente p, Scanner sc) {
		// Mostrar submenú
		System.out.println("SubMenú Proyecto Clínica Dentista");
		System.out.println("-------------------------------------------------\n");
		System.out.println("NUEVO TRATAMIENTO\n");
		// Recoger datos Paciente
		String codigo, descripcion, fecha;
		float precio;

		System.out.println("Codigo");
		codigo = leerStringTeclado(sc);
		System.out.println("Descripcion");
		descripcion = leerStringTeclado(sc);
		System.out.println("Fecha prescripción DD/MM/AAAA");
		fecha = leerStringTeclado(sc);
		System.out.println("Precio en Euros");
		precio = leerFloatTeclado(sc);

		// Crear tratamiento
		if (p.nuevoTratamiento(codigo, descripcion, fecha, precio)) {
			System.out.println(ProjectStrings.ALTA_TRATAMIENTO_OK);
		} else {
			System.out.println(ProjectStrings.ALTA_TRATAMIENTO_ERROR);
		}
	}

	private static void eliminarTratamiento(Paciente p, Scanner sc) {
		// Mostrar submenú
		System.out.println("SubMenú Proyecto Clínica Dentista");
		System.out.println("-------------------------------------------------\n");
		System.out.println("ELIMINAR TRATAMIENTO\n");
		// Recoger datos Paciente
		String codigo;

		System.out.println("Codigo");
		codigo = leerStringTeclado(sc);

		// Crear tratamiento
		if (p.eliminaTratamiento(codigo)) {
			System.out.println(ProjectStrings.ELIMINA_TRATAMIENTO_OK);
		} else {
			System.out.println(ProjectStrings.ELIMINA_TRATAMIENTO_ERROR);
		}
	}

	private static void cobrarTratamiento(Paciente p, Scanner sc) {
		// Mostrar submenú
		System.out.println("SubMenú Proyecto Clínica Dentista");
		System.out.println("-------------------------------------------------\n");
		System.out.println("COBRAR TRATAMIENTO\n");
		// Recoger datos Paciente
		String codigo;

		System.out.println("Codigo");
		codigo = leerStringTeclado(sc);

		// Cobrar tratamiento
		float precio = p.cobraTratamiento(codigo);
		if (precio >= 0f) {
			System.out.println("El precio a cobrar es: " + String.valueOf(precio));
			System.out.println(ProjectStrings.COBRA_TRATAMIENTO_OK);
		} else {
			System.out.println(ProjectStrings.COBRA_TRATAMIENTO_ERROR);
		}
	}

	private static void buscarTratamientos(Paciente p, Scanner sc) {
		Tratamiento[] lista;
		String campo;
		Tratamiento.AtributosTratamiento atributoBusqueda;
		int opcion;

		System.out.println("SubMenú Proyecto Clínica Dentista");
		System.out.println("-------------------------------------------------\n");
		System.out.println("BUSCAR TRATAMIENTO/S\n");
		System.out.println("Escoja el campo a buscar:");
		System.out.println("1. Código");
		System.out.println("2. Descripción");
		System.out.println("3. Fecha");
		System.out.println("4. Cobrados");
		System.out.println("5. Pendientes de cobro");
		System.out.println("Cualquier otra cosa. Precio");
		opcion = leerIntTeclado(sc);
		switch (opcion) {
		case 1:
			atributoBusqueda = Tratamiento.AtributosTratamiento.CODIGO;
			break;
		case 2:
			atributoBusqueda = Tratamiento.AtributosTratamiento.DESCRIPCION;
			break;
		case 3:
			atributoBusqueda = Tratamiento.AtributosTratamiento.FECHA;
			break;
		case 4:
		case 5:
			atributoBusqueda = Tratamiento.AtributosTratamiento.COBRADO;
			break;
		default:
			atributoBusqueda = Tratamiento.AtributosTratamiento.PRECIO;
		}
		System.out.println("Ahora introduzca el valor a buscar del campo seleccionado:");
		System.out.println("NOTA: Para cobrados, poner true, para no cobrados, poner false");
		campo = leerStringTeclado(sc);
		lista = p.buscaTratamientos(campo, atributoBusqueda);
		if (lista != null && lista.length > 0) {
			System.out.println("Tratamientos encontrados:");
			mostrarTratamientos(lista);
		} else {
			System.out.println("No existen tratamientos con ese criterio de búsqueda");
		}
	}

	private static void listarTratamientos(Paciente p) {
		mostrarTratamientos(p.todosTratamientos());

	}

	private static void mostrarTratamientos(Tratamiento[] lista) {
		System.out.println("Lista de tratamientos:");
		for (Tratamiento t : lista) {
			System.out.println(t.toString());
		}
	}

	private static void saveClinica() {
		if (ControladorFicheros.writeText("clinica.csv", miClinica.toCSV())) {
			System.out.println("Proceso de volcado a disco exitoso");
		} else {
			System.out.println("Error al escribir en disco. ¿Tiene espacio en el disco?");
		}
		
		String jsonClinica = new Gson().toJson(miClinica);
		
		if (ControladorFicheros.writeText("clinica.json", jsonClinica)) {
			System.out.println("Proceso de volcado json a disco exitoso");
		} else {
			System.out.println("Error al escribir en disco. ¿Tiene espacio en el disco?");
		}
	}

	private static void loadClinica(Scanner sc) {
		System.out.println("Se van a recargar los datos desde disco");
		System.out.println("Todos los datos actuales serán sustituidos");
		System.out.println("¿Está seguro?(S para Sí; Otra letra para No) ");
		String respuesta = leerStringTeclado(sc);
		if (respuesta.equals("S"))
			miClinica = new Clinica(ControladorFicheros.readText("clinica.csv"));
	}

}
