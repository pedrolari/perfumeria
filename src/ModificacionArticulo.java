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
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ModificacionArticulo extends JInternalFrame {
	
	private JPanel principal, norte, sur, este, oeste, centro, jpBuscar, jpModificar;
	private JLabel lb1, lb2, lb3, lb4, lb5, lb6;
	private JTextField tf2, tf3, tf4, tf5, tf6;
	private JButton btnBusqueda;
	private JComboBox busquedaProducto;
	
	ModificacionArticulo(){
		this.setPreferredSize(new Dimension(1050, 500));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Modificacion Articulo", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), Color.GRAY));
		
		//PANEL PRINCIPAL QUE CONTENDRA LOS PANELES NORTE, SUR, ESTE Y OESTE
		principal = new JPanel(new BorderLayout(150, 30));

		
		//PANEL DE BUSQUEDA
		jpBuscar=new JPanel(new GridLayout(5, 1, 20, 20));
		lb1=new JLabel("Buscar articulo");
		jpBuscar.add(lb1);
		busquedaProducto = new JComboBox();
		jpBuscar.add(busquedaProducto);
		btnBusqueda = new JButton("Modificar Producto");
		btnBusqueda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//CUANDO HAGA CLICK EN BUSQUEDA MOSTRARA LOS CAMPOS QUE SE PERMITE MODIFICAR YA RELLENADOS
				jpModificar.setVisible(true);
			}
		});
		jpBuscar.add(btnBusqueda);
		
		//PANEL DE MODIFICACION
		jpModificar=new JPanel(new GridLayout(5, 2, 10, 10));
		jpModificar.setVisible(false);
		lb2=new JLabel("Nombre");
		lb3=new JLabel("Precio");
		lb4=new JLabel("Descripcion");
		lb5=new JLabel("Volumen");
		lb6=new JLabel("Embalaje");
		
		tf2=new JTextField();
		tf3=new JTextField();
		tf4=new JTextField();
		tf5=new JTextField();
		tf6=new JTextField();
		
		jpModificar.add(lb2);
		jpModificar.add(tf2);
		jpModificar.add(lb3);
		jpModificar.add(tf3);
		jpModificar.add(lb4);
		jpModificar.add(tf4);
		jpModificar.add(lb5);
		jpModificar.add(tf5);
		jpModificar.add(lb6);
		jpModificar.add(tf6);

		
//		principal.add(norte, BorderLayout.NORTH);
		//principal.add(sur, BorderLayout.SOUTH);
		principal.add(jpModificar, BorderLayout.CENTER);
//		principal.add(este, BorderLayout.EAST);
		principal.add(jpBuscar, BorderLayout.WEST);
		
		
		this.getContentPane().add(principal);
//		
		
//
//		
		
//		
//		
//		centro.add(lb1);
//		centro.add(tf1);
//		
//		centro.add(lb2);
//		centro.add(tf2);
//		
//		centro.add(lb3);
//		centro.add(tf3);
//		
//		centro.add(lb4);
//		centro.add(tf4);
//		
//		centro.add(lb5);
//		centro.add(tf5);
//
//		
//		principal.add(centro, BorderLayout.CENTER);
//		
//		sur=new JPanel(new FlowLayout(FlowLayout.CENTER));
//		
//		btn=new JButton("Enviar");
//		
//		sur.add(btn);
//		
//		principal.add(sur, BorderLayout.SOUTH);
				
		
	}
	
}
