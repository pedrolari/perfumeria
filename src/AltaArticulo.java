
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class AltaArticulo extends JInternalFrame implements ActionListener{
	private JPanel principal, centro, sur; //paneles
	private JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8,lb9; //etiquetas
	private JTextField tf1, tf2, tf3, tf5, tf8;//textfield
	private HintTextField tf4;//textfield con hint
	private JButton btn;//bton
	private JComboBox<String> cb, cb2, cb3; //combobox para proveedor, categoria y subcategoria
	private DefaultComboBoxModel<String> dcb, dcb2,dcb3; //default comboboxmodel de los combobox
	
	public AltaArticulo() throws ClassNotFoundException, SQLException {
		//se define tamaño y propiedades 
		this.setPreferredSize(new Dimension(1050, 640));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		this.getContentPane().setBackground(Color.white);
		
		//panel principal
		principal=new JPanel(new BorderLayout(20, 20));
		principal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65)), "ALTA ARTICULO", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), new Color(41, 53, 65)));
		principal.setBackground(Color.white);
		
		//panel central
		centro=new JPanel(new GridLayout(9, 2, 20, 20));
		centro.setBorder(new EmptyBorder(50, 350, 40, 350));
		centro.setBackground(Color.WHITE);
		
		//definicion de label y propiedades
		lb1=new JLabel("Nombre");
		lb2=new JLabel("Precio");
		lb3=new JLabel("Descripcion");
		lb4=new JLabel("Volumen");
		lb5=new JLabel("Embalaje");
		lb6=new JLabel("Proveedor");
		lb7=new JLabel("Categoría");
		lb8=new JLabel("Subcategoría");
		lb9=new JLabel("Stock");
		lb1.setFont(new Font("Arial",Font.PLAIN,16));
		lb2.setFont(new Font("Arial",Font.PLAIN,16));
		lb3.setFont(new Font("Arial",Font.PLAIN,16));
		lb4.setFont(new Font("Arial",Font.PLAIN,16));
		lb5.setFont(new Font("Arial",Font.PLAIN,16));
		lb6.setFont(new Font("Arial",Font.PLAIN,16));
		lb7.setFont(new Font("Arial",Font.PLAIN,16));
		lb8.setFont(new Font("Arial",Font.PLAIN,16));
		lb9.setFont(new Font("Arial",Font.PLAIN,16));
		
		//definicion de textfield, hinttextfield, combobox y defaultlistmodel
		tf1=new JTextField(11);
		tf2=new JTextField(11);
		tf3=new JTextField(11);
		tf4=new HintTextField(" en ml");
		tf5=new JTextField();
		dcb3=new DefaultComboBoxModel<>(); //default listmodel de proveedores
		dcb3=buscarProveedor(); //se le pasan los proveedores
		cb3=new JComboBox<>(dcb3);
		dcb=new DefaultComboBoxModel<>(); //defaultlistmodel de categorias
		dcb=buscarCategoria(); //se le pasan las categorias
		cb=new JComboBox<>(dcb);
		tf8=new JTextField();
		dcb2=new DefaultComboBoxModel<>(); //default listmodel de subcategorias
		dcb2=buscarCategoria2(cb.getSelectedIndex()); //se le pasan las subcategorias en funcion de la categoria que esta seleccionada
		cb2=new JComboBox<>(dcb2);
		
		//se añaden los componentes al panel central
		centro.add(lb1);
		centro.add(tf1);
		
		centro.add(lb2);
		centro.add(tf2);
		
		centro.add(lb3);
		centro.add(tf3);
		
		centro.add(lb4);
		centro.add(tf4);
		
		centro.add(lb5);
		centro.add(tf5);
		
		centro.add(lb6);
		centro.add(cb3);
		
		centro.add(lb7);
		centro.add(cb);
		
		centro.add(lb8);
		centro.add(cb2);
		
		centro.add(lb9);
		centro.add(tf8);
		
		//se añade panel central al principal
		principal.add(centro, BorderLayout.CENTER);
		
		//defincion panel sur que contiene boton insertar
		sur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		sur.setBackground(Color.WHITE);
		btn=new BotonInterior("Insertar");
		sur.add(btn);
		principal.add(sur, BorderLayout.SOUTH);
		
		//se añade panel principal
		this.getContentPane().add(principal, BorderLayout.CENTER);
		
		//escucha del combobox de categorias, para que cambien las subcategorias en funcion de la categoria elegida
		cb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource()==cb){
					cb2.removeAll(); //borra todo los items del combobox
					dcb2.removeAllElements(); //borra todos los items del defaultlistmodel
					try {
						dcb2=buscarCategoria2(cb.getSelectedIndex()); //le pasamos las subcategorias que corresponden
						cb2.setModel(dcb2); //se asigna el default listmodel al combobox
						
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		btn.addActionListener(this);//se implementa escucha del boton
	}

	//escucha del boton
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn) {
			if(comprobar()) { //comprueba que todo los campos estan correctamente cumplimentados
				try {
					//crea un articulo, se le pasa 1 porque el id no se va a insertar y no es necesario porque es autoincremental
					Articulo a=new Articulo(1,tf1.getText(),Double.parseDouble(tf2.getText()),tf3.getText(),tf4.getText()+"ml",tf5.getText(),recogerCif(),recogerIdCat(),Integer.parseInt(tf8.getText()));
					a.insertNoId();//se inserta  articulo sin id (autoincremental)
					JOptionPane.showMessageDialog(this, "Se inserto artículo correctamente");
					vaciar();//se vacian los campos
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, "Error al insertar articulo, intentelo de nuevo");
				}
				
			}
		}
		
	}
	/**
	 * Funcion que devuelve DefaultComboBoxModel<String> con las categorias que son = 0 (padre)
	 * @return Devuelve DefaultComboBoxModel<String> con las categorias que son = 0 (padre)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public DefaultComboBoxModel<String> buscarCategoria() throws ClassNotFoundException, SQLException{
		DefaultComboBoxModel<String> aux = new DefaultComboBoxModel<String>();
		Conexion c=new Conexion();
		ResultSet rs = c.consultar("select * from categorias where id_categoria_padre = 0");
		while(rs.next()){
			aux.addElement(rs.getString(2));
		}
		c.close();
		return aux;
	}
	/**
	 * Función que devuelve defaultcomboboxmodel con las subcategorias en función de la categoria seleccionada
	 * @param i Se le pasa el indice del elemento seleccionado del combobox categorias
	 * @return Devuelve DefaultComboBoxModel con las subcategorias correspondientes a la categoria seleccionada 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public DefaultComboBoxModel<String> buscarCategoria2(int i) throws ClassNotFoundException, SQLException{
		DefaultComboBoxModel<String> aux = new DefaultComboBoxModel<String>();
		Conexion c=new Conexion();
		ResultSet rs = c.consultar("select * from categorias where id_categoria_padre = "+(i+1));
		while(rs.next()){
			aux.addElement(rs.getString(2));
		}
		c.close();
		return aux;
	}
	/**
	 * Funcion que devuelve defaultcomboboxmodel con todos los proveedores menos el de la propia empresa
	 * @return Devuelve defaultcomboboxmodel<String> con los proveedores menos el de la propia empresa
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public DefaultComboBoxModel<String> buscarProveedor() throws ClassNotFoundException, SQLException{
		DefaultComboBoxModel<String> aux = new DefaultComboBoxModel<String>();
		Conexion c=new Conexion();
		ResultSet rs = c.consultar("select * from proveedores where cif not like 'A3333333'");
		while(rs.next()){
			aux.addElement(rs.getString(2));
		}
		c.close();
		return aux;
	}
	/**
	 * Funcion que valida todos los campos y muestra mensaje de error si no estan bien cumplimentados. True si esta todo validado, False si hay algun campo mal cumplimentado
	 * @return Booleano, true si esta todo validado, false si hay algun campo mal cumplimentado
	 */
	public boolean comprobar(){
		boolean cond=true;
		Validaciones v=new Validaciones();
		if(v.campovacio(tf1.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo nombre no puede estar vacío");
		}else if(!comprobarNumerosDentro(tf1.getText())) {
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo nombre no puede contener números");
		}else if(v.campovacio(tf2.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo precio no puede estar vacío");
		}else if(!v.isDouble(tf2.getText())) {
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo precio debe ser numérico");
		}else if(Integer.parseInt(tf2.getText())<0){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo precio no puede ser menor que 0");
		}else if(v.campovacio(tf3.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo descripción no puede estar vacío");
		}else if(v.campovacio(tf4.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo volumen no puede estar vacío");
		}else if(!v.isNumeric(tf4.getText())) {
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo volumen debe ser numérico");
		}else if(Integer.parseInt(tf4.getText())<0){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo volumen no puede ser menor que 0");
		}else if(v.campovacio(tf5.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo embalaje no puede estar vacío");
		}else if(v.campovacio(tf8.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo stock no puede estar vacío");
		}else if(!v.isNumeric(tf8.getText())) {
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo stock debe ser numérico");
		}else if(Integer.parseInt(tf8.getText())<0){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo  stock no puede ser menor que 0");
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
			}catch(NumberFormatException e) {}
		
		}
		return cond;
	}
	/**
	 * Funcion que devuelve string con el cif seleccionado del combobox
	 * @return String. Devuelve Cif del combobox seleccionado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String recogerCif() throws ClassNotFoundException, SQLException {
		String cif="";
		Conexion con=new Conexion();
		ResultSet rs = con.consultar("select * from proveedores where nombre like '"+dcb3.getSelectedItem().toString()+"'");
		if(rs.next()) {
			cif=rs.getString(1);
		}
		con.close();
		return cif;
	}
	/**
	 * Función que devuelve el id de la categoria en funcion de la categoria y subcategorias seleccionadas en sus respectivos combobox
	 * @return Id_categoria
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int recogerIdCat() throws ClassNotFoundException, SQLException {
		int idCat=0;
		Conexion con=new Conexion();
	//	JOptionPane.showMessageDialog(this,"select * from categorias where nombre like '"+dcb2.getSelectedItem().toString()+"' and id_categoria_padre = "+(cb.getSelectedIndex()+1));
		ResultSet rs = con.consultar("select * from categorias where nombre like '"+dcb2.getSelectedItem().toString()+"' and id_categoria_padre = "+(cb.getSelectedIndex()+1));
		if(rs.next()) {
			idCat=rs.getInt(1);
		}
		con.close();
		return idCat;
	}
	/**
	 * Funcion que vacia todos los campos de la ventan y los combobox los inicia al 0
	 */
	public void vaciar() {
		tf1.setText("");
		tf2.setText("");
		tf3.setText("");
		tf4.setText("");
		tf5.setText("");
		tf8.setText("");
		cb.setSelectedIndex(0);
		cb2.setSelectedIndex(0);
		cb3.setSelectedIndex(0);
	}
	
}
