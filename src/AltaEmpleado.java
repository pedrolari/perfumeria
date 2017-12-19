
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
	private JTextField usu,nom,ape,tel,rol;
	private JPasswordField pass,repass;
	private JPanel[] aux = new JPanel[7];
	private JPanel[] auxt = new JPanel[7];
	private JPanel tras,prin,izq,der,sur;
	private JButton carga,limpiar;
	private Empleado c;
	
	public AltaEmpleado() {
		// TODO Auto-generated constructor stub
		this.setResizable(false);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,1,10));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			usu.setText(null);
			pass.setText(null);
			repass.setText(null);
			nom.setText(null);
			ape.setText(null);
			tel.setText(null);
			rol.setText(null);
		}
		
		if(this.getCarga() == e.getSource()){
			if(v.campovacio(usu.getText()) || v.campovacio(pass.getText()) || v.campovacio(repass.getText()) || v.campovacio(nom.getText()) || v.campovacio(ape.getText()) || v.campovacio(tel.getText()) || v.campovacio(rol.getText())){
				JOptionPane.showMessageDialog(null, "Faltan campos por rellenar!");
			}else{
				if(pass.getText().equalsIgnoreCase(repass.getText())){
					if(v.validartelefono(tel.getText())){
						JOptionPane.showMessageDialog(null, "Telefono introducido no valido!");
					}else{
						if(v.isNumeric(tel.getText())){
							if(rol.getText().equalsIgnoreCase("1") || rol.getText().equalsIgnoreCase("0")){
								try {
									c = new Empleado(usu.getText(),repass.getText(),nom.getText(),ape.getText(),Integer.parseInt(tel.getText()),Integer.parseInt(rol.getText()));
									String [] opciones ={"Si","No"};
									int eleccion = JOptionPane.showOptionDialog(null,"En realidad desea crear","Mensaje de Confirmacion",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE,null,opciones,"Si");
									
									if (eleccion == JOptionPane.YES_OPTION){
									c.insertar();
									JOptionPane.showMessageDialog(null,"Datos Registrados correctamente");
											
									}
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}else{
								JOptionPane.showMessageDialog(null, "Solo se admite 1 o 0 en casilla rol!");
							}
						}else{
							JOptionPane.showMessageDialog(null, "Datos errones en teléfono!");
						}
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
		prin.setPreferredSize(new Dimension(1000, 600));
		prin.setLayout(new FlowLayout(FlowLayout.CENTER,1,70));
		prin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65)), "Datos de Usuario",TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25),new Color(41, 53, 65)));
		
		
		tras = new JPanel();
		tras.setBackground(Color.white);
		tras.setPreferredSize(new Dimension(300, 400));
		tras.setLayout(new BorderLayout());
		
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
		
		usu = new JTextField(10);
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
		
		aux[0].add(usu);
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
		
		tras.add(BorderLayout.CENTER, izq);
		tras.add(BorderLayout.EAST, der);
		tras.add(BorderLayout.SOUTH, sur);
		
		prin.add(tras);

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
		return usu;
	}

	public void setUser(JTextField user) {
		this.usu = user;
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
