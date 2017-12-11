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
		pcen=new JPanel(new GridLayout(9, 2,50,15));
		pcen.setBorder(new EmptyBorder(0, 20, 0, 20));
		ptotal.add(pcen,BorderLayout.CENTER);
		pcen.setBackground(Color.white);
		
		//parte izquierda
		String[] texto={"DNI","Nombre","Apellidos","Fecha Nacimiento","Direccion","Telefono","Email","Sexo","Fecha Ingreso"};
		lbl=new JLabel[texto.length];
		for (int i = 0; i < texto.length; i++) {
			lbl[i]=new JLabel(texto[i]);
		}
		
		//parte derecha
		txtdni=new HintTextField("Introduce 8 numeros");
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
			//funcion que busque en la base de datos e instancien un cliente
			//funcion que cargue los datos del cliente en los textfield
			//activa el resto de textfield y bloquea el del dni
		}else if(arg0.getSource()==btnmod){
			
		}
		
	}
	public Clientes buscarCliente(int id) throws ClassNotFoundException, SQLException{
		Clientes c=new Clientes();
		ResultSet rs = c.mostrarDatosClientePorDni(id);
		if(rs.next()){
			
		}
	}
}
