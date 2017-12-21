import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
		//propiedades de la ventana
		this.setPreferredSize(new Dimension(1050, 640));
		this.getContentPane().setBackground(Color.white);
		this.setClosable(false);
		this.setMaximizable(false);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,40,30));
		
		//panel total y propiedades
		ptotal=new JPanel(new BorderLayout(150,30));
		ptotal.setBackground(Color.white);
		ptotal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65), 1), "MODIFICACIÓN CLIENTE",TitledBorder.LEFT,TitledBorder.TOP,new Font(null, Font.BOLD,25), new Color(41, 53, 65)));
		this.getContentPane().add(ptotal);
		
		//panel central y propiedades
		pcen=new JPanel(new GridLayout(9, 2,50,12));
		pcen.setBorder(new EmptyBorder(70, 350, 40, 350));
		ptotal.add(pcen,BorderLayout.CENTER);
		pcen.setBackground(Color.white);
		
		//parte izquierda
		//Definicion de las etiquetas
		String[] texto={"DNI","Nombre","Apellidos","Fecha Nacimiento","Direccion","Telefono","Email","Sexo","Fecha Ingreso"};
		lbl=new JLabel[texto.length];
		for (int i = 0; i < texto.length; i++) {
			lbl[i]=new JLabel(texto[i]);
			lbl[i].setFont(new Font("Arial",Font.PLAIN,16));
		}
		
		//parte derecha
		//Defincion de los textfields, hinttextfield , jdatechooser y radiobuttons todos deshabilitados menos dni
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
		
		//panel sur con los botones
		psur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		ptotal.add(psur, BorderLayout.SOUTH);
		btnbuscar=new BotonInterior("Buscar");
		btnmod=new BotonInterior("Modificar");
		btnmod.setEnabled(false); //boton modificar deshabilitado
		btnbuscar.addActionListener(this);
		btnmod.addActionListener(this);
		psur.add(btnbuscar);
		psur.add(btnmod);
		psur.setBackground(Color.white);
		
	}
	//escuchas
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnbuscar){//escucha del botono buscar, busca dni, muestra los datos en el campo y habilita el resto de campos y el boton modificar
			c = new Clientes();//se crea cliente vacio
			ResultSet rs;
			try {
				rs = c.mostrarDatosClientePorDni(txtdni.getText()); //se consulta los datos del cliente por dni
				if(rs.next()){
					try {
						c=buscarCliente(txtdni.getText());//se cargan los datos en el cliente
						cargarCliente(c); //se cargan los datos del cliente en los campos
						activarTextfield(true);//se activan el resto de textfields
						btnmod.setEnabled(true);//se activa boton modificar
					
					} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(this, "Error al cargar el cliente, vuelva a intentarlo");
					e.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(this, "No se encontro cliente con DNI "+txtdni.getText());
				}
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else if(arg0.getSource()==btnmod){//escucha para modificar
			
			try {
				modificar();//se modifican datos en la base de datos
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	/**
	 * Funcion que busca en la base de datos el dni que se le pasa como parametro y devuelve el objeto cliente con esos datos
	 * @param id
	 * @return Devuelve objeto cliente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
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
		}
		return c2;
	}
	/**
	 * Funcion que cargar los datos en los campos del objeto cliente que se le pasa como parametro
	 * @param c
	 */
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
	/**
	 * Funcion que activa/desactiva todos los campos de la ventana menos el dni, en funcion del booleano que se le pasa como parametro. True activa, False deshabilita
	 * @param cond
	 */
	public void activarTextfield(Boolean cond){
		txtnom.setEnabled(cond);
		txtapels.setEnabled(cond);
		txtdir.setEnabled(cond);
		txttel.setEnabled(cond);
		txtmail.setEnabled(cond);
		date1.setEnabled(cond);
		sex[0].setEnabled(cond);
		sex[1].setEnabled(cond);
	}
	/**
	 * Funcion que comprueba si hubo un cambio en el campo, se le pasa el jtextfield y el valor inicial
	 * @param tf
	 * @param valor
	 * @return Devuelve true si el campo contiene el mismo valor, y falso si cambio
	 */
	public boolean comprobarCambioTxt(JTextField tf, String valor){
		boolean cond=true;
		if(!tf.getText().equals(valor)){
			cond=false;
		}
		return cond;
	}
	/**
	 * Funcion que modifica los datos del cliente, comprobando que se realizo alguna modificación y confirmando mediante mensaje
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modificar() throws ClassNotFoundException, SQLException{
		if(comprobar()){
			if(!comprobarCambioTxt(txtnom, c.getNombre())&&comprobarNumerosDentro(txtnom.getText())){
				if(JOptionPane.showConfirmDialog(this, "¿Desea cambiar el nombre del cliente?","Modificacion cliente", JOptionPane.YES_NO_OPTION)==0) {
					c.setNombre(txtnom.getText());
					c.actualizarClienteBBDD(c.getDni(), "nombre", c.getNombre());
					JOptionPane.showMessageDialog(this, "Se modificó el nombre del cliente "+c.getDni());
				}
				
			}
			if(!comprobarCambioTxt(txtapels, c.getApellidos())&& comprobarNumerosDentro(txtapels.getText())){
				if(JOptionPane.showConfirmDialog(this, "¿Desea cambiar el apellido del cliente?","Modificacion cliente", JOptionPane.YES_NO_OPTION)==0) {
					c.setApellidos(txtapels.getText());
					c.actualizarClienteBBDD(c.getDni(), "apellidos", c.getApellidos());
					JOptionPane.showMessageDialog(this, "Se modificó los apellidos del cliente "+c.getDni());
				}
			}
			if(!comprobarCambioTxt(txtdir, c.getDireccion())){
				if(JOptionPane.showConfirmDialog(this, "¿Desea cambiar la dirección del cliente?","Modificacion cliente", JOptionPane.YES_NO_OPTION)==0) {
					c.setDireccion(txtdir.getText());
					c.actualizarClienteBBDD(c.getDni(), "direccion", c.getDireccion());
					JOptionPane.showMessageDialog(this, "Se modificó la direcció del cliente "+c.getDni());
				}
			}
			if(!comprobarCambioTxt(txttel, String.valueOf(c.getTelefono()))){
				if(JOptionPane.showConfirmDialog(this, "¿Desea cambiar el teléfono del cliente?","Modificacion cliente", JOptionPane.YES_NO_OPTION)==0) {
					c.setDireccion(txttel.getText());
					c.actualizarClienteBBDD(c.getDni(), "telefono", String.valueOf(c.getTelefono()));
					JOptionPane.showMessageDialog(this, "Se modificó el telefono del cliente "+c.getDni());
				}
				
			}
			if(!comprobarCambioTxt(txtmail, c.getEmail())){
				if(JOptionPane.showConfirmDialog(this, "¿Desea cambiar el email del cliente?","Modificacion cliente", JOptionPane.YES_NO_OPTION)==0) {
					c.setEmail(txtmail.getText());
					c.actualizarClienteBBDD(c.getDni(), "email", c.getEmail());
					JOptionPane.showMessageDialog(this, "Se modificó el email del cliente "+c.getDni());
				}
				
			}
			if(sex[0].isSelected() && c.getSexo()!='V'){
				if(JOptionPane.showConfirmDialog(this, "¿Desea cambiar el sexo del cliente?","Modificacion cliente", JOptionPane.YES_NO_OPTION)==0) {
					c.setSexo('V');
					c.actualizarClienteBBDD(c.getDni(), "sexo", String.valueOf(c.getSexo()));
					JOptionPane.showMessageDialog(this, "Se modificó el sexo del cliente "+c.getDni());
				}
			}else if(sex[1].isSelected() && c.getSexo()!='H'){
				if(JOptionPane.showConfirmDialog(this, "¿Desea cambiar el sexo del cliente?","Modificacion cliente", JOptionPane.YES_NO_OPTION)==0) {
					c.setSexo('V');
					c.actualizarClienteBBDD(c.getDni(), "sexo", String.valueOf(c.getSexo()));
					JOptionPane.showMessageDialog(this, "Se modificó el sexo del cliente "+c.getDni());
				}
			}
			c.actualizarClienteBBDD(c.getDni(), "sexo", String.valueOf(c.getSexo()));
			java.sql.Date sqlDate1 = new java.sql.Date(date1.getDate().getTime());
			if(!sqlDate1.equals(c.getFecha_nacimiento())){
				if(JOptionPane.showConfirmDialog(this, "¿Desea cambiar la fecha de nacimiento del cliente?","Modificacion cliente", JOptionPane.YES_NO_OPTION)==0) {
					Conexion con=new Conexion();
					c.setFecha_nacimiento(sqlDate1);
					JOptionPane.showMessageDialog(this, getFechaSQL(date1));
					con.modificar("update clientes set fecha_nacimiento = '"+getFechaSQL(date1)+"' where dni like '"+c.getDni()+"'");
					JOptionPane.showMessageDialog(this, "Se modificó la fecha de nacimiento del cliente "+c.getDni());
				}
				
			}
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
	
	//validaciones
	/**
	 * Funcion que comprueba que los campos estan cumplimentados correctamente
	 * @return Devuelve true si estan bien validados, false si no lo estan
	 */
	public boolean comprobar(){
		boolean cond=true;
		Validaciones v=new Validaciones();
		if(v.campovacio(txtnom.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo nombre no puede estar vacío");
		}else if(v.campovacio(txtapels.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo apellido no puede estar vacío");
		}else if(v.campovacio(txtdir.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo direccion no puede estar vacío");
		}else if(v.campovacio(txttel.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo telefono no puede estar vacío");
		}else if(v.campovacio(txtmail.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo email no puede estar vacío");
		}else if(!comprobarFecha(date1)){
			cond=false;
		}else if(!comprobarFechaExiste(date1)){
			cond=false;
			JOptionPane.showMessageDialog(this, "La fecha es erronea");
		}else if(v.validartelefono(txttel.getText())){
			JOptionPane.showMessageDialog(this, "Telefono no válido");
			cond=false;
		}else if(!v.validaremail(txtmail.getText())){
			JOptionPane.showMessageDialog(this, "Email no válido");
			cond=false;
			
		}
		return cond;
	}
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
			JOptionPane.showMessageDialog(this, "La fecha de nacimiento no es correcta");
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
				JOptionPane.showMessageDialog(this, "No se permiten numeros en los campos nombre y apellidos");
			}catch(NumberFormatException e) {}
		
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
}
