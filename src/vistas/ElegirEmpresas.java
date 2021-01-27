package vistas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import general.Controlador;
import general.Modelo;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ElegirEmpresas extends JFrame {
	private Controlador miControlador;
	private Modelo miModelo;

	private Login miLogin;
	private VerElegidas miVerElegidas;

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldEmprElegida;
	private JComboBox comboBoxOrdenEmp;

	public VerElegidas getMiVerElegidas() {
		return miVerElegidas;
	}

	public void setMiVerElegidas(VerElegidas miVerElegidas) {
		this.miVerElegidas = miVerElegidas;
	}

	public JTextField getTextFieldEmprElegida() {
		return textFieldEmprElegida;
	}

	public void setTextFieldEmprElegida(JTextField textFieldEmprElegida) {
		this.textFieldEmprElegida = textFieldEmprElegida;
	}

	public VerElegidas getMiVerElergidas() {
		return miVerElegidas;
	}

	public void setMiVerElergidas(VerElegidas miVerElergidas) {
		this.miVerElegidas = miVerElergidas;
	}

	public JComboBox getComboBoxOrdenEmp() {
		return comboBoxOrdenEmp;
	}

	public void setComboBoxOrdenEmp(JComboBox comboBoxOrdenEmp) {
		this.comboBoxOrdenEmp = comboBoxOrdenEmp;
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

	public ElegirEmpresas() {
		setTitle("Elegir Empresas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 700, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 658, 222);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int numRow = table.getSelectedRow();
				// int numCol = table.getSelectedColumn();

				String[] companyChoosen = new String[1];

				for (int i = 0; i < companyChoosen.length; i++) {
					companyChoosen[i] = (String) miModelo.getTablaEmpresaDefModel().getValueAt(numRow, i);
					// System.out.println(companyChoosen[i]);
					// System.out.println(i);
				}

				textFieldEmprElegida.setText(companyChoosen[0]);

			}
		});

		// table.setModel(defModtable);
		// scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		textFieldEmprElegida = new JTextField();
		textFieldEmprElegida.setBounds(44, 259, 233, 20);
		contentPane.add(textFieldEmprElegida);
		textFieldEmprElegida.setColumns(10);

		comboBoxOrdenEmp = new JComboBox();
		comboBoxOrdenEmp
				.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
		comboBoxOrdenEmp.setBounds(299, 259, 53, 22);
		contentPane.add(comboBoxOrdenEmp);

		JButton btnElegir = new JButton("Elegir");
		btnElegir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreEmpresaElegida = textFieldEmprElegida.getText();
				String cifEmpresaElegida = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("CIF", "empresa", "nombre",
						nombreEmpresaElegida);
				String ordencito = (String) comboBoxOrdenEmp.getSelectedItem();
				int ordenPreferencia = Integer.parseInt(ordencito);
				// System.out.println(empresaElegida);
				// String dniUsr = miModelo.getDniUsr(miLogin.getTextFieldUsuario().getText());

				String dniUsr = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("dni", "alumnos", "email",
						miLogin.getTextFieldUsuario().getText());

				String profesor = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre_profesor", "alumnos", "dni",
						dniUsr);

				if (miModelo.rellenarTablaEleccion_alumno_empresa(cifEmpresaElegida, dniUsr, ordenPreferencia,
						profesor)) {

					comboBoxOrdenEmp.removeItem(Integer.toString(ordenPreferencia));

					textFieldEmprElegida.setText("");

				} else {
					JOptionPane.showMessageDialog(null, "La empresa ya ha sido elegida");
					textFieldEmprElegida.setText("");
				}

			}
		});
		btnElegir.setBounds(383, 258, 89, 23);
		contentPane.add(btnElegir);

		JButton btnVerElegidas = new JButton("Ver elegidas");
		btnVerElegidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// String dniUsr = miModelo.getDniUsr(miLogin.getTextFieldUsuario().getText());
				String dniUsr = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("dni", "alumnos", "email",
						miLogin.getTextFieldUsuario().getText());
				String[] nombCol = { "Orden", "Nombre" };

				miModelo.CargarTablasTraducidas(
						"Select orden, cif from eleccion_alumno_empresa where dni = " + "'" + dniUsr + "'", 2, nombCol,
						"orden", "eleccion_alumno_empresa", "orden", "nombre", "empresa", "CIF");

				miVerElegidas.getTable().setModel(miModelo.getTablaEmpresaDefModel());
				miControlador.setCellsAlignment(miVerElegidas.getTable(), SwingConstants.CENTER);

				miControlador.cambiarDePantalla(6, 7);

			}
		});
		btnVerElegidas.setBounds(563, 280, 105, 23);
		contentPane.add(btnVerElegidas);

		JButton button = new JButton("\u2190");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cambiarDePantalla(6, 3);
			}
		});
		button.setBounds(0, 0, 53, 23);
		contentPane.add(button);

	}
}
