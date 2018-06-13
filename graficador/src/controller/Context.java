package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import model.Biseccion;
import model.Newton;
import model.Metodo;
import view.GraficadorClasico;
import view.ZonaGrafica;



public class Context implements ActionListener, MouseListener, MouseMotionListener, MouseWheelListener{

	  private Metodo strategy;
	  Thread t ;
	  GraficadorClasico g;
	  ZonaGrafica z;
	  boolean dragging;

	  public Context(GraficadorClasico g) {
		  this.g = g;
		  z= (ZonaGrafica) g.getPanelGrafico();
		  t=new Thread();
		  
	       z.addMouseListener(this);
	       z.addMouseMotionListener(this);
	       z.addMouseWheelListener(this);
		  
	  }
	  
	  public void setMetodoStrategy(Metodo strategy) {

	    this.strategy = strategy;
	    

	  }

	  

	  //use the strategy

	  public void calculate() {
		t.interrupt();
		 
		t = new Thread(strategy);

	    t.start();
	   
	}
	  public void sendMessage(String S, Color c) {
		  
		  g.showMessage(S,c);
	  }
	  public void actualize(Object source) {
		z.setIntervaloA(Integer.parseInt(g.getCampoIntervaloA().getText()));
		z.setIntervaloB(Integer.parseInt(g.getCampoIntervaloB().getText()));
		z.setnroPuntos(Integer.parseInt(g.getCampoNoPuntos().getText()));
		z.setFuncion(g.getCampoFuncion().getText());
		z.repaint();

		
	}
	 public void actionPerformed (ActionEvent evt)
	{
		Object source = evt.getSource ();
		//ACTUALIZA LA GRAFICA SI SE OPROME UN BOTON O ENTER EN UN CAMPO DE TEXTO
		if ( source == g.getBtnGraficar() || source == g.getCampoFuncion() || source == g.getCampoIntervaloA() || source == g.getCampoIntervaloB() || source == g.getCampoNoPuntos()){
			t.interrupt();
			this.actualize(source);
			
		}

		/*if(source == g.BtnAyuda){
			g.getfFrame().setVisible (true);
		}//
		*/
		if(source == g.getBotonMetodo1())
		    {
		        //listModel.clear();
		        this.setMetodoStrategy(new Biseccion(z,this));
				strategy.setIt(Integer.parseInt(g.getCampoNumeroDeInteraciones().getText()));
		        this.calculate();
		        
		        
		    }
		
		if(source == g.getBotonMetodo2())
		{
			System.out.print(1);
        	//listModel.clear();
            this.setMetodoStrategy(new Newton(z,this));
            strategy.setIt(Integer.parseInt(g.getCampoNumeroDeInteraciones().getText()));
            this.calculate();
            
        }
		
		
	
}//
	
	public void mousePressed(MouseEvent evt)
	    {
	        if (dragging)
	            return;
	        int x = evt.getX();  // clic inicial
	        int y = evt.getY();
	        z.pressed(x, y);
	        //offsetX = x - x0;
	        //offsetY = y - y0;
	        dragging = true;
	    }

	public void mouseReleased(MouseEvent evt)
	    {
	        dragging = false;
	        //repaint();
	    }

	    public void mouseDragged(MouseEvent evt)
	    {
	        if (dragging == false)
	            return;
	        int x = evt.getX();   // posici—n del mouse
	        int y = evt.getY();
	        z.drag(x, y);
	        //x0 = x - offsetX;     // mover origen
	        //y0 = y - offsetY;
	        //repaint();
	    }
	    
	    public void mouseWheelMoved(MouseWheelEvent evt) {
	        int zoom = evt.getWheelRotation();
	        z.zoomer(zoom);
	        //escalaY += -zoom;
	        //escalaX += -zoom;
	        //repaint();
	    }

	           //el resto hace nada 
	    public void mouseMoved(MouseEvent evt) {}
	    public void mouseClicked(MouseEvent evt) { }
	    public void mouseEntered(MouseEvent evt) { }
	    public void mouseExited(MouseEvent evt) { }
	  
}