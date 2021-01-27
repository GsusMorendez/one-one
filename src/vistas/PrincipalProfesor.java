package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import general.Controlador;
import general.Modelo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PrincipalProfesor extends JFrame {
	private Controlador miControlador;
	private Modelo miModelo;

	private VerAlumnos miVerAlumnos;
	private Login miLogin;
	private VerEleccionesDeAlumnos miVerEleccionesDeAlumnos;
	private VerEmpresas miVerEmpresas;

	private JPanel contentPane;

	public VerEmpresas getMiVerEmpresas() {
		return miVerEmpresas;
	}

	public void setMiVerEmpresas(VerEmpresas miVerEmpresas) {
		this.miVerEmpresas = miVerEmpresas;
	}

	public VerEleccionesDeAlumnos getMiVerEleccionesDeAlumnos() {
		return miVerEleccionesDeAlumnos;
	}

	public void setMiVerEleccionesDeAlumnos(VerEleccionesDeAlumnos miVerEleccionesDeAlumnos) {
		this.miVerEleccionesDeAlumnos = miVerEleccionesDeAlumnos;
	}

	public Login getMiLogin() {
		return miLogin;
	}

	public void setMiLogin(Login miLogin) {
		this.miLogin = miLogin;
	}

	public VerAlumnos getMiVerAlumnos() {
		return miVerAlumnos;
	}

	public void setMiVerAlumnos(VerAlumnos miVerAlumnos) {
		this.miVerAlumnos = miVerAlumnos;
	}

	public Controlador getMiControlador() {
		return miControlador;
	}

	public void setMiControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public Modelo getMiModelo() {
		return miModelo;
	}

	public void setMiModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public PrincipalProfesor() {
		setTitle("Principal Profesor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnVerEleccionesDe = new JButton("ver elecciones / hacer matches");
		btnVerEleccionesDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombreProf = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "profesor", "email",
						miLogin.getTextFieldUsuario().getText());
				
				String [] nombreColumnas = {"Alumno", "Empresa", "ordenPreferencia"};
				
				miModelo.TraducirEleccionesAlumnos("Select dni, cif, orden from eleccion_alumno_empresa where profesor =  " + "'"
						+ nombreProf + "'", nombreColumnas);;

				miVerEleccionesDeAlumnos.getTable().setModel(miModelo.getTablaEmpresaDefModel());

				ArrayList<String> listaAlumnos = miModelo.traerUnaColumnaDeUnaTabla(
						"Select nombre from alumnos where nombre_profesor = " + "'" + nombreProf + "'");

				for (String alumno : listaAlumnos) {
					miVerEleccionesDeAlumnos.getComboBoxAlumno().addItem(alumno);
					miVerEleccionesDeAlumnos.getComboBoxFiltrarAlumnos().addItem(alumno);
				}

				ArrayList<String> listaEmpresas = miModelo.traerUnaColumnaDeUnaTabla(
						"Select CIF from porfesor_cuenta_con_estas_empesas where nombre = " + "'" + nombreProf + "'");

				for (String empresa : listaEmpresas) {

					// miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "empresa", "CIF",
					// empresa);
					
					

					miVerEleccionesDeAlumnos.getComboBoxEmpresa().addItem(
							miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "empresa", "CIF", empresa));
					miVerEleccionesDeAlumnos.getComboBoxFiltrarEmpresa().addItem(
							miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "empresa", "CIF", empresa));

				}

				miControlador.cambiarDePantalla(4, 9);

			}
		});
		btnVerEleccionesDe.setBounds(104, 32, 217, 23);
		contentPane.add(btnVerEleccionesDe);

		JButton btnVerAlumnos = new JButton("Ver alumnos");
		btnVerAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// String nombreProf =
				// miModelo.getNombreDesdeEmail(miLogin.getTextFieldUsuario().getText());

				String nombreProf = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "profesor", "email",
						miLogin.getTextFieldUsuario().getText());

				// miModelo.cargarTablaAlumnosProfesorEnConcreto(miModelo.getQueryTraerTablaAlumnos(),
				// nombreProf);

				miModelo.cargarTablas(
						"Select dni, nombre, apellido1, apellido2, email, telefono, promocion, año_nac from alumnos where nombre_profesor = "
								+ "'" + nombreProf + "'");
				miVerAlumnos.getTable().setModel(miModelo.getTablaEmpresaDefModel());

				miControlador.cambiarDePantalla(4, 8);
			}
		});
		btnVerAlumnos.setBounds(104, 142, 217, 23);
		contentPane.add(btnVerAlumnos);

		JButton btnIntroducirEmpresa = new JButton("Introducir empresa");
		btnIntroducirEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cambiarDePantalla(4, 5);
			}
		});
		btnIntroducirEmpresa.setBounds(104, 202, 217, 23);
		contentPane.add(btnIntroducirEmpresa);

		JButton btnVerEmpresas = new JButton("Ver empresas");
		btnVerEmpresas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombreProf = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "profesor", "email",
						miLogin.getTextFieldUsuario().getText());
				String[] nombreColumnas = { "CIF", "Empresa" };

				miModelo.traerCifYempresa(
						"Select * from porfesor_cuenta_con_estas_empesas where nombre = " + "'" + nombreProf + "'",
						nombreColumnas);

				miVerEmpresas.getTable().setModel(miModelo.getTablaEmpresaDefModel());

				miControlador.cambiarDePantalla(4, 10);

			}
		});
		btnVerEmpresas.setBounds(104, 86, 217, 23);
		contentPane.add(btnVerEmpresas);
	}
}
