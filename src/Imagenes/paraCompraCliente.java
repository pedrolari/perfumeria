package Imagenes;

import javax.swing.*;

public class paraCompraCliente {

	private JComboBox c1, c2;
	private JTextField t1, t2, t3;

	public paraCompraCliente(JComboBox c1, JComboBox c2, JTextField t1, JTextField t2, JTextField t3) {
		super();
		this.c1 = c1;
		this.c2 = c2;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
	}
	
	public JComboBox getC1() {
		return c1;
	}
	public void setC1(JComboBox c1) {
		this.c1 = c1;
	}
	public JComboBox getC2() {
		return c2;
	}
	public void setC2(JComboBox c2) {
		this.c2 = c2;
	}
	public JTextField getT1() {
		return t1;
	}
	public void setT1(JTextField t1) {
		this.t1 = t1;
	}
	public JTextField getT2() {
		return t2;
	}
	public void setT2(JTextField t2) {
		this.t2 = t2;
	}
	public JTextField getT3() {
		return t3;
	}
	public void setT3(JTextField t3) {
		this.t3 = t3;
	}
	
	
	
	
}
