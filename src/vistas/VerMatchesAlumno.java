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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerMatchesAlumno extends JFrame {

	private Controlador miControlador;
	private Modelo miModelo;

	private Login miLogin;
	private JLabel lblMatchUno;
	private JPanel contentPane;
	private JLabel lblMatchTres;
	private JLabel lblMatchDos;

	public JLabel getLblMatchUno() {
		return lblMatchUno;
	}

	public void setLblMatchUno(JLabel lblMatchUno) {
		this.lblMatchUno = lblMatchUno;
	}

	public JLabel getLblMatchTres() {
		return lblMatchTres;
	}

	public void setLblMatchTres(JLabel lblMatchTres) {
		this.lblMatchTres = lblMatchTres;
	}

	public JLabel getLblMatchDos() {
		return lblMatchDos;
	}

	public void setLblMatchDos(JLabel lblMatchDos) {
		this.lblMatchDos = lblMatchDos;
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

	public VerMatchesAlumno() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 557, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEstsSonLas = new JLabel("Est\u00E1s son las empresas en las que te entrevistar\u00E1s :");
		lblEstsSonLas.setFont(new Font("Verdana Pro Cond Semibold", Font.BOLD | Font.ITALIC, 22));
		lblEstsSonLas.setBounds(29, 34, 502, 54);
		contentPane.add(lblEstsSonLas);

		JButton button = new JButton("\u2190");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cambiarDePantalla(11, 3);
			}
		});
		button.setBounds(0, 0, 54, 23);
		contentPane.add(button);

		JLabel lblEmpresa1 = new JLabel("Empresa uno:");
		lblEmpresa1.setFont(new Font("Verdana Pro Semibold", Font.PLAIN, 13));
		lblEmpresa1.setBounds(84, 121, 148, 14);
		contentPane.add(lblEmpresa1);

		JLabel lblEmpresa2 = new JLabel("Empresa dos:");
		lblEmpresa2.setFont(new Font("Verdana Pro Semibold", Font.PLAIN, 13));
		lblEmpresa2.setBounds(84, 172, 148, 14);
		contentPane.add(lblEmpresa2);

		JLabel lblEmpresa3 = new JLabel("Empresa tres:");
		lblEmpresa3.setFont(new Font("Verdana Pro Semibold", Font.PLAIN, 13));
		lblEmpresa3.setBounds(84, 220, 148, 14);
		contentPane.add(lblEmpresa3);

		lblMatchUno = new JLabel("Aun por determinar");
		lblMatchUno.setBounds(242, 123, 228, 14);
		contentPane.add(lblMatchUno);

		lblMatchDos = new JLabel("A\u00FAn por determinar");
		lblMatchDos.setBounds(242, 174, 228, 14);
		contentPane.add(lblMatchDos);

		lblMatchTres = new JLabel("A\u00FAn por determinar");
		lblMatchTres.setBounds(242, 222, 228, 14);
		contentPane.add(lblMatchTres);
	}
}
