import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	private HintTextField[] datosEmp;
	private JCheckBox ckModificar;
	private BotonInterior btSend;
	private JComboBox<String> cbEmpleado;
	private DefaultComboBoxModel<String> modelEmp;
	private GridBagConstraints cons;
	private Empleado e;

	/**
	 * Constructor de la clase, defino los paneles, un gridbag donde lo coloco,
	 * voy llamando a los métodos que he creado para dibujar los elementos y
	 * dibujo los elementos mas simples
	 */
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
		principal.setPreferredSize(new Dimension(1000, 600));
		principal.setLayout(new BorderLayout());
		principal.setBackground(Color.WHITE);
		// Panel central, gridbaglayout
		centro = new JPanel(new GridBagLayout());

		centro.setPreferredSize(principal.getPreferredSize());
		cons = new GridBagConstraints();
		centro.setBorder(new EmptyBorder(0, 20, 0, 20));
		// LLamo a los métodos para dibujar los elementos
		drawComboBox();
		drawLabels();
		drawTextFields();
		// Dibujo el boton y el checkbox
		ckModificar = new JCheckBox("Modificar");
		ckModificar.setBackground(Color.white);
		btSend = new BotonInterior("Enviar");
		btSend.setEnabled(false);

		// Añado el boton para actualizar, y un checkbox para permitir modificar
		cons.gridy = 8;
		cons.gridx = 1;
		centro.add(ckModificar, cons);
		cons.gridx = 3;
		cons.gridwidth = 1;
		centro.add(btSend, cons);
		centro.setBackground(Color.white);
		principal.add(centro);
		this.getContentPane().add(principal);
		// LLamo a los listener
		Listener();
		loadEmpleadoData();

	}

	/**
	 * Método que recoge de la bbdd todos los empleados y con un DefaultCom
	 */
	private void drawComboBox() {

		cbEmpleado = new JComboBox<String>();
		cbEmpleado.setPrototypeDisplayValue("123456789012345678901234567890");
		setEmpleadoModel(); // LLamo al método que extrae los datos de la bbdd
		cons.gridx = 3;
		cons.gridy = 0;
		cons.gridwidth = 2;
		cons.weightx = 1;
		cons.ipady = 10;
		centro.add(cbEmpleado, cons);

	}

	/**
	 * Dibujo las etiquetas, asigno una columna y mediante un bucle, voy
	 * asignado la fila una a una
	 */
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
		datosEmp = new HintTextField[6];
		String[] textoLbl = { "User", "Pass", "Nombre", "Apellidos", "Teléfono", "Rol" };

		for (int i = 0; i < datosEmp.length; i++) {
			datosEmp[i] = new HintTextField(textoLbl[i]);
			datosEmp[i].setEnabled(false);
			cons.gridx = 3;
			cons.gridy = i + 1;
			cons.gridwidth = 2;
			cons.weightx = 1;
			cons.insets=  new Insets(10, 10, 10, 10);
			cons.fill = GridBagConstraints.HORIZONTAL;
			centro.add(datosEmp[i], cons);

		}
	}

	/**
	 * Model para el combobox, busco los datos en la BBDD y muestro el id de
	 * usuario que quiero modificar
	 */
	private void setEmpleadoModel() {
		modelEmp = new DefaultComboBoxModel<String>();

		modelEmp.addElement("Seleccionar Nombre Empleado - Usuario");
		try {
			Conexion c = new Conexion();
			ResultSet rs = c.consultar("SELECT * FROM EMPLEADOS");

			while (rs.next()) {
				modelEmp.addElement(rs.getString("nombre") + " - " + rs.getString("user"));

			}
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("ERROR EN LA CONSULTA. REVISAR");
			modelEmp.addElement("No hay empleados en la BBDD");
			e.printStackTrace();
		}
		cbEmpleado.setModel(modelEmp);

	}

	private void loadEmpleadoData() {

		e = new Empleado();

		String[] user = cbEmpleado.getSelectedItem().toString().split("-");
		try {

			if (e.buscar(user[1].trim())) {
				this.datosEmp[0].setText(e.getUser());
				this.datosEmp[1].setText(e.getPass());
				this.datosEmp[2].setText(e.getNombre());
				this.datosEmp[3].setText(e.getApellidos());
				this.datosEmp[4].setText(String.valueOf(e.getTelefono()));
				this.datosEmp[5].setText(String.valueOf(e.getRol()));
			}

		} catch (ClassNotFoundException | SQLException e1) {
			System.out.println("Error en la conexion");
			e1.printStackTrace();
		}

	}

	/**
	 * Método que valida los campos antes de poder enviarlos
	 * 
	 * @return
	 */
	private boolean validateFields() {
		Validaciones v = new Validaciones();
		boolean valido = true;

		// Valido los que no pueden ser nulos
		for (int i = 0; i < datosEmp.length; i++) {
			if (v.campovacio(datosEmp[i].getText()) && i != 3) {
				JOptionPane.showMessageDialog(this, "Error, ha dejado el campo " + label[i + 1].getText() + " vacío",
						"Campo Vacío", JOptionPane.ERROR_MESSAGE);
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

	private void saveData() {

		String[] campos = { "user", "pass", "nombre", "apellidos", "telefono", "rol" };
		String[] opciones = { "Si", "No" };
		int eleccion = JOptionPane.showOptionDialog(null, "Actualizar " + e.getUser(), "Mensaje de Confirmacion",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "Si");

		if (eleccion == JOptionPane.YES_OPTION) {
			try {
				Conexion c = new Conexion();
				// Primero el nombre de usuario
				e.modificar(e.getUser(), campos[0], datosEmp[0].getText());
				for (int i = 1; i < campos.length; i++) {
					// Y ahora el resto de campos
					e.modificar(datosEmp[0].getText(), campos[i], datosEmp[i].getText());
				}
				setEmpleadoModel();

			} catch (ClassNotFoundException | SQLException e1) {
				System.out.println(e1.getMessage());
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Datos Registrados correctamente");

		}

	}

	/**
	 * Activa desactiva los campos
	 * 
	 * @param enabled
	 */
	private void switchEnabled(boolean enabled) {
		for (JTextField dato : datosEmp) {
			dato.setEnabled(enabled);
		}
	}

	/**
	 * Método con todos los listener de cada elemento
	 */
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

		//Listener para el boton enviar, primero valida los datos
		// y si son validos, los guarda
		this.btSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchEnabled(false);
				ckModificar.setSelected(false);

				if (validateFields()) {

					saveData();
				}
			

			}
		});
		
		this.cbEmpleado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbEmpleado.getSelectedIndex()==0)
				{
					btSend.setEnabled(false);
				}else
				{
					btSend.setEnabled(true);
				}
				
			}
		});
	}

}
