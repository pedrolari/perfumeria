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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class AltaCliente extends JInternalFrame{
	private JPanel ptotal,pizq,pder,pcen,psur;
	private JLabel[] lbl;
	private JTextField txtdni,txtnom,txtapels,txtfechanaci,txtdir,txttel,txtmail,txtsexo,txtfechaing;
	private JButton btn;
	AltaCliente(){
		//tamaño 1050,500
		this.setPreferredSize(new Dimension(1050, 500));
		this.setClosable(false);
		this.setMaximizable(false);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,40,20));
		ptotal=new JPanel(new BorderLayout(150,30));
		
		ptotal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 1), "ALTA CLIENTE",TitledBorder.LEFT,TitledBorder.TOP,new Font(null, Font.BOLD,25), Color.gray));
		this.getContentPane().add(ptotal);
		pcen=new JPanel(new GridLayout(9, 2,40,10));
		pcen.setBorder(new EmptyBorder(0, 10, 0, 10));
		ptotal.add(pcen,BorderLayout.CENTER);
	
		
		//parte izquierda
		String[] texto={"DNI","Nombre","Apellidos","Fecha Nacimiento","Direccion","Telefono","Email","Sexo","Fecha Ingreso"};
		lbl=new JLabel[texto.length];
		for (int i = 0; i < texto.length; i++) {
			lbl[i]=new JLabel(texto[i]);
			//pcen.add(lbl[i]);
		}
		
		//parte derecha
		txtdni=new JTextField(10);
		txtnom=new JTextField(10);
		txtapels=new JTextField(10);
		txtfechanaci=new JTextField(10);
		txtdir=new JTextField(10);
		txttel=new JTextField(10);
		txtmail=new JTextField(10);
		txtsexo=new JTextField(10);
		txtfechaing=new JTextField(10);
		pcen.add(lbl[0]);
		pcen.add(txtdni);
		pcen.add(lbl[1]);
		pcen.add(txtnom);
		pcen.add(lbl[2]);
		pcen.add(txtapels);
		pcen.add(lbl[3]);
		pcen.add(txtfechanaci);
		pcen.add(lbl[4]);
		pcen.add(txtdir);
		pcen.add(lbl[5]);
		pcen.add(txttel);
		pcen.add(lbl[6]);
		pcen.add(txtmail);
		pcen.add(lbl[7]);
		pcen.add(txtsexo);
		pcen.add(lbl[8]);
		pcen.add(txtfechaing);
		
		
		psur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		ptotal.add(psur, BorderLayout.SOUTH);
		btn=new JButton("Enviar");
		psur.add(btn);
		
		
		
		
	}
}
