package model;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.DefaultListModel;

import auxi.Evaluador;
import controller.Context;
import view.GraficadorClasico;
import view.ZonaGrafica;

public class Biseccion implements Metodo
{
    float puntoMedio,a1,b1;
    double p1,p2,p3,last;
    Graphics2D g;  //clave para poder pintar con el run
    int numeroDeIteraciones;
    int iteraciones;
    String cadenaLista;
    
    Point2D.Double puntoA1;
    Point2D.Double puntoB1;
    Point2D.Double pointMedio;
    
    
    
    Evaluador E;
    
    int y0,x0,escalaX;
    
    DefaultListModel listModel;
    Context ctx;
	private ZonaGrafica z;
    
    
    //negrada el ca√±o XD
    
    public Biseccion(ZonaGrafica z, Context ctx)
    {
    	this.z = z;
    	this.ctx = ctx;
    	g = (Graphics2D)z.getGraphics();
        a1 = z.getIntervaloA();
        b1 =z.getIntervaloB();
        puntoA1 = new Point2D.Double(z.getX0()+a1*z.getEscalaX(),z.getY0());
        puntoB1 = new Point2D.Double(z.getX0()+b1*z.getEscalaX(),z.getY0());
        numeroDeIteraciones = ctx.hashCode();
        iteraciones = 0;
        puntoMedio = (a1+b1)/2;
        E=Evaluador.getInstance();
        y0=z.getY0();
    	x0=z.getX0();
    	escalaX=z.getEscalaX();
    	listModel = new DefaultListModel();
    	
    	

    }
    
    public void run()
    {
    	//DefaultListModel listModel= graficador.getListModel();
    	
    	
    	
        //E.addVariable("x", a1);
        p1=E.getValue(a1);
        
        //E.addVariable("x", b1);
        p2=E.getValue(b1);
        
        
        ((Graphics2D)z.getGraphics()).setPaint(Color.red);
        z.dibujarLinea(g,(int)Math.round(puntoA1.getX()),y0+10,(int)Math.round(puntoA1.getX()),y0 -10 );
        z.dibujarLinea(g,(int)Math.round(puntoB1.getX()),y0+10,(int)Math.round(puntoB1.getX()),y0 -10 );
        try{Thread.sleep((int)(Math.random()*2000));}
        catch(InterruptedException e){System.out.println("error"+e.toString());}
        
        if(p1==0) {
        	g.setPaint(Color.blue);
        	z.dibujarLinea(g,(int)Math.round(a1),y0+10,(int)Math.round(a1),y0 -10 );
        }
        
        if(p2==0) {
        	g.setPaint(Color.blue);
        	z.dibujarLinea(g,(int)Math.round(b1),y0+10,(int)Math.round(b1),y0 -10 );
        }
        
        else if(p1*p2<0){
            cadenaLista = new String(" n \t\t\t "+" int A \t\t\t"+" int B \t\t\t"+" punto medio \t\t\t"+" f(punto medio)");
            listModel.addElement(cadenaLista);
            
            g.setPaint(Color.red);
            z.dibujarLinea(g,(int)Math.round(puntoA1.getX()),y0+10,(int)Math.round(puntoA1.getX()),y0 -10 );
            z.dibujarLinea(g,(int)Math.round(puntoB1.getX()),y0+10,(int)Math.round(puntoB1.getX()),y0 -10 );
            
            g.setPaint(Color.blue);
            pointMedio = new Point2D.Double(x0+puntoMedio*escalaX,y0);
            z.dibujarLinea(g,(int)Math.round(pointMedio.getX()),y0+10,(int)Math.round(pointMedio.getX()),y0 -10 );
            try{Thread.sleep((int)(Math.random()*2000));}
            catch(InterruptedException e){System.out.println("error"+e.toString());}
            
            //E.addVariable("x", puntoMedio);
            p3=E.getValue(puntoMedio);
            
            while( p3 != 0 && iteraciones < numeroDeIteraciones  ){
                cadenaLista = new String();
                listModel.addElement(cadenaLista);
                //E.addVariable("x", a1);
                p1=E.getValue(a1);
                //E.addVariable("x", b1);
                p2=E.getValue(b1);
                //E.addVariable("x", puntoMedio);
                p3=E.getValue(puntoMedio);
                
                if(p1*p3<0){
                    cadenaLista = new String(""+iteraciones+" \t\t\t "+a1+" \t\t\t "+b1+" \t\t\t"+puntoMedio);
                    listModel.addElement(cadenaLista);
                    
                    g.setPaint(Color.yellow);
                    z.dibujarLinea(g,(int)Math.round(puntoB1.getX()),y0+10,(int)Math.round(puntoB1.getX()),y0 -10 );
                    b1=puntoMedio;
                    puntoA1 = new Point2D.Double(x0+a1*escalaX,y0);
                    puntoB1 = new Point2D.Double(x0+b1*escalaX,y0);
                    
                    g.setPaint(Color.red);
                    z.dibujarLinea(g,(int)Math.round(puntoA1.getX()),y0+10,(int)Math.round(puntoA1.getX()),y0 -10 );
                    z.dibujarLinea(g,(int)Math.round(puntoB1.getX()),y0+10,(int)Math.round(puntoB1.getX()),y0 -10 );
                    puntoMedio = (a1+b1)/2;

                
                    try{Thread.sleep((int)(Math.random()*2000));}
                    catch(InterruptedException e){System.out.println("error"+e.toString());}
                
                    g.setPaint(Color.blue);
                    pointMedio = new Point2D.Double(x0+puntoMedio*escalaX,y0);
                    z.dibujarLinea(g,(int)Math.round(pointMedio.getX()),y0+10,(int)Math.round(pointMedio.getX()),y0 -10 );
                
                    try{Thread.sleep((int)(Math.random()*2000));}
                    catch(InterruptedException e){System.out.println("error"+e.toString());}
                
                }
                else if(p3*p2<0){
                    cadenaLista = new String(""+iteraciones+" \t\t\t "+a1+" \t\t\t "+b1+" \t\t\t"+puntoMedio);
                    listModel.addElement(cadenaLista);

                    g.setPaint(Color.yellow);
                    z.dibujarLinea(g,(int)Math.round(puntoA1.getX()),y0+10,(int)Math.round(puntoA1.getX()),y0 -10 );
                    
                    a1=puntoMedio;
                    puntoA1 = new Point2D.Double(x0+a1*escalaX,y0);
                    puntoB1 = new Point2D.Double(x0+b1*escalaX,y0);
                                
                    g.setPaint(Color.red);
                    z.dibujarLinea(g,(int)Math.round(puntoA1.getX()),y0+10,(int)Math.round(puntoA1.getX()),y0 -10 );
                    z.dibujarLinea(g,(int)Math.round(puntoB1.getX()),y0+10,(int)Math.round(puntoB1.getX()),y0 -10 );
                    puntoMedio = (a1+b1)/2;
                
                    try{Thread.sleep((int)(Math.random()*2000));}
                    catch(InterruptedException e){System.out.println("error"+e.toString());}
                
                    g.setPaint(Color.blue);
                    pointMedio = new Point2D.Double(x0+puntoMedio*escalaX,y0);
                    z.dibujarLinea(g,(int)Math.round(pointMedio.getX()),y0+10,(int)Math.round(pointMedio.getX()),y0 -10 );
                
                    try{Thread.sleep((int)(Math.random()*2000));}
                    catch(InterruptedException e){System.out.println("error"+e.toString());}
                }
                iteraciones++;
            }//while
        }//if
        else{
        	
        	ctx.sendMessage("     Este mÈtodo no puede aplicarse a esta funciÛn ", Color.RED);
            //graficador.getMensaje().setText("esa funcion no sirve para este metodo");
            //Mensaje.setForeground(Color.red);
            //graficador.getCampoFuncion().setForeground(Color.red);
        }
        //graficador.getPanelGrafico().repaint();
    }//run

	@Override
	public void setIt(int x) {
		numeroDeIteraciones=x;
		
	}
	
	public double getP3() {
		return p3;
	}
        
        
}//clase Bisecci‚Äîn
    
