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
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

public class ModificacionCliente extends JInternalFrame implements ActionListener{
	private JPanel ptotal,pizq,pder,pcen,psur,pradiobtn;
	private JLabel[] lbl;
	private HintTextField txtdni;
	private JTextField txtnom,txtapels,txtdir,txttel,txtmail;
	private JButton btnbuscar,btnmod;
	private JRadioButton[] sex;
	private Dni d;
	private JDateChooser date1,date2;
	private Clientes c;
	ModificacionCliente(){
		//tamaño 1050,500
		this.setPreferredSize(new Dimension(1050, 640));
		this.getContentPane().setBackground(Color.white);
		this.setClosable(false);
		this.setMaximizable(false);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,40,50));
		ptotal=new JPanel(new BorderLayout(150,50));
		ptotal.setBackground(Color.white);
		ptotal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65), 1), "MODIFICACIÓN CLIENTE",TitledBorder.LEFT,TitledBorder.TOP,new Font(null, Font.BOLD,25), new Color(41, 53, 65)));
		this.getContentPane().add(ptotal);
		pcen=new JPanel(new GridLayout(9, 2,50,10));
		pcen.setBorder(new EmptyBorder(50, 250, 50, 250));
		ptotal.add(pcen,BorderLayout.CENTER);
		pcen.setBackground(Color.white);
		
		//parte izquierda
		String[] texto={"DNI","Nombre","Apellidos","Fecha Nacimiento","Direccion","Telefono","Email","Sexo","Fecha Ingreso"};
		lbl=new JLabel[texto.length];
		for (int i = 0; i < texto.length; i++) {
			lbl[i]=new JLabel(texto[i]);
		}
		
		//parte derecha
		txtdni=new HintTextField("numeros y la letra");
		txtnom=new JTextField(11);
		txtnom.setEnabled(false);
		txtapels=new JTextField(10);
		txtapels.setEnabled(false);
		txtdir=new JTextField(10);
		txtdir.setEnabled(false);
		txttel=new JTextField(10);
		txttel.setEnabled(false);
		txtmail=new JTextField(10);
		txtmail.setEnabled(false);
		date1=new JDateChooser("dd-MM-yyyy", "####-##-##", ' ');		
		date2=new JDateChooser("dd-MM-yyyy", "####-##-##", ' ');
		Calendar hoy = new GregorianCalendar().getInstance();
		date2.setCalendar(hoy);
		date1.setEnabled(false);
		date2.setEnabled(false);
	
		//panel radiobuttons sexo
		pradiobtn = new JPanel(new GridLayout(1, 2));
		pradiobtn.setBackground(Color.white);
		ButtonGroup bg=new ButtonGroup();
		String[] texsex={"V","H"};
		sex=new JRadioButton[texsex.length];
	
		for (int i = 0; i < texsex.length; i++) {
			sex[i]=new JRadioButton(texsex[i]);
			sex[i].setEnabled(false);
			bg.add(sex[i]);
			pradiobtn.add(sex[i]);
			sex[i].setBackground(Color.white);
		}

		
		
		//se añaden los elementos a los paneles
		pcen.add(lbl[0]);
		pcen.add(txtdni);
		pcen.add(lbl[1]);
		pcen.add(txtnom);
		pcen.add(lbl[2]);
		pcen.add(txtapels);
		pcen.add(lbl[3]);
		pcen.add(date1);
		pcen.add(lbl[4]);
		pcen.add(txtdir);
		pcen.add(lbl[5]);
		pcen.add(txttel);
		pcen.add(lbl[6]);
		pcen.add(txtmail);
		pcen.add(lbl[7]);
		pcen.add(pradiobtn);
		pcen.add(lbl[8]);
		pcen.add(date2);
		
		
		psur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		ptotal.add(psur, BorderLayout.SOUTH);
		btnbuscar=new JButton("Buscar");
		btnmod=new JButton("Modificar");
		btnmod.setEnabled(false);
		btnbuscar.addActionListener(this);
		btnmod.addActionListener(this);
		psur.add(btnbuscar);
		psur.add(btnmod);
		psur.setBackground(Color.white);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnbuscar){
			try {
				c = new Clientes();
				c=buscarCliente(txtdni.getText());
				cargarCliente(c);
				activarTextfield(true);
				btnmod.setEnabled(true);
				
			} catch (ClassNotFoundException | SQLException e) {
				JOptionPane.showMessageDialog(this, "Error al cargar el cliente, vuelva a intentarlo");
				e.printStackTrace();
				
			}
		}else if(arg0.getSource()==btnmod){
			if(!comprobarCambioTxt(txtnom, c.getNombre())){
				c.setNombre(txtnom.getText());
				try {
					//c.actualizarClienteBBDD(c.getDni(), "nombre", "emo3abcd");
					c.actualizarClienteBBDD(c.getDni(), "nombre", txtnom.getText());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
			}
			
		}
		
	}
	public Clientes buscarCliente(String id) throws ClassNotFoundException, SQLException{
		Clientes c2=new Clientes();
		ResultSet rs = c2.mostrarDatosClientePorDni(id);
		if(rs.next()){
			c2.setDni(rs.getString(1));
			c2.setNombre(rs.getString(2));
			c2.setApellidos(rs.getString(3));
			c2.setDireccion(rs.getString(4));
			c2.setTelefono(rs.getInt(5));
			c2.setEmail(rs.getString(6));
			c2.setSexo(rs.getString(7).charAt(0));
			c2.setFecha_nacimiento(rs.getDate(8));
			c2.setFecha_ingreso(rs.getDate(9));
		}else{
			JOptionPane.showMessageDialog(this, "No se encontro cliente con DNI "+id);
		}
		return c2;
	}
	public void cargarCliente(Clientes c){
		txtnom.setText(c.getNombre());
		txtapels.setText(c.getApellidos());
		txtdir.setText(c.getDireccion());
		txttel.setText(String.valueOf(c.getTelefono()));
		txtmail.setText(c.getEmail());
		date1.setDate(c.getFecha_nacimiento());
		date2.setDate(c.getFecha_ingreso());
		if(c.getSexo()=='V'){
			sex[0].setSelected(true);
		}else if(c.getSexo()=='H'){
			sex[1].setSelected(true);
		}
		
	}
	public void activarTextfieldDni(boolean cond){
		txtdni.setEnabled(cond);
	}
	public void activarTextfield(Boolean cond){
		txtnom.setEnabled(cond);
		txtapels.setEnabled(cond);
		txtdir.setEnabled(cond);
		txttel.setEnabled(cond);
		txtmail.setEnabled(cond);
		date1.setEnabled(cond);
		sex[0].setEnabled(cond);
		sex[1].setEnabled(cond);
		//date2.setEnabled(cond);
	}
	public boolean comprobarCambioTxt(JTextField tf, String valor){
		boolean cond=true;
		
		if(!tf.getText().equals(valor)){
			cond=false;
		}
		return cond;
	}
}
