
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;

import javafx.scene.control.PasswordField;

public class AltaEmpleado extends JInternalFrame implements ActionListener{
	
	private JLabel jl,jl1,jl1r,jl2,jl3,jl4,jl5;
	private JTextField user,nom,ape,tel,rol;
	private JPasswordField pass,repass;
	private JPanel[] aux = new JPanel[7];
	private JPanel[] auxt = new JPanel[7];
	private JPanel prin,izq,der,sur;
	private JButton carga,limpiar;
	
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
		this.getLimpiar().addActionListener(this);
		this.getCarga().addActionListener(this);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Validaciones v = new Validaciones();
		if(this.getLimpiar() == e.getSource()){
			user.setText(null);
			pass.setText(null);
			repass.setText(null);
			nom.setText(null);
			ape.setText(null);
			tel.setText(null);
			rol.setText(null);
		}
		
		if(this.getCarga() == e.getSource()){
			if(v.campovacio(user.getText()) || v.campovacio(pass.getText()) || v.campovacio(repass.getText()) || v.campovacio(nom.getText()) || v.campovacio(ape.getText()) || v.campovacio(tel.getText()) || v.campovacio(rol.getText())){
				JOptionPane.showMessageDialog(null, "Faltan campos por rellenar!");
			}else{
				if(pass.getText().equalsIgnoreCase(repass.getText())){
					if(v.validartelefono(tel.getText())){
						JOptionPane.showMessageDialog(null, "Telefono introducido no valido!");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Las Contraseñas no coinciden!");
				}
			}
		}
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
		pass = new JPasswordField(10);
		repass = new JPasswordField(10);
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
		limpiar = new JButton("LIMPIAR");
		sur.add(carga);
		sur.add(limpiar);
		
		prin.add(BorderLayout.CENTER, izq);
		prin.add(BorderLayout.EAST, der);
		prin.add(BorderLayout.SOUTH, sur);

		this.getContentPane().add(prin);
		this.getContentPane().setBackground(Color.white);
	}

	public JLabel getJl() {
		return jl;
	}

	public void setJl(JLabel jl) {
		this.jl = jl;
	}

	public JLabel getJl1() {
		return jl1;
	}

	public void setJl1(JLabel jl1) {
		this.jl1 = jl1;
	}

	public JLabel getJl1r() {
		return jl1r;
	}

	public void setJl1r(JLabel jl1r) {
		this.jl1r = jl1r;
	}

	public JLabel getJl2() {
		return jl2;
	}

	public void setJl2(JLabel jl2) {
		this.jl2 = jl2;
	}

	public JLabel getJl3() {
		return jl3;
	}

	public void setJl3(JLabel jl3) {
		this.jl3 = jl3;
	}

	public JLabel getJl4() {
		return jl4;
	}

	public void setJl4(JLabel jl4) {
		this.jl4 = jl4;
	}

	public JLabel getJl5() {
		return jl5;
	}

	public void setJl5(JLabel jl5) {
		this.jl5 = jl5;
	}

	public JTextField getUser() {
		return user;
	}

	public void setUser(JTextField user) {
		this.user = user;
	}

	public JPasswordField getPass() {
		return pass;
	}

	public void setPass(JPasswordField pass) {
		this.pass = pass;
	}

	public JPasswordField getRepass() {
		return repass;
	}

	public void setRepass(JPasswordField repass) {
		this.repass = repass;
	}

	public JTextField getNom() {
		return nom;
	}

	public void setNom(JTextField nom) {
		this.nom = nom;
	}

	public JTextField getApe() {
		return ape;
	}

	public void setApe(JTextField ape) {
		this.ape = ape;
	}

	public JTextField getTel() {
		return tel;
	}

	public void setTel(JTextField tel) {
		this.tel = tel;
	}

	public JTextField getRol() {
		return rol;
	}

	public void setRol(JTextField rol) {
		this.rol = rol;
	}

	public JPanel[] getAux() {
		return aux;
	}

	public void setAux(JPanel[] aux) {
		this.aux = aux;
	}

	public JPanel[] getAuxt() {
		return auxt;
	}

	public void setAuxt(JPanel[] auxt) {
		this.auxt = auxt;
	}

	public JPanel getPrin() {
		return prin;
	}

	public void setPrin(JPanel prin) {
		this.prin = prin;
	}

	public JPanel getIzq() {
		return izq;
	}

	public void setIzq(JPanel izq) {
		this.izq = izq;
	}

	public JPanel getDer() {
		return der;
	}

	public void setDer(JPanel der) {
		this.der = der;
	}

	public JPanel getSur() {
		return sur;
	}

	public void setSur(JPanel sur) {
		this.sur = sur;
	}

	public JButton getCarga() {
		return carga;
	}

	public void setCarga(JButton carga) {
		this.carga = carga;
	}

	public JButton getLimpiar() {
		return limpiar;
	}

	public void setLimpiar(JButton limpiar) {
		this.limpiar = limpiar;
	}
	
	
}
