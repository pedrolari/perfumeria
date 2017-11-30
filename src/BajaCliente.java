

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class BajaCliente extends JInternalFrame{
	private JPanel principal, centro;
	private JLabel lb1;
	private JComboBox<String> combo;
	private DefaultComboBoxModel<String> modelo;
	private JButton btn;
	
	BajaCliente(){
		this.setPreferredSize(new Dimension(1050, 500));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		
		principal=new JPanel(new BorderLayout(40, 40));
		principal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Baja Cliente", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), Color.GRAY));
	
		centro=new JPanel(new BorderLayout(40, 40));
		centro.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		lb1=new JLabel("Seleccione Cliente a eliminar: ");
		modelo=new DefaultComboBoxModel<String>();
		modelo.addElement("Prueba");
		combo=new JComboBox<String>(modelo);
		btn=new JButton("Borrar Cliente");
		
		centro.add(lb1, BorderLayout.NORTH);
		centro.add(combo, BorderLayout.CENTER);
		centro.add(btn, BorderLayout.SOUTH);
		
		principal.add(centro);
		
		this.getContentPane().add(principal);
		
	}
}
