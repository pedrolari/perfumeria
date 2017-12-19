
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.*;

public class BotonMenu extends JButton{
	
	BotonMenu(String letras, String img){
		super();
		
		ImageIcon i=new ImageIcon(img);
		Image i2=i.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		i=new ImageIcon(i2);
		this.setIcon(i);
		this.setText(letras);
		this.setBackground(new Color(41, 53, 65));
		this.setForeground(Color.WHITE);
		this.setFocusable(false);
		this.setBorderPainted(false);
		this.setHorizontalAlignment(CENTER);
		this.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setIconTextGap(15);
	}
}
