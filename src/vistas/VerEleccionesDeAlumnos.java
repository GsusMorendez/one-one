package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import general.Controlador;
import general.Modelo;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Font;

public class VerEleccionesDeAlumnos extends JFrame {

	private Controlador miControlador;
	private Modelo miModelo;

	private Login miLogin;
	private VerMatchesProfesor miVerMatchesProfesor;

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBoxFiltrarAlumnos;
	private JComboBox comboBoxEmpresa;
	private JComboBox comboBoxAlumno;
	private JComboBox comboBoxFiltrarEmpresa;
	private JButton btnFiltrar;
	private JButton btnFiltrarPorEmpresa;

	public VerMatchesProfesor getMiVerMatchesProfesor() {
		return miVerMatchesProfesor;
	}

	public void setMiVerMatchesProfesor(VerMatchesProfesor miVerMatchesProfesor) {
		this.miVerMatchesProfesor = miVerMatchesProfesor;
	}

	public Login getMiLogin() {
		return miLogin;
	}

	public void setMiLogin(Login miLogin) {
		this.miLogin = miLogin;
	}

	public JComboBox getComboBoxFiltrarEmpresa() {
		return comboBoxFiltrarEmpresa;
	}

	public void setComboBoxFiltrarEmpresa(JComboBox comboBoxFiltrarEmpresa) {
		this.comboBoxFiltrarEmpresa = comboBoxFiltrarEmpresa;
	}

	public JComboBox getComboBoxFiltrarAlumnos() {
		return comboBoxFiltrarAlumnos;
	}

	public void setComboBoxFiltrarAlumnos(JComboBox comboBoxFiltrarAlumnos) {
		this.comboBoxFiltrarAlumnos = comboBoxFiltrarAlumnos;
	}

	public JComboBox getComboBoxEmpresa() {
		return comboBoxEmpresa;
	}

	public void setComboBoxEmpresa(JComboBox comboBoxEmpresa) {
		this.comboBoxEmpresa = comboBoxEmpresa;
	}

	public JComboBox getComboBoxAlumno() {
		return comboBoxAlumno;
	}

	public void setComboBoxAlumno(JComboBox comboBoxAlumno) {
		this.comboBoxAlumno = comboBoxAlumno;
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

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public VerEleccionesDeAlumnos() {
		setTitle("Ver elecci\u00F3n alumnos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 756, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 29, 689, 175);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int numRow = table.getSelectedRow();
				// int numCol = table.getSelectedColumn();
				String[] rowChoosen = new String[3];

				for (int i = 0; i < rowChoosen.length; i++) {
					if (i == 0) {
						String nombreYappellido = (String) miModelo.getTablaEmpresaDefModel().getValueAt(numRow, i);

						String[] nombreCompleto = nombreYappellido.split(" ");

						String nombre = nombreCompleto[0];

						rowChoosen[i] = nombre;

					} else {
						rowChoosen[i] = (String) miModelo.getTablaEmpresaDefModel().getValueAt(numRow, i);
					}

				}

				String empresa = rowChoosen[1];
				String alumno = rowChoosen[0];

				comboBoxEmpresa.setSelectedItem(empresa);
				comboBoxFiltrarEmpresa.setSelectedItem(empresa);

				comboBoxAlumno.setSelectedItem(alumno);
				comboBoxFiltrarAlumnos.setSelectedItem(alumno);

			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		JButton button = new JButton("\u2190");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				comboBoxFiltrarAlumnos.removeAllItems();
				comboBoxEmpresa.removeAllItems();
				comboBoxAlumno.removeAllItems();
				comboBoxFiltrarEmpresa.removeAllItems();
				miControlador.cambiarDePantalla(9, 4);
			}
		});
		button.setBounds(0, 0, 59, 23);
		contentPane.add(button);

		comboBoxAlumno = new JComboBox();
		comboBoxAlumno.setBounds(10, 302, 154, 22);
		contentPane.add(comboBoxAlumno);

		comboBoxEmpresa = new JComboBox();
		comboBoxEmpresa.setBounds(185, 302, 216, 22);
		contentPane.add(comboBoxEmpresa);

		JButton btnMatch = new JButton("Match");
		btnMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreEmpresa = (String) comboBoxEmpresa.getSelectedItem();
				String nombreAlumno = (String) comboBoxAlumno.getSelectedItem();

				String cifEmpresa = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("CIF", "empresa", "nombre",
						nombreEmpresa);

				String dniAlumno = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("dni", "alumnos", "nombre",
						nombreAlumno);

				boolean existeElmatch = miModelo
						.comprobarElecciones("Select entrevista from eleccion_alumno_empresa where dni = " + "'"
								+ dniAlumno + "'" + " and CIF = " + "'" + cifEmpresa + "'" + "and entrevista = 'Si'");

				boolean hayMasDeTres = miModelo
						.traerNumeroDeMatches("Select entrevista from eleccion_alumno_empresa where dni = " + "'"
								+ dniAlumno + "'" + " and entrevista = 'Si' ");

				if (existeElmatch) {
					JOptionPane.showMessageDialog(null, " Error: el match ya existe");
				} else if (hayMasDeTres) {
					JOptionPane.showMessageDialog(null, "Error: este alumno ya tiene 3 entrevistas planificadas");

				} else if (existeElmatch && hayMasDeTres) {
					JOptionPane.showMessageDialog(null,
							"Error: el match ya existe y el alumno ya tiene 3 entrevistas planificadas");

				} else if (!existeElmatch && !hayMasDeTres) {

					// hay que ver si el alumno ha hecho esa eleccion de empresa si no lo ha hecho
					// no existirá ese insert en eleccion_alumno_empresa
					// y o bien hay que crear el insert o avisar de que el alumno no ha hecho esa
					// eleccion
					// o sacar un cuadro de dialogo que puedas elegir si o no

					// El codigo podría ir aqui dentro de un if

//					JOptionPane.showConfirmDialog(null,
//							"El alumno no tiene esa empresa entre sus preferncias, ¿desea continuar haciendo el match?",
//							"Confirmacion de match", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);

					// devuelve 0 si es si y 1 si es no ahi saco otro if y fuera

					Boolean existeElInsert = miModelo
							.comprobarElecciones("Select entrevista from eleccion_alumno_empresa where dni = " + "'"
									+ dniAlumno + "'" + " and CIF = " + "'" + cifEmpresa + "'");

					if (!existeElInsert) {
						JOptionPane.showMessageDialog(null,
								"No se puede realizar el match porque la empresa no está entre las elecciones del alumno");
					} else {
						System.out.println(dniAlumno);
						System.out.println(cifEmpresa);

						miModelo.actualizarUnCampoDeUnaTabla(
								"UPDATE eleccion_alumno_empresa SET entrevista = 'Si' where dni = " + "'" + dniAlumno
										+ "'" + " and CIF = " + "'" + cifEmpresa + "'");

						String nombreProf = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "profesor",
								"email", miLogin.getTextFieldUsuario().getText());

						String[] nombreColumnas = { "Alumno", "Empresa", "ordenPreferencia" };
						miModelo.TraducirEleccionesAlumnos(
								"Select dni, cif, orden from eleccion_alumno_empresa where profesor =  " + "'"
										+ nombreProf + "'",
								nombreColumnas);

						table.setModel(miModelo.getTablaEmpresaDefModel());

						JOptionPane.showMessageDialog(null, "Match realizado con éxito");
					}

				}

			}
		});
		btnMatch.setBounds(417, 302, 89, 23);
		contentPane.add(btnMatch);

		comboBoxFiltrarAlumnos = new JComboBox();
		comboBoxFiltrarAlumnos.setBounds(418, 249, 88, 22);
		contentPane.add(comboBoxFiltrarAlumnos);

		btnFiltrar = new JButton("Filtrar por Alumno");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombreAlumno = (String) comboBoxFiltrarAlumnos.getSelectedItem();

				String dniAlumno = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("dni", "alumnos", "nombre",
						nombreAlumno);
				String nombreProf = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "profesor", "email",
						miLogin.getTextFieldUsuario().getText());

				String[] nombreColumnas = { "Alumno", "Empresa", "ordenPreferencia" };
				miModelo.TraducirEleccionesAlumnos(
						"Select dni, cif, orden from eleccion_alumno_empresa where profesor =  " + "'" + nombreProf
								+ "'" + " and dni = " + "'" + dniAlumno + "'",
						nombreColumnas);

				table.setModel(miModelo.getTablaEmpresaDefModel());

			}
		});
		btnFiltrar.setBounds(536, 249, 141, 23);
		contentPane.add(btnFiltrar);

		comboBoxFiltrarEmpresa = new JComboBox();
		comboBoxFiltrarEmpresa.setBounds(29, 249, 191, 22);
		contentPane.add(comboBoxFiltrarEmpresa);

		btnFiltrarPorEmpresa = new JButton("Filtrar por Empresa");
		btnFiltrarPorEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombreProf = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "profesor", "email",
						miLogin.getTextFieldUsuario().getText());

				String nombreEmpresa = (String) getComboBoxFiltrarEmpresa().getSelectedItem();

				String cifEmpresa = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("CIF", "empresa", "nombre",
						nombreEmpresa);

				String[] nombreColumnas = { "Alumno", "Empresa", "ordenPreferencia" };
				miModelo.TraducirEleccionesAlumnos(
						"Select dni, cif, orden from eleccion_alumno_empresa where profesor =  " + "'" + nombreProf
								+ "'" + " and CIF = " + "'" + cifEmpresa + "'",
						nombreColumnas);

				table.setModel(miModelo.getTablaEmpresaDefModel());

			}
		});
		btnFiltrarPorEmpresa.setBounds(230, 249, 167, 23);
		contentPane.add(btnFiltrarPorEmpresa);

		JButton btnBorrrarMatch = new JButton("Borrrar match");
		btnBorrrarMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreEmpresa = (String) comboBoxEmpresa.getSelectedItem();
				String nombreAlumno = (String) comboBoxAlumno.getSelectedItem();
				String cifEmpresa = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("CIF", "empresa", "nombre",
						nombreEmpresa);
				String dniAlumno = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("dni", "alumnos", "nombre",
						nombreAlumno);

				boolean existeParaBorrarlo = miModelo
						.comprobarElecciones("select * from eleccion_alumno_empresa where dni =  " + "'" + dniAlumno
								+ "'" + " and CIF = " + "'" + cifEmpresa + "'" + " and entrevista = 'Si'");

				if (existeParaBorrarlo) {
					miModelo.actualizarUnCampoDeUnaTabla(
							"UPDATE eleccion_alumno_empresa SET entrevista = 'No' WHERE CIF = " + "'" + cifEmpresa + "'"
									+ " and dni = " + "'" + dniAlumno + "'");
					JOptionPane.showMessageDialog(null, "Éxito al eliminar");
				} else {
					JOptionPane.showMessageDialog(null, "El match no se puede borrar porque no existe");

				}

			}
		});
		btnBorrrarMatch.setBounds(536, 302, 123, 23);
		contentPane.add(btnBorrrarMatch);

		JButton btnVerTodosLos = new JButton("Ver todos los matches");
		btnVerTodosLos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombreAlumno = (String) comboBoxFiltrarAlumnos.getSelectedItem();

				String dniAlumno = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("dni", "alumnos", "nombre",
						nombreAlumno);

				String nombreProf = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "profesor", "email",
						miLogin.getTextFieldUsuario().getText());

				String[] nombreColumnas = { "Alumno", "Empresa", "ordenPreferencia" };
				miModelo.TraducirEleccionesAlumnos(
						"Select dni, cif, orden from eleccion_alumno_empresa where profesor =  " + "'" + nombreProf
								+ "'" + " and entrevista = 'Si'",
						nombreColumnas);
				miVerMatchesProfesor.getTable().setModel(miModelo.getTablaEmpresaDefModel());

				ArrayList<String> listaAlumnos = miModelo.traerUnaColumnaDeUnaTabla(
						"Select nombre from alumnos where nombre_profesor = " + "'" + nombreProf + "'");

				for (String alumno : listaAlumnos) {
					miVerMatchesProfesor.getComboBoxAlumno().addItem(alumno);
					miVerMatchesProfesor.getComboBoxFiltrarAlumno().addItem(alumno);
				}

				ArrayList<String> listaEmpresas = miModelo.traerUnaColumnaDeUnaTabla(
						"Select CIF from porfesor_cuenta_con_estas_empesas where nombre = " + "'" + nombreProf + "'");

				for (String empresa : listaEmpresas) {

					// miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "empresa", "CIF",
					// empresa);

					miVerMatchesProfesor.getComboBoxEmpresa().addItem(
							miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "empresa", "CIF", empresa));
					miVerMatchesProfesor.getComboBoxFiltrarEmpresa().addItem(
							miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "empresa", "CIF", empresa));

				}

				miControlador.cambiarDePantalla(9, 12);

			}
		});
		btnVerTodosLos.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		btnVerTodosLos.setForeground(new Color(0, 0, 0));
		btnVerTodosLos.setBounds(291, 341, 179, 23);
		contentPane.add(btnVerTodosLos);
	}
}
