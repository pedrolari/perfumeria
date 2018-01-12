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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import net.sf.jasperreports.engine.JRException;

public class PedidiosPendientes extends JInternalFrame implements ActionListener{
	private JLabel txt; //etiqueta
	private JPanel ptotal,pcen,psur; //paneles
	private JScrollPane scroll; //scroll del jlist
	private JList<String> list; //jlist que mostrara los pedidos pendientes
	private DefaultListModel<String> dlm; //defualtlistmodel del jlist
	private JButton btn, btnprueba; //boton
	PedidiosPendientes() throws ClassNotFoundException, SQLException{
		
		//propiedades de la ventana
		this.setPreferredSize(new Dimension(1050, 640));
		this.getContentPane().setBackground(Color.white);
		this.setClosable(false);
		this.setMaximizable(false);
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,40,50));
		
		//panel total y propiedades
		ptotal=new JPanel(new BorderLayout(150,30));
		ptotal.setBackground(Color.white);
		ptotal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 53, 65), 1), "PEDIDOS PENDIENTES",TitledBorder.LEFT,TitledBorder.TOP,new Font(null, Font.BOLD,25), new Color(41, 53, 65)));
		this.getContentPane().add(ptotal);

		//centro con jlist con los pedidos pendientes
		pcen=new JPanel(new FlowLayout(FlowLayout.CENTER));
		pcen.setBorder(new EmptyBorder(70, 350, 30, 350));
		ptotal.add(pcen,BorderLayout.CENTER);
		pcen.setBackground(Color.white);
		dlm=new DefaultListModel<>(); 
		dlm=cogerPedidos(); //coge los pedidos pendientes 
		list=new JList<String>(dlm);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//seleccion multiple
		scroll=new JScrollPane(list);//scroll para la lista
		scroll.setPreferredSize(new Dimension(300, 300));//tama�o del scroll
		pcen.add(scroll);
		
		//zona sur con botones
		psur=new JPanel(new FlowLayout(FlowLayout.CENTER,40,10));
		psur.setBackground(Color.white);
		ptotal.add(psur, BorderLayout.SOUTH);
		btn=new BotonInterior("Marcar como recibido");
		btn.addActionListener(this);
		btnprueba=new BotonInterior("Prueba pdf");
		btnprueba.addActionListener(this);
		psur.add(btn);
		psur.add(btnprueba);
		
	}
	/**
	 * Funcion que coge los pedidos que estan pendientes, mostrando id_pedido, nombre de proveedor y fecha de compra
	 * @return DefaultListModel con todos los pedidos pendientes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected DefaultListModel<String> cogerPedidos() throws ClassNotFoundException, SQLException{
		DefaultListModel<String> aux=new DefaultListModel<>();
		Conexion c=new Conexion();
		ResultSet rs =c.consultar("select * from compras where estado = 0");
		while(rs.next()) {
			String elto=rs.getString(1)+"-";
			ResultSet rs2=c.consultar("select * from proveedores where cif like '"+rs.getString(3)+"'");
			if(rs2.next()) {
				elto+=rs2.getString(2)+"-";
			}
			elto+=rs.getString(4);
			aux.addElement(elto);

		}
		c.close();
		return aux;
	}
	//escucha de boton
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn) {
			try {
				actualizarPedido();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource()==btnprueba) {
			try {
				PedidoPdf pp=new PedidoPdf();
			} catch (ClassNotFoundException | SQLException | JRException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	/**
	 * Funci�n que actualiza el estado de pedido a 1, que es que ha llegado, y actualiza el stock
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void actualizarPedido() throws ClassNotFoundException, SQLException {
		Conexion c=new Conexion();
		if(list.getSelectedIndices().length==0) {
			JOptionPane.showMessageDialog(this, "No seleccion� ningu pedido, seleccione el/los pedidos que haya recibido");
		}else {
			//JOptionPane.showMessageDialog(this, list.getSelectedIndices().length);
			for (int i = 0; i < list.getSelectedIndices().length; i++) {
				
				int numPed=Integer.parseInt(dlm.get(list.getSelectedIndices()[i]).split("-")[0]);
				JOptionPane.showMessageDialog(this, dlm.get(list.getSelectedIndices()[i]));
				c.modificar("update compras set estado = 1 where id_compra = "+numPed);
				actualizarLineaPedido(numPed, c);
				
			}
			dlm.removeAllElements();
			dlm=cogerPedidos();
			list.setModel(dlm);
		}
		
	}
	/**
	 * Funcion que actualiza el stock en articulos en funcion de las lineas de pedido
	 * @param idPed
	 * @param c
	 * @throws SQLException
	 */
	public void actualizarLineaPedido(int idPed, Conexion c) throws SQLException {
		ResultSet rs= c.consultar("select * from lineas_de_compras where id_compra = "+idPed);
		while(rs.next()) {
			c.modificar("update articulos set stock = stock + "+rs.getInt(4)+" where id_articulo = "+rs.getInt(3));
		}
	}
}
