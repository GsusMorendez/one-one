package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import general.Controlador;
import general.Modelo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProponerEmpresaAlumno extends JFrame {
	private Controlador miControlador;
	private Modelo miModelo;

	private Login miLogin;

	private JPanel contentPane;
	private JTextField textFieldNombreEmpresa;
	private JTextField textFieldDireccion;
	private JTextField textFieldCif;
	private JTextField textFieldComentarios;

	public Login getMiLogin() {
		return miLogin;
	}

	public void setMiLogin(Login miLogin) {
		this.miLogin = miLogin;
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

	public ProponerEmpresaAlumno() {
		setTitle("Proponer empresa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCif = new JLabel("CIF:");
		lblCif.setBounds(100, 53, 57, 14);
		contentPane.add(lblCif);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(100, 92, 57, 14);
		contentPane.add(lblNombre);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n: ");
		lblDireccin.setBounds(100, 127, 86, 14);
		contentPane.add(lblDireccin);

		JLabel lblComentarios = new JLabel("Comentarios:");
		lblComentarios.setBounds(100, 162, 86, 14);
		contentPane.add(lblComentarios);

		JLabel lblnuevaEmpresa = new JLabel("\u00A1Nueva empresa!");
		lblnuevaEmpresa.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblnuevaEmpresa.setBounds(136, 11, 138, 14);
		contentPane.add(lblnuevaEmpresa);

		textFieldNombreEmpresa = new JTextField();
		textFieldNombreEmpresa.setBounds(204, 89, 86, 20);
		contentPane.add(textFieldNombreEmpresa);
		textFieldNombreEmpresa.setColumns(10);

		textFieldDireccion = new JTextField();
		textFieldDireccion.setBounds(204, 124, 86, 20);
		contentPane.add(textFieldDireccion);
		textFieldDireccion.setColumns(10);

		textFieldCif = new JTextField();
		textFieldCif.setBounds(204, 50, 86, 20);
		contentPane.add(textFieldCif);
		textFieldCif.setColumns(10);

		textFieldComentarios = new JTextField();
		textFieldComentarios.setBounds(204, 159, 86, 20);
		contentPane.add(textFieldComentarios);
		textFieldComentarios.setColumns(10);

		JButton btnAadirEmpresa = new JButton("A\u00F1adir empresa");
		btnAadirEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String cif = textFieldCif.getText();
				String nombre = textFieldNombreEmpresa.getText();
				String direc = textFieldDireccion.getText();
				String coment = textFieldComentarios.getText();

				String[] datosNewEmpresa = { cif, nombre, direc, coment };

				String user = miLogin.getTextFieldUsuario().getText();

				int profOalu = miModelo.saberRol(user);

				// 3 es alumno y 4 es profesor
				if (profOalu == 3) {

					String nombreDelProf = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre_profesor", "alumnos",
							"email", user);

					if (miModelo.insertarEmpresa(miControlador.comprobarCif(cif), datosNewEmpresa, nombreDelProf)) {
						textFieldCif.setText("");
						textFieldComentarios.setText("");
						textFieldNombreEmpresa.setText("");
						textFieldDireccion.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Los campos CIF, Nombre, y Dirección deben completarse");
					}
				} else {
					String nombreDelProf = miModelo.ObtenerUnCampoDeUnaTablaApartirDeOtro("nombre", "profesor", "email",
							user);
					if (miModelo.insertarEmpresa(miControlador.comprobarCif(cif), datosNewEmpresa, nombreDelProf)) {

						textFieldCif.setText("");
						textFieldComentarios.setText("");
						textFieldNombreEmpresa.setText("");
						textFieldDireccion.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "Los campos CIF, Nombre, y Dirección deben completarse");
					}

				}

			}
		});
		btnAadirEmpresa.setBounds(149, 204, 125, 23);
		contentPane.add(btnAadirEmpresa);

		JButton button = new JButton("\u2190");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String user = miLogin.getTextFieldUsuario().getText();
				miControlador.cambiarDePantalla(5, miModelo.saberRol(user));

			}
		});
		button.setBounds(0, 0, 46, 23);
		contentPane.add(button);
	}
}
