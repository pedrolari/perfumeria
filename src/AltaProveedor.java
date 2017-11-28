import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class AltaProveedor extends JInternalFrame{

	private JPanel ptotal, pcen, psur;
	private JLabel[] lbl;
	private JTextField textcif, textnom, texttel, textdirec;
	private JButton btn;
	
	AltaProveedor()
	{
		
		this.getContentPane().setBackground(Color.white);
		this.setClosable(false);
		this.setMaximizable(false);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,40,20));
		ptotal=new JPanel(new BorderLayout(150,30));
		ptotal.setBackground(Color.white);
		ptotal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 1), "ALTA CLIENTE",TitledBorder.LEFT,TitledBorder.TOP,new Font(null, Font.BOLD,25), Color.gray));
		this.getContentPane().add(ptotal);
		pcen=new JPanel(new GridLayout(4, 2,10,10));
		pcen.setBorder(new EmptyBorder(0, 20, 0, 20));
		ptotal.add(pcen,BorderLayout.CENTER);
		pcen.setBackground(Color.white);
		
		String[] texto={"CIF","Nombre","Telefono","Dirección"};
		lbl=new JLabel[texto.length];
		for (int i = 0; i < texto.length; i++) {
			lbl[i]=new JLabel(texto[i]);
		}
		
		textcif=new JTextField(10);
		textnom=new JTextField(10);
		texttel=new JTextField(10);
		textdirec=new JTextField(10);
		
		pcen.add(lbl[0]);
		pcen.add(textcif);
		pcen.add(lbl[1]);
		pcen.add(textnom);
		pcen.add(lbl[2]);
		pcen.add(texttel);
		pcen.add(lbl[3]);
		pcen.add(textdirec);
		
		psur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		ptotal.add(psur, BorderLayout.SOUTH);
		btn=new JButton("Enviar");
		psur.add(btn);
		psur.setBackground(Color.white);
		
	}
	
	
}
