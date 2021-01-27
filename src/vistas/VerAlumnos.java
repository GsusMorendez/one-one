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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerAlumnos extends JFrame {

	private Controlador miControlador;
	private Modelo miModelo;

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldNombreAlum;
	private JButton button;

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

	public VerAlumnos() {
		setTitle("Ver alumnos");
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

				String nombreAlum = (String) miModelo.getTablaEmpresaDefModel().getValueAt(numRow, 1);

				textFieldNombreAlum.setText(nombreAlum);

			}
		});
		scrollPane.setViewportView(table);

		textFieldNombreAlum = new JTextField();
		textFieldNombreAlum.setBounds(260, 279, 86, 20);
		contentPane.add(textFieldNombreAlum);
		textFieldNombreAlum.setColumns(10);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miModelo.BorrarAlum(textFieldNombreAlum.getText());
			}
		});
		btnBorrar.setBounds(431, 278, 89, 23);
		contentPane.add(btnBorrar);
		
		button = new JButton("\u2190");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cambiarDePantalla(8, 4);
			}
		});
		button.setBounds(0, 0, 53, 23);
		contentPane.add(button);
	}
}
