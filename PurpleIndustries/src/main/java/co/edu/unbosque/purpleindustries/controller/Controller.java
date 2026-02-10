package co.edu.unbosque.purpleindustries.controller;

import co.edu.unbosque.purpleindustries.model.*;
import co.edu.unbosque.purpleindustries.view.*;

import java.util.InputMismatchException;
import co.edu.unbosque.purpleindustries.util.exception.*;

public class Controller {

	private Console con;
	private ModelFacade mf;

	public Controller() {
		con = new Console();
		mf = new ModelFacade();
	}

	public void run() {
		boolean salir = false;

		while (!salir) {
			try {
				con.imprimirConSalto("\n 🏥🚑💉💖Bienvenido al sistema de pacientes🏥🚑💉💖");
				con.imprimirConSalto("\nElija una opcion:");
				con.imprimirConSalto("1. Ingresar un paciente");
				con.imprimirConSalto("2. Ingresar un nuevo doctor");
				con.imprimirConSalto("3. Actualizar un paciente");
				con.imprimirConSalto("4. Actualizar un doctor");
				con.imprimirConSalto("5. Eliminar un paciente");
				con.imprimirConSalto("6. Eliminar un doctor");
				con.imprimirConSalto("7. Ver todos los pacientes");
				con.imprimirConSalto("8. Ver todos los doctores");
				con.imprimirConSalto("9. Diagnosticar pacientes");
				con.imprimirConSalto("0. salir");

				int opcion;

				try {
					opcion = con.leerEntero();
					con.leerLinea();
				} catch (InputMismatchException e) {
					con.imprimirConSalto(" Opción inválida. Debe ingresar un número.");
					con.quemarLinea();
					continue;
				}

				if (opcion < 0 || opcion > 9) {
					System.out.println(" Opción incorrecta. Ingrese un número entre 0 y 9.");
					continue;
				}

				switch (opcion) {
				case 1:
					try {
						String nombre = "";
						while (true) {
							con.imprimirConSalto("Ingresa el nombre del paciente 🧑🏼‍⚕️✨");
							nombre = con.leerLinea();

							try {
								ExceptionChecker.checkNombre(nombre);
								con.imprimirConSalto("Nombre registrado: " + nombre);
								break;
							} catch (EmptyFieldException | InvalidFormatException e) {
								con.imprimirConSalto("Error: " + e.getMessage());
							}
						}

						String fechaDeNacimiento = "";
						while (true) {
							con.imprimirConSalto("Ingrese la fecha de nacimiento (dd/mm/aaaa) 🧑🏼‍⚕️✨");
							fechaDeNacimiento = con.leerLinea();

							try {
								ExceptionChecker.checkFechaNacimiento(fechaDeNacimiento);
								con.imprimirConSalto("Fecha registrada: " + fechaDeNacimiento);
								break;
							} catch (EmptyFieldException | InvalidFormatException e) {
								con.imprimirConSalto("Error: " + e.getMessage());
							}
						}

						int documento = 0;
						while (true) {
							con.imprimirConSalto("Ingrese el número de identidad del paciente 🧑🏼‍⚕️✨");
							try {
								documento = con.leerEntero();
								con.quemarLinea();
								
								char doc[] = new char[10];

								String num = String.valueOf(documento);
								String id ="";

								for (int i = 0; i < doc.length; i++) {
									doc[i] = '*';
									if (num.charAt(i) == '0') {

										doc[i] = num.charAt(i);
										id += doc[i];
									} else {
										continue;
									}

								}
								
								con.imprimirConSalto(id);
								

								
								ExceptionChecker.checkDocumento(documento);
								con.imprimirConSalto("Documento registrado: " + id);
								break;
							} catch (InputMismatchException e) {
								con.imprimirConSalto("Error: debe ingresar solo números.");
								con.quemarLinea();
							} catch (NegativeValueException | OutOfRangeException e) {
								con.imprimirConSalto("Error: " + e.getMessage());
							}
						}

						String rh = "";
						while (true) {
							con.imprimirConSalto("Ingrese el tipo de sangre del paciente 🧑🏼‍⚕️✨");
							rh = con.leerLinea();

							try {
								ExceptionChecker.checkRh(rh);
								con.imprimirConSalto("RH registrado: " + rh);
								break;
							} catch (EmptyFieldException | InvalidFormatException e) {
								con.imprimirConSalto("Error: " + e.getMessage());
							}
						}

						double peso = 0;
						while (true) {
							con.imprimirConSalto("Ingrese el peso del paciente (kg) 🧑🏼‍⚕️✨");
							try {
								peso = con.leerEntero();
								con.quemarLinea();
								ExceptionChecker.checkPeso(peso);
								con.imprimirConSalto("Peso registrado: " + peso + " kg");
								break;
							} catch (InputMismatchException e) {
								con.imprimirConSalto("Error: debe ingresar un número válido.");
								con.quemarLinea();
							} catch (NegativeValueException | OutOfRangeException e) {
								con.imprimirConSalto("Error: " + e.getMessage());
							}
						}

						double altura = 0;
						while (true) {
							con.imprimirConSalto("Ingrese la altura del paciente (m) 🧑🏼‍⚕️✨");
							try {
								altura = con.leerEntero();
								con.quemarLinea();
								ExceptionChecker.checkAltura(altura);
								con.imprimirConSalto("Altura registrada: " + altura + " m");
								break;
							} catch (InputMismatchException e) {
								con.imprimirConSalto("Error: debe ingresar un número válido.");
								con.quemarLinea();
							} catch (NegativeValueException | OutOfRangeException e) {
								con.imprimirConSalto("Error: " + e.getMessage());
							}
						}

						Paciente nuevo = new Paciente(nombre, fechaDeNacimiento, documento, altura, peso, rh, 1,
								"Sin registrar");
						mf.getPacienteDAO().crear(nuevo);

					} catch (Exception e) {

						con.imprimirConSalto("Error: " + e.getMessage());

					}
					break;

				case 2:

					try {
						String nombre = "";
						while (true) {
							con.imprimirConSalto("Ingresa el nombre del doctor 🧑🏼‍⚕️✨");
							nombre = con.leerLinea();

							try {
								ExceptionChecker.checkNombre(nombre);
								con.imprimirConSalto("Nombre registrado: " + nombre);
								break;
							} catch (EmptyFieldException | InvalidFormatException e) {
								con.imprimirConSalto("Error: " + e.getMessage());
							}
						}

						String fechaDeNacimiento = "";
						while (true) {
							con.imprimirConSalto("Ingrese la fecha de nacimiento del doctor (dd/mm/aaaa) 🧑🏼‍⚕️✨");
							fechaDeNacimiento = con.leerLinea();

							try {
								ExceptionChecker.checkFechaNacimiento(fechaDeNacimiento);
								con.imprimirConSalto("Fecha registrada: " + fechaDeNacimiento);
								break;
							} catch (EmptyFieldException | InvalidFormatException e) {
								con.imprimirConSalto("Error: " + e.getMessage());
							}
						}

						int documento = 0;
						while (true) {
							con.imprimirConSalto("Ingrese el número de documento del doctor 🧑🏼‍⚕️✨");
							try {
								documento = con.leerEntero();
								con.quemarLinea();
								ExceptionChecker.checkDocumento(documento);
								con.imprimirConSalto("Documento registrado: " + documento);
								break;
							} catch (InputMismatchException e) {
								con.imprimirConSalto("Error: debe ingresar solo números.");
								con.quemarLinea();
							} catch (NegativeValueException | OutOfRangeException e) {
								con.imprimirConSalto("Error: " + e.getMessage());
							}
						}
						String especialidad = "";
						while (true) {
							con.imprimirConSalto("Ingrese la especialidad del doctor 🧑🏼‍⚕️✨");
							especialidad = con.leerLinea();

							try {
								ExceptionChecker.checkEspecialidad(especialidad);
								con.imprimirConSalto("Especialidad registrada: " + especialidad);
								break;
							} catch (EmptyFieldException | InvalidFormatException e) {
								con.imprimirConSalto("Error: " + e.getMessage());
							}
						}

						Doctor nuevo = new Doctor(nombre, fechaDeNacimiento, documento, especialidad);
						mf.getDoctorDAO().crear(nuevo);

					} catch (Exception e) {

						con.imprimirConSalto("Error: " + e.getMessage());

					}
					break;

				case 3:
					int documentoPaciente1 = 0;
					while (true) {
						con.imprimirConSalto("Seleccionar el paciente para actualizar 😷🤒🧑🏼");
						con.imprimirConSalto(mf.getPacienteDAO().mostrarTodo());
						con.imprimirConSalto("Ingrese el número de identificación del paciente:");

						try {
							documentoPaciente1 = con.leerEntero();
							con.quemarLinea();
							ExceptionChecker.checkDocumento(documentoPaciente1);
							break;
						} catch (InputMismatchException e) {
							con.imprimirConSalto("Error: debe ingresar un número válido.");
							con.quemarLinea();
						} catch (NegativeValueException | OutOfRangeException e) {
							con.imprimirConSalto("Error: " + e.getMessage());
						}
					}

					String nombre = "";
					while (true) {
						con.imprimirConSalto("Ingresa el nombre del paciente 🧑🏼‍⚕️✨");
						nombre = con.leerLinea();

						try {
							ExceptionChecker.checkNombre(nombre);
							con.imprimirConSalto("Nombre registrado: " + nombre);
							break;
						} catch (EmptyFieldException | InvalidFormatException e) {
							con.imprimirConSalto("Error: " + e.getMessage());
						}
					}

					String fechaDeNacimiento = "";
					while (true) {
						con.imprimirConSalto("Ingrese la fecha de nacimiento (dd/mm/aaaa) 🧑🏼‍⚕️✨");
						fechaDeNacimiento = con.leerLinea();

						try {
							ExceptionChecker.checkFechaNacimiento(fechaDeNacimiento);
							con.imprimirConSalto("Fecha registrada: " + fechaDeNacimiento);
							break;
						} catch (EmptyFieldException | InvalidFormatException e) {
							con.imprimirConSalto("Error: " + e.getMessage());
						}
					}

					String rh = "";
					while (true) {
						con.imprimirConSalto("Ingrese el tipo de sangre del paciente 🧑🏼‍⚕️✨");
						rh = con.leerLinea();

						try {
							ExceptionChecker.checkRh(rh);
							con.imprimirConSalto("RH registrado: " + rh);
							break;
						} catch (EmptyFieldException | InvalidFormatException e) {
							con.imprimirConSalto("Error: " + e.getMessage());
						}
					}

					double peso = 0;
					while (true) {
						con.imprimirConSalto("Ingrese el peso del paciente (kg) 🧑🏼‍⚕️✨");
						try {
							peso = con.leerEntero();
							con.quemarLinea();
							ExceptionChecker.checkPeso(peso);
							con.imprimirConSalto("Peso registrado: " + peso + " kg");
							break;
						} catch (InputMismatchException e) {
							con.imprimirConSalto("Error: debe ingresar un número válido.");
							con.quemarLinea();
						} catch (NegativeValueException | OutOfRangeException e) {
							con.imprimirConSalto("Error: " + e.getMessage());
						}
					}

					double altura = 0;
					while (true) {
						con.imprimirConSalto("Ingrese la altura del paciente (m) 🧑🏼‍⚕️✨");
						try {
							altura = con.leerEntero();
							con.quemarLinea();
							ExceptionChecker.checkAltura(altura);
							con.imprimirConSalto("Altura registrada: " + altura + " m");
							break;
						} catch (InputMismatchException e) {
							con.imprimirConSalto("Error: debe ingresar un número válido.");
							con.quemarLinea();
						} catch (NegativeValueException | OutOfRangeException e) {
							con.imprimirConSalto("Error: " + e.getMessage());
						}
					}

					Paciente nuevo = new Paciente(nombre, fechaDeNacimiento, documentoPaciente1, altura, peso, rh, 1,
							"Sin registrar");
					mf.getPacienteDAO().actualizar(documentoPaciente1, nuevo);
					con.imprimirConSalto("Paciente " + nombre + "actualizado correctamente");
					break;

				case 4:
					int documentoDoctor1 = 0;
					while (true) {
						con.imprimirConSalto("Seleccionar el doctor para actualizar 😷🤒🧑🏼");
						con.imprimirConSalto(mf.getDoctorDAO().mostrarTodo());
						con.imprimirConSalto("Ingrese el número de identificación del doctor:");

						try {
							documentoPaciente1 = con.leerEntero();
							con.quemarLinea();
							ExceptionChecker.checkDocumento(documentoPaciente1);
							break;
						} catch (InputMismatchException e) {
							con.imprimirConSalto("Error: debe ingresar un número válido.");
							con.quemarLinea();
						} catch (NegativeValueException | OutOfRangeException e) {
							con.imprimirConSalto("Error: " + e.getMessage());
						}
					}
					String nombre1 = "";
					while (true) {
						con.imprimirConSalto("Ingresa el nombre del doctor 🧑🏼‍⚕️✨");
						nombre1 = con.leerLinea();

						try {
							ExceptionChecker.checkNombre(nombre1);
							con.imprimirConSalto("Nombre registrado: " + nombre1);
							break;
						} catch (EmptyFieldException | InvalidFormatException e) {
							con.imprimirConSalto("Error: " + e.getMessage());
						}
					}

					String fechaDeNacimiento2 = "";
					while (true) {
						con.imprimirConSalto("Ingrese la fecha de nacimiento del doctor (dd/mm/aaaa) 🧑🏼‍⚕️✨");
						fechaDeNacimiento2 = con.leerLinea();

						try {
							ExceptionChecker.checkFechaNacimiento(fechaDeNacimiento2);
							con.imprimirConSalto("Fecha registrada: " + fechaDeNacimiento2);
							break;
						} catch (EmptyFieldException | InvalidFormatException e) {
							con.imprimirConSalto("Error: " + e.getMessage());
						}
					}

					String especialidad = "";
					while (true) {
						con.imprimirConSalto("Ingrese la especialidad del doctor 🧑🏼‍⚕️✨");
						especialidad = con.leerLinea();

						try {
							ExceptionChecker.checkEspecialidad(especialidad);
							con.imprimirConSalto("Especialidad registrada: " + especialidad);
							break;
						} catch (EmptyFieldException | InvalidFormatException e) {
							con.imprimirConSalto("Error: " + e.getMessage());
						}
					}

					Doctor nuevo1 = new Doctor(nombre1, fechaDeNacimiento2, documentoDoctor1, especialidad);
					mf.getDoctorDAO().actualizar(documentoDoctor1, nuevo1);

				case 5:
					int documento1 = 0;
					while (true) {
						con.imprimirConSalto("Seleccionar el paciente para eliminar 😷🤒🧑🏼");
						con.imprimirConSalto(mf.getPacienteDAO().mostrarTodo());
						con.imprimirConSalto("Ingrese el número de identificación del paciente:");

						try {
							documento1 = con.leerEntero();
							con.quemarLinea();
							ExceptionChecker.checkDocumento(documento1);
							break;
						} catch (InputMismatchException e) {
							con.imprimirConSalto("Error: debe ingresar un número válido.");
							con.quemarLinea();
						} catch (NegativeValueException | OutOfRangeException e) {
							con.imprimirConSalto("Error: " + e.getMessage());
						}
					}
					mf.getPacienteDAO().eliminar(mf.getPacienteDAO().getPacienteById(documento1));
					con.imprimirConSalto("Paciente eliminado");
					break;
				case 6:
					int documento2 = 0;
					while (true) {
						con.imprimirConSalto("Seleccionar el doctor para eliminar 😷🤒🧑🏼");
						con.imprimirConSalto(mf.getDoctorDAO().mostrarTodo());
						con.imprimirConSalto("Ingrese el número de identificación del doctor:");

						try {
							documento2 = con.leerEntero();
							con.quemarLinea();
							ExceptionChecker.checkDocumento(documento2);
							break;
						} catch (InputMismatchException e) {
							con.imprimirConSalto("Error: debe ingresar un número válido.");
							con.quemarLinea();
						} catch (NegativeValueException | OutOfRangeException e) {
							con.imprimirConSalto("Error: " + e.getMessage());
						}
					}
					mf.getDoctorDAO().eliminar(mf.getDoctorDAO().getDoctorById(documento2));
					con.imprimirConSalto("Doctor eliminado");
					break;
				case 7:
					con.imprimirConSalto("Mostrando pacientes....");
					con.imprimirConSalto(mf.getPacienteDAO().mostrarTodo());
					break;

				case 8:
					con.imprimirConSalto("Mostrando doctores....");
					con.imprimirConSalto(mf.getDoctorDAO().mostrarTodo());
					break;

				case 9:
					try {
						int documentoPaciente = 0;
						while (true) {
							con.imprimirConSalto("Seleccionar el paciente a diagnosticar 😷🤒🧑🏼");
							con.imprimirConSalto(mf.getPacienteDAO().mostrarTodo());
							con.imprimirConSalto("Ingrese el número de identificación del paciente:");

							try {
								documentoPaciente = con.leerEntero();
								con.imprimirConSalto(mf.getPacienteDAO().getPacienteById(documentoPaciente).toString());
								con.quemarLinea();
								ExceptionChecker.checkDocumento(documentoPaciente);
								break;
							} catch (InputMismatchException e) {
								con.imprimirConSalto("Error: debe ingresar un número válido.");
								con.quemarLinea();
							} catch (NegativeValueException | OutOfRangeException e) {
								con.imprimirConSalto("Error: " + e.getMessage());
							}
						}

						int triage = 0;
						while (true) {
							con.imprimirConSalto("Ingrese el número del triage 😷🧑🏼‍⚕️✨");
							con.imprimirConSalto("1. Resucitación 🩻");
							con.imprimirConSalto("2. Emergencia 🏥");
							con.imprimirConSalto("3. Urgencia 🚑");
							con.imprimirConSalto("4. Urgencia menor 🤒");
							con.imprimirConSalto("5. Sin urgencia 🩺");

							try {
								triage = con.leerEntero();
								con.quemarLinea();
								ExceptionChecker.checkTriage(triage);
								con.imprimirConSalto("Triage registrado: " + triage);
								break;

							} catch (OutOfRangeException e) {
								con.imprimirConSalto("Error: " + e.getMessage());

							} catch (InputMismatchException e) {
								con.imprimirConSalto("Error: debe ingresar un número.");
								con.quemarLinea();
							}
						}

						String diagnostico = "";
						while (true) {
							con.imprimirConSalto("Ingrese el diagnóstico final del paciente:");
							diagnostico = con.leerLinea();

							try {
								ExceptionChecker.checkDiagnostico(diagnostico);
								con.imprimirConSalto("Diagnóstico registrado: " + diagnostico);
								break;
							} catch (EmptyFieldException e) {
								con.imprimirConSalto("Error: " + e.getMessage());
							}
						}
						mf.getPacienteDAO().modificarDatosMedicos(documentoPaciente, triage, diagnostico);
						con.imprimirConSalto("Los datos del paciente ahora son: ");
						con.imprimirConSalto(mf.getPacienteDAO().getPacienteById(documentoPaciente).toString());
						con.imprimirConSalto(" Diagnóstico registrado correctamente");
						break;

					} catch (Exception e) {
						// TODO: handle exception
					}
					break;
				case 0:
					salir = true;
					con.imprimirConSalto("Saliendo del programa, hasta luego!!! 😷");

				}
			} catch (InputMismatchException e) {
				con.quemarLinea();
			}

		}
	}
}