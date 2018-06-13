package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import auxi.Evaluador;
import controller.Context;
import view.ZonaGrafica;

public class Newton implements Metodo
{
    double x1,xi;
    double fx1,fxi;
    int numeroDeInteraciones;
    int interaciones = 0;
    Graphics2D g;  //clave para poder pintar con el run
    Point2D.Double puntoA1;
    JPanel panelGrafico;
    
    
	private Context ctx;
	private java.awt.geom.Point2D.Double puntoB1;
	private int numeroDeIteraciones;
	private int iteraciones;
	private Evaluador E;
	
	private int escalaX;
	private DefaultListModel listModel;
	private ZonaGrafica z;
    
    /*public Newton(JPanel panelGrafico,JTextField campoNumeroDeInteraciones,int x0, int escalaX, int y0)
    {
    	this.x0=x0;
    	g = (Graphics2D)panelGrafico.getGraphics();
        xi = 1;
        numeroDeInteraciones = Integer.parseInt( campoNumeroDeInteraciones.getText() );
        puntoA1 = new Point2D.Double(x0+xi*escalaX,y0);
    }*/
    
    public Newton(ZonaGrafica z, Context ctx)
    {
    	System.out.print(2);
    	this.z = z;
    	this.ctx = ctx;
    	g = (Graphics2D)z.getGraphics();
        //puntoA1 = new Point2D.Double(z.getX0()+a1*z.getEscalaX(),z.getY0());
        //puntoB1 = new Point2D.Double(z.getX0()+b1*z.getEscalaX(),z.getY0());
        //numeroDeIteraciones = ctx.hashCode();
        iteraciones = 10;
        E=Evaluador.getInstance();
        
    	listModel = new DefaultListModel();
    	

    }
    
    public void run()
    {
    	System.out.print(3);
    	double tolerance = .01; // Our approximation of zero
    	int max_count = 200; // Maximum number of Newton's method iterations

   /* x is our current guess. If no command line guess is given, 
      we take 0 as our starting point. */

   	  double x = 0;
   	  z.dibujarLinea(g,(int) x*z.getEscalaX() + z.getX0() ,z.getY0()+10, (int) x*z.getEscalaX() + z.getX0() ,z.getY0() -10 );

      for( int count=1; (Math.abs(E.getValue(x)) > tolerance) && ( count < iteraciones); count ++)  {
    	  
    	  g.setPaint(Color.orange);
    	  z.dibujarLinea(g, x*z.getEscalaX() + z.getX0() ,z.getY0()+10, (int) x*z.getEscalaX()+ z.getX0() ,z.getY0() -10 );
    	  g.drawString("x"+(count-1),(int) (z.getX0()+(x*z.getEscalaX())) ,z.getY0()-12 );
    	  
    	  x= Math.round(x - E.getValue(x)/E.getDValue(x));
    	  //System.out.println("Step: "+count+" x:"+x+" Value:"+f(x));
    	  
    	  
    	  System.out.println(x+","+z.getY0());
    	  try{Thread.sleep((int)(Math.random()*2000));}
          catch(InterruptedException e){System.out.println("error"+e.toString());}
   	  }            

   	  if( Math.abs(E.getValue(x)) <= tolerance) {
   	   ctx.sendMessage("Raiz encontrada en x="+x, Color.black);
   	   g.setPaint(Color.green);
   	   z.dibujarLinea(g,(int) x*z.getEscalaX() + z.getX0() ,z.getY0()+10, (int) x*z.getEscalaX()+ z.getX0() ,z.getY0() -10 );
   	   g.drawString("xf",(int) (z.getX0()+(x*z.getEscalaX())) ,z.getY0()-12 );
   	  }
   	  else {
   	   System.out.println("Failed to find a zero");
   	  }
        }

    

	@Override
	public void setIt(int x) {
		numeroDeIteraciones=x;
		
	}
        
}

