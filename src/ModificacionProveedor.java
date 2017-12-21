import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import sun.net.www.content.image.jpeg;

public class ModificacionProveedor extends JInternalFrame implements ActionListener{
	
	private JComboBox<String> jc;
	private Conexion c;
	private JPanel aux,central,norte,sur,centralL;
	private JTextField cif,nom,tel,dir;
	private BotonInterior jb,jb3;
	private JPanel[] jp = new JPanel[8];
	
	private Proveedor p;
	private Validaciones v;
	
	public ModificacionProveedor() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		
		this.setResizable(false);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.white);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.setPreferredSize(new Dimension(1050,640));
		this.setBackground(Color.white);
		componentes();
		this.setVisible(true);
	}
	
	private void componentes() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		jc = new JComboBox<>();
		jc.setPreferredSize(new Dimension(150, 26));
		c = new Conexion();
		ResultSet rs = c.consultar("SELECT * FROM proveedores");
		jc.addItem("Selecione Proveedor");
		while(rs.next()){
			jc.addItem(rs.getString(1)+"/"+rs.getString(2));
		}
		central = new JPanel();
		central.setLayout(new BorderLayout());
		central.setBackground(Color.WHITE);
		
		central.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createLineBorder(new Color(41, 53, 65)),
						"Modificar Proveedor",TitledBorder.LEFT, 
						TitledBorder.TOP, new Font(null, Font.BOLD, 25),new Color(41, 53, 65)));
		central.setPreferredSize(new Dimension(300,300));
		
		
		jb = new BotonInterior("Modificar");
		jb.setEnabled(false);
		jb.addActionListener(this);
		jb3 = new  BotonInterior("Cargar");
		jb3.addActionListener(this);
		
		norte = new JPanel();
		norte.setBackground(Color.WHITE);
		norte.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
		norte.add(jc);
		norte.add(jb3);
		
		sur = new JPanel();
		sur.setLayout(new FlowLayout(FlowLayout.CENTER));
		sur.setBackground(Color.WHITE);
		sur.add(jb);	
		
		for (int i = 0; i < jp.length; i++) {
			jp[i] = new JPanel();
			jp[i].setBackground(Color.white);
			jp[i].setLayout(new FlowLayout(FlowLayout.LEFT));
		}
		
		cif = new JTextField();
		cif.setColumns(9);
		cif.setEnabled(false);
		nom = new JTextField(10);
		nom.setEnabled(false);
		tel = new JTextField();
		tel.setColumns(9);
		tel.setEnabled(false);
		dir = new JTextField(12);
		dir.setEnabled(false);
		
		jp[0].add(new JLabel("CIF: "));
		jp[1].add(cif);
		jp[2].add(new JLabel("NOMBRE: "));
		jp[3].add(nom);
		jp[4].add(new JLabel("TELEFONO: "));
		jp[5].add(tel);
		jp[6].add(new JLabel("DIRECCIÓN: "));
		jp[7].add(dir);
		
		centralL = new JPanel();
		centralL.setLayout(new GridLayout(4, 2));
		centralL.setBackground(Color.white);
		for (int i = 0; i < jp.length; i++) {
			centralL.add(jp[i]);
		}
		
		
		central.add(BorderLayout.NORTH,norte);
		central.add(BorderLayout.CENTER, centralL);
		central.add(BorderLayout.SOUTH, sur);
		
		this.getContentPane().add(central);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(jb3 == e.getSource()){
			p = new Proveedor();
			String[] cadena=jc.getSelectedItem().toString().split("/");
			String cadena1 = cadena[0];
			try {
				if(p.find(cadena1)==true){
					
					cif.setText(p.getNifcif());
					nom.setText(p.getNombre());
					tel.setText(p.getTlf());
					dir.setText(p.getDireccion());
					
					jb.setEnabled(true);
					cif.setEnabled(true);
					nom.setEnabled(true);
					tel.setEnabled(true);
					dir.setEnabled(true);
				
					
				}else{
					JOptionPane.showMessageDialog(null, "Error en la busqueda!");
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else if(jb == e.getSource()){
			p = new Proveedor();
			v = new Validaciones();
			String[] cadena=jc.getSelectedItem().toString().split("/");
			String dni = cadena[0];
			try {
				if(v.campovacio(nom.getText()) || v.campovacio(tel.getText()) || v.campovacio(dir.getText()) || v.campovacio(cif.getText())){
					JOptionPane.showMessageDialog(null, "Quedan campos sin rellenar!");
				}else{
					if (v.validarNIF(cif.getText())) {
						if (v.isNumeric(tel.getText())) {
							if (v.validartelefono(tel.getText())==false) {
								String [] opciones ={"Si","No"};
								int eleccion = JOptionPane.showOptionDialog(null,"¿Desea modificar los datos?","Mensaje de Confirmacion",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,null,opciones,"Si");		
								if (eleccion == JOptionPane.YES_OPTION){
									p.update(dni, "nombre", nom.getText());
									p.update(dni, "telefono", tel.getText());
									p.update(dni, "direccion", dir.getText());
									p.update(dni, "cif", cif.getText());
									JOptionPane.showMessageDialog(null, "Datos actualizados");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Teléfono incorrecto!");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Datos erroneos en Teléfono!");
						}
					} else {
						JOptionPane.showMessageDialog(null, "NIF incorrecto!");
					}
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public JComboBox<String> getJc() {
		return jc;
	}

	public void setJc(JComboBox<String> jc) {
		this.jc = jc;
	}

	public Conexion getC() {
		return c;
	}

	public void setC(Conexion c) {
		this.c = c;
	}

	public JPanel getCentral() {
		return central;
	}

	public void setCentral(JPanel central) {
		this.central = central;
	}

	public JPanel getNorte() {
		return norte;
	}

	public void setNorte(JPanel norte) {
		this.norte = norte;
	}

	public JPanel getSur() {
		return sur;
	}

	public void setSur(JPanel sur) {
		this.sur = sur;
	}

	public JPanel getCentralL() {
		return centralL;
	}

	public void setCentralL(JPanel centralL) {
		this.centralL = centralL;
	}

	public JTextField getCif() {
		return cif;
	}

	public void setCif(JTextField cif) {
		this.cif = cif;
	}

	public JTextField getNom() {
		return nom;
	}

	public void setNom(JTextField nom) {
		this.nom = nom;
	}

	public JTextField getTel() {
		return tel;
	}

	public void setTel(JTextField tel) {
		this.tel = tel;
	}

	public JTextField getDir() {
		return dir;
	}

	public void setDir(JTextField dir) {
		this.dir = dir;
	}

	public BotonInterior getJb() {
		return jb;
	}

	public void setJb(BotonInterior jb) {
		this.jb = jb;
	}

	public BotonInterior getJb3() {
		return jb3;
	}

	public void setJb3(BotonInterior jb3) {
		this.jb3 = jb3;
	}

	public JPanel[] getJp() {
		return jp;
	}

	public void setJp(JPanel[] jp) {
		this.jp = jp;
	}
	
	
}
