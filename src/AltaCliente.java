import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DateFormatter;



import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javafx.scene.control.DatePicker;

public class AltaCliente extends JInternalFrame implements ActionListener{
	private JPanel ptotal,pizq,pder,pcen,psur,pradiobtn;
	private JLabel[] lbl;
	private HintTextField txtdni;
	private JTextField txtnom,txtapels,txtdir,txttel,txtmail;
	private JButton btn,btnborrar;
	private JRadioButton[] sex;
	private Dni d;
	private JDateChooser date1,date2;
	AltaCliente(){
		//tamaño 1050,500
		this.setPreferredSize(new Dimension(1050, 640));
		this.getContentPane().setBackground(Color.white);
		this.setClosable(false);
		this.setMaximizable(false);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,40,30));
		ptotal=new JPanel(new BorderLayout(150,30));
		ptotal.setBackground(Color.white);
		ptotal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65), 1), "ALTA CLIENTE",TitledBorder.LEFT,TitledBorder.TOP,new Font(null, Font.BOLD,25), new Color(41, 53, 65)));
		this.getContentPane().add(ptotal);
		pcen=new JPanel(new GridLayout(9, 2,50,12));
		pcen.setBorder(new EmptyBorder(70, 350, 40, 350));
		ptotal.add(pcen,BorderLayout.CENTER);
		pcen.setBackground(Color.white);
		
		//parte izquierda
		String[] texto={"DNI","Nombre","Apellidos","Fecha Nacimiento","Direccion","Telefono","Email","Sexo","Fecha Ingreso"};
		lbl=new JLabel[texto.length];
		for (int i = 0; i < texto.length; i++) {
			lbl[i]=new JLabel(texto[i]);
			lbl[i].setFont(new Font("Arial",Font.PLAIN,16));
		}
		
		//parte derecha
		txtdni=new HintTextField("Introduce 8 numeros");
		txtdni.setColumns(11);
		txtnom=new JTextField(10);
		txtapels=new JTextField(10);
		txtdir=new JTextField(10);
		txttel=new JTextField(10);
		txtmail=new JTextField(10);
		date1=new JDateChooser("dd-MM-yyyy", "####-##-##", ' ');		
		date2=new JDateChooser("dd-MM-yyyy", "####-##-##", ' ');
		Calendar hoy = new GregorianCalendar().getInstance();
		
		date2.setCalendar(hoy);
		date2.setEnabled(false);
	
		//panel radiobuttons sexo
		pradiobtn = new JPanel(new GridLayout(1, 2));
		pradiobtn.setBackground(Color.white);
		ButtonGroup bg=new ButtonGroup();
		String[] texsex={"V","H"};
		sex=new JRadioButton[texsex.length];
	
		for (int i = 0; i < texsex.length; i++) {
			sex[i]=new JRadioButton(texsex[i]);
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
		
		//botones
		psur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		ptotal.add(psur, BorderLayout.SOUTH);
		btn=new BotonInterior("Enviar");
		btnborrar=new BotonInterior("Borrar");
		btn.addActionListener(this);
		btnborrar.addActionListener(this);
		psur.add(btn);
		psur.add(btnborrar);
		psur.setBackground(Color.white);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn){
			int[] fechaing= new int[3];
			int[] fechanaci=new int[3];
			Date d1= date1.getDate();
			Date d2=date2.getDate();
			
			try {
				if(comprobar()&&comprobarRB()&&comprobarDniBBDD(txtdni.getText())&&comprobarNumerosDentro(txtnom.getText())&&comprobarNumerosDentro(txtapels.getText())){
					if(comprobarFecha(date1)&&comprobarFecha(date2)) {
						java.sql.Date sqlDate1 = new java.sql.Date(d1.getTime());
						java.sql.Date sqlDate2 = new java.sql.Date(d2.getTime());
						if(comprobarFechaExiste(date1)) {
							Clientes c=new Clientes(d.recogerdniconletra(txtdni.getText()), Integer.parseInt(txttel.getText()), txtnom.getText(), txtapels.getText(), txtdir.getText(), txtmail.getText(), recogerSexo(), sqlDate1,sqlDate2);
							try {
								c.insertarClienteBBDD();
								JOptionPane.showMessageDialog(this, "Se creó cliente nuevo con DNI "+txtdni.getText());
								vaciar();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}else {
							JOptionPane.showMessageDialog(this, "La fecha de nacimiento introducida no es valida");
						}
					}
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource()==btnborrar){
			vaciar();
		}
	}
	
	//validaciones
	public boolean comprobarFecha(JDateChooser jd) {
		boolean cond=false;
		if(jd.getDate()!=null) {
			cond=true;
		}else {
			JOptionPane.showMessageDialog(this, "No inserto la fecha de nacimiento correctamente");
		}
		return cond;
	}
	public String getFecha(JDateChooser jd) {
		SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyyy");
		if(jd.getDate()!=null) {
			return formato.format(jd.getDate());
		}else {
			return null;
		}
	}
	public String getFechaSQL(JDateChooser jd) {
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		if(jd.getDate()!=null) {
			return formato.format(jd.getDate());
		}else {
			return null;
		}
	}
	public boolean comprobar(){
		boolean cond=true;
		Validaciones v=new Validaciones();
		d=new Dni();
		if(v.campovacio(txtdni.getText())||v.campovacio(txtnom.getText())||v.campovacio(txtapels.getText())||v.campovacio(txtdir.getText())||v.campovacio(txttel.getText())||v.campovacio(txtmail.getText())){
			JOptionPane.showMessageDialog(this, "Dejó algún campo vacio, todos son obligatorios");
			cond=false;
		}else if(!d.validardnisinletra(txtdni.getText())){
			JOptionPane.showMessageDialog(this, "DNI incorrecto");
			txtdni.setText("");
			cond=false;
		}else if(v.validartelefono(txttel.getText())){
			JOptionPane.showMessageDialog(this, "Telefono no válido");
			cond=false;
		}else if(!v.validaremail(txtmail.getText())){
			JOptionPane.showMessageDialog(this, "Email no válido");
			cond=false;
			
		}
		return cond;
	}
	public boolean comprobarRB(){
		boolean cond=true;
		if(!sex[0].isSelected()&&!sex[1].isSelected()){
			JOptionPane.showMessageDialog(this, "Sexo no seleccionado");
			cond=false;
		}
		return cond;
	}
	public boolean comprobarFechaExiste(JDateChooser jd){
		int dia=Integer.parseInt(getFecha(jd).split("-")[0]);
		int mes=Integer.parseInt(getFecha(jd).split("-")[1]);
		int año=Integer.parseInt(getFecha(jd).split("-")[2]);
		boolean cond=true;
	
		Calendar hoy = new GregorianCalendar().getInstance();
		if(año<1900||año>hoy.get(Calendar.YEAR)){
			cond=false;
			
		}else if(año==hoy.get(Calendar.YEAR)){
			if(mes>hoy.get(Calendar.MONTH)+1){
				cond=false;
			
			}else if(mes==hoy.get(Calendar.MONTH)+1){
				if(dia>hoy.get(Calendar.DAY_OF_MONTH)){
					cond=false;
				
				}
			}
		}else if(mes<1||mes>12){
			cond=false;
		
		}else if(mes==2){
			if(año%4==0 || (año%100!=0&&año%400==0)){
				if(dia<1||dia>29){
					cond=false;
				}
			}else {
				if(dia<1||dia>28){
					cond=false;
				}
			}
		}else if(mes==4||mes==6||mes==9||mes==11){
			if(dia<1||dia>30){
				cond=false;
			}
		}else{
			if(dia<1||dia>31){
				cond=false;
			}
		}

		return cond;
	}
	public boolean comprobarDniBBDD(String dni) throws ClassNotFoundException, SQLException {
		boolean cond=true;
		Conexion c=new Conexion();
		ResultSet rs=c.consultar("select * from clientes where dni like '"+dni+"'");
		if(rs.next()) {
			cond=false;
			JOptionPane.showMessageDialog(this, "DNI en uso");
		}
		c.close();
		return cond;
	}
	public boolean comprobarNumerosDentro(String s) {
		boolean cond=true;
		for (int j = 0; j < s.length() && cond; j++) {
			try{
				Integer.parseInt(String.valueOf(s.charAt(j)));
				cond=false;
				JOptionPane.showMessageDialog(this, "No se permiten numeros en los campos nombre y apellidos");
			}catch(NumberFormatException e) {}
		
		}
		return cond;
	}
	public char recogerSexo() {
		char sexo=' ';
		if(sex[0].isSelected()) {
			sexo= 'V';
		}else if(sex[1].isSelected()) {
			sexo= 'H';
		}
		return sexo;
	}
	public void vaciar(){
		txtdni.setText("");
		txtnom.setText("");
		txtapels.setText("");
		txtdir.setText("");
		txtmail.setText("");
		txttel.setText("");
		date1.setCalendar(null);
		sex[0].setSelected(false);
		sex[1].setSelected(false);
	}
	
}
