
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class AltaEmpleado extends JInternalFrame{
	
	private JLabel jl,jl1,jl1r,jl2,jl3,jl4,jl5;
	private JTextField user,pass,repass,nom,ape,tel,rol;
	private JPanel[] aux = new JPanel[7];
	private JPanel[] auxt = new JPanel[7];
	private JPanel prin,izq,der,sur;
	private JButton carga;
	
	public AltaEmpleado() {
		// TODO Auto-generated constructor stub
		this.setSize(1050,640);
		this.setResizable(false);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,1,100));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.getContentPane().setBackground(new Color(19, 34, 41));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		componentes();
		this.setVisible(true);
	}
	
	private void componentes() {
		// TODO Auto-generated method stub
	
		prin = new JPanel();
		prin.setBackground(Color.WHITE);
		prin.setPreferredSize(new Dimension(300, 400));
		prin.setLayout(new BorderLayout());
		prin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65)), "Datos de Usuario",TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25),new Color(41, 53, 65)));
		
		
		jl = new JLabel(" Usuario: ");
		jl.setFont(new Font(null, Font.BOLD, 15));
		jl1 = new JLabel(" Contraseña: ");
		jl1.setFont(new Font(null, Font.BOLD, 15));
		jl1r = new JLabel("Repetir Contraseña: ");
		jl1r.setFont(new Font(null, Font.BOLD, 15));
		jl2 = new JLabel(" Nombre: ");
		jl2.setFont(new Font(null, Font.BOLD, 15));
		jl3 = new JLabel(" Apellidos: ");
		jl3.setFont(new Font(null, Font.BOLD, 15));
		jl4 = new JLabel(" Telefono: ");
		jl4.setFont(new Font(null, Font.BOLD, 15));
		jl5 = new JLabel(" Rol: ");
		jl5.setFont(new Font(null, Font.BOLD, 15));
		
		user = new JTextField(10);
		pass = new JTextField(10);
		repass = new JTextField(10);
		nom = new JTextField(10);
		ape = new JTextField(10);
		tel = new JTextField(10);
		tel.setColumns(9);
		rol = new JTextField(10);
		
		for (int i = 0; i < aux.length; i++) {
			aux[i] = new JPanel();
			aux[i].setLayout(new FlowLayout(FlowLayout.LEFT,1,1));
			aux[i].setBackground(Color.WHITE);
		}
		
		for (int i = 0; i < aux.length; i++) {
			auxt[i] = new JPanel();
			auxt[i].setBackground(Color.white);
			auxt[i].setLayout(new FlowLayout(FlowLayout.LEFT,1,1));
		}
		
		aux[0].add(user);
		aux[1].add(pass);
		aux[2].add(repass);
		aux[3].add(nom);
		aux[4].add(ape);
		aux[5].add(tel);
		aux[6].add(rol);
		
		izq = new JPanel();
		izq.setBackground(Color.white);
		izq.setLayout(new GridLayout(7, 1));
		//izq.setBackground(Color.RED);
		der = new JPanel();
		der.setBackground(Color.white);
		//der.setBackground(Color.blue);
		der.setLayout(new GridLayout(7, 1));
		
		auxt[0].add(jl);
		auxt[1].add(jl1);
		auxt[2].add(jl1r);
		auxt[3].add(jl2);
		auxt[4].add(jl3);
		auxt[5].add(jl4);
		auxt[6].add(jl5);
		
		izq.add(auxt[0]);
		izq.add(auxt[1]);
		izq.add(auxt[2]);
		izq.add(auxt[3]);
		izq.add(auxt[4]);
		izq.add(auxt[5]);
		izq.add(auxt[6]);
		
		der.add(aux[0]);
		der.add(aux[1]);
		der.add(aux[2]);
		der.add(aux[3]);
		der.add(aux[4]);
		der.add(aux[5]);
		der.add(aux[6]);
		
		sur = new JPanel();
		sur.setBackground(Color.white);
		sur.setLayout(new FlowLayout(FlowLayout.CENTER));
		carga = new JButton("REGISTRAR");
		sur.add(carga);
		
		prin.add(BorderLayout.CENTER, izq);
		prin.add(BorderLayout.EAST, der);
		prin.add(BorderLayout.SOUTH, sur);

		this.getContentPane().add(prin);
		this.getContentPane().setBackground(Color.white);
	}
	
}
