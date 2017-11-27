import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class PantallaPrin extends JInternalFrame {

	private JPanel jpOeste, jpOesteNorte, jpOesteCentro, jPanelCentro, JpanelCentroNorte, JpanelMitad,
			JpanelOpcionesMenu, JpanelMitadCentral, JpanelCargarJIframe, jpcsur;
	private ImageIcon imagen, imagencarnet;
	private JLabel jlImagen, jlRol, jlNombre, jlPersona, jlMenu;
	private JScrollPane barraScrollPanelCargarJIframe;
	private JButton  jbOpcMenu []=new JButton[5];
	private JButton jbAjuste, jbfecha, jbOpcMenu1;

	//jbOpc1, jbOpc2, jbOpc3, jbOpc4, jbOpc5
	
	public PantallaPrin() {
		// TODO Auto-generated constructor stub
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.setBorder(null);
		this.setLayout(new BorderLayout(0, 0));

		Componentes();
	}

	private void Componentes() {
		// TODO Auto-generated method stub
		ParteOeste();
		ParteCentro();

		// jpcsur = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// jpcsur.setPreferredSize(new Dimension(this.getWidth(), 50));
		// jpcsur.setBackground(new Color(41, 53, 65));
		// this.getContentPane().add(BorderLayout.SOUTH, jpcsur);
	}

	private void ParteCentro() {
		jPanelCentro = new JPanel(new BorderLayout(0, 0)); // este

		DatosUsuario(); // Rol del usuario , Ajustes ,Imagen de usuario ,Nombre de usuario

		JpanelMitad = new JPanel(new BorderLayout(0, 0));

		OpcionesMenu(); // Aqui Van las opciones de menu

		JpanelMitad.add(BorderLayout.NORTH, JpanelOpcionesMenu);

		JpanelMitadCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JpanelMitadCentral.setBackground(Color.WHITE);

		PanelJInternalFrame();// En este panel se añadiran los JInternalFrame

		this.getContentPane().add(BorderLayout.CENTER, jPanelCentro);
	}

	private void PanelJInternalFrame() {
		
		JpanelCargarJIframe = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)); 
		JpanelCargarJIframe.setPreferredSize(new Dimension(1070, 550));
		JpanelCargarJIframe.setBackground(Color.white);

		barraScrollPanelCargarJIframe = new JScrollPane(JpanelCargarJIframe);
		barraScrollPanelCargarJIframe.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		barraScrollPanelCargarJIframe.setBorder(null);

		JpanelMitadCentral.add(barraScrollPanelCargarJIframe);

		JpanelMitad.add(BorderLayout.CENTER, JpanelMitadCentral);

		jPanelCentro.add(BorderLayout.CENTER, JpanelMitad);
	}

	private void DatosUsuario() {

		JpanelCentroNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JpanelCentroNorte.setBackground(Color.WHITE);
		JpanelCentroNorte.setPreferredSize(new Dimension(this.getWidth(), 75));

		jlRol = new JLabel("Administrador"); // Rol que tiene el usuario
		jlRol.setBorder(new EmptyBorder(0, 10, 0, 600));
		jlRol.setFont(new Font(null, 1, 25));
		JpanelCentroNorte.add(jlRol);

		jbAjuste = new JButton("");
		jbAjuste.setOpaque(false);
		jbAjuste.setContentAreaFilled(false);
		jbAjuste.setBorderPainted(false);
		jbAjuste.setIcon(new ImageIcon(getClass().getResource("Imagenes/ajuste.png"))); // menu de Ajuste
		JpanelCentroNorte.add(jbAjuste);

		imagencarnet = new ImageIcon(getClass().getResource("Imagenes/foto1.jpg"));// foto del usuario
		jlPersona = new JLabel(imagencarnet);
		JpanelCentroNorte.add(jlPersona);

		jlNombre = new JLabel("Paco Garcia Perez"); // nombre del usuario
		JpanelCentroNorte.add(jlNombre);

		jbfecha = new JButton("");
		jbfecha.setOpaque(false);
		jbfecha.setContentAreaFilled(false);
		jbfecha.setBorderPainted(false);
		jbfecha.setIcon(new ImageIcon(getClass().getResource("Imagenes/flecha.png"))); // menu desplegable
		JpanelCentroNorte.add(jbfecha);

		jPanelCentro.add(BorderLayout.NORTH, JpanelCentroNorte);
	}

	private void OpcionesMenu() {
		/* Menu de opciones de cada menu */
		
		JpanelOpcionesMenu = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		JpanelOpcionesMenu.setBackground(new Color(236, 237, 243));//
		JpanelOpcionesMenu.setPreferredSize(new Dimension(this.getWidth(), 35));

		jlMenu = new JLabel("Menu 1");
		jlMenu.setBorder(new EmptyBorder(0, 10, 0, 0));
		JpanelOpcionesMenu.add(jlMenu);

		jbOpcMenu1 = new JButton("Opcion1");
		jbOpcMenu1.setOpaque(false);
		jbOpcMenu1.setContentAreaFilled(false);
		jbOpcMenu1.setBorderPainted(false);
		JpanelOpcionesMenu.add(jbOpcMenu1);
	}

	private void ParteOeste() {
		jpOeste = new JPanel(new BorderLayout(0, 0));

		jpOesteNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		imagen = new ImageIcon(getClass().getResource("Imagenes/logo.png"));
		jlImagen = new JLabel(imagen);
		jpOesteNorte.add(jlImagen);
		jpOesteNorte.setBackground(new Color(41, 53, 65));
		jpOeste.add(BorderLayout.NORTH, jpOesteNorte);

		jpOesteCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
		jpOesteCentro.setPreferredSize(new Dimension(200, this.getHeight()));
		jpOesteCentro.setBackground(new Color(41, 53, 65));

		/*Inicializacion de los botones de menu y caracteristicas*/
		for (int i = 0; i < jbOpcMenu.length; i++) {
			
			jbOpcMenu[i]=new JButton("Opc"+i);
			jbOpcMenu[i].setForeground(Color.white);
			jbOpcMenu[i].setOpaque(false);
			jbOpcMenu[i].setContentAreaFilled(false);
			jbOpcMenu[i].setBorderPainted(false);
			jbOpcMenu[i].setPreferredSize(new Dimension(200, 50));
			jpOesteCentro.add(jbOpcMenu[i]);
			jpOesteCentro.add(jbOpcMenu[i]);
		}
	
		jpOeste.add(BorderLayout.CENTER, jpOesteCentro);

		this.getContentPane().add(BorderLayout.WEST, jpOeste);
	}
	
	
}
