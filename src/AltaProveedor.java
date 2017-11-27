import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class AltaProveedor extends JInternalFrame{

	private JLabel lbl;
	
	AltaProveedor()
	{
		
		this.setPreferredSize(new Dimension(1050, 640));
		this.setBorder(null);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		
		lbl=new JLabel("HOLAAAAA");
		
		this.getContentPane().add(lbl);
		
	}
	
	
}
