package general;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Modelo {
	private String nombreBBDD;
	private String usuario;
	private String contra;
	private Connection miConexion;
	private ArrayList<JFrame> misPantallas = new ArrayList<JFrame>();

	private DefaultTableModel tablaEmpresaDefModel;
	// private String queryTraerTablaEmpresa = "SELECT * FROM
	// porfesor_cuenta_con_estas_empesas where nombre = ";
	private String queryTraerTablaEleccionAlumnos = "SELECT * FROM eleccion_alumno_empresa where dni = ?";
	private String queryTraerTablaAlumnos = "SELECT * FROM alumnos where nombre_profesor = ?";

	public String getQueryTraerTablaAlumnos() {
		return queryTraerTablaAlumnos;
	}

	public void setQueryTraerTablaAlumnos(String queryTraerTablaAlumnos) {
		this.queryTraerTablaAlumnos = queryTraerTablaAlumnos;
	}

	public String getQueryTraerTablaEleccionAlumnos() {
		return queryTraerTablaEleccionAlumnos;
	}

	public void setQueryTraerTablaEleccionAlumnos(String queryTraerTablaEleccionAlumnos) {
		this.queryTraerTablaEleccionAlumnos = queryTraerTablaEleccionAlumnos;
	}

	public DefaultTableModel getTablaEmpresaDefModel() {
		return tablaEmpresaDefModel;
	}

	public void setTablaEmpresaDefModel(DefaultTableModel tablaEmpresaDefModel) {
		this.tablaEmpresaDefModel = tablaEmpresaDefModel;
	}

	/*
	 * public String getQueryTraerTablaEmpresa() { return queryTraerTablaEmpresa; }
	 * 
	 * public void setQueryTraerTablaEmpresa(String queryTraerTablaEmpresa) {
	 * this.queryTraerTablaEmpresa = queryTraerTablaEmpresa; }
	 */

	public ArrayList<JFrame> getMisPantallas() {
		return misPantallas;
	}

	public void setMisPantallas(JFrame pantalla) {

		misPantallas.add(pantalla);

	}

	public void establecerConexion() {
		nombreBBDD = "swingjavapracticasbbdd";
		usuario = "root";
		contra = "";

		try {

			miConexion = DriverManager.getConnection("jdbc:mysql://localhost/" + nombreBBDD
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					usuario, contra);

			System.out.println("conexion existosa");
		} catch (SQLException e) {
			System.out.println("Problema en la conexión");
			e.printStackTrace();
		}
	}

	public ArrayList<String> rellenarCombo() {

		ArrayList<String> listaDeProfesores = new ArrayList<String>();

		String query = "SELECT nombre from profesor";

		try {
			Statement miStatement = miConexion.createStatement();
			ResultSet miResultSet = miStatement.executeQuery(query);

			while (miResultSet.next()) {

				listaDeProfesores.add(miResultSet.getString("nombre"));

			}

		} catch (SQLException e) {
			System.out.println("problema al traer los datos del ComboBox");
			e.printStackTrace();
		}

		return listaDeProfesores;

	}

	public void registrarProfesor(String[] datosRegistro) {

		String query = "INSERT INTO profesor (nombre, apellido1, apellido2, email, dni, año_nac, contraseña, promocion, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 677548749)";
		try {
			PreparedStatement pstmnt = miConexion.prepareStatement(query);

			pstmnt.setString(1, datosRegistro[0]);
			pstmnt.setString(2, datosRegistro[1]);
			pstmnt.setString(3, datosRegistro[2]);
			pstmnt.setString(4, datosRegistro[3]);
			pstmnt.setString(5, datosRegistro[4]);
			pstmnt.setString(6, datosRegistro[5]);
			pstmnt.setString(7, datosRegistro[6]);
			pstmnt.setString(8, datosRegistro[7]);

			pstmnt.executeUpdate();
			pstmnt.close();

			System.out.println("exito al insertar profesor");
		} catch (SQLException e) {
			System.out.println("Insert registro profesor ERROR");
			e.printStackTrace();
		}
	}

	public void registrarAlumno(String[] datosRegistro) {
		String query = "INSERT INTO alumnos (nombre, apellido1, apellido2, email, dni, año_nac, contraseña, promocion, nombre_profesor, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?, 677548749)";
		try {
			PreparedStatement pstmnt = miConexion.prepareStatement(query);

			pstmnt.setString(1, datosRegistro[0]);
			pstmnt.setString(2, datosRegistro[1]);
			pstmnt.setString(3, datosRegistro[2]);
			pstmnt.setString(4, datosRegistro[3]);
			pstmnt.setString(5, datosRegistro[4]);
			pstmnt.setString(6, datosRegistro[5]);
			pstmnt.setString(7, datosRegistro[6]);
			pstmnt.setString(8, datosRegistro[7]);
			pstmnt.setString(9, datosRegistro[8]);

			pstmnt.executeUpdate();
			pstmnt.close();

			System.out.println("exito en al insertar Alumno");
		} catch (SQLException e) {
			System.out.println("Insert registro alumno ERROR");
			e.printStackTrace();
		}

	}

	public int buscarUsuario(String usr, String pwd) {

		try {
			String queryAlum = "select email, contraseña from alumnos where email = ? and contraseña = ?";
			PreparedStatement pstmnt;

			pstmnt = miConexion.prepareStatement(queryAlum);
			pstmnt.setString(1, usr);
			pstmnt.setString(2, pwd);

			ResultSet miResultSet = pstmnt.executeQuery();

			if (!miResultSet.next()) {

				String queryProf = "select email, contraseña from profesor where email = ? and contraseña = ?";
				PreparedStatement PrepareS;

				PrepareS = miConexion.prepareStatement(queryProf);
				PrepareS.setString(1, usr);
				PrepareS.setString(2, pwd);

				ResultSet ResultProf = PrepareS.executeQuery();

				if (!ResultProf.next()) {
					JOptionPane.showMessageDialog(null, "El usuario no está registrado");
					pstmnt.close();
					miResultSet.close();

					PrepareS.close();
					ResultProf.close();

					return 0;

				} else {
					pstmnt.close();
					miResultSet.close();

					PrepareS.close();
					ResultProf.close();

					System.out.println("hola prof");
					return 2;

				}

			} else {

				return 1;

			}

		} catch (SQLException e) {
			System.out.println("Error en el login");
			e.printStackTrace();
		}
		return 0;

	}

	public boolean comprobarSiExisteYaElUsuario(String nombre, String dni) {

		try {
			String queryComprobacion = "select nombre, dni from alumnos where nombre = ? and dni = ?";
			PreparedStatement pstmnt;
			pstmnt = miConexion.prepareStatement(queryComprobacion);
			pstmnt.setString(1, nombre);
			pstmnt.setString(2, dni);
			ResultSet miResultSet = pstmnt.executeQuery();

			if (!miResultSet.next()) {

				String queryComprobacionProf = "select nombre, dni from profesor where nombre = ? and dni = ?";
				PreparedStatement pstmntProf;
				pstmntProf = miConexion.prepareStatement(queryComprobacionProf);
				pstmntProf.setString(1, nombre);
				pstmntProf.setString(2, dni);
				ResultSet miResultSetProf = pstmnt.executeQuery();

				if (!miResultSetProf.next()) {

					return false;

				} else {

					return true;

				}

			} else {

				return false;

			}

		} catch (SQLException e) {
			System.out.println("Fallo en la query de comprobacion de usuario ");
			e.printStackTrace();
			return false;
		}

	}

	public boolean insertarEmpresa(boolean comprobarCif, String[] datosNewEmpresa, String nombreProf) {

		if (comprobarCif) {
			if (datosNewEmpresa[1].equals("") && datosNewEmpresa[2].equals("")) {
				return false;
			}

			String query = "INSERT INTO empresa (CIF, nombre, direccion, comentarios) VALUES (?, ?, ?, ?)";
			String query2 = "INSERT INTO porfesor_cuenta_con_estas_empesas (CIF, nombre) VALUES (?, ?)";

			try {
				PreparedStatement pstmnt = miConexion.prepareStatement(query);

				pstmnt.setString(1, datosNewEmpresa[0]);
				pstmnt.setString(2, datosNewEmpresa[1]);
				pstmnt.setString(3, datosNewEmpresa[2]);
				pstmnt.setString(4, datosNewEmpresa[3]);

				pstmnt.executeUpdate();
				pstmnt.close();

				PreparedStatement pstmnt2 = miConexion.prepareStatement(query2);

				pstmnt2.setString(1, datosNewEmpresa[0]);
				pstmnt2.setString(2, nombreProf);

				pstmnt2.executeUpdate();
				pstmnt2.close();
				System.out.println("exito al insertar");
				JOptionPane.showMessageDialog(null, "Empresa insertada con exito");

				return true;
			} catch (SQLException e) {
				System.out.println("Insert registro empresa ERROR");
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Problema al insertar empresa");
				return false;

			}

		} else {
			JOptionPane.showMessageDialog(null, "El cif de la empresa es incorrecto");
			return false;
		}

	}

	public int saberRol(String usr) {

		try {
			String queryComprobacionRol = "select nombre_profesor from alumnos where email = ?";
			PreparedStatement pstmntRol;
			pstmntRol = miConexion.prepareStatement(queryComprobacionRol);
			pstmntRol.setString(1, usr);
			ResultSet miResultSetRol = pstmntRol.executeQuery();

			if (!miResultSetRol.next()) {

				pstmntRol.close();
				miResultSetRol.close();
				return 4;

			} else {

				pstmntRol.close();
				miResultSetRol.close();

				return 3;

			}

		} catch (SQLException e) {
			System.out.println("Error al saber si es profesor o alumno en la vista ProponerEmpresa");
			e.printStackTrace();
			return 4;

		}

	}

	public void cargarTablas(String queryTraerTablaEmpresa) {
		int numColumnas = getNumColumnas(queryTraerTablaEmpresa);
		int numFilas = getNumFilas(queryTraerTablaEmpresa);

		String[] nombreColumna = new String[numColumnas];

		Object[][] contenidoFilas = new Object[numFilas][numColumnas];
		PreparedStatement pstmt;

		try {
			pstmt = miConexion.prepareStatement(queryTraerTablaEmpresa);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			for (int i = 0; i < numColumnas; i++) {
				nombreColumna[i] = rsmd.getColumnName(i + 1);
			}
			int fila = 0;
			while (rset.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenidoFilas[fila][col - 1] = rset.getString(col);
				}
				fila++;
			}
		} catch (SQLException e) {
			System.out.println("Error al traer la bbdd de Empresa");
			e.printStackTrace();
		}

		tablaEmpresaDefModel = new DefaultTableModel(contenidoFilas, nombreColumna);

	}

	public void cargarTablaAlumnosProfesorEnConcreto(String queryTraerTablaEmpresa, String prof) {
		String query = "SELECT * FROM alumnos where nombre_profesor = " + "'" + prof + "'";

		int numColumnas = getNumColumnas(query);
		int numFilas = getNumFilas(query);

		String[] nombreColumna = new String[numColumnas];

		Object[][] contenidoFilas = new Object[numFilas][numColumnas];
		PreparedStatement pstmt;

		try {
			pstmt = miConexion.prepareStatement(queryTraerTablaEmpresa);
			pstmt.setString(1, prof);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			for (int i = 0; i < numColumnas; i++) {
				nombreColumna[i] = rsmd.getColumnName(i + 1);
			}
			int fila = 0;
			while (rset.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenidoFilas[fila][col - 1] = rset.getString(col);
				}
				fila++;
			}
		} catch (SQLException e) {
			System.out.println("Error al traer la bbdd de Empresa");
			e.printStackTrace();
		}

		tablaEmpresaDefModel = new DefaultTableModel(contenidoFilas, nombreColumna);

	}

	public void cargarTablasConWhen(String queryTraerTablaEmpresa, String dni) {

		// System.out.println(queryTraerTablaEmpresa.substring(0, 50) + "'" + dni + "'"
		// + ";");

		String consultaDef = queryTraerTablaEmpresa.substring(0, 50) + "'" + dni + "'" + ";";

		int numColumnas = getNumColumnas(consultaDef);
		int numFilas = getNumFilas(consultaDef);

		String[] nombreColumna = new String[numColumnas];

		Object[][] contenidoFilas = new Object[numFilas][numColumnas];
		PreparedStatement pstmt;

		try {
			pstmt = miConexion.prepareStatement(queryTraerTablaEmpresa);
			pstmt.setString(1, dni);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			for (int i = 0; i < numColumnas; i++) {
				nombreColumna[i] = rsmd.getColumnName(i + 1);
			}
			int fila = 0;
			while (rset.next()) {
				for (int col = 1; col <= numColumnas; col++) {
					contenidoFilas[fila][col - 1] = rset.getString(col);
				}
				fila++;
			}
		} catch (SQLException e) {
			System.out.println("Error al traer la bbdd de Empresa");
			e.printStackTrace();
		}

		tablaEmpresaDefModel = new DefaultTableModel(contenidoFilas, nombreColumna);

	}

	private int getNumColumnas(String sql) {
		int num = 0;
		try {
			PreparedStatement pstmt = miConexion.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();
			num = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	private int getNumFilas(String sql) {
		int numFilas = 0;
		try {
			PreparedStatement pstmt = miConexion.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
			while (rset.next())
				numFilas++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numFilas;
	}

	public String getCif(String empresaElegida) {

		try {
			String queryGetCif = "select CIF from empresa where nombre = ?";
			PreparedStatement pstmnt;
			pstmnt = miConexion.prepareStatement(queryGetCif);
			pstmnt.setString(1, empresaElegida);
			ResultSet miResultSet = pstmnt.executeQuery();

			miResultSet.next();
			String cifDeLaElegida = miResultSet.getString(1);
			// System.out.println(cifDeLaElegida);

			return cifDeLaElegida;

		} catch (SQLException e) {
			System.out.println("Problema al recuperar el CIF de la empresa");
			e.printStackTrace();
			return "Error";
		}

	}

	public String getNombreEmpresaApartirDeCif(String cif) {

		try {
			String queryGetNombre = "select nombre from empresa where CIF = ?";
			PreparedStatement pstmnt;
			pstmnt = miConexion.prepareStatement(queryGetNombre);
			pstmnt.setString(1, cif);
			ResultSet miResultSet = pstmnt.executeQuery();

			miResultSet.next();
			String nombreEmpresa = miResultSet.getString(1);

			// System.out.println(nombreEmpresa);
			// System.out.println(cifDeLaElegida);

			return nombreEmpresa;

		} catch (SQLException e) {
			System.out.println("Problema al recuperar el Nombre de la empresa");
			e.printStackTrace();
			return "Error";
		}

	}

	public boolean rellenarTablaEleccion_alumno_empresa(String cif, String dni, int orden, String profesor) {

		String ordenStrign = Integer.toString(orden);

		String query = "INSERT INTO eleccion_alumno_empresa (orden, entrevista, CIF, dni, profesor) VALUES (?, ?, ?, ?, ?)";

		try {
			PreparedStatement pstmnt = miConexion.prepareStatement(query);

			pstmnt.setString(1, ordenStrign);
			pstmnt.setString(2, "No");
			pstmnt.setString(3, cif);
			pstmnt.setString(4, dni);
			pstmnt.setString(5, profesor);

			pstmnt.executeUpdate();
			pstmnt.close();

			JOptionPane.showMessageDialog(null, "La empresa se ha añadido correctamente en el orden " + orden);
			return true;
		} catch (SQLException e) {
			System.out.println("Error al insertar en la tabla eleccion_alumno_empresa");
			e.printStackTrace();
			return false;
		}

	}

	public String[] controlDelComboBoxDePreferencias(String dni) {

		try {
			String queryGetOrden = "SELECT orden from eleccion_alumno_empresa where dni = ? ";
			PreparedStatement pstmnt;
			pstmnt = miConexion.prepareStatement(queryGetOrden);
			pstmnt.setString(1, dni);
			ResultSet miResultSet = pstmnt.executeQuery();

			miResultSet.last();
			int cuantos = miResultSet.getRow();

			String[] ordenes = new String[cuantos];

			// int i = 1;

			miResultSet.first();
			ordenes[0] = miResultSet.getString(1);

			for (int j = 1; j < ordenes.length; j++) {
				miResultSet.next();
				// System.out.println(miResultSet.getString(1));
				ordenes[j] = miResultSet.getString(1);
			}

			// System.out.println(Arrays.toString(ordenes));

			return ordenes;

		} catch (SQLException e) {
			System.out.println("Problema al recuperar el orden de preferencia");
			e.printStackTrace();
			return null;
		}

	}

	public void cambiarOrden(String ordenUno, String ordenDos) {

		String query = "UPDATE eleccion_alumno_empresa SET orden = 11 WHERE orden = ? ";
		String query2 = "UPDATE eleccion_alumno_empresa SET orden = ? WHERE orden = ? ";
		String query3 = "UPDATE eleccion_alumno_empresa SET orden = ? WHERE orden = 11 ";

		try {

			PreparedStatement pstmnt = miConexion.prepareStatement(query);
			pstmnt.setString(1, ordenUno);
			pstmnt.executeUpdate();
			pstmnt.close();

			PreparedStatement pstmnt2 = miConexion.prepareStatement(query2);
			pstmnt2.setString(1, ordenUno);
			pstmnt2.setString(2, ordenDos);
			pstmnt2.executeUpdate();
			pstmnt2.close();

			PreparedStatement pstmnt3 = miConexion.prepareStatement(query3);
			pstmnt3.setString(1, ordenDos);
			pstmnt3.executeUpdate();
			pstmnt3.close();

		} catch (SQLException e) {
			System.out.println("Error al cambiar los ordenes de preferencia de las empresas");
			e.printStackTrace();
		}

	}

	public void BorrarAlum(String text) {
		String query = "DELETE FROM alumnos  WHERE nombre = ? ";

		try {
			PreparedStatement pstmnt = miConexion.prepareStatement(query);
			pstmnt.setString(1, text);
			pstmnt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al borrar alumno");
			e.printStackTrace();
		}

	}

	public void borrarLoQueSeaDeUnaBBDD(String query) {

		try {
			PreparedStatement pstmnt = miConexion.prepareStatement(query);
			pstmnt.executeUpdate();
			pstmnt.close();
		} catch (SQLException e) {
			System.out.println("Error al borrar alumno");
			e.printStackTrace();
		}
	}

	// ESTOS DOS METODOS LOS HE CONSEGUIDO METER EN
	// ObtenerUnCampoDeUnaTablaApartirDeOtro

	/*
	 * public String getDniUsr(String email) {
	 * 
	 * try { String queryGetDni = "SELECT dni from alumnos where email = ? ";
	 * PreparedStatement pstmnt; pstmnt = miConexion.prepareStatement(queryGetDni);
	 * pstmnt.setString(1, email); ResultSet miResultSet = pstmnt.executeQuery();
	 * 
	 * miResultSet.next(); String dniDelElegido = miResultSet.getString(1); //
	 * System.out.println(dniDelElegido);
	 * 
	 * return dniDelElegido;
	 * 
	 * } catch (SQLException e) {
	 * System.out.println("Problema al recuperar el DNI del usuario");
	 * e.printStackTrace(); return "Error"; } }
	 */

	/*
	 * public String getNombreDesdeEmail(String email) {
	 * 
	 * try { String queryGetDni = "SELECT nombre from profesor where email = ? ";
	 * PreparedStatement pstmnt; pstmnt = miConexion.prepareStatement(queryGetDni);
	 * pstmnt.setString(1, email); ResultSet miResultSet = pstmnt.executeQuery();
	 * 
	 * miResultSet.next(); String nombreProf = miResultSet.getString(1);
	 * 
	 * return nombreProf;
	 * 
	 * } catch (SQLException e) {
	 * System.out.println("Problema al obtener el nombre del profesor con el email"
	 * ); e.printStackTrace(); return "error"; }
	 * 
	 * }
	 */

	public String ObtenerUnCampoDeUnaTablaApartirDeOtro(String CampoAseleccionar, String tabla, String campoWhere,
			String igualAque) {

		try {
			String queryGetDni = "select " + CampoAseleccionar + " from " + tabla + " where " + campoWhere + " = '"
					+ igualAque + "'";
			PreparedStatement pstmnt;
			pstmnt = miConexion.prepareStatement(queryGetDni);
			// pstmnt.setString(1, CampoAseleccionar);
			// pstmnt.setString(2, tabla);
			// pstmnt.setString(3, campoWhere);
			// pstmnt.setString(4, igualAque);
			ResultSet miResultSet = pstmnt.executeQuery();

			miResultSet.next();
			String miResultado = miResultSet.getString(1);

			return miResultado;

		} catch (SQLException e) {
			System.out.println("Problema al obtener el campo solictiado");
			e.printStackTrace();
			return "error";
		}

	}

	public ArrayList<String> traerUnaColumnaDeUnaTabla(String query) {

		ArrayList<String> listaAlumnos = new ArrayList<String>();

		try {

			PreparedStatement pstmnt;
			pstmnt = miConexion.prepareStatement(query);

			ResultSet miResultSet = pstmnt.executeQuery();

			while (miResultSet.next()) {
				String alumno = miResultSet.getString(1);
				listaAlumnos.add(alumno);
			}

			return listaAlumnos;

		} catch (SQLException e) {
			System.out.println("Error al traer los alumnos al combobox");
			e.printStackTrace();
			return null;
		}

	}

	public void actualizarUnCampoDeUnaTabla(String query) {
		try {
			PreparedStatement pstmnt = miConexion.prepareStatement(query);
			pstmnt.executeUpdate();
			pstmnt.close();

		} catch (SQLException e) {
			System.out.println("Error cuando el profesor hace los matches");
			e.printStackTrace();
		}

	}

	// este metodo sería interesante hacerle de tal forma que pueda valer en
	// cualquier tabla y tal

	public void CargarTablasTraducidas(String query, int numColumnas, String[] nombCol,
			String nombreQueQuieresPonerDeOtraTablaEnVezDeElQueSaldria,
			String nombreDeLaTablaEnLaQueEstaElDatoQueQueremos, String condicionWhere,
			String nombreQueQuieresPonerDeOtraTablaEnVezDeElQueSaldria2,
			String nombreDeLaTablaEnLaQueEstaElDatoQueQueremos2, String condicionWhere2) {

		// int numColumnas = getNumColumnas(queryTraerTablaEmpresa);
		int numFilas = getNumFilas(query);

		// String[] nombreColumna = new String[numColumnas];

		Object[][] contenidoFilas = new Object[numFilas][numColumnas];
		PreparedStatement pstmt;

		try {
			pstmt = miConexion.prepareStatement(query);
			ResultSet rset = pstmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();

			int fila = 0;
			String mismaFilaColumnaAnterior = "";
			String nombreDeLaTablaDespues = "";
			while (rset.next()) {
				for (int col = 1; col <= numColumnas; col++) {

					if (col == 1) {

						mismaFilaColumnaAnterior = rset.getString(col);
						nombreDeLaTablaDespues = ObtenerUnCampoDeUnaTablaApartirDeOtro(
								nombreQueQuieresPonerDeOtraTablaEnVezDeElQueSaldria,
								nombreDeLaTablaEnLaQueEstaElDatoQueQueremos, condicionWhere, mismaFilaColumnaAnterior);
					} else {
						mismaFilaColumnaAnterior = rset.getString(col);
						nombreDeLaTablaDespues = ObtenerUnCampoDeUnaTablaApartirDeOtro(
								nombreQueQuieresPonerDeOtraTablaEnVezDeElQueSaldria2,
								nombreDeLaTablaEnLaQueEstaElDatoQueQueremos2, condicionWhere2,
								mismaFilaColumnaAnterior);
					}

					contenidoFilas[fila][col - 1] = nombreDeLaTablaDespues;
				}
				fila++;
			}
		} catch (SQLException e) {
			System.out.println("Error al traer la bbdd de Empresa");
			e.printStackTrace();
		}

		tablaEmpresaDefModel = new DefaultTableModel(contenidoFilas, nombCol);

	}

	public String[] TraerMatches(String query) {

		int numFilas = getNumFilas(query);
		String misMatches[] = new String[numFilas];
		try {

			PreparedStatement pstmnt;
			pstmnt = miConexion.prepareStatement(query);
			ResultSet miResultSet = pstmnt.executeQuery();
			int i = 0;
			while (miResultSet.next()) {
				String cif = miResultSet.getString(1);
				misMatches[i] = ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "empresa", "CIF", cif);
				i++;
			}

			return misMatches;
		} catch (SQLException e) {
			System.out.println("Error al traer los matches");
			e.printStackTrace();
			return null;
		}
	}

	public void traerCifYempresa(String query, String[] nombreColumna) {

		int numFilas = getNumFilas(query);
		Object[][] contenidoFilas = new Object[numFilas][nombreColumna.length];
		try {

			PreparedStatement pstmnt;
			pstmnt = miConexion.prepareStatement(query);
			ResultSet rset = pstmnt.executeQuery();

			ResultSetMetaData rsmd = rset.getMetaData();

			int fila = 0;
			while (rset.next()) {
				for (int col = 1; col <= nombreColumna.length; col++) {
					if (col == 2) {
						String changeAux = rset.getString(1);

						String NuevoValor = ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "empresa", "CIF",
								changeAux);

						contenidoFilas[fila][col - 1] = NuevoValor;
					} else {
						contenidoFilas[fila][col - 1] = rset.getString(col);
					}

				}
				fila++;
			}

			/*
			 * while (miResultSet.next()) { String cif = miResultSet.getString(1);
			 * misMatches[i] = ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "empresa",
			 * "CIF", cif); i++; }
			 */

		} catch (SQLException e) {
			System.out.println("Error al traer los matches");
			e.printStackTrace();

		}

		tablaEmpresaDefModel = new DefaultTableModel(contenidoFilas, nombreColumna);

	}

	public void TraducirEleccionesAlumnos(String query, String[] nombreColumna) {

		int numFilas = getNumFilas(query);
		Object[][] contenidoFilas = new Object[numFilas][nombreColumna.length];
		try {

			PreparedStatement pstmnt;
			pstmnt = miConexion.prepareStatement(query);
			ResultSet rset = pstmnt.executeQuery();

			ResultSetMetaData rsmd = rset.getMetaData();

			int fila = 0;
			while (rset.next()) {
				for (int col = 1; col <= nombreColumna.length; col++) {
					if (col == 1) {
						String changeAux = rset.getString(col);

						String NuevoValor = ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "alumnos", "dni",
								changeAux);
						String NuevoValor2 = ObtenerUnCampoDeUnaTablaApartirDeOtro("apellido1", "alumnos", "dni",
								changeAux);

						contenidoFilas[fila][col - 1] = NuevoValor + " " + NuevoValor2;
					} else if (col == 2) {
						String changeAuxEmp = rset.getString(col);

						String NuevoValor = ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "empresa", "CIF",
								changeAuxEmp);
						contenidoFilas[fila][col - 1] = NuevoValor;
					} else {
						contenidoFilas[fila][col - 1] = rset.getString(col);
					}

				}
				fila++;
			}

		} catch (SQLException e) {
			System.out.println("Error al traer los matches");
			e.printStackTrace();

		}

		tablaEmpresaDefModel = new DefaultTableModel(contenidoFilas, nombreColumna);

	}

	public boolean comprobarElecciones(String query) {

		boolean yaSeHaHecho = false;

		try {
			PreparedStatement pstmnt;
			pstmnt = miConexion.prepareStatement(query);
			ResultSet rs = pstmnt.executeQuery();

			int cont = 0;
			while (rs.next()) {
				cont++;
			}

			if (cont > 0) {

				yaSeHaHecho = true;
			}

			return yaSeHaHecho;

		} catch (SQLException e) {
			System.out.println("probelams al comprobar si existe el match");
			e.printStackTrace();
			return true;
		}

	}

	public boolean traerNumeroDeMatches(String query) {

		boolean hay3 = false;
		try {
			PreparedStatement pstmnt;
			pstmnt = miConexion.prepareStatement(query);
			ResultSet rs = pstmnt.executeQuery();

			int cont = 0;
			while (rs.next()) {
				cont++;
			}

			if (cont == 3) {

				hay3 = true;
			}

			return hay3;

		} catch (SQLException e) {
			System.out.println("problemas al contar el numero de matches de un alumno");
			e.printStackTrace();
			return true;
		}
	}

}
