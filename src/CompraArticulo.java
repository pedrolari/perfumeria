import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class CompraArticulo extends JInternalFrame {

	
	private JLabel lb1;
	private JTextField tf1, tfCantidad;
	private JPanel principal, jpBuscar, jpListaCompra;
	private JButton btnBusqueda;
	private DefaultTableModel lineaPedido;
	private JTable listaCompra;
	private String[] columnas = { "Id", "Articulo", "Cantidad", "Precio", "Total" };
	private Articulo nuevo;
	CompraArticulo (){
		this.setPreferredSize(new Dimension(1050, 500));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Modificacion Articulo", TitledBorder.LEFT, TitledBorder.TOP, new Font(null, Font.BOLD, 25), Color.GRAY));

		//PANEL PRINCIPAL QUE CONTENDRA LOS PANELES NORTE, SUR, ESTE Y OESTE
		principal = new JPanel(new BorderLayout(150, 30));
		//===================================================================
				
		//PANEL DE BUSQUEDA
		jpBuscar=new JPanel(new GridLayout(7, 1, 20, 20));
				
		lb1 = new JLabel("Introduce el ID del articulo");
		tf1 = new JTextField(20);
		btnBusqueda = new JButton("A�adir a la lista");
		btnBusqueda.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				//BUSQUEDA DE PRODUCTO E INSERCCION EN LISTA DE COMPRA
				nuevo = new Articulo();
				if(!tf1.getText().equals("")){
					if(nuevo.find(Integer.parseInt(tf1.getText()))){
						// A�ADIR POR VECTOR LOS DATOS DEL OBJETO NUEVO
						lineaPedido.addRow(new Object[]{nuevo.getId_articulo(),nuevo.getNombre(),0,nuevo.getPrecio(), 0});
						//A�ADIMOS UN JTEXTFIELD EN LA TABLA PARA CALCULAR LAS CANTIDADES
						tfCantidad =  new JTextField();
						TableColumn tc1 = listaCompra.getColumnModel().getColumn(2);
						tc1.setCellEditor(new DefaultCellEditor(tfCantidad));
						
						//CUANDO SE PINCHE EN LA CELDA DE CANTIDAD HABRA UN ESCUCHADOR PARA REALIZAR EL CALCULO
						tfCantidad.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								listaCompra.setValueAt((Integer.parseInt(tfCantidad.getText()) * nuevo.getPrecio()), listaCompra.getSelectedRow(), 4);
								
							}
						});
					}
					else{
						JOptionPane.showMessageDialog(null, "El producto no existe en la BBDD");
					}
				}
				
			}
		});
		

		
		
		
		jpBuscar.add(lb1);
		jpBuscar.add(tf1);
		jpBuscar.add(btnBusqueda);
		//===================================================================
		
		//PANEL DE COMRPA
		jpListaCompra = new JPanel(new GridLayout(1, 1, 20, 20));
		
		lineaPedido = new DefaultTableModel();
		listaCompra = new JTable(lineaPedido);
		for (int i = 0; i < columnas.length; i++) {
			lineaPedido.addColumn(columnas[i]);
		}
		
		JScrollPane panelScroll = new JScrollPane(listaCompra);
		jpListaCompra.add(panelScroll);
		//===================================================================
		
		principal.add(jpBuscar, BorderLayout.WEST);
		principal.add(jpListaCompra, BorderLayout.CENTER);
		this.getContentPane().add(principal);
	}
	

}
