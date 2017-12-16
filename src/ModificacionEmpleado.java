import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ModificacionEmpleado extends JInternalFrame {

	private JPanel principal, centro;
	private JLabel[] label;
	private JTextField[] datosEmp;
	private JCheckBox ckModificar;
	private JButton btSend;
	private JComboBox<String> cbEmpleado;
	private DefaultComboBoxModel<String> modelEmp;
	private GridBagConstraints cons;
	private Conexion c;

	public ModificacionEmpleado() {

		super(null, false, true, false, false);
		// tamaño 1050,500
		this.setPreferredSize(new Dimension(1050, 640));
		this.getContentPane().setBackground(Color.white);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);

		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));

		principal = new JPanel(new BorderLayout(20, 20));
		principal.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Modificación Empleado",
						TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), Color.GRAY));
		principal.setPreferredSize(new Dimension(400, 600));
		principal.setLayout(new BorderLayout());
		centro = new JPanel(new GridBagLayout());
		centro.setPreferredSize(principal.getPreferredSize());
		cons = new GridBagConstraints();
		centro.setBorder(new EmptyBorder(0, 20, 0, 20));

		drawComboBox();
		drawLabels();
		drawTextFields();
		// Dibujo el boton y el checkbox
		ckModificar = new JCheckBox("Modificar");
		btSend = new JButton("Enviar");
		// Añado el boton para actualizar, y un checkbox para permitir modificar
		cons.gridy = 8;
		cons.gridx = 1;
		centro.add(ckModificar, cons);
		cons.gridx = 3;
		cons.gridwidth = 1;
		centro.add(btSend, cons);

		principal.add(centro);
		this.getContentPane().add(principal);

		Listener();
		loadEmpleadoData();

	}

	/**
	 * Método que recoge de la bbdd todos los empleados y con un DefaultCom
	 */
	private void drawComboBox() {

		cbEmpleado = new JComboBox<String>();
		cbEmpleado.setPrototypeDisplayValue("123456789012345678901234567890");
		setEmpleadoModel();
		cons.gridx = 3;
		cons.gridy = 0;
		cons.gridwidth = 2;
		cons.weightx = 1;
		cons.ipady = 10;
		centro.add(cbEmpleado, cons);

	}

	
	private void drawLabels() {

		String[] textoLbl = { "Empleado", "User", "Pass", "Nombre", "Apellidos", "Teléfono", "Rol" };
		this.label = new JLabel[textoLbl.length];
		for (int i = 0; i < label.length; i++) {
			label[i] = new JLabel(textoLbl[i]);
			label[i].setFont(new Font(null, Font.BOLD, 15));
			cons.gridx = 1;
			cons.gridy = i;
			cons.weightx = 1;
			cons.ipady = 10;
			centro.add(label[i], cons);
		}

	}

	private void drawTextFields() {
		datosEmp = new JTextField[6];
		for (int i = 0; i < datosEmp.length; i++) {
			datosEmp[i] = new JTextField(10);
			datosEmp[i].setEnabled(false);
			cons.gridx = 3;
			cons.gridy = i + 1;
			cons.gridwidth = 2;
			cons.weightx = 1;
			cons.ipady = 10;
			cons.fill = GridBagConstraints.HORIZONTAL;
			centro.add(datosEmp[i], cons);

		}
	}
	
	private void setEmpleadoModel() {
		modelEmp = new DefaultComboBoxModel<String>();

		try {
			Conexion c = new Conexion();
			ResultSet rs = c.consultar("SELECT * FROM EMPLEADOS");

			while (rs.next()) {
				modelEmp.addElement(rs.getString(1));

			}
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("ERROR EN LA CONSULTA. REVISAR");
			modelEmp.addElement("No hay empleados en la BBDD");
			e.printStackTrace();
		}
		cbEmpleado.setModel(modelEmp);

	}

	private void loadEmpleadoData() {
		Conexion c;
		Empleado e = new Empleado();

		try {
			c = new Conexion();
			e.buscar(this.cbEmpleado.getItemAt(cbEmpleado.getSelectedIndex()));
			this.datosEmp[0].setText(e.getUser());
			this.datosEmp[1].setText(e.getPass());
			this.datosEmp[2].setText(e.getNombre());
			this.datosEmp[3].setText(e.getApellidos());
			this.datosEmp[4].setText(String.valueOf(e.getTelefono()));
			this.datosEmp[5].setText(String.valueOf(e.getRol()));

		} catch (ClassNotFoundException | SQLException e1) {
			System.out.println("Error en la conexion");
			e1.printStackTrace();
		}

	

	}

	private boolean validateFields() {
		Validaciones v = new Validaciones();
		boolean valido = true;

		// Valido los que no pueden ser nulos
		for (int i = 0; i < datosEmp.length; i++) {
			if (v.campovacio(datosEmp[i].getText()) && i != 3) {
				JOptionPane.showMessageDialog(this, "Error, ha dejado el campo "+label[i+1].getText()+" vacío", "Campo Vacío",
						JOptionPane.ERROR_MESSAGE);
				datosEmp[i].requestFocus();
				valido = false;
			}
		}
		// Valido el telefon
		if (v.validartelefono(datosEmp[4].getText())) {
			JOptionPane.showMessageDialog(this, "Error, telefono incorrecto", "Telefono incorrecto",
					JOptionPane.ERROR_MESSAGE);
			datosEmp[4].requestFocus();
			valido = false;
		}

		return valido;

	}

	private void switchEnabled(boolean enabled) {
		for (JTextField dato : datosEmp) {
			dato.setEnabled(enabled);
		}
	}

	private void Listener() {

		// Carga la información del empleado que he seleccionado en el combobox
		cbEmpleado.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				loadEmpleadoData();
			}
		});

		// Hablitia los campos para su edicion
		ckModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JCheckBox ck = (JCheckBox) e.getSource();
				if (ck.isSelected()) {
					switchEnabled(true);
				} else {
					switchEnabled(false);
				}

			}
		});

		this.btSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchEnabled(false);
				ckModificar.setSelected(false);

				if (validateFields()) {
					
					saveData();
				}

			}

			private void saveData() {

				String[] campos = { "user", "pass", "nombre", "apellidos", "telefono", "rol" };
				Empleado e = new Empleado();
				try {
					Conexion c = new Conexion();
					// Primero el usuario
					e.modificar(cbEmpleado.getSelectedItem().toString(), campos[0], datosEmp[0].getText(), c);
					for (int i = 1; i < campos.length; i++) {
						System.out.println("campo" + i);
						// Y ahora el resto de campos
						e.modificar(datosEmp[0].getText(), campos[i], datosEmp[i].getText(), c);
					}
					setEmpleadoModel();

				} catch (ClassNotFoundException | SQLException e1) {
					System.out.println(e1.getMessage());
					e1.printStackTrace();
				}

			}
		});
	}

}
