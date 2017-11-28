import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class AltaProveedor extends JInternalFrame implements ActionListener{

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
		ptotal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 1), "ALTA PROVEEDOR",TitledBorder.LEFT,TitledBorder.TOP,new Font(null, Font.BOLD,25), Color.gray));
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
		
		btn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
			
		Validaciones val=new Validaciones();
		
		if(arg0.getSource()==this.getBtn())
		{
			if(val.campovacio(this.getTextcif().getText().toString())==true||val.campovacio(this.getTextnom().getText().toString())==true||val.campovacio(this.getTexttel().getText().toString())==true||val.campovacio(this.getTextdirec().getText().toString())==true)
			{
				JOptionPane.showMessageDialog(this, "No puedes dejar ningún campo vacío.");
			}
		}
		
		
	}

	public JPanel getPtotal() {
		return ptotal;
	}

	public void setPtotal(JPanel ptotal) {
		this.ptotal = ptotal;
	}

	public JPanel getPcen() {
		return pcen;
	}

	public void setPcen(JPanel pcen) {
		this.pcen = pcen;
	}

	public JPanel getPsur() {
		return psur;
	}

	public void setPsur(JPanel psur) {
		this.psur = psur;
	}

	public JLabel[] getLbl() {
		return lbl;
	}

	public void setLbl(JLabel[] lbl) {
		this.lbl = lbl;
	}

	public JTextField getTextcif() {
		return textcif;
	}

	public void setTextcif(JTextField textcif) {
		this.textcif = textcif;
	}

	public JTextField getTextnom() {
		return textnom;
	}

	public void setTextnom(JTextField textnom) {
		this.textnom = textnom;
	}

	public JTextField getTexttel() {
		return texttel;
	}

	public void setTexttel(JTextField texttel) {
		this.texttel = texttel;
	}

	public JTextField getTextdirec() {
		return textdirec;
	}

	public void setTextdirec(JTextField textdirec) {
		this.textdirec = textdirec;
	}

	public JButton getBtn() {
		return btn;
	}

	public void setBtn(JButton btn) {
		this.btn = btn;
	}
	
	
}
