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
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerMatchesProfesor extends JFrame {

	private Controlador miControlador;
	private Modelo miModelo;

	private Login miLogin;
	private VerEleccionesDeAlumnos miVerEleccionesDeAlumnos;

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBoxFiltrarEmpresa;
	private JComboBox comboBoxEmpresa;
	private JComboBox comboBoxAlumno;
	private JComboBox comboBoxFiltrarAlumnos;

	public VerEleccionesDeAlumnos getMiVerEleccionesDeAlumnos() {
		return miVerEleccionesDeAlumnos;
	}

	public void setMiVerEleccionesDeAlumnos(VerEleccionesDeAlumnos miVerEleccionesDeAlumnos) {
		this.miVerEleccionesDeAlumnos = miVerEleccionesDeAlumnos;
	}

	public JComboBox getComboBoxFiltrarEmpresa() {
		return comboBoxFiltrarEmpresa;
	}

	public void setComboBoxFiltrarEmpresa(JComboBox comboBoxFiltrarEmpresa) {
		this.comboBoxFiltrarEmpresa = comboBoxFiltrarEmpresa;
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

	public JComboBox getComboBoxFiltrarAlumno() {
		return comboBoxFiltrarAlumnos;
	}

	public void setComboBoxFiltrarAlumno(JComboBox comboBoxFiltrarAlumno) {
		this.comboBoxFiltrarAlumnos = comboBoxFiltrarAlumno;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
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

	public Login getMiLogin() {
		return miLogin;
	}

	public void setMiLogin(Login miLogin) {
		this.miLogin = miLogin;
	}

	public VerMatchesProfesor() {
		setTitle("Ver Matches");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 778, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 25, 675, 194);
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
		scrollPane.setViewportView(table);

		comboBoxFiltrarEmpresa = new JComboBox();
		comboBoxFiltrarEmpresa.setBounds(10, 243, 212, 22);
		contentPane.add(comboBoxFiltrarEmpresa);

		JButton btnFiltrarPorEmpresa = new JButton("Filtrar por empresa");
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
								+ "'" + " and CIF = " + "'" + cifEmpresa + "' and entrevista = 'Si'",
						nombreColumnas);

				table.setModel(miModelo.getTablaEmpresaDefModel());
			}
		});
		btnFiltrarPorEmpresa.setBounds(232, 243, 160, 23);
		contentPane.add(btnFiltrarPorEmpresa);

		comboBoxFiltrarAlumnos = new JComboBox();
		comboBoxFiltrarAlumnos.setBounds(417, 243, 160, 22);
		contentPane.add(comboBoxFiltrarAlumnos);

		JButton btnFiltrarPorAlumno = new JButton("Filtrar por alumno");
		btnFiltrarPorAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombreAlumno = (String) comboBoxFiltrarAlumnos.getSelectedItem();

				String dniAlumno = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("dni", "alumnos", "nombre",
						nombreAlumno);
				String nombreProf = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "profesor", "email",
						miLogin.getTextFieldUsuario().getText());

				String[] nombreColumnas = { "Alumno", "Empresa", "ordenPreferencia" };
				miModelo.TraducirEleccionesAlumnos(
						"Select dni, cif, orden from eleccion_alumno_empresa where profesor =  " + "'" + nombreProf
								+ "'" + " and dni = " + "'" + dniAlumno + "' and entrevista = 'Si'",
						nombreColumnas);

				table.setModel(miModelo.getTablaEmpresaDefModel());
			}
		});
		btnFiltrarPorAlumno.setBounds(603, 243, 149, 23);
		contentPane.add(btnFiltrarPorAlumno);

		comboBoxEmpresa = new JComboBox();
		comboBoxEmpresa.setBounds(28, 301, 364, 22);
		contentPane.add(comboBoxEmpresa);

		comboBoxAlumno = new JComboBox();
		comboBoxAlumno.setBounds(422, 301, 155, 22);
		contentPane.add(comboBoxAlumno);

		JButton btnBorrarMatch = new JButton("Borrar Match");
		btnBorrarMatch.addActionListener(new ActionListener() {
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

				String nombreProf = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "profesor", "email",
						miLogin.getTextFieldUsuario().getText());

				String[] nombreColumnas = { "Alumno", "Empresa", "ordenPreferencia" };
				miModelo.TraducirEleccionesAlumnos(
						"Select dni, cif, orden from eleccion_alumno_empresa where profesor =  " + "'" + nombreProf
								+ "'" + " and entrevista = 'Si'",
						nombreColumnas);
				table.setModel(miModelo.getTablaEmpresaDefModel());

			}
		});
		btnBorrarMatch.setBounds(603, 301, 123, 23);
		contentPane.add(btnBorrarMatch);

		JButton button = new JButton("\u2190");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombreProf = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "profesor", "email",
						miLogin.getTextFieldUsuario().getText());
				
				String [] nombreColumnas = {"Alumno", "Empresa", "ordenPreferencia"};

				miModelo.TraducirEleccionesAlumnos(
						"Select dni, cif, orden from eleccion_alumno_empresa where profesor =  " + "'" + nombreProf
								+ "'",
						nombreColumnas);
				

				miVerEleccionesDeAlumnos.getTable().setModel(miModelo.getTablaEmpresaDefModel());
				
				  comboBoxFiltrarEmpresa.removeAllItems();
				  comboBoxEmpresa.removeAllItems();
				  comboBoxAlumno.removeAllItems();
				 comboBoxFiltrarAlumnos.removeAllItems();

				miControlador.cambiarDePantalla(12, 9);
			}
		});
		button.setBounds(0, 0, 63, 23);
		contentPane.add(button);
	}
}
