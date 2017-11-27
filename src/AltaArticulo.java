
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class AltaArticulo extends JInternalFrame{
	private JPanel principal, centro, sur;
	private JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8;
	private JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
	private JButton btn;
	
	public AltaArticulo() {
		this.setPreferredSize(new Dimension(1050, 500));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		
		principal=new JPanel(new BorderLayout(20, 20));
		principal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Alta Articulo", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), Color.GRAY));
	
		centro=new JPanel(new GridLayout(8, 2, 20, 20));
		centro.setBorder(new EmptyBorder(0, 20, 0, 20));
		
		lb1=new JLabel("Nombre");
		lb2=new JLabel("Precio");
		lb3=new JLabel("Descripcion");
		lb4=new JLabel("Volumen");
		lb5=new JLabel("Embalaje");
		lb6=new JLabel("Cif");
		lb7=new JLabel("Id Categoria");
		lb8=new JLabel("Stock");
		
		tf1=new JTextField();
		tf2=new JTextField();
		tf3=new JTextField();
		tf4=new JTextField();
		tf5=new JTextField();
		tf6=new JTextField();
		tf7=new JTextField();
		tf8=new JTextField();
		
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
		centro.add(tf7);
		
		centro.add(lb8);
		centro.add(tf8);
		
		principal.add(centro, BorderLayout.CENTER);
		
		sur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btn=new JButton("Enviar");
		
		sur.add(btn);
		
		principal.add(sur, BorderLayout.SOUTH);
				
		this.getContentPane().add(principal, BorderLayout.CENTER);
	}
}
