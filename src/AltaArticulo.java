
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
	private JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
	private JButton btn;
	private JComboBox<String> cb, cb2;
	private DefaultComboBoxModel<String> dcb, dcb2;
	public AltaArticulo() throws ClassNotFoundException, SQLException {
		this.setPreferredSize(new Dimension(1050, 500));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		
		principal=new JPanel(new BorderLayout(20, 20));
		principal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Alta Articulo", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), Color.GRAY));
	
		centro=new JPanel(new GridLayout(9, 2, 20, 20));
		centro.setBorder(new EmptyBorder(0, 20, 0, 20));
		
		lb1=new JLabel("Nombre");
		lb2=new JLabel("Precio");
		lb3=new JLabel("Descripcion");
		lb4=new JLabel("Volumen");
		lb5=new JLabel("Embalaje");
		lb6=new JLabel("Cif");
		lb7=new JLabel("Categoría");
		lb8=new JLabel("Subcategoría");
		lb9=new JLabel("Stock");
		
		tf1=new JTextField();
		tf2=new JTextField();
		tf3=new JTextField();
		tf4=new JTextField();
		tf5=new JTextField();
		tf6=new JTextField();
		//tf7=new JTextField();
		dcb=new DefaultComboBoxModel<>();
		dcb=buscarCategoria();
		cb=new JComboBox<>(dcb);
		tf8=new JTextField();
		cb2=new JComboBox<>();
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
		centro.add(tf6);
		
		centro.add(lb7);
//		centro.add(tf7);
		centro.add(cb);
		
		centro.add(lb8);
		//centro.add(tf8);
		centro.add(cb2);
		
		centro.add(lb9);
		centro.add(tf8);
		
		principal.add(centro, BorderLayout.CENTER);
		
		sur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btn=new JButton("Enviar");
		
		sur.add(btn);
		
		principal.add(sur, BorderLayout.SOUTH);
				
		this.getContentPane().add(principal, BorderLayout.CENTER);
		
		cb.setSelectedItem(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource()==cb){
					dcb2.removeAllElements();
					try {
						dcb2=buscarCategoria2(cb.getSelectedIndex());
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
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
		ResultSet rs = c.consultar("select * from categorias where id_categoria_padre = "+i);
		while(rs.next()){
			aux.addElement(rs.getString(2));
		}
		c.close();
		return aux;
	}
	//JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
	public boolean comprobarVacio(){
		boolean cond=true;
		Validaciones v=new Validaciones();
		if(v.campovacio(tf1.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo nombre no puede estar vacío");
		}else if(v.campovacio(tf2.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo precio no puede estar vacío");
		}else if(v.campovacio(tf3.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo descripción no puede estar vacío");
		}else if(v.campovacio(tf4.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo volumen no puede estar vacío");
		}else if(v.campovacio(tf5.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo embalaje no puede estar vacío");
		}else if(v.campovacio(tf4.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo Cif no puede estar vacío");
		}else if(v.campovacio(tf4.getText())){
			cond=false;
			JOptionPane.showMessageDialog(this, "El campo stock no puede estar vacío");
		}
		return cond;
	}
	
}
