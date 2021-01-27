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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Registro extends JFrame {

	private Controlador miControlador;
	private Modelo miModelo;

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido1;
	private JTextField textFieldApellido2;
	private JTextField textFieldDNI;
	private JTextField textFieldEmail;
	private JTextField textFieldEdad;
	private JPasswordField passwordField;
	private JTextField textFieldPromocion;
	private JComboBox comboBox;

	public Registro() {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setFont(new Font("Verdana Pro Cond Black", Font.BOLD, 20));
		lblRegistro.setBounds(158, 0, 159, 26);
		contentPane.add(lblRegistro);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(23, 37, 57, 14);
		contentPane.add(lblNombre);

		JLabel lblPrimerApellido = new JLabel("Primer Apellido:");
		lblPrimerApellido.setBounds(23, 93, 96, 14);
		contentPane.add(lblPrimerApellido);

		JLabel lblSegundoApellido = new JLabel("Segundo Apellido:");
		lblSegundoApellido.setBounds(23, 161, 116, 14);
		contentPane.add(lblSegundoApellido);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(280, 93, 46, 14);
		contentPane.add(lblDni);

		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(168, 37, 79, 14);
		contentPane.add(lblEmail);

		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(168, 93, 79, 14);
		contentPane.add(lblEdad);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(168, 161, 79, 14);
		contentPane.add(lblContrasea);

		JLabel lblPromocin = new JLabel("Promoci\u00F3n: ");
		lblPromocin.setBounds(280, 37, 79, 14);
		contentPane.add(lblPromocin);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(23, 62, 86, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldApellido1 = new JTextField();
		textFieldApellido1.setBounds(23, 121, 86, 20);
		contentPane.add(textFieldApellido1);
		textFieldApellido1.setColumns(10);

		textFieldApellido2 = new JTextField();
		textFieldApellido2.setBounds(23, 186, 86, 20);
		contentPane.add(textFieldApellido2);
		textFieldApellido2.setColumns(10);

		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(280, 121, 86, 20);
		contentPane.add(textFieldDNI);
		textFieldDNI.setColumns(10);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(158, 62, 86, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		textFieldEdad = new JTextField();
		textFieldEdad.setBounds(158, 121, 86, 20);
		contentPane.add(textFieldEdad);
		textFieldEdad.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(168, 180, 77, 26);
		contentPane.add(passwordField);

		textFieldPromocion = new JTextField();
		textFieldPromocion.setBounds(280, 62, 86, 20);
		contentPane.add(textFieldPromocion);
		textFieldPromocion.setColumns(10);

		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombre = textFieldNombre.getText();
				String appeUno = textFieldApellido1.getText();
				String appeDos = textFieldApellido2.getText();
				String email = textFieldEmail.getText();
				String dni = textFieldDNI.getText();
				String edad = textFieldEdad.getText();
				String pwd = passwordField.getText();
				String promocion = textFieldPromocion.getText();
				String profesor = (String) comboBox.getSelectedItem();
				
				String [] datosRegistro = {nombre, appeUno, appeDos, email, dni, edad, pwd, promocion, profesor};

				if (miControlador.verificarDatos(datosRegistro)) {
					
					if (miModelo.comprobarSiExisteYaElUsuario(email, dni)) {
						if (profesor.equals("Soy profesor")) {
							miModelo.registrarProfesor(datosRegistro);
							JOptionPane.showMessageDialog(null, "registro correcto");
							miControlador.cambiarDePantalla(2, 1);
						}else {
							miModelo.registrarAlumno(datosRegistro);
							JOptionPane.showMessageDialog(null, "registro correcto");
							miControlador.cambiarDePantalla(2, 1);
						}
					}else {
						JOptionPane.showMessageDialog(null, "El usuario que intenta registrar ya existe");
					}
					
					
					

				} else {
					JOptionPane.showMessageDialog(null, "registro incorrecto");

				}

			}
		});
		btnRegistrarse.setBounds(158, 227, 106, 23);
		contentPane.add(btnRegistrarse);

		JButton button = new JButton("\u2190");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				miControlador.cambiarDePantalla(2, 1);
			}
		});
		button.setBounds(0, 0, 69, 23);
		contentPane.add(button);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Soy profesor" }));
		comboBox.setBounds(280, 185, 130, 22);
		contentPane.add(comboBox);

		JLabel lblSeleccionaATu = new JLabel("Selecciona a tu profesor: ");
		lblSeleccionaATu.setBounds(280, 161, 154, 14);
		contentPane.add(lblSeleccionaATu);
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

	public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}

	public void setTextFieldNombre(JTextField textFieldNombre) {
		this.textFieldNombre = textFieldNombre;
	}

	public JTextField getTextFieldApellido1() {
		return textFieldApellido1;
	}

	public void setTextFieldApellido1(JTextField textFieldApellido1) {
		this.textFieldApellido1 = textFieldApellido1;
	}

	public JTextField getTextFieldApellido2() {
		return textFieldApellido2;
	}

	public void setTextFieldApellido2(JTextField textFieldApellido2) {
		this.textFieldApellido2 = textFieldApellido2;
	}

	public JTextField getTextFieldDNI() {
		return textFieldDNI;
	}

	public void setTextFieldDNI(JTextField textFieldDNI) {
		this.textFieldDNI = textFieldDNI;
	}

	public JTextField getTextFieldEmail() {
		return textFieldEmail;
	}

	public void setTextFieldEmail(JTextField textFieldEmail) {
		this.textFieldEmail = textFieldEmail;
	}

	public JTextField getTextFieldEdad() {
		return textFieldEdad;
	}

	public void setTextFieldEdad(JTextField textFieldEdad) {
		this.textFieldEdad = textFieldEdad;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JTextField getTextFieldPromocion() {
		return textFieldPromocion;
	}

	public void setTextFieldPromocion(JTextField textFieldPromocion) {
		this.textFieldPromocion = textFieldPromocion;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

}
