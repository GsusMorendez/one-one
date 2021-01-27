package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import general.*;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inicial extends JFrame {
	private Controlador miControlador;
	private Modelo miModelo;

	private Login miLogin;
	private Registro miRegistro;

	private JPanel contentPane;
	private JTextField textFieldName;

	public Inicial() {
		setTitle("Inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBienvenidoAOneone = new JLabel("Bienvenido a One&One ");
		lblBienvenidoAOneone.setFont(new Font("Verdana Pro Black", Font.BOLD, 25));
		lblBienvenidoAOneone.setBounds(48, 45, 360, 56);
		contentPane.add(lblBienvenidoAOneone);

		JLabel lblElProgramaPara = new JLabel("El programa para la gesti\u00F3n de las practicas de alumnos de FP");
		lblElProgramaPara.setBounds(67, 111, 341, 14);
		contentPane.add(lblElProgramaPara);

		JLabel lblIntroduzcaSuNombre = new JLabel("Introduzca su nombre:");
		lblIntroduzcaSuNombre.setBounds(67, 146, 140, 31);
		contentPane.add(lblIntroduzcaSuNombre);

		textFieldName = new JTextField();
		textFieldName.setBounds(229, 151, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		JButton btnEnviarNombre = new JButton("Enviar");
		btnEnviarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldName.getText();
				if (miControlador.comprobarNombreInicial(nombre)) {

					miLogin.getLblHolaJessvamos().setText("Hola " + nombre + " !vamos a empezar!:");
					miModelo.establecerConexion();
					miRegistro.getTextFieldNombre().setText(nombre);
					miControlador.cambiarDePantalla(0, 1);
					
				} else if (nombre.equals("ej: \"Juan\"")) {
					textFieldName.setText("");

				} else {
					textFieldName.setText("ej: \"Juan\"");
				}
			}

		});
		btnEnviarNombre.setBounds(171, 210, 89, 23);
		contentPane.add(btnEnviarNombre);
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

	public Registro getMiRegistro() {
		return miRegistro;
	}

	public void setMiRegistro(Registro miRegistro) {
		this.miRegistro = miRegistro;
	}
	
}
