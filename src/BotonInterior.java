import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

public class BotonInterior extends JButton{

    private Color color1 = new Color(41, 53, 65);
    private Color color2 = Color.WHITE;
    private Color color3 = Color.white;

    
    public BotonInterior(String s) {
    	super(s);
        setOpaque(false);
        setContentAreaFilled(false);
       // setBackground(new Color(41, 53, 65));
        setMargin(new Insets(15,20,15,20));
        setForeground(Color.WHITE);
        setFont(new Font("Arial",Font.BOLD,16));
        setFocusPainted(false);
        setBorderPainted(false);
        
       
    }

    protected void paintComponent(Graphics g) {
        Color c1,c2,c3;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        ButtonModel m = getModel();

         Paint oldPaint = g2.getPaint();
        if (m.isArmed()){
           c2=color1.darker();
           c1=color2.darker();
           c3=color3;
        }else{
           c1=color1.darker();
           c2=color2.darker();
           c3=color3.brighter();
        }
        if (!m.isEnabled()){
        	c1=new Color(138,136,136);
        	c2=new Color(40,39,39);
        	c3=color3;
         /*  c2=color1.brighter();
           c1=color2.brighter();
           c3=color3.darker();*/
           
           
        }
          RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0,0,getWidth(),getHeight()-1,20,20);
            g2.clip(r2d);
            g2.setPaint(new GradientPaint(0.0f, 0.0f, c1,0.0f, getHeight(), c2));
            g2.fillRect(0,0,getWidth(),getHeight());

            g2.setStroke(new BasicStroke(4f));
            g2.setPaint(new GradientPaint(0.0f, 0.0f, c3,0.0f, getHeight(), c3));
            g2.drawRoundRect(0, 0, getWidth()-2 , getHeight() -2, 18, 18);

        g2.setPaint(oldPaint);
        super.paintComponent(g);
    }
    public void setEnabled(boolean b){
    	super.setEnabled(b);
    	if(this.isEnabled()){
    		this.setForeground(Color.WHITE);
    	}
    }
    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public Color getColor3() {
        return color3;
    }

    public void setColor3(Color color3) {
        this.color3 = color3;
    }

}
