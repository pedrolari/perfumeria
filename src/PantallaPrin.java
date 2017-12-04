import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
	private JButton  jbOpcMenu []=new JButton[5];
	private JButton jbAjuste, jbfecha, jbOpcMenu1 ,jbOpcMenu2,jbOpcMenu3,jbOpcMenu4,jbOpcMenu5;

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

		String[] noms={"Empleados", "Proveedores", "Clientes", "Articulos", "Salir"};
		
		/*Inicializacion de los botones de menu y caracteristicas*/
		for (int i = 0; i < jbOpcMenu.length; i++) {
			
			jbOpcMenu[i]=new JButton(noms[i]);
			jbOpcMenu[i].setForeground(Color.white);
			jbOpcMenu[i].setOpaque(false);
			jbOpcMenu[i].setContentAreaFilled(false);
			jbOpcMenu[i].setBorderPainted(false);
			jbOpcMenu[i].setPreferredSize(new Dimension(200, 50));
			jpOesteCentro.add(jbOpcMenu[i]);
			
		}
	
		jpOeste.add(BorderLayout.CENTER, jpOesteCentro);

		this.getContentPane().add(BorderLayout.WEST, jpOeste);
		
		jbOpcMenu[0].addActionListener(this);
		jbOpcMenu[1].addActionListener(this);
		jbOpcMenu[2].addActionListener(this);
		jbOpcMenu[3].addActionListener(this);
		jbOpcMenu[4].addActionListener(this);
	}
	
	
	//ESCUCHA
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource().equals(this.getJbOpcMenu()[0]))
		{
			this.getJlMenu().setText("EMPLEADOS");
			this.getJlMenu().setVisible(true);
			this.getJbOpcMenu1().setText("ALTA");
			this.getJbOpcMenu1().setVisible(true);
			this.getJbOpcMenu1().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AltaEmpleado ae=new AltaEmpleado();
					JpanelCargarJIframe.add(ae);
					ae.setVisible(true);
					JpanelCargarJIframe.updateUI();
				}
			});
			this.getJbOpcMenu2().setText("MODIFICACION");
			this.getJbOpcMenu2().setVisible(true);
			this.getJbOpcMenu3().setText("BAJA");
			this.getJbOpcMenu3().setVisible(true);
			this.getJbOpcMenu4().setVisible(false);
			this.getJbOpcMenu5().setVisible(false);
			
		}
		else if(e.getSource().equals(this.getJbOpcMenu()[1]))
		{
			this.getJlMenu().setText("PROVEEDORES");
			this.getJlMenu().setVisible(true);
			this.getJbOpcMenu1().setText("ALTA");
			this.getJbOpcMenu1().setVisible(true);
			
			this.getJbOpcMenu1().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					AltaProveedor ap=new AltaProveedor();
					JpanelCargarJIframe.add(ap);
					ap.setVisible(true);
		
				}
			});
			
			this.getJbOpcMenu2().setText("MODIFICACION");
			this.getJbOpcMenu2().setVisible(true);
			this.getJbOpcMenu2().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
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
			
			
			this.getJbOpcMenu4().setText("PEDIDO");
			this.getJbOpcMenu4().setVisible(true);
			this.getJbOpcMenu4().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JpanelCargarJIframe.removeAll();
				
					try {
						PedidoProveedor	pp = new PedidoProveedor();
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
		
		else if(e.getSource().equals(this.getJbOpcMenu()[2]))
		{
			this.getJlMenu().setText("CLIENTES");
			this.getJlMenu().setVisible(true);
			
			this.getJbOpcMenu1().setText("ALTA");
			this.getJbOpcMenu1().setVisible(true);
			this.getJbOpcMenu1().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Entramos");
					AltaCliente ac=new AltaCliente();
					JpanelCargarJIframe.add(ac);
					ac.setVisible(true);
				
					
				}
			});
			this.getJbOpcMenu2().setText("MODIFICACION");
			this.getJbOpcMenu2().setVisible(true);
			this.getJbOpcMenu3().setText("BAJA");
			this.getJbOpcMenu3().setVisible(true);
			this.getJbOpcMenu4().setText("COMPRA");
			this.getJbOpcMenu4().addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					CompraCliente cc=new CompraCliente();
					JpanelCargarJIframe.add(cc);
					cc.setVisible(true);
					
				}
				
			});
			this.getJbOpcMenu4().setVisible(true);
			this.getJbOpcMenu5().setVisible(false);
		}
		else if(e.getSource().equals(this.getJbOpcMenu()[3]))
		{
			this.getJlMenu().setText("ARTICULOS");
			this.getJlMenu().setVisible(true);
			this.getJbOpcMenu1().setText("ALTA");
			this.getJbOpcMenu1().setVisible(true);
			this.getJbOpcMenu1().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO ALTA DEL ARTICULO
					JpanelCargarJIframe.removeAll();
					AltaArticulo aa=new AltaArticulo();
					JpanelCargarJIframe.add(aa);
					aa.setVisible(true);
					
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
					// TODO BAJA DE ARTICULO
					JpanelCargarJIframe.removeAll();
					
				}
			});
			
			this.getJbOpcMenu4().setText("COMPRA");
			this.getJbOpcMenu4().setVisible(true);
			this.getJbOpcMenu4().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {

					JpanelCargarJIframe.removeAll();
					CompraArticulo ca = new CompraArticulo();
					JpanelCargarJIframe.add(ca);
					ca.setVisible(true);

				}
			});
			this.getJbOpcMenu5().setText("PEDIDO");
			this.getJbOpcMenu5().setVisible(true);
			this.getJbOpcMenu5().addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO PEDDIO ARTICULOS
					JpanelCargarJIframe.removeAll();
					
				}
			});
		}
		else if(e.getSource().equals(this.getJbOpcMenu()[4]))
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

	public void setJbOpcMenu(JButton[] jbOpcMenu) {
		this.jbOpcMenu = jbOpcMenu;
	}

	public JButton getJbAjuste() {
		return jbAjuste;
	}

	public void setJbAjuste(JButton jbAjuste) {
		this.jbAjuste = jbAjuste;
	}

	public JButton getJbfecha() {
		return jbfecha;
	}

	public void setJbfecha(JButton jbfecha) {
		this.jbfecha = jbfecha;
	}

	public JButton getJbOpcMenu1() {
		return jbOpcMenu1;
	}

	public void setJbOpcMenu1(JButton jbOpcMenu1) {
		this.jbOpcMenu1 = jbOpcMenu1;
	}


	
	
}
