import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PedidiosPendientes extends JInternalFrame{
	private JLabel txt;
	PedidiosPendientes(){
		//tamaño 1050,500
		this.setPreferredSize(new Dimension(1050, 640));
		this.getContentPane().setBackground(Color.white);
		this.setClosable(false);
		this.setMaximizable(false);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,40,50));
		txt=new JLabel("Texto");
		this.getContentPane().add(txt);
	}
}
