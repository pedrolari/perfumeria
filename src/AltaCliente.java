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
	private JPanel ptotal,pizq,pder,pcen,psur,pradiobtn; //paneles
	private JLabel[] lbl; //etiquetas
	private HintTextField txtdni; //texfield con hint
	private JTextField txtnom,txtapels,txtdir,txttel,txtmail; //textfield
	private JButton btn,btnborrar; //botones
	private JRadioButton[] sex; //radiobuton de sexo
	private ButtonGroup bg;//buttongruop que agrupa los radiobuttons
	private Dni d; //objeto dni
	private JDateChooser date1,date2; //cajas para fechas
	AltaCliente(){
		//propiedades de la ventana alta cliente
		this.setPreferredSize(new Dimension(1050, 640));
		this.getContentPane().setBackground(Color.white);
		this.setClosable(false);
		this.setMaximizable(false);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,40,30));
		
		//panel principal y sus propiedades
		ptotal=new JPanel(new BorderLayout(150,30));
		ptotal.setBackground(Color.white);
		ptotal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65), 1), "ALTA CLIENTE",TitledBorder.LEFT,TitledBorder.TOP,new Font(null, Font.BOLD,25), new Color(41, 53, 65)));
		this.getContentPane().add(ptotal);
		
		//panel central
		pcen=new JPanel(new GridLayout(9, 2,50,12));
		pcen.setBorder(new EmptyBorder(70, 350, 40, 350));
		ptotal.add(pcen,BorderLayout.CENTER);
		pcen.setBackground(Color.white);
		
		//parte izquierda
		//definicion de etiquetas
		String[] texto={"DNI","Nombre","Apellidos","Fecha Nacimiento","Direccion","Telefono","Email","Sexo","Fecha Ingreso"};
		lbl=new JLabel[texto.length];
		for (int i = 0; i < texto.length; i++) {
			lbl[i]=new JLabel(texto[i]);
			lbl[i].setFont(new Font("Arial",Font.PLAIN,16));
		}
		
		//parte derecha
		//definicion de textfields
		txtdni=new HintTextField("Introduce 8 numeros");
		txtdni.setColumns(11);
		txtnom=new JTextField(10);
		txtapels=new JTextField(10);
		txtdir=new JTextField(10);
		txttel=new JTextField(10);
		txtmail=new JTextField(10);
		date1=new JDateChooser("dd-MM-yyyy", "####-##-##", ' ');	// se le pasa el formato, luego como debe aparecer en pantalla, y el caracter que aparecera en la pantalla	
		date2=new JDateChooser("dd-MM-yyyy", "####-##-##", ' ');	// se le pasa el formato, luego como debe aparecer en pantalla, y el caracter que aparecera en la pantalla	
		Calendar hoy = new GregorianCalendar().getInstance(); //se instancia fecha del sistema en un calendar
		date2.setCalendar(hoy); //se pone fecha del sistema en el date2 (fecha_ingreso)
		date2.setEnabled(false);//se deshabilita campo date2 (fecha_ingreso) no modificable
		//panel radiobuttons sexo
		pradiobtn = new JPanel(new GridLayout(1, 2));
		pradiobtn.setBackground(Color.white);
		bg=new ButtonGroup();
		String[] texsex={"V","H"};
		sex=new JRadioButton[texsex.length];
		for (int i = 0; i < texsex.length; i++) {
			sex[i]=new JRadioButton(texsex[i]);
			bg.add(sex[i]);
			pradiobtn.add(sex[i]);
			sex[i].setBackground(Color.white);
		}

		//se añaden los elementos al panel central
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
		
		//panel sur que contiene botones de enviar y borrar
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
	//escucha para los botones
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn){ //escucha para insertar el cliente
			Date d1= date1.getDate(); //cogemos las fechas de los datachooser
			Date d2=date2.getDate();
			try {
				if(comprobar()&&comprobarRB()&&comprobarDniBBDD(txtdni.getText())&&comprobarNumerosDentro(txtnom.getText())&&comprobarNumerosDentro(txtapels.getText())){//se comprueban que todos los campos estan bien cumplimentados
					if(comprobarFecha(date1)&&comprobarFecha(date2)) { //se comprueba que las fechas son correctas
						java.sql.Date sqlDate1 = new java.sql.Date(d1.getTime());//se pasa a date de sql las fechas
						java.sql.Date sqlDate2 = new java.sql.Date(d2.getTime());
						if(comprobarFechaExiste(date1)) {//se comprueba si fechade nacimiento existe
							//se instancia cliente con los datos de los campos
							Clientes c=new Clientes(d.recogerdniconletra(txtdni.getText()), Integer.parseInt(txttel.getText()), txtnom.getText(), txtapels.getText(), txtdir.getText(), txtmail.getText(), recogerSexo(), sqlDate1,sqlDate2);
							try {
								c.insertarClienteBBDD();//se inserta cliente, se muestra mensaje de isertado y se vacian los campos
								JOptionPane.showMessageDialog(this, "Se creó correctamente cliente nuevo con DNI "+txtdni.getText(),"Alta Cliente",JOptionPane.INFORMATION_MESSAGE);
								vaciar();
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(this, "Error al insertar cliente, intentelo de nuevo","Alta Cliente",JOptionPane.INFORMATION_MESSAGE);
							}							
						}else {
							JOptionPane.showMessageDialog(this, "La fecha de nacimiento introducida no es valida","Alta Cliente",JOptionPane.INFORMATION_MESSAGE);
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
		}else if(e.getSource()==btnborrar){//escucha para borrar los campos 
			vaciar();
		}
	}
	
	//validaciones
	/**
	 * Funcion que comprueba si no esta vacía la fecha, devuelve true si no está vacia, false si esta vacia
	 * @param jd JDatachooser
	 * @return devuelve true si no está vacia, false si esta vacia
	 */
	public boolean comprobarFecha(JDateChooser jd) {
		boolean cond=false;
		if(jd.getDate()!=null) {
			cond=true;
		}else {
			JOptionPane.showMessageDialog(this, "No inserto la fecha de nacimiento correctamente","Alta Cliente",JOptionPane.INFORMATION_MESSAGE);
		}
		return cond;
	}
	/**
	 * Funcion a la que se le pasa el datachooser y devuelve la fecha en un string en formato 'dd-MM-yyyy'
	 * @param jd JdataChooser
	 * @return Devuelve la fecha en un string
	 */
	public String getFecha(JDateChooser jd) {
		SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyyy");
		if(jd.getDate()!=null) {
			return formato.format(jd.getDate());
		}else {
			return null;
		}
	}
	/**
	 * Funcion a la que se le pasa el datachooser y devuelve la fecha en un string en formato sql 'yyyy-MM-dd'
	 * @param jd JdataChooser
	 * @return Devuelve la fecha en un string
	 */
	public String getFechaSQL(JDateChooser jd) {
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		if(jd.getDate()!=null) {
			return formato.format(jd.getDate());
		}else {
			return null;
		}
	}
	/**
	 * Funcion que comprueba que los campos estan correctamente cumplimentados. Devuelve true si los campos se cumplimentaron correctamente y false si no lo estan.
	 * @return Devuelve true si los campos se cumplimentaron correctamente y false si no lo estan.
	 */
	public boolean comprobar(){
		boolean cond=true;
		Validaciones v=new Validaciones();
		d=new Dni();
		if(v.campovacio(txtdni.getText())||v.campovacio(txtnom.getText())||v.campovacio(txtapels.getText())||v.campovacio(txtdir.getText())||v.campovacio(txttel.getText())||v.campovacio(txtmail.getText())){
			JOptionPane.showMessageDialog(this, "Dejó algún campo vacio, todos son obligatorios","Alta cliente",JOptionPane.INFORMATION_MESSAGE);
			cond=false;
		}else if(!d.validardnisinletra(txtdni.getText())){
			JOptionPane.showMessageDialog(this, "DNI incorrecto","Alta Cliente",JOptionPane.INFORMATION_MESSAGE);
			txtdni.setText("");
			cond=false;
		}else if(v.validartelefono(txttel.getText())||!v.isNumeric(txttel.getText())){
			JOptionPane.showMessageDialog(this, "Telefono no válido","Alta Cliente",JOptionPane.INFORMATION_MESSAGE);
			cond=false;
		}else if(!v.validaremail(txtmail.getText())){
			JOptionPane.showMessageDialog(this, "Email no válido","Alta Cliente",JOptionPane.INFORMATION_MESSAGE);
			cond=false;
			
		}
		return cond;
	}
	/**
	 * Funcion que comprueba que se selecciono una opcion del seño
	 * @return true si selecciono alguna opcion, false si no se selecciono ninguna
	 */
	public boolean comprobarRB(){
		boolean cond=true;
		if(!sex[0].isSelected()&&!sex[1].isSelected()){
			JOptionPane.showMessageDialog(this, "Sexo no seleccionado","Alta Cliente",JOptionPane.INFORMATION_MESSAGE);
			cond=false;
		}
		return cond;
	}
	/**
	 * Funcion que comprueba que la fecha existe y que no es posterior a hoy. Se le pasa el JDatechooser
	 * @param jd
	 * @return Devuelve true si la fecha cumple los parametros, false si no los cumple
	 */
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
	/**
	 * Función que compureba si el dni esta en la base de datos
	 * @param dni
	 * @return true si no esta el dni en la base de datos, false si el dni esta en la BBDD
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean comprobarDniBBDD(String dni) throws ClassNotFoundException, SQLException {
		boolean cond=true;
		Conexion c=new Conexion();
		ResultSet rs=c.consultar("select * from clientes where dni like '"+dni+"'");
		if(rs.next()) {
			cond=false;
			JOptionPane.showMessageDialog(this, "DNI en uso","Alta Cliente",JOptionPane.INFORMATION_MESSAGE);
		}
		c.close();
		return cond;
	}
	/**
	 * Funcion que comprueba si un String que se le pasa como parametro contiene numeros dentro
	 * @param s 
	 * @return boolean Devuelve true si no contiene numeros, false si contiene numeros
	 */
	public boolean comprobarNumerosDentro(String s) {
		boolean cond=true;
		for (int j = 0; j < s.length() && cond; j++) {
			try{
				Integer.parseInt(String.valueOf(s.charAt(j)));
				cond=false;
				JOptionPane.showMessageDialog(this, "No se permiten numeros en los campos nombre y apellidos","Alta Cliente",JOptionPane.INFORMATION_MESSAGE);
			}catch(NumberFormatException e) {}
		
		}
		return cond;
	}
	/**
	 * Funcion que de devuelve el char seleccionado en el campo sexo
	 * @return Caracter V o H en función del radiobutton seleccionado
	 */
	public char recogerSexo() {
		char sexo=' ';
		if(sex[0].isSelected()) {
			sexo= 'V';
		}else if(sex[1].isSelected()) {
			sexo= 'H';
		}
		return sexo;
	}
	/**
	 * Función que vacia los campos
	 */
	public void vaciar(){
		txtdni.setText("");
		txtnom.setText("");
		txtapels.setText("");
		txtdir.setText("");
		txtmail.setText("");
		txttel.setText("");
		date1.setCalendar(null);
		bg.clearSelection();
		
	}
	
}
