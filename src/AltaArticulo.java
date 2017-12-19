
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
	private JPanel principal, centro, sur;
	private JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8,lb9;
	private JTextField tf1, tf2, tf3, tf5, tf8;
	private HintTextField tf4;
	private JButton btn;
	private JComboBox<String> cb, cb2, cb3;
	private DefaultComboBoxModel<String> dcb, dcb2,dcb3;
	
	public AltaArticulo() throws ClassNotFoundException, SQLException {
		this.setPreferredSize(new Dimension(1050, 500));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		this.getContentPane().setBackground(Color.white);
		principal=new JPanel(new BorderLayout(20, 20));
		principal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Alta Articulo", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), Color.GRAY));
		principal.setBackground(Color.white);
		
		centro=new JPanel(new GridLayout(9, 2, 20, 20));
		centro.setBorder(new EmptyBorder(0, 20, 0, 20));
		centro.setBackground(Color.WHITE);
		
		lb1=new JLabel("Nombre");
		lb2=new JLabel("Precio");
		lb3=new JLabel("Descripcion");
		lb4=new JLabel("Volumen");
		lb5=new JLabel("Embalaje");
		lb6=new JLabel("Proveedor");
		lb7=new JLabel("Categoría");
		lb8=new JLabel("Subcategoría");
		lb9=new JLabel("Stock");
		
		tf1=new JTextField(11);
		tf2=new JTextField(11);
		tf3=new JTextField(11);
		tf4=new HintTextField(" en ml");
		tf5=new JTextField();
		//tf6=new JTextField();
		dcb3=new DefaultComboBoxModel<>();
		dcb3=buscarProveedor();
		cb3=new JComboBox<>(dcb3);
		//tf7=new JTextField();
		dcb=new DefaultComboBoxModel<>();
		dcb=buscarCategoria();
		cb=new JComboBox<>(dcb);
		tf8=new JTextField();
		
		dcb2=new DefaultComboBoxModel<>();
		dcb2=buscarCategoria2(cb.getSelectedIndex());
		cb2=new JComboBox<>(dcb2);
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
		
		principal.add(centro, BorderLayout.CENTER);
		
		sur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		sur.setBackground(Color.WHITE);
		btn=new JButton("Insertar");
		
		sur.add(btn);
		
		principal.add(sur, BorderLayout.SOUTH);
				
		this.getContentPane().add(principal, BorderLayout.CENTER);
		
		cb.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource()==cb){
					cb2.removeAll();
					dcb2.removeAllElements();
					try {
						dcb2=buscarCategoria2(cb.getSelectedIndex());
						cb2.setModel(dcb2);
						
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
		btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn) {
			if(comprobar()) {
				try {
					JOptionPane.showMessageDialog(this, recogerCif());
					JOptionPane.showMessageDialog(this, recogerIdCat());
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
				//Articulo(int id_articulo, String nombre, Double precio, String descripcion, String volumen, String embalaje,String cif, int id_categoria, int stock)
				try {
					Articulo a=new Articulo(1,tf1.getText(),Double.parseDouble(tf2.getText()),tf3.getText(),tf4.getText()+"ml",tf5.getText(),recogerCif(),recogerIdCat(),Integer.parseInt(tf8.getText()));
					a.insertNoId();
					JOptionPane.showMessageDialog(this, "Se inserto artículo correctamente");
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				vaciar();
			}
		}
		
	}
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
	//JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
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
		}else if(v.campovacio(tf3.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo descripción no puede estar vacío");
		}else if(v.campovacio(tf4.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo volumen no puede estar vacío");
		}else if(!v.isNumeric(tf4.getText())) {
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo volumen debe ser numérico");
		}
		else if(v.campovacio(tf5.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo embalaje no puede estar vacío");
		}else if(v.campovacio(tf8.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo stock no puede estar vacío");
		}else if(!v.isNumeric(tf8.getText())) {
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo stock debe ser numérico");
		}
		return cond;
	}
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
