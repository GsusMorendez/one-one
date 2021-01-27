package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import general.Controlador;
import general.Modelo;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	private Controlador miControlador;
	private Modelo miModelo;

	private Registro miRegistro;

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField textFieldContraseña;
	private JLabel lblHolaJessvamos;

	public Registro getMiRegistro() {
		return miRegistro;
	}

	public void setMiRegistro(Registro miRegistro) {
		this.miRegistro = miRegistro;
	}

	public JTextField getTextFieldUsuario() {
		return textFieldUsuario;
	}

	public void setTextFieldUsuario(JTextField textFieldUsuario) {
		this.textFieldUsuario = textFieldUsuario;
	}

	public JTextField getTextFieldContraseña() {
		return textFieldContraseña;
	}

	public void setTextFieldContraseña(JTextField textFieldContraseña) {
		this.textFieldContraseña = textFieldContraseña;
	}

	public JLabel getLblHolaJessvamos() {
		return lblHolaJessvamos;
	}

	public void setLblHolaJessvamos(JLabel lblHolaJessvamos) {
		this.lblHolaJessvamos = lblHolaJessvamos;
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

	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInicioDeSesin = new JLabel("Inicio de sesi\u00F3n");
		lblInicioDeSesin.setFont(new Font("Verdana Pro Semibold", Font.BOLD, 15));
		lblInicioDeSesin.setBounds(127, 11, 142, 20);
		contentPane.add(lblInicioDeSesin);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(90, 91, 70, 14);
		contentPane.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(90, 136, 70, 14);
		contentPane.add(lblContrasea);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(195, 88, 86, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		textFieldContraseña = new JTextField();
		textFieldContraseña.setBounds(195, 133, 86, 20);
		contentPane.add(textFieldContraseña);
		textFieldContraseña.setColumns(10);

		JButton btnEnviarUsuarioContra = new JButton("Enviar");
		btnEnviarUsuarioContra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = textFieldUsuario.getText();
				String contra = textFieldContraseña.getText();
				miControlador.llevarAlaParteProfOalumn(miModelo.buscarUsuario(usuario, contra));

			}
		});
		btnEnviarUsuarioContra.setBounds(150, 184, 89, 23);
		contentPane.add(btnEnviarUsuarioContra);

		lblHolaJessvamos = new JLabel("Hola " + ", !vamos a empezar!");
		lblHolaJessvamos.setBounds(112, 45, 192, 14);
		contentPane.add(lblHolaJessvamos);

		JButton btnRegistrate = new JButton("Registrate");
		btnRegistrate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miRegistro.getTextFieldPromocion().setText(miControlador.ponerPromocion());

				ArrayList<String> lista = miModelo.rellenarCombo();

				

				boolean existSoyProf = false;

				for (int i = 0; i < miRegistro.getComboBox().getItemCount(); i++) {
					String item = (String) miRegistro.getComboBox().getItemAt(i);

					if (item.equals("Soy profesor")) {
						existSoyProf = true;
					}
				}
				
				if (!existSoyProf) {
					miRegistro.getComboBox().addItem("Soy profesor");
				}

				for (int i = 0; i < lista.size(); i++) {

					miRegistro.getComboBox().addItem(lista.get(i));
				}

				miControlador.cambiarDePantalla(1, 2);
			}
		});
		btnRegistrate.setBounds(323, 0, 111, 23);
		contentPane.add(btnRegistrate);

		JButton btnatrs = new JButton("\u2190  Atr\u00E1s");
		btnatrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cambiarDePantalla(1, 0);
			}
		});
		btnatrs.setBounds(0, 0, 89, 23);
		contentPane.add(btnatrs);
	}
}
