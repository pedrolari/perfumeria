import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	private BotonInterior btn;
	private Conexion c;
	
	
	//Aqui se dibuja toda la ventana
	AltaProveedor()
	{
		
		this.getContentPane().setBackground(Color.white);
		this.setClosable(false);
		this.setMaximizable(false);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,40,30));
		ptotal=new JPanel(new BorderLayout(150,30));
		ptotal.setBackground(Color.white);
		ptotal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65), 1), "ALTA PROVEEDOR",TitledBorder.LEFT,TitledBorder.TOP,new Font(null, Font.BOLD,25), new Color(41, 53, 65)));
		this.getContentPane().add(ptotal);
		pcen=new JPanel(new GridLayout(4, 2,50,50));
		pcen.setBorder(new EmptyBorder(100, 360, 120, 360));
		ptotal.add(pcen,BorderLayout.CENTER);
		pcen.setBackground(Color.white);
		
		String[] texto={"CIF","Nombre","Telefono","Dirección"};
		lbl=new JLabel[texto.length];
		for (int i = 0; i < texto.length; i++) {
			lbl[i]=new JLabel(texto[i]);
			lbl[i].setFont(new Font("Arial",Font.PLAIN,16));
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
		btn=new BotonInterior("Enviar");
		psur.add(btn);
		psur.setBackground(Color.white);
		
		btn.addActionListener(this);
		
	}

	//Aqui hago la escucha con sus correspondientes validaciones, y luego inserto en la base de datos
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
			
		Validaciones val=new Validaciones();
		try {
			c=new Conexion();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(arg0.getSource()==this.getBtn())
		{
			if(val.campovacio(this.getTextcif().getText().toString())==true||val.campovacio(this.getTextnom().getText().toString())==true||val.campovacio(this.getTexttel().getText().toString())==true||val.campovacio(this.getTextdirec().getText().toString())==true)
			{
				JOptionPane.showMessageDialog(this, "No puedes dejar ningún campo vacío.");
			}
			else if(val.validarNIF(this.getTextcif().getText().toString())==false)
			{
				JOptionPane.showMessageDialog(this, "Nif incorrecto.");
			}
			else if(val.validartelefono(this.getTexttel().getText().toString())==true)
			{
				JOptionPane.showMessageDialog(this, "Teléfono incorrecto.");
			}
			else
			{
				ResultSet rs = null;
				Boolean enc=false;
				try {
					 rs=c.consultar("SELECT * FROM proveedores WHERE cif LIKE '"+this.getTextcif().getText().toString()+"'");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if(rs.next())
					{
						enc=true;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(enc==true)
				{
					JOptionPane.showMessageDialog(this, "Este nif ya existe, introduzca otro.");
				}
				else
				{
				int telefono=Integer.parseInt(this.getTexttel().getText().toString());
				try {
					c.modificar("INSERT INTO proveedores VALUES ('"+this.getTextcif().getText().toString()+"', '"+this.getTextnom().getText().toString()+"', "+telefono+", '"+this.getTextdirec().getText() .toString()+"')");
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Datos insertados correctamente.");
				}
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

	public void setBtn(BotonInterior btn) {
		this.btn = btn;
	}
	
	
}
