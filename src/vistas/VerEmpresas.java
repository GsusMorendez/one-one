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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerEmpresas extends JFrame {

	private Controlador miControlador;
	private Modelo miModelo;

	private Login miLogin;

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldNombreEmpresa;
	private JButton button;

	public Login getMiLogin() {
		return miLogin;
	}

	public void setMiLogin(Login miLogin) {
		this.miLogin = miLogin;
	}

	public JTextField getTextFieldNombreEmpresa() {
		return textFieldNombreEmpresa;
	}

	public void setTextFieldNombreEmpresa(JTextField textFieldNombreEmpresa) {
		this.textFieldNombreEmpresa = textFieldNombreEmpresa;
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

	public VerEmpresas() {
		setTitle("Ver empresas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 758, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 25, 690, 229);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				int numRow = table.getSelectedRow();
				// int numCol = table.getSelectedColumn();

				String CifEmpre = (String) miModelo.getTablaEmpresaDefModel().getValueAt(numRow, 0);

				String nombreEmpre = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "empresa", "CIF",
						CifEmpre);

				textFieldNombreEmpresa.setText(nombreEmpre);

			}
		});
		scrollPane.setViewportView(table);

		textFieldNombreEmpresa = new JTextField();
		textFieldNombreEmpresa.setBounds(175, 279, 216, 20);
		contentPane.add(textFieldNombreEmpresa);
		textFieldNombreEmpresa.setColumns(10);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombreEmpresa = textFieldNombreEmpresa.getText();

				String cifEmpresa = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("CIF", "empresa", "nombre",
						nombreEmpresa);

				String nombreProf = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "profesor", "email",
						miLogin.getTextFieldUsuario().getText());

				String miquery = "delete FROM porfesor_cuenta_con_estas_empesas WHERE CIF = " + "'" + cifEmpresa + "'"
						+ " and nombre = " + "'" + nombreProf + "'";

				miModelo.borrarLoQueSeaDeUnaBBDD("delete FROM porfesor_cuenta_con_estas_empesas WHERE CIF = " + "'"
						+ cifEmpresa + "'" + " and nombre = " + "'" + nombreProf + "'");

				JOptionPane.showMessageDialog(null, "Empresa borrada");

				textFieldNombreEmpresa.setText("");

				String[] nombreColumnas = { "CIF", "Empresa" };
				miModelo.traerCifYempresa(
						"Select * from porfesor_cuenta_con_estas_empesas where nombre = " + "'" + nombreProf + "'",
						nombreColumnas);
				table.setModel(miModelo.getTablaEmpresaDefModel());

			}
		});
		btnBorrar.setBounds(431, 278, 89, 23);
		contentPane.add(btnBorrar);

		button = new JButton("\u2190");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cambiarDePantalla(10, 4);
			}
		});
		button.setBounds(0, 0, 53, 23);
		contentPane.add(button);
	}
}
