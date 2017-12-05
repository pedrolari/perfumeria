import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DateFormatter;



import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javafx.scene.control.DatePicker;

public class AltaCliente extends JInternalFrame implements ActionListener{
	private JPanel ptotal,pizq,pder,pcen,psur,pradiobtn;
	private JLabel[] lbl;
	private HintTextField txtdni;
	private JTextField txtnom,txtapels,txtfechanaci,txtdir,txttel,txtmail,txtsexo,txtfechaing;
	private JButton btn;
	private JRadioButton[] sex;
	private Dni d;
	//private JDatePickerImpl datePicker, datePicker2;
//	private JCalendar date1,date2;
	private JDateChooser date1,date2;
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
		txtdni=new HintTextField("Introduce 8 numeros");
		txtnom=new JTextField(10);
		txtapels=new JTextField(10);
	
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
			sex[i].setBackground(Color.white);
		}

		
		date1=new JDateChooser("yyyy-MM-dd", "####-##-##", ' ');
		date2=new JDateChooser("yyyy-MM-dd", "####-##-##", ' ');
		
	//	date1.setSize(30, 30);
		
		pcen.add(lbl[0]);
		pcen.add(txtdni);
		pcen.add(lbl[1]);
		pcen.add(txtnom);
		pcen.add(lbl[2]);
		pcen.add(txtapels);
		pcen.add(lbl[3]);
		
		pcen.add(date1);
		
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
		pcen.add(date2);
		//pcen.add(txtfechaing);
		
		psur=new JPanel(new FlowLayout(FlowLayout.CENTER));
		ptotal.add(psur, BorderLayout.SOUTH);
		btn=new JButton("Enviar");
		btn.addActionListener(this);
		psur.add(btn);
		psur.setBackground(Color.white);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Date d1= date1.getDate();
		Date d2=date2.getDate();
		/*int n=d1.getDate();
		int n2=d1.getMonth();*/
		int fechaing[]= new int[3];
		int fechanaci[]=new int[3];
		
		
		fechaing[0]=d1.getDate();
		fechaing[1]=d1.getMonth();
		fechaing[2]=d1.getYear();
		fechanaci[0]=d2.getDate();
		fechanaci[1]=d2.getMonth();
		fechanaci[2]=d2.getYear();
		
		if(comprobar()&&comprobarRB()&&comprobarFechaExiste(fechaing)&&comprobarFechaExiste(fechanaci)){
			JOptionPane.showMessageDialog(this, "Hola");
			txtdni.setText(d.recogerdniconletra(txtdni.getText()));
			//JOptionPane.showMessageDialog(this, txtdni.getText());
		}else{
			
		}
	//	JOptionPane.showMessageDialog(this, n2);
		
	}
	//validaciones
	public boolean comprobar(){
		boolean cond=true;
		Validaciones v=new Validaciones();
		d=new Dni();
		if(v.campovacio(txtnom.getText())||v.campovacio(txtapels.getText())||v.campovacio(txtdir.getText())||v.campovacio(txttel.getText())||v.campovacio(txtmail.getText())){
			JOptionPane.showMessageDialog(this, "Dejo algún campo vacio, todos son obligatorios");
			cond=false;
		}else if(!d.validardnisinletra(txtdni.getText())){
			JOptionPane.showMessageDialog(this, "DNI incorrecto");
			cond=false;
		}else if(v.validartelefono(txttel.getText())){
			JOptionPane.showMessageDialog(this, "Telefono no válido");
			cond=false;
		}else if(v.validaremail(txtmail.getText())){
			JOptionPane.showMessageDialog(this, "Email no válido");
			cond=false;
		}
		return cond;
	}
	public boolean comprobarRB(){
		boolean cond=true;
		if(sex[0].isSelected()||sex[1].isSelected()){
			JOptionPane.showMessageDialog(this, "Sexo no seleccionado");
			cond=false;
		}
		return cond;
	}
	public boolean comprobarFechaExiste(int[] f){
		int dia=f[0];
		int mes=f[1];
		int año=f[2];
		boolean cond=true;
		Calendar hoy = new GregorianCalendar().getInstance();
		if(año<1900||año>hoy.get(Calendar.YEAR)){
			cond=false;
		}else if(año==hoy.get(Calendar.YEAR)){
			if(mes>hoy.get(Calendar.MONTH)+1){
				cond=false;
			}else if(mes==hoy.get(Calendar.DAY_OF_MONTH)){
				if(dia>hoy.get(Calendar.DAY_OF_MONTH)){
					cond=false;
				}
			}
		}else if(mes<1||mes>12){
			cond=false;
		}else if(mes==2){
			if(año%4==0 || (año%100!=0&&año%400==0)){
				if(dia<1||dia>29){
					cond=false;
				}
			}
		}else if(mes==4||mes==6||mes==9||mes==11){
			if(dia<1||dia>30){
				cond=false;
			}
		}else{
			if(dia<1||dia>31){
				cond=false;
			}
		}
		return cond;
	}
	
	
}
