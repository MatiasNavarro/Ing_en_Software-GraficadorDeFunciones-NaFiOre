package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

    public class myFrame extends JFrame
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JPanel aaa;
        public myFrame(){
            setSize(1000,690);//500,350
            setTitle("Graficador");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(true);
            
            
            Container Contenedor = getContentPane();
            GraficadorClasico mipanel = new GraficadorClasico(Contenedor);
    
            this.add(mipanel);
    
            setVisible(true);
            setLocationRelativeTo(null);
            
            
        }
    }
