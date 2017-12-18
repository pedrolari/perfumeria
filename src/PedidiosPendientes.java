import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PedidiosPendientes extends JInternalFrame{
	private JLabel txt;
	private JPanel ptotal,pizq,pder,pcen,psur;
	private JScrollPane barra;
	PedidiosPendientes(){
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
		ptotal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65), 1), "PEDIDOS PENDIENTES",TitledBorder.LEFT,TitledBorder.TOP,new Font(null, Font.BOLD,25), new Color(41, 53, 65)));
		this.getContentPane().add(ptotal);

		/*pcen=new JPanel(new GridLayout(9, 2,50,12));
		pcen.setBorder(new EmptyBorder(70, 350, 70, 350));
		ptotal.add(pcen,BorderLayout.CENTER);
		pcen.setBackground(Color.white);*/
	}
}
