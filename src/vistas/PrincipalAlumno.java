package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import general.Controlador;
import general.Modelo;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class PrincipalAlumno extends JFrame {

	private Controlador miControlador;
	private Modelo miModelo;

	private ElegirEmpresas miElegirEmpresas;
	private Login miLogin;
	private VerMatchesAlumno miVerMatchesAlumno;

	private JPanel contentPane;

	public VerMatchesAlumno getMiVerMatchesAlumno() {
		return miVerMatchesAlumno;
	}

	public void setMiVerMatchesAlumno(VerMatchesAlumno miVerMatchesAlumno) {
		this.miVerMatchesAlumno = miVerMatchesAlumno;
	}

	public Login getMiLogin() {
		return miLogin;
	}

	public void setMiLogin(Login miLogin) {
		this.miLogin = miLogin;
	}

	public ElegirEmpresas getMiElegirEmpresas() {
		return miElegirEmpresas;
	}

	public void setMiElegirEmpresas(ElegirEmpresas miElegirEmpresas) {
		this.miElegirEmpresas = miElegirEmpresas;
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

	public PrincipalAlumno() {
		setTitle("Principal Alumno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnElegirEmpresas = new JButton("Elegir empresas");
		btnElegirEmpresas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// String dniUsr = miModelo.getDniUsr(miLogin.getTextFieldUsuario().getText());

				String dniUsr = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("dni", "alumnos", "email",
						miLogin.getTextFieldUsuario().getText());

				String profesor = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre_profesor", "alumnos", "email",
						miLogin.getTextFieldUsuario().getText());

//				miModelo.cargarTablas(
//						"SELECT * FROM porfesor_cuenta_con_estas_empesas where nombre = " + "'" + profesor + "'");
				
				String [] nombCol = {"Empresa"}; 

				miModelo.CargarTablasTraducidas(
						"SELECT * FROM porfesor_cuenta_con_estas_empesas where nombre = " + "'" + profesor + "'",
						1, nombCol, "nombre",
						"empresa", "CIF",
						"dni",
						"profesor", "nombre");
				

				miElegirEmpresas.getTable().setModel(miModelo.getTablaEmpresaDefModel());
				
				miControlador.setCellsAlignment(miElegirEmpresas.getTable(), SwingConstants.CENTER);

				try {

					String[] ordenes = miModelo.controlDelComboBoxDePreferencias(dniUsr);

					if (ordenes.length > 0) {
						for (int i = 0; i < ordenes.length; i++) {
							miElegirEmpresas.getComboBoxOrdenEmp().removeItem(ordenes[i]);
						}

					}

					miControlador.cambiarDePantalla(3, 6);

				} catch (Exception e2) {
					System.out.println("Aun no ha elegido ningun orden");
					miControlador.cambiarDePantalla(3, 6);

				}

			}
		});
		btnElegirEmpresas.setBounds(42, 77, 146, 23);
		contentPane.add(btnElegirEmpresas);

		JButton btnVerMatches = new JButton("ver matches");
		btnVerMatches.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dniUsr = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("dni", "alumnos", "email",
						miLogin.getTextFieldUsuario().getText());

				// miModelo.TraerMatches("Select CIF from eleccion_alumno_empresa where dni = "
				// + "'"+ dniUsr + "'");

				String[] entrevistas = miModelo.TraerMatches("Select CIF from eleccion_alumno_empresa where dni = "
						+ "'" + dniUsr + "'" + "and entrevista = 'Si'");

				if (entrevistas.length == 1) {
					miVerMatchesAlumno.getLblMatchUno().setText(entrevistas[0]);

				} else if (entrevistas.length == 2) {
					miVerMatchesAlumno.getLblMatchUno().setText(entrevistas[0]);
					miVerMatchesAlumno.getLblMatchDos().setText(entrevistas[1]);
				} else if (entrevistas.length == 3) {
					miVerMatchesAlumno.getLblMatchUno().setText(entrevistas[0]);
					miVerMatchesAlumno.getLblMatchDos().setText(entrevistas[1]);
					miVerMatchesAlumno.getLblMatchTres().setText(entrevistas[2]);
				}

				miControlador.cambiarDePantalla(3, 11);

			}
		});
		btnVerMatches.setBounds(249, 77, 133, 23);
		contentPane.add(btnVerMatches);

		JButton btnProponerEmpresa = new JButton("Proponer empresa");
		btnProponerEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cambiarDePantalla(3, 5);
			}
		});
		btnProponerEmpresa.setBounds(143, 153, 161, 23);
		contentPane.add(btnProponerEmpresa);
	}
}
