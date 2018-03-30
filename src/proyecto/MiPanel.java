/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author eulises
 */
public class MiPanel extends javax.swing.JPanel {
 
        public MiPanel(){    
            this.setSize(400,280);
        }

        @Override
        public void paint(Graphics g){
            
            Dimension tamanio = getSize();
            ImageIcon imagenFondo = new ImageIcon(getClass().getResource("images.jpg"));        
            g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null);        
            setOpaque(false);
            super.paintComponent(g);
        }    
}
