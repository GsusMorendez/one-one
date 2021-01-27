package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import general.Controlador;
import general.Modelo;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

public class VerElegidas extends JFrame {

	private Controlador miControlador;
	private Modelo miModelo;

	private Login miLogin;
	private ElegirEmpresas miElegirEmpresas;

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldNombreEmpresa;
	private JComboBox comboBoxOrdenActual;
	private JComboBox comboBoxOrdenFinal;

	public ElegirEmpresas getMiElegirEmpresas() {
		return miElegirEmpresas;
	}

	public void setMiElegirEmpresas(ElegirEmpresas miElegirEmpresas) {
		this.miElegirEmpresas = miElegirEmpresas;
	}

	public Login getMiLogin() {
		return miLogin;
	}

	public void setMiLogin(Login miLogin) {
		this.miLogin = miLogin;
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

	public VerElegidas() {
		setTitle("Ver elegidas por el alumno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 586, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 31, 517, 223);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int numRow = table.getSelectedRow();
				// int numCol = table.getSelectedColumn();

				String[] companyChoosen = new String[2];

				for (int i = 0; i < companyChoosen.length; i++) {
					companyChoosen[i] = (String) miModelo.getTablaEmpresaDefModel().getValueAt(numRow, i);
					// System.out.println(" el dato numero " + i + companyChoosen[i]);
				}

				textFieldNombreEmpresa.setText(companyChoosen[1]);
				comboBoxOrdenActual.removeAllItems();
				comboBoxOrdenActual.addItem(companyChoosen[0]);

				comboBoxOrdenFinal.removeAllItems();

				for (int j = 1; j < 11; j++) {
					comboBoxOrdenFinal.addItem(j);
				}
				comboBoxOrdenFinal.removeItem(Integer.parseInt(companyChoosen[0]));

			}
		});
		scrollPane.setViewportView(table);

		JButton button = new JButton("\u2190");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String dniUsr = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("dni", "alumnos", "email",
						miLogin.getTextFieldUsuario().getText());

				String profesor = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre_profesor", "alumnos", "email",
						miLogin.getTextFieldUsuario().getText());

				String[] nombCol = { "Empresa" };

				miModelo.CargarTablasTraducidas(
						"SELECT * FROM porfesor_cuenta_con_estas_empesas where nombre = " + "'" + profesor + "'", 1,
						nombCol, "nombre", "empresa", "CIF", "dni", "profesor", "nombre");

				miElegirEmpresas.getTable().setModel(miModelo.getTablaEmpresaDefModel());

				miControlador.setCellsAlignment(miElegirEmpresas.getTable(), SwingConstants.CENTER);

				try {

					String[] ordenes = miModelo.controlDelComboBoxDePreferencias(dniUsr);

					if (ordenes.length > 0) {
						for (int i = 0; i < ordenes.length; i++) {
							miElegirEmpresas.getComboBoxOrdenEmp().removeItem(ordenes[i]);
						}

					}

				} catch (Exception e2) {
					System.out.println("Aun no ha elegido ningun orden");

				}

				miControlador.cambiarDePantalla(7, 6);
			}
		});
		button.setBounds(0, 0, 53, 23);
		contentPane.add(button);

		textFieldNombreEmpresa = new JTextField();
		textFieldNombreEmpresa.setBounds(35, 288, 86, 20);
		contentPane.add(textFieldNombreEmpresa);
		textFieldNombreEmpresa.setColumns(10);

		comboBoxOrdenActual = new JComboBox();
		comboBoxOrdenActual.setBounds(202, 287, 53, 22);
		contentPane.add(comboBoxOrdenActual);

		comboBoxOrdenFinal = new JComboBox();
		comboBoxOrdenFinal
				.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
		comboBoxOrdenFinal.setBounds(320, 287, 53, 22);
		contentPane.add(comboBoxOrdenFinal);

		JButton btnCambiarOrden = new JButton("Cambiar Orden");
		btnCambiarOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ordenUno = (String) comboBoxOrdenActual.getSelectedItem();
				int ordenDosInt = (int) comboBoxOrdenFinal.getSelectedItem();
				String ordenDos = Integer.toString(ordenDosInt);

				if (textFieldNombreEmpresa.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Debes pinchar una empresa para cambiarla");
				} else {
					miModelo.cambiarOrden(ordenUno, ordenDos);

					// String dniUsr = miModelo.getDniUsr(miLogin.getTextFieldUsuario().getText());
					String dniUsr = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("dni", "alumnos", "email",
							miLogin.getTextFieldUsuario().getText());

					String[] nombCol = { "Orden", "Nombre" };

					miModelo.CargarTablasTraducidas(
							"Select orden, cif from eleccion_alumno_empresa where dni = " + "'" + dniUsr + "'", 2,
							nombCol, "orden", "eleccion_alumno_empresa", "orden", "nombre", "empresa", "CIF");

					table.setModel(miModelo.getTablaEmpresaDefModel());
					miControlador.setCellsAlignment(table, SwingConstants.CENTER);

					textFieldNombreEmpresa.setText("");

					comboBoxOrdenActual.removeAllItems();
					
					miElegirEmpresas.getComboBoxOrdenEmp().removeAllItems();
					for (int i = 1; i < 11; i++) {
						miElegirEmpresas.getComboBoxOrdenEmp().addItem(Integer.toString(i));

					}

				}

			}
		});
		btnCambiarOrden.setBounds(396, 287, 150, 23);
		contentPane.add(btnCambiarOrden);

		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setBounds(10, 263, 74, 14);
		contentPane.add(lblEmpresa);

		JLabel lblOrdenActual = new JLabel("Orden actual:");
		lblOrdenActual.setBounds(146, 265, 95, 14);
		contentPane.add(lblOrdenActual);

		JLabel lblCmabiarALa = new JLabel("Cambiar a la posici\u00F3n: ");
		lblCmabiarALa.setBounds(273, 262, 150, 14);
		contentPane.add(lblCmabiarALa);

		JButton btnBorrarEleccin = new JButton("Borrar empresa");
		btnBorrarEleccin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String dniUsr = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("dni", "alumnos", "email",
						miLogin.getTextFieldUsuario().getText());

				String nombreEmpresaSeleccionada = textFieldNombreEmpresa.getText();
				String cifEmpresaSeleccionada = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("CIF", "empresa",
						"nombre", nombreEmpresaSeleccionada);

				miModelo.borrarLoQueSeaDeUnaBBDD("DELETE FROM eleccion_alumno_empresa WHERE CIF = " + "'"
						+ cifEmpresaSeleccionada + "' and dni = " + "'" + dniUsr + "'");

				String[] nombCol = { "Orden", "Nombre" };

				miModelo.CargarTablasTraducidas(
						"Select orden, cif from eleccion_alumno_empresa where dni = " + "'" + dniUsr + "'", 2, nombCol,
						"orden", "eleccion_alumno_empresa", "orden", "nombre", "empresa", "CIF");

				table.setModel(miModelo.getTablaEmpresaDefModel());
				miControlador.setCellsAlignment(table, SwingConstants.CENTER);

				textFieldNombreEmpresa.setText("");

				comboBoxOrdenActual.removeAllItems();

				miElegirEmpresas.getComboBoxOrdenEmp().removeAllItems();
				for (int i = 1; i < 11; i++) {
					miElegirEmpresas.getComboBoxOrdenEmp().addItem(Integer.toString(i));

				}

			}
		});
		btnBorrarEleccin.setBounds(10, 327, 150, 23);
		contentPane.add(btnBorrarEleccin);
	}
}
