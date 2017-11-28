import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DateFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Imagenes.HintTextField;
import javafx.scene.control.DatePicker;

public class AltaCliente extends JInternalFrame implements ActionListener{
	private JPanel ptotal,pizq,pder,pcen,psur,pradiobtn;
	private JLabel[] lbl;
	private HintTextField txtdni;
	private JTextField txtnom,txtapels,txtfechanaci,txtdir,txttel,txtmail,txtsexo,txtfechaing;
	private JButton btn;
	private JRadioButton[] sex;
	private DatePicker dpfechanaci;
	AltaCliente(){
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
		ptotal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65), 1), "ALTA CLIENTE",TitledBorder.LEFT,TitledBorder.TOP,new Font(null, Font.BOLD,25), new Color(41, 53, 65)));
		this.getContentPane().add(ptotal);
		pcen=new JPanel(new GridLayout(9, 2,10,10));
		pcen.setBorder(new EmptyBorder(0, 20, 0, 20));
		ptotal.add(pcen,BorderLayout.CENTER);
		pcen.setBackground(Color.white);
		
		//parte izquierda
		String[] texto={"DNI","Nombre","Apellidos","Fecha Nacimiento","Direccion","Telefono","Email","Sexo","Fecha Ingreso"};
		lbl=new JLabel[texto.length];
		for (int i = 0; i < texto.length; i++) {
			lbl[i]=new JLabel(texto[i]);
		}
		
		//parte derecha
		HintTextField txtdni=new HintTextField("Introduce 8 numeros");
		
		
		txtnom=new JTextField(10);
		txtapels=new JTextField(10);
	//	dpfechanaci=new DatePicker();
		txtfechanaci=new JTextField(10);
		txtdir=new JTextField(10);
		txttel=new JTextField(10);
		txtmail=new JTextField(10);
		txtsexo=new JTextField(10);
		txtfechaing=new JTextField(10);
		
		//panel radiobuttons sexo
		pradiobtn = new JPanel(new GridLayout(1, 2));
		pradiobtn.setBackground(Color.white);
		ButtonGroup bg=new ButtonGroup();
		String[] texsex={"V","H"};
		sex=new JRadioButton[texsex.length];
	
		for (int i = 0; i < texsex.length; i++) {
			sex[i]=new JRadioButton(texsex[i]);
			bg.add(sex[i]);
			pradiobtn.add(sex[i]);
		//	pradiobtn.setBackground(Color.WHITE);
			sex[i].setBackground(Color.white);
		}
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateFormatter());
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel, new DateFormatter());
		pcen.add(lbl[0]);
		pcen.add(txtdni);
		pcen.add(lbl[1]);
		pcen.add(txtnom);
		pcen.add(lbl[2]);
		pcen.add(txtapels);
		pcen.add(lbl[3]);
		pcen.add(datePicker);
	//	pcen.add(txtfechanaci);
		pcen.add(lbl[4]);
		pcen.add(txtdir);
		pcen.add(lbl[5]);
		pcen.add(txttel);
		pcen.add(lbl[6]);
		pcen.add(txtmail);
		pcen.add(lbl[7]);
		pcen.add(pradiobtn);
		pcen.add(lbl[8]);
		pcen.add(datePicker2);
		//pcen.add(txtfechaing);
		
		
		psur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		ptotal.add(psur, BorderLayout.SOUTH);
		btn=new JButton("Enviar");
		psur.add(btn);
		psur.setBackground(Color.white);
				
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	//validaciones
	public boolean comprobar(){
		boolean cond=true;
		if(txtdni.getText().length()==0||txtnom.getText().length()==0||txtapels.getText().length()==0||txtnom.getText().length()==0){
			
		}
		return cond;
	}
}
