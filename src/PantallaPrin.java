import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class PantallaPrin extends JInternalFrame implements ActionListener{

	private JPanel jpOeste, jpOesteNorte, jpOesteCentro, jPanelCentro, JpanelCentroNorte, JpanelMitad,
			JpanelOpcionesMenu, JpanelMitadCentral, JpanelCargarJIframe, jpcsur;
	private ImageIcon imagen, imagencarnet;
	private JLabel jlImagen, jlRol, jlNombre, jlPersona, jlMenu;
	private JScrollPane barraScrollPanelCargarJIframe;
	private BotonMenu  jbOpcMenu []=new BotonMenu[7];
	private JButton jbAjuste, jbcerrar, jbOpcMenu1 ,jbOpcMenu2,jbOpcMenu3,jbOpcMenu4,jbOpcMenu5, jbOpcMenu6;

	private String usuario;
	//jbOpc1, jbOpc2, jbOpc3, jbOpc4, jbOpc5
	
	public PantallaPrin(String user, int rol, String nom, String ape, Ventana vent) {
		// TODO Auto-generated constructor stub
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.setBorder(null);
		this.setLayout(new BorderLayout(0, 0));
		usuario=user;
		Componentes(nom,ape,rol,vent);
	}

	private void Componentes(String nom, String ape, int rol, Ventana vent) {
		// TODO Auto-generated method stub
		ParteOeste(rol);
		ParteCentro(nom,ape,vent);
		
	
		
		// jpcsur = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// jpcsur.setPreferredSize(new Dimension(this.getWidth(), 50));
		// jpcsur.setBackground(new Color(41, 53, 65));
		// this.getContentPane().add(BorderLayout.SOUTH, jpcsur);
	}

	private void ParteCentro(String nom, String ape, Ventana vent) {
		jPanelCentro = new JPanel(new BorderLayout(0, 0)); // este

		DatosUsuario(nom,ape,vent); // Rol del usuario , Ajustes ,Imagen de usuario ,Nombre de usuario

		JpanelMitad = new JPanel(new BorderLayout(0, 0));

		OpcionesMenu(); // Aqui Van las opciones de menu

		JpanelMitad.add(BorderLayout.NORTH, JpanelOpcionesMenu);

		JpanelMitadCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JpanelMitadCentral.setBackground(Color.white);

		PanelJInternalFrame();// En este panel se añadiran los JInternalFrame

		this.getContentPane().add(BorderLayout.CENTER, jPanelCentro);
	}

	private void PanelJInternalFrame() {
		
		JpanelCargarJIframe = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)); 
		JpanelCargarJIframe.setPreferredSize(new Dimension(1070, 640));
		JpanelCargarJIframe.setBackground(Color.white);

		barraScrollPanelCargarJIframe = new JScrollPane(JpanelCargarJIframe);
		barraScrollPanelCargarJIframe.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		barraScrollPanelCargarJIframe.setBorder(null);

		JpanelMitadCentral.add(barraScrollPanelCargarJIframe);

		JpanelMitad.add(BorderLayout.CENTER, JpanelMitadCentral);

		jPanelCentro.add(BorderLayout.CENTER, JpanelMitad);
	}

	private void DatosUsuario(String nom, String ape, Ventana vent) {

		String nombre = nom +" "+ ape;
		JpanelCentroNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JpanelCentroNorte.setBackground(Color.WHITE);
		JpanelCentroNorte.setPreferredSize(new Dimension(this.getWidth(), 75));
		

		jlRol = new JLabel(nombre); // Rol que tiene el usuario
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension screenSize = t.getScreenSize();
		if (nombre.length() >= 20) {
			jlRol.setBorder(new EmptyBorder(0, 10, 0, screenSize.height-275));
		} else {
			jlRol.setBorder(new EmptyBorder(0, 10, 0, screenSize.height-25));
		}
		

		jlRol.setFont(new Font(null, 1, 25));
		JpanelCentroNorte.add(jlRol);

		imagencarnet = new ImageIcon(getClass().getResource("Imagenes/user.png"));// foto del usuario
		Image img=imagencarnet.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		imagencarnet=new ImageIcon(img);
		
		jlPersona = new JLabel(imagencarnet);
		JpanelCentroNorte.add(jlPersona);

		jlNombre = new JLabel(nombre); // nombre del usuario
		JpanelCentroNorte.add(jlNombre);

		jbcerrar = new JButton("");
		jbcerrar.setOpaque(false);
		jbcerrar.setContentAreaFilled(false);
		jbcerrar.setBorderPainted(false);
		
		ImageIcon ii=new ImageIcon(getClass().getResource("Imagenes/exit.png"));
		img=ii.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ii=new ImageIcon(img);
		
		jbcerrar.setIcon(ii); // menu desplegable
		jbcerrar.setFocusable(false);
		JpanelCentroNorte.add(jbcerrar);
		jbcerrar.addActionListener(new EscuchaCerrar(vent));

		jPanelCentro.add(BorderLayout.NORTH, JpanelCentroNorte);
	}

	private void OpcionesMenu() {
		/* Menu de opciones de cada menu */
		
		JpanelOpcionesMenu = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		JpanelOpcionesMenu.setBackground(new Color(236, 237, 243));//
		JpanelOpcionesMenu.setPreferredSize(new Dimension(this.getWidth(), 35));

		jlMenu = new JLabel("");
		jlMenu.setBorder(new EmptyBorder(0, 10, 0, 0));
		JpanelOpcionesMenu.add(jlMenu);

		jbOpcMenu1 = new JButton("");
		jbOpcMenu1.setOpaque(false);
		jbOpcMenu1.setContentAreaFilled(false);
		jbOpcMenu1.setBorderPainted(false);
		JpanelOpcionesMenu.add(jbOpcMenu1);
		
		jbOpcMenu2 = new JButton("");
		jbOpcMenu2.setOpaque(false);
		jbOpcMenu2.setContentAreaFilled(false);
		jbOpcMenu2.setBorderPainted(false);
		JpanelOpcionesMenu.add(jbOpcMenu2);
		
		jbOpcMenu3 = new JButton("");
		jbOpcMenu3.setOpaque(false);
		jbOpcMenu3.setContentAreaFilled(false);
		jbOpcMenu3.setBorderPainted(false);
		JpanelOpcionesMenu.add(jbOpcMenu3);
		
		jbOpcMenu4 = new JButton("");
		jbOpcMenu4.setOpaque(false);
		jbOpcMenu4.setContentAreaFilled(false);
		jbOpcMenu4.setBorderPainted(false);
		JpanelOpcionesMenu.add(jbOpcMenu4);
		
		jbOpcMenu5 = new JButton("");
		jbOpcMenu5.setOpaque(false);
		jbOpcMenu5.setContentAreaFilled(false);
		jbOpcMenu5.setBorderPainted(false);
		JpanelOpcionesMenu.add(jbOpcMenu5);
		
		jbOpcMenu6 = new JButton("");
		jbOpcMenu6.setOpaque(false);
		jbOpcMenu6.setContentAreaFilled(false);
		jbOpcMenu6.setBorderPainted(false);
		JpanelOpcionesMenu.add(jbOpcMenu6);
		
		
	}

	private void ParteOeste(int rol) {
		jpOeste = new JPanel(new BorderLayout(0, 0));

		jpOesteNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		imagen = new ImageIcon(getClass().getResource("Imagenes/logo.png"));
		jlImagen = new JLabel(imagen);
		jpOesteNorte.add(jlImagen);
		jpOesteNorte.setBackground(new Color(41, 53, 65));
		jpOeste.add(BorderLayout.NORTH, jpOesteNorte);

		//jpOesteCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
		jpOesteCentro = new JPanel(new GridLayout(7, 1));
		jpOesteCentro.setPreferredSize(new Dimension(200, this.getHeight()));
		jpOesteCentro.setBackground(new Color(41, 53, 65));

		String[] noms={"ARTÍCULOS", "CLIENTES", "EMPLEADOS", "PROVEEDORES","PEDIDOS", "INFORMES", "SALIR"};
		
		/*Inicializacion de los botones de menu y caracteristicas*/
		for (int i = 0; i < jbOpcMenu.length; i++) {
			
			jbOpcMenu[i]=new BotonMenu(noms[i], "src/Imagenes/menu"+i+".png");			
			jpOesteCentro.add(jbOpcMenu[i]);
			
		}
		//Rol=0 Admin
		//Rol=1 Empleado Rango 1
		//Rol=2 Empleado Rango 2
		if(rol==1) {
			jbOpcMenu[2].setVisible(false);
			jbOpcMenu[3].setVisible(false);
			jbOpcMenu[4].setVisible(false);
		}
	
		jpOeste.add(BorderLayout.CENTER, jpOesteCentro);

		this.getContentPane().add(BorderLayout.WEST, jpOeste);
		
		jbOpcMenu[0].addActionListener(this);
		jbOpcMenu[1].addActionListener(this);
		jbOpcMenu[2].addActionListener(this);
		jbOpcMenu[3].addActionListener(this);
		jbOpcMenu[4].addActionListener(this);
		jbOpcMenu[5].addActionListener(this);
		jbOpcMenu[6].addActionListener(this);
	}
	
	
	//ESCUCHA
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource().equals(this.getJbOpcMenu()[0]))
		{	if(this.getJbOpcMenu1().getActionListeners().length!=0)
			this.getJbOpcMenu1().removeActionListener(this.getJbOpcMenu1().getActionListeners()[0]);// quita el action listener actual
		if(this.getJbOpcMenu2().getActionListeners().length!=0)
			this.getJbOpcMenu2().removeActionListener(this.getJbOpcMenu2().getActionListeners()[0]);// quita el action listener actual
		if(this.getJbOpcMenu3().getActionListeners().length!=0)
			this.getJbOpcMenu3().removeActionListener(this.getJbOpcMenu3().getActionListeners()[0]);// quita el action listener actual
		if(this.getJbOpcMenu4().getActionListeners().length!=0)
			this.getJbOpcMenu4().removeActionListener(this.getJbOpcMenu4().getActionListeners()[0]);// quita el action listener actual
		if(this.getJbOpcMenu5().getActionListeners().length!=0)
			this.getJbOpcMenu5().removeActionListener(this.getJbOpcMenu5().getActionListeners()[0]);// quita el action listener actual
		if(this.getJbOpcMenu6().getActionListeners().length!=0)
			this.getJbOpcMenu6().removeActionListener(this.getJbOpcMenu6().getActionListeners()[0]);// quita el action listener actual
			JpanelCargarJIframe.removeAll();
			JpanelCargarJIframe.updateUI();
			this.getJlMenu().setText("ARTICULOS");
			this.getJlMenu().setVisible(true);
			this.getJlMenu().setFont(new Font("Arial Black",Font.BOLD,18));
			this.getJbOpcMenu1().setText("ALTA");
			this.getJbOpcMenu1().setVisible(true);
			this.getJbOpcMenu1().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO ALTA DEL ARTICULO
					JpanelCargarJIframe.removeAll();
					JpanelCargarJIframe.updateUI();
					AltaArticulo aa;
					try {
						aa = new AltaArticulo();
						JpanelCargarJIframe.add(aa);
						aa.setVisible(true);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			});
			this.getJbOpcMenu2().setText("MODIFICACION");
			this.getJbOpcMenu2().setVisible(true);
			this.getJbOpcMenu2().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						JpanelCargarJIframe.removeAll();
						ModificacionArticulo ma = new ModificacionArticulo();
						JpanelCargarJIframe.add(ma);
						JpanelCargarJIframe.updateUI();
						ma.setVisible(true);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			});
			this.getJbOpcMenu3().setText("BAJA");
			this.getJbOpcMenu3().setVisible(true);
			this.getJbOpcMenu3().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JpanelCargarJIframe.removeAll();
					BajaArticulo ba=new BajaArticulo();
					JpanelCargarJIframe.add(ba);
					ba.setVisible(true);
					JpanelCargarJIframe.updateUI();
				}
			});
			
			this.getJbOpcMenu4().setText("COMPRA");
			this.getJbOpcMenu4().setVisible(true);
			this.getJbOpcMenu4().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {

					JpanelCargarJIframe.removeAll();
					CompraArticulo ca = new CompraArticulo(usuario);
					JpanelCargarJIframe.add(ca);
					JpanelCargarJIframe.updateUI();
					ca.setVisible(true);

				}
			});
			this.getJbOpcMenu5().setText("DEVOLUCIONES");
			this.getJbOpcMenu5().setVisible(true);
			this.getJbOpcMenu5().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO PEDDIO ARTICULOS
					JpanelCargarJIframe.removeAll();
					DevolverArticulo ca = new DevolverArticulo();
					JpanelCargarJIframe.add(ca);
					JpanelCargarJIframe.updateUI();
					ca.setVisible(true);
					
				}
			});
		}
		else if(e.getSource().equals(this.getJbOpcMenu()[1]))
		{	
			if(this.getJbOpcMenu1().getActionListeners().length!=0)
				this.getJbOpcMenu1().removeActionListener(this.getJbOpcMenu1().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu2().getActionListeners().length!=0)
				this.getJbOpcMenu2().removeActionListener(this.getJbOpcMenu2().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu3().getActionListeners().length!=0)
				this.getJbOpcMenu3().removeActionListener(this.getJbOpcMenu3().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu4().getActionListeners().length!=0)
				this.getJbOpcMenu4().removeActionListener(this.getJbOpcMenu4().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu5().getActionListeners().length!=0)
				this.getJbOpcMenu5().removeActionListener(this.getJbOpcMenu5().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu6().getActionListeners().length!=0)
				this.getJbOpcMenu6().removeActionListener(this.getJbOpcMenu6().getActionListeners()[0]);
			JpanelCargarJIframe.removeAll();
			JpanelCargarJIframe.updateUI();
			this.getJlMenu().setText("CLIENTES");
			this.getJlMenu().setFont(new Font("Arial Black",Font.BOLD,18));
			this.getJlMenu().setVisible(true);
			
			this.getJbOpcMenu1().setText("ALTA");
			this.getJbOpcMenu1().setVisible(true);
			this.getJbOpcMenu1().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JpanelCargarJIframe.removeAll();
					AltaCliente ac=new AltaCliente();
					JpanelCargarJIframe.add(ac);
					ac.setVisible(true);
					JpanelCargarJIframe.updateUI();
				
					
				}
			});
			this.getJbOpcMenu2().setText("MODIFICACION");
			this.getJbOpcMenu2().setVisible(true);
			this.getJbOpcMenu2().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JpanelCargarJIframe.removeAll();
					ModificacionCliente mc=new ModificacionCliente();
					JpanelCargarJIframe.add(mc);
					mc.setVisible(true);
					JpanelCargarJIframe.updateUI();
				
					
				}
			});
			this.getJbOpcMenu3().setText("BAJA");
			this.getJbOpcMenu3().setVisible(true);
			this.getJbOpcMenu3().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JpanelCargarJIframe.removeAll();
					BajaCliente bc=new BajaCliente();
					JpanelCargarJIframe.add(bc);
					bc.setVisible(true);
					JpanelCargarJIframe.updateUI();
				}
			});
			
			this.getJbOpcMenu4().setText("COMPRA");
			this.getJbOpcMenu4().addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					JpanelCargarJIframe.removeAll();
					CompraCliente cc = null;
					try {
						cc = new CompraCliente(usuario);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JpanelCargarJIframe.add(cc);
					cc.setVisible(true);
					JpanelCargarJIframe.updateUI();
					
				}
				
			});
			this.getJbOpcMenu4().setVisible(true);
			this.getJbOpcMenu5().setVisible(false);
		}
		
		else if(e.getSource().equals(this.getJbOpcMenu()[2]))
		{
			if(this.getJbOpcMenu1().getActionListeners().length!=0)
				this.getJbOpcMenu1().removeActionListener(this.getJbOpcMenu1().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu2().getActionListeners().length!=0)
				this.getJbOpcMenu2().removeActionListener(this.getJbOpcMenu2().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu3().getActionListeners().length!=0)
				this.getJbOpcMenu3().removeActionListener(this.getJbOpcMenu3().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu4().getActionListeners().length!=0)
				this.getJbOpcMenu4().removeActionListener(this.getJbOpcMenu4().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu5().getActionListeners().length!=0)
				this.getJbOpcMenu5().removeActionListener(this.getJbOpcMenu5().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu6().getActionListeners().length!=0)
				this.getJbOpcMenu6().removeActionListener(this.getJbOpcMenu6().getActionListeners()[0]);
			JpanelCargarJIframe.removeAll();
			JpanelCargarJIframe.updateUI();
			this.getJlMenu().setText("EMPLEADOS");
			this.getJlMenu().setVisible(true);
			this.getJlMenu().setFont(new Font("Arial Black",Font.BOLD,18));
			this.getJbOpcMenu1().setText("ALTA");
			this.getJbOpcMenu1().setVisible(true);
			this.getJbOpcMenu1().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JpanelCargarJIframe.removeAll();
					AltaEmpleado ae=new AltaEmpleado();
					JpanelCargarJIframe.add(ae);
					ae.setVisible(true);
					JpanelCargarJIframe.updateUI();
				}
			});
			this.getJbOpcMenu2().setText("MODIFICACION");
			this.getJbOpcMenu2().setVisible(true);
			this.getJbOpcMenu2().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JpanelCargarJIframe.removeAll();
					ModificacionEmpleado me=new ModificacionEmpleado();
					JpanelCargarJIframe.add(me);
					me.setVisible(true);
					JpanelCargarJIframe.updateUI();
				}
			});
			this.getJbOpcMenu3().setText("BAJA");
			this.getJbOpcMenu3().setVisible(true);
			this.getJbOpcMenu3().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JpanelCargarJIframe.removeAll();
					BajaEmpleado be=new BajaEmpleado();
					JpanelCargarJIframe.add(be);
					be.setVisible(true);
					JpanelCargarJIframe.updateUI();
				}
			});
			this.getJbOpcMenu4().setVisible(false);
			this.getJbOpcMenu5().setVisible(false);
		}
		else if(e.getSource().equals(this.getJbOpcMenu()[3]))
		{
			if(this.getJbOpcMenu1().getActionListeners().length!=0)
				this.getJbOpcMenu1().removeActionListener(this.getJbOpcMenu1().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu2().getActionListeners().length!=0)
				this.getJbOpcMenu2().removeActionListener(this.getJbOpcMenu2().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu3().getActionListeners().length!=0)
				this.getJbOpcMenu3().removeActionListener(this.getJbOpcMenu3().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu4().getActionListeners().length!=0)
				this.getJbOpcMenu4().removeActionListener(this.getJbOpcMenu4().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu5().getActionListeners().length!=0)
				this.getJbOpcMenu5().removeActionListener(this.getJbOpcMenu5().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu6().getActionListeners().length!=0)
				this.getJbOpcMenu6().removeActionListener(this.getJbOpcMenu6().getActionListeners()[0]);
			JpanelCargarJIframe.removeAll();
			JpanelCargarJIframe.updateUI();
			this.getJlMenu().setText("PROVEEDORES");
			this.getJlMenu().setVisible(true);
			this.getJbOpcMenu1().setText("ALTA PROVEEDOR");
			this.getJbOpcMenu1().setVisible(true);
			
			this.getJbOpcMenu1().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JpanelCargarJIframe.removeAll();
					AltaProveedor ap=new AltaProveedor();
					JpanelCargarJIframe.add(ap);
					ap.setVisible(true);
					JpanelCargarJIframe.updateUI();
		
				}
			});
			
			this.getJbOpcMenu2().setText("MODIFICACION");
			this.getJbOpcMenu2().setVisible(true);
			this.getJbOpcMenu2().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JpanelCargarJIframe.removeAll();
					ModificacionProveedor mo;
					try {
						mo = new ModificacionProveedor();
						JpanelCargarJIframe.add(mo);
						mo.setVisible(true);
						JpanelCargarJIframe.updateUI();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
		
				}
			});
			this.getJbOpcMenu3().setText("BAJA");
			this.getJbOpcMenu3().setVisible(true);
			this.getJbOpcMenu3().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JpanelCargarJIframe.removeAll();
					BajaProveedor bp=new BajaProveedor();
					JpanelCargarJIframe.add(bp);
					bp.setVisible(true);
					JpanelCargarJIframe.updateUI();
				}
			});
			
			
			this.getJbOpcMenu4().setText("PEDIDO");
			this.getJbOpcMenu4().setVisible(true);
			this.getJbOpcMenu4().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JpanelCargarJIframe.removeAll();
					JpanelCargarJIframe.updateUI();
				
					try {
						
						PedidoProveedor	pp = new PedidoProveedor(usuario);
						JpanelCargarJIframe.add(pp);
						pp.setVisible(true);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
		
				}
			});
			
			this.getJbOpcMenu5().setVisible(false);
			
		}
		else if(e.getSource().equals(this.getJbOpcMenu()[4]))
		{
			if(this.getJbOpcMenu1().getActionListeners().length!=0)
				this.getJbOpcMenu1().removeActionListener(this.getJbOpcMenu1().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu2().getActionListeners().length!=0)
				this.getJbOpcMenu2().removeActionListener(this.getJbOpcMenu2().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu3().getActionListeners().length!=0)
				this.getJbOpcMenu3().removeActionListener(this.getJbOpcMenu3().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu4().getActionListeners().length!=0)
				this.getJbOpcMenu4().removeActionListener(this.getJbOpcMenu4().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu5().getActionListeners().length!=0)
				this.getJbOpcMenu5().removeActionListener(this.getJbOpcMenu5().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu6().getActionListeners().length!=0)
				this.getJbOpcMenu6().removeActionListener(this.getJbOpcMenu6().getActionListeners()[0]);
			JpanelCargarJIframe.removeAll();
			JpanelCargarJIframe.updateUI();
			this.getJlMenu().setText("PEDIDOS");
			this.getJlMenu().setVisible(true);
			this.getJlMenu().setFont(new Font("Arial Black",Font.BOLD,18));
			this.getJbOpcMenu1().setText("PENDIENTES");
			this.getJbOpcMenu1().setVisible(true);
			this.getJbOpcMenu1().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// ARTICULOS PENDIENTES, PARA CARGAR EN BBDD CUANDO LLEGUEN
					JpanelCargarJIframe.removeAll();
					JpanelCargarJIframe.updateUI();
					
					PedidiosPendientes pp;
					try {
						pp = new PedidiosPendientes();
						JpanelCargarJIframe.add(pp);
						pp.setVisible(true);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				}
			});
			this.getJbOpcMenu2().setText("DEVOLUCIONES");
			this.getJbOpcMenu2().setVisible(true);
			this.getJbOpcMenu2().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// ARTICULOS PENDIENTES, PARA CARGAR EN BBDD CUANDO LLEGUEN
					JpanelCargarJIframe.removeAll();
					JpanelCargarJIframe.updateUI();
					
					DevolverProveedor dp;
					dp = new DevolverProveedor();
					JpanelCargarJIframe.add(dp);
					dp.setVisible(true);
					
					
					
				}
			});
			this.getJbOpcMenu3().setVisible(false);
			this.getJbOpcMenu4().setVisible(false);
			this.getJbOpcMenu5().setVisible(false);
			
		}
		else if(e.getSource().equals(this.getJbOpcMenu()[5]))
		{
			if(this.getJbOpcMenu1().getActionListeners().length!=0)
				this.getJbOpcMenu1().removeActionListener(this.getJbOpcMenu1().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu2().getActionListeners().length!=0)
				this.getJbOpcMenu2().removeActionListener(this.getJbOpcMenu2().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu3().getActionListeners().length!=0)
				this.getJbOpcMenu3().removeActionListener(this.getJbOpcMenu3().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu4().getActionListeners().length!=0)
				this.getJbOpcMenu4().removeActionListener(this.getJbOpcMenu4().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu5().getActionListeners().length!=0)
				this.getJbOpcMenu5().removeActionListener(this.getJbOpcMenu5().getActionListeners()[0]);// quita el action listener actual
			if(this.getJbOpcMenu6().getActionListeners().length!=0)
				this.getJbOpcMenu6().removeActionListener(this.getJbOpcMenu6().getActionListeners()[0]);
			JpanelCargarJIframe.removeAll();
			JpanelCargarJIframe.updateUI();
			this.getJlMenu().setText("INFORMES");
			this.getJlMenu().setVisible(true);
			this.getJlMenu().setFont(new Font("Arial Black",Font.BOLD,18));
			this.getJbOpcMenu1().setText("VENTA POR CLIENTE");
			this.getJbOpcMenu1().setVisible(true);
			this.getJbOpcMenu1().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JpanelCargarJIframe.removeAll();
					JpanelCargarJIframe.updateUI();
					
					
				}
			});
			this.getJbOpcMenu2().setText("VENTA POR FECHA");
			this.getJbOpcMenu2().setVisible(true);
			this.getJbOpcMenu2().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JpanelCargarJIframe.removeAll();
					JpanelCargarJIframe.updateUI();
					
					
				}
			});
			this.getJbOpcMenu3().setText("PEDIDO PROVEEDOR");
			this.getJbOpcMenu3().setVisible(true);
			this.getJbOpcMenu3().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JpanelCargarJIframe.removeAll();
					JpanelCargarJIframe.updateUI();
					
					
				}
			});
			this.getJbOpcMenu4().setText("INVENTARIO");
			this.getJbOpcMenu4().setVisible(true);
			this.getJbOpcMenu4().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JpanelCargarJIframe.removeAll();
					JpanelCargarJIframe.updateUI();
					
					
				}
			});
			this.getJbOpcMenu5().setText("LISTADO DE CLIENTES");
			this.getJbOpcMenu5().setVisible(true);
			this.getJbOpcMenu5().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JpanelCargarJIframe.removeAll();
					JpanelCargarJIframe.updateUI();
					
					
				}
			});
			this.getJbOpcMenu6().setText("LISTADO DE EMPLEADOS");
			this.getJbOpcMenu6().setVisible(true);
			this.getJbOpcMenu6().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JpanelCargarJIframe.removeAll();
					JpanelCargarJIframe.updateUI();
					
					
				}
			});
		}else if(e.getSource().equals(this.getJbOpcMenu()[6]))
		{
			System.exit(0);
		}
		
		
	}
	

	
	
	public JButton getJbOpcMenu2() {
		return jbOpcMenu2;
	}

	public void setJbOpcMenu2(JButton jbOpcMenu2) {
		this.jbOpcMenu2 = jbOpcMenu2;
	}

	public JButton getJbOpcMenu3() {
		return jbOpcMenu3;
	}

	public void setJbOpcMenu3(JButton jbOpcMenu3) {
		this.jbOpcMenu3 = jbOpcMenu3;
	}

	public JButton getJbOpcMenu4() {
		return jbOpcMenu4;
	}

	public void setJbOpcMenu4(JButton jbOpcMenu4) {
		this.jbOpcMenu4 = jbOpcMenu4;
	}

	public JButton getJbOpcMenu5() {
		return jbOpcMenu5;
	}

	public void setJbOpcMenu5(JButton jbOpcMenu5) {
		this.jbOpcMenu5 = jbOpcMenu5;
	}
	
	public JButton getJbOpcMenu6() {
		return jbOpcMenu6;
	}

	public void setJbOpcMenu6(JButton jbOpcMenu6) {
		this.jbOpcMenu6 = jbOpcMenu6;
	}

	public JPanel getJpOeste() {
		return jpOeste;
	}

	public void setJpOeste(JPanel jpOeste) {
		this.jpOeste = jpOeste;
	}

	public JPanel getJpOesteNorte() {
		return jpOesteNorte;
	}

	public void setJpOesteNorte(JPanel jpOesteNorte) {
		this.jpOesteNorte = jpOesteNorte;
	}

	public JPanel getJpOesteCentro() {
		return jpOesteCentro;
	}

	public void setJpOesteCentro(JPanel jpOesteCentro) {
		this.jpOesteCentro = jpOesteCentro;
	}

	public JPanel getjPanelCentro() {
		return jPanelCentro;
	}

	public void setjPanelCentro(JPanel jPanelCentro) {
		this.jPanelCentro = jPanelCentro;
	}

	public JPanel getJpanelCentroNorte() {
		return JpanelCentroNorte;
	}

	public void setJpanelCentroNorte(JPanel jpanelCentroNorte) {
		JpanelCentroNorte = jpanelCentroNorte;
	}

	public JPanel getJpanelMitad() {
		return JpanelMitad;
	}

	public void setJpanelMitad(JPanel jpanelMitad) {
		JpanelMitad = jpanelMitad;
	}

	public JPanel getJpanelOpcionesMenu() {
		return JpanelOpcionesMenu;
	}

	public void setJpanelOpcionesMenu(JPanel jpanelOpcionesMenu) {
		JpanelOpcionesMenu = jpanelOpcionesMenu;
	}

	public JPanel getJpanelMitadCentral() {
		return JpanelMitadCentral;
	}

	public void setJpanelMitadCentral(JPanel jpanelMitadCentral) {
		JpanelMitadCentral = jpanelMitadCentral;
	}

	public JPanel getJpanelCargarJIframe() {
		return JpanelCargarJIframe;
	}

	public void setJpanelCargarJIframe(JPanel jpanelCargarJIframe) {
		JpanelCargarJIframe = jpanelCargarJIframe;
	}

	public JPanel getJpcsur() {
		return jpcsur;
	}

	public void setJpcsur(JPanel jpcsur) {
		this.jpcsur = jpcsur;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}

	public ImageIcon getImagencarnet() {
		return imagencarnet;
	}

	public void setImagencarnet(ImageIcon imagencarnet) {
		this.imagencarnet = imagencarnet;
	}

	public JLabel getJlImagen() {
		return jlImagen;
	}

	public void setJlImagen(JLabel jlImagen) {
		this.jlImagen = jlImagen;
	}

	public JLabel getJlRol() {
		return jlRol;
	}

	public void setJlRol(JLabel jlRol) {
		this.jlRol = jlRol;
	}

	public JLabel getJlNombre() {
		return jlNombre;
	}

	public void setJlNombre(JLabel jlNombre) {
		this.jlNombre = jlNombre;
	}

	public JLabel getJlPersona() {
		return jlPersona;
	}

	public void setJlPersona(JLabel jlPersona) {
		this.jlPersona = jlPersona;
	}

	public JLabel getJlMenu() {
		return jlMenu;
	}

	public void setJlMenu(JLabel jlMenu) {
		this.jlMenu = jlMenu;
	}

	public JScrollPane getBarraScrollPanelCargarJIframe() {
		return barraScrollPanelCargarJIframe;
	}

	public void setBarraScrollPanelCargarJIframe(JScrollPane barraScrollPanelCargarJIframe) {
		this.barraScrollPanelCargarJIframe = barraScrollPanelCargarJIframe;
	}

	public JButton[] getJbOpcMenu() {
		return jbOpcMenu;
	}

	public void setJbOpcMenu(BotonMenu[] jbOpcMenu) {
		this.jbOpcMenu = jbOpcMenu;
	}

	public JButton getJbAjuste() {
		return jbAjuste;
	}

	public void setJbAjuste(JButton jbAjuste) {
		this.jbAjuste = jbAjuste;
	}

	public JButton getJbfecha() {
		return jbcerrar;
	}

	public void setJbfecha(JButton jbfecha) {
		this.jbcerrar = jbfecha;
	}

	public JButton getJbOpcMenu1() {
		return jbOpcMenu1;
	}

	public void setJbOpcMenu1(JButton jbOpcMenu1) {
		this.jbOpcMenu1 = jbOpcMenu1;
	}


	
	
}
