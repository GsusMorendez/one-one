package general;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Controlador {

	private Modelo miModelo;
	private ArrayList<JFrame> misPantallas = new ArrayList<JFrame>();

	public Modelo getMiModelo() {
		return miModelo;
	}

	public void setMiModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public ArrayList<JFrame> getMisPantallas() {
		return misPantallas;
	}

	public void setMisPantallas(JFrame pantalla) {

		misPantallas.add(pantalla);

	}

	public void cambiarDePantalla(int origen, int destino) {

		misPantallas.get(origen).setVisible(false);
		misPantallas.get(destino).setVisible(true);

	}

	public boolean comprobarNombreInicial(String nombre) {
		int contadorDeNumeros = 0;

		for (int i = 0; i < nombre.length(); i++) {
			if (!Character.isLetter(nombre.charAt(i))) {
				contadorDeNumeros++;

			}
		}

		if (contadorDeNumeros > 0) {
			JOptionPane.showMessageDialog(null, "El nombre solo puede contener letras");
			return false;
		} else if (nombre.equals("")) {
			JOptionPane.showMessageDialog(null, "Debes introducir tu nombre para continuar");
			return false;

		}

		return true;

	}

	public boolean verificarDatos(String[] datosInsert) {

		if (comprobarNombreInicial(datosInsert[0]) && comprobarNombreInicial(datosInsert[1])
				&& comprobarNombreInicial(datosInsert[2]) && comprobarDni(datosInsert[4])
				&& comprobarEmail(datosInsert[3]) && comprobarEdad(datosInsert[5]) && comprobarPromocion(datosInsert[7])
				&& comprobarContra(datosInsert[6])) {
			return true;

		} else {
			return false;
		}

	}

	public boolean comprobarEmail(String email) {

		if (email.contains("@") && email.substring(email.length() - 4).equals(".com")
				&& !email.substring(email.length() - 5, email.length() - 4).equals("@")) {

			return true;

		} else {
			JOptionPane.showMessageDialog(null, "El email introducido no es válido");

			return false;
		}

	}

	public boolean comprobarDni(String dni) {

		String letraMetida = "";
		int miDNI = 0;
		String letraQueTieneQueSer = "";

		if (dni.length() == 9 && Character.isLetter(dni.charAt(8)) == true) {
			letraMetida = dni.substring(dni.length() - 1).toUpperCase();
			miDNI = Integer.parseInt(dni.substring(0, 8));
			int resto = 0;
			letraQueTieneQueSer = "";
			String[] asignacionLetra = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S",
					"Q", "V", "H", "L", "C", "K", "E" };

			resto = miDNI % 23;

			letraQueTieneQueSer = asignacionLetra[resto];
		} else {
			JOptionPane.showMessageDialog(null, "El DNI introducido no es correcto");

			return false;
		}

		if (letraMetida.equals(letraQueTieneQueSer)) {

			return true;
		} else {
			JOptionPane.showMessageDialog(null, "El DNI introducido no es correcto");

			return false;
		}

	}

	public boolean comprobarEdad(String edad) {

		int contadorDeNumeros = 0;

		if (edad.length() == 2) {
			for (int i = 0; i < edad.length(); i++) {
				if (!Character.isLetter(edad.charAt(i))) {
					contadorDeNumeros++;

				}
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Para cursar un FP debes tener al menos 18 años y no concebimos mayores de 99 haciéndolo");
		}
		if (contadorDeNumeros == 2) {
			return true;
		} else {
			return false;
		}

	}

	public boolean comprobarContra(String contra) {

		if (contra.length() < 19) {
			return true;

		} else {
			return false;
		}

	}

	public boolean comprobarPromocion(String promo) {

		if (promo.length() == 9 && promo.substring(4, 5).equals("/")) {
			return true;

		} else {
			JOptionPane.showMessageDialog(null, "La promoción tiene que seguir el siguiente formato 2020/2021");

			return false;
		}

	}

	public String ponerPromocion() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int nextYear = year + 1;
		String año = Integer.toString(year) + "/" + Integer.toString(nextYear);
		return año;

	}

	public void llevarAlaParteProfOalumn(int buscarUsuario) {

		if (buscarUsuario == 0) {

			JOptionPane.showMessageDialog(null, "Usuario o contraseña erróneos");

		} else if (buscarUsuario == 1) {

			cambiarDePantalla(1, 3);

		} else {

			cambiarDePantalla(1, 4);
		}

	}

	public boolean comprobarCif(String cif) {

		if (cif.length() != 9) {
			JOptionPane.showMessageDialog(null, "La longitud del cif debe ser de 9 caracteres");
			return false;
		} else if (!Character.isLetter(cif.charAt(0))) {
			JOptionPane.showMessageDialog(null, "El primer caracter debe ser una letra");
			return false;
		} else {

			String[] datosIndividuales = cif.split("");
			String primeraLetra = datosIndividuales[0].toUpperCase();
			String ultimoCaracter = datosIndividuales[datosIndividuales.length - 1].toUpperCase();
			String sieteNumCentrales = "";

			boolean areTheSevenCentralDigitsNumbers = true;

			for (int i = 0; i < datosIndividuales.length; i++) {
				if (i != 0 && i != datosIndividuales.length - 1) {
					char michardecomprobacion = datosIndividuales[i].charAt(0);
					sieteNumCentrales = sieteNumCentrales + datosIndividuales[i];
					if (Character.isLetter(michardecomprobacion)) {
						areTheSevenCentralDigitsNumbers = false;

					}
				}
			}

			if (!areTheSevenCentralDigitsNumbers) {
				JOptionPane.showMessageDialog(null, "Los siete digitos centrales deben ser siempre números");
				return false;
			} else {

				// System.out.println(sieteNumCentrales);

				String[] sieteNumCentArr = sieteNumCentrales.split("");

				int sumaPosicionesPares = 0;
				int resultadoFinalImapres = 0;

				for (int i = 0; i < sieteNumCentArr.length; i++) {
					if (i == 1 || i == 3 || i == 5) {

						sumaPosicionesPares = sumaPosicionesPares + Integer.parseInt(sieteNumCentArr[i]);

					} else {
						int numeroPosImparPorDos = Integer.parseInt(sieteNumCentArr[i]) * 2;
						String numeroPosImparPorDosString = String.valueOf(numeroPosImparPorDos);

						if (numeroPosImparPorDosString.length() == 2) {

							int decenas = Integer.parseInt(numeroPosImparPorDosString.substring(0, 1));
							int unidades = Integer.parseInt(numeroPosImparPorDosString.substring(1));

							int sumaUnidadesYdecenas = unidades + decenas;

							resultadoFinalImapres = resultadoFinalImapres + sumaUnidadesYdecenas;

						} else {
							int imparUnaCifra = Integer.parseInt(numeroPosImparPorDosString);
							resultadoFinalImapres = resultadoFinalImapres + imparUnaCifra;
						}

						// System.out.println( "el numero pos impar " + numeroPosImparPorDosString);
					}

				}

				System.out.println("suma de impares " + resultadoFinalImapres);

				System.out.println("suma de pares " + sumaPosicionesPares);

				int sumaParesImpares = resultadoFinalImapres + sumaPosicionesPares;

				String sumaParesImparesString = String.valueOf(sumaParesImpares);

				int unidadesSumaParesImpares = Integer.parseInt(sumaParesImparesString.substring(1));

				// System.out.println(unidadesSumaParesImpares);

				String[] letrasResuLt = { "J", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

				int resultadoeNumero = 10 - unidadesSumaParesImpares;
				String resultadoEnLetra = letrasResuLt[resultadoeNumero];

				System.out.println(resultadoeNumero);

				System.out.println(resultadoEnLetra);

				// System.out.println(restar10);

				String[] siEsUnaDeEstasElDCNumero = { "A", "B", "C", "D", "E", "F", "G", "H", "J", "U", "V" };
				String[] siEsUnaDeEstasElDCLetra = { "K", "P", "Q", "S", "N", "R", "W", "W", "W", "W", "W" };
				String[] otros = { "L", "M", "N", "I", "O", "T", "X", "Y", "Z", "Z", "Z" };

				char ultimodigito = ultimoCaracter.charAt(0);

				boolean condicion = false;
				int j = 0;

				boolean definitivo = false;

				while (!condicion) {
					if (primeraLetra.equals(siEsUnaDeEstasElDCNumero[j])) {

						if (Character.isLetter(ultimodigito)) {
							// AQUI SE ACABA ESTÁ MAL
							System.out.println("esta mal 0");
							condicion = true;
							definitivo = false;
						} else {
							int numeroDC = Integer.parseInt(ultimoCaracter);
							if (numeroDC == resultadoeNumero) {
								// está bien

								System.out.println("está bien 1");
								condicion = true;
								definitivo = true;
							} else {
								// esta mal

								System.out.println("está mal 0.5");
								condicion = true;
							}
						}

					} else if (primeraLetra.equals(siEsUnaDeEstasElDCLetra[j])) {

						if (!Character.isLetter(ultimodigito)) {
							// AQUI SE ACABA ESTÁ MAL
							System.out.println("esta mal 1");
							condicion = true;
							definitivo = false;

						} else {

							if (ultimoCaracter.equals(resultadoEnLetra)) {
								// aqui bien
								System.out.println("esta bien 2");
								condicion = true;
								definitivo = true;

							} else {
								// esta mal
								System.out.println("esta mal 2");
								condicion = true;
								definitivo = false;

							}

						}

					} else if (primeraLetra.equals(otros[j])) {
						// esta mal
						System.out.println("esta mal 3");
						condicion = true;
						definitivo = false;

					}

					j++;
				}

				if (definitivo) {
					return true;
				} else {
					return false;
				}

			}

		}

		/*
		 * 
		 * 
		 * 
		 * if (cif.length() == 9) { char letra = cif.charAt(0); if
		 * (Character.isLetter(letra)) {
		 * 
		 * // la primera letra te dice el tipo de sociedad que es // el ultimo digito
		 * puede ser una letra o un numero es el dígito de control String
		 * numeroSinLaPrimeraYultimaPosicion = cif.substring(1, cif.length() - 1);
		 * 
		 * System.out.println(numeroSinLaPrimeraYultimaPosicion);
		 * 
		 * if (numeroSinLaPrimeraYultimaPosicion.matches("\\d*")) {
		 * 
		 * // int loMismoEnInt = Integer.parseInt(numeroSinLaPrimeraYultimaPosicion);
		 * 
		 * String[] numeros = numeroSinLaPrimeraYultimaPosicion.split("");
		 * 
		 * int buscarAscii = 0;
		 * 
		 * // primero sumamos las posiciones pares (cuidado con el indice)
		 * 
		 * int sumaTotal = Integer.parseInt(numeros[1]) + Integer.parseInt(numeros[3]) +
		 * Integer.parseInt(numeros[5]);
		 * 
		 * System.out.println(sumaTotal);
		 * 
		 * for (int i = 0; i < numeros.length; i++) { if (i == 0 || i == 2 || i == 4 ||
		 * i == 6) { int imparesPorDos = Integer.parseInt(numeros[i]) * 2; String
		 * imparesPorDosString = String.valueOf(imparesPorDos);
		 * 
		 * // tenemos que saber si el resultado de la suma de las posiciones impares
		 * tiene // uno o dos dígitos if (imparesPorDosString.length() == 2) {
		 * 
		 * int uno = Integer.parseInt(imparesPorDosString.substring(0, 1)); int dos =
		 * Integer.parseInt(imparesPorDosString.substring(1));
		 * 
		 * // System.out.println(uno + " " + dos);
		 * 
		 * int numeroAsumar = uno + dos;
		 * 
		 * sumaTotal += numeroAsumar;
		 * 
		 * } else { int numeroAsumar = imparesPorDos; sumaTotal += numeroAsumar;
		 * 
		 * }
		 * 
		 * String sumaTotalString = String.valueOf(sumaTotal); int restaConDiez = 0;
		 * 
		 * if (sumaTotalString.length() == 2) { int segundaCifraUnidades =
		 * Integer.parseInt(sumaTotalString.substring(1)); restaConDiez = 10 -
		 * segundaCifraUnidades;
		 * 
		 * } else { restaConDiez = 10 - sumaTotal; }
		 * 
		 * buscarAscii = 64 + restaConDiez;
		 * 
		 * System.out.println(restaConDiez);
		 * 
		 * System.out.println(buscarAscii);
		 * 
		 * } }
		 * 
		 * String charAscii = Character.toString((char) buscarAscii); //String
		 * letraAcomparar = cif.substring(0, 1); String letraAcomparar =
		 * cif.substring(cif.length()-1, cif.length());
		 * 
		 * System.out.println(charAscii); System.out.println(letraAcomparar);
		 * 
		 * if (charAscii.equals(letraAcomparar)) {
		 * 
		 * return true;
		 * 
		 * } else { JOptionPane.showMessageDialog(null, "El cif no es correcto"); return
		 * false;
		 * 
		 * }
		 * 
		 * // System.out.println(charAscii);
		 * 
		 * } else { JOptionPane.showMessageDialog(null,
		 * "Las 7 cifras intermedias deben ser números"); return false; }
		 * 
		 * } else { JOptionPane.showMessageDialog(null,
		 * "El primer caracter debe ser una letra"); return false;
		 * 
		 * }
		 * 
		 * } else { JOptionPane.showMessageDialog(null,
		 * "El CIF introducido es erroneo"); return false; }
		 */

	}

	public String [] ayudaMatches(int filas, int tamañoArr) {
		String[] alumYemp = new String [2];

		int numRow = filas;
		// int numCol = table.getSelectedColumn();

		if (tamañoArr == 3) {
			String[] rowChoosen = new String[3];

			for (int i = 0; i < rowChoosen.length; i++) {
				rowChoosen[i] = (String) miModelo.getTablaEmpresaDefModel().getValueAt(numRow, i);
				// System.out.println(" el dato numero " + i + companyChoosen[i]);
			}

			String cifEmpresa = rowChoosen[1];
			String dniAlumnos = rowChoosen[0];
			String nombreAlumno = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "alumnos", "dni",
					dniAlumnos);

			String nombreEmpresa = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "empresa", "CIF",
					cifEmpresa);
			
			alumYemp[0] = nombreAlumno;
			alumYemp[1] = nombreEmpresa;
			return alumYemp;


		} else {

			String[] rowChoosen = new String[4];

			for (int i = 0; i < rowChoosen.length; i++) {
				rowChoosen[i] = (String) miModelo.getTablaEmpresaDefModel().getValueAt(numRow, i);
				// System.out.println(" el dato numero " + i + companyChoosen[i]);
			}

			String cifEmpresa = rowChoosen[2];
			String dniAlumnos = rowChoosen[3];
			String nombreAlumno = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "alumnos", "dni",
					dniAlumnos);

			String nombreEmpresa = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "empresa", "CIF",
					cifEmpresa);

			alumYemp[0] = nombreAlumno;
			alumYemp[1] = nombreEmpresa;
			return alumYemp;

		}

//		comboBoxEmpresa.setSelectedItem(nombreEmpresa);
//		comboBoxFiltrarEmpresa.setSelectedItem(nombreEmpresa);
//		comboBoxAlumno.setSelectedItem(nombreAlumno);
//		comboBoxFiltrarAlumnos.setSelectedItem(nombreAlumno);

	}
	
    public void setCellsAlignment(JTable table, int alignment)
    {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }
	


}
