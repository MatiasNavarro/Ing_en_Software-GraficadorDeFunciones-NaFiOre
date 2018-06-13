package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import java.awt.geom.Line2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



import org.nfunk.jep.type.Complex;

import auxi.Evaluador;
import controller.Context;



public class ZonaGrafica extends JPanel  
{
	ImageIcon imageIcon;
	int     offsetX, offsetY;
    boolean dragging;
	private int    x0;
	private int y0;           
    private int    escalaX;
	int escalaY;
	int aumento1,aumento2;
    private int intervaloA;
	private int intervaloB;
    int numeroDeIteraciones, puntosIt;
    double xmin,xmax,imgx;
    String funcion;
    
    private Evaluador E;
    
    //VARIABLES PARA GROSOR DE LAS LINEAS
    final static BasicStroke grosor1 = new BasicStroke(1.5f); //thickness
    final static float dash1[] = {5.0f};
    final static BasicStroke dashed = new BasicStroke(1.0f,
                                                      BasicStroke.CAP_BUTT, 
                                                      BasicStroke.JOIN_MITER, 
                                                      5.0f, dash1, 0.0f);
    
    boolean    errorEnExpresion; //si hay error de sintaxis en la función
    boolean    errorEnNumero   ; //si hay error de sintaxis en la función
      
    Context ctx;
    
    ZonaGrafica() 
    {
    
    	setE(Evaluador.getInstance());
    	
    
    	imageIcon = new ImageIcon(getClass().getResource("background.jpg"));  //imagen de fondo
    	 
    	funcion=new String("(x+2)^2-1");
 
        setEscalaX(30);
        escalaY=30;
        x0=Const.GANCHO.dim/2;
        setY0(Const.GALTO.dim/2);
        aumento1=7;
        setBackground(Color.white);             
        offsetX=x0; offsetY=getY0();

    }
    
    public void pressed(int x, int y)
    {
        //if (dragging)
            //return;
        //int x = evt.getX();  // clic inicial
        //int y = evt.getY();
        
        offsetX = x - x0;
        offsetY = y - getY0();
        //dragging = true;
    }

    /*public void mouseReleased(MouseEvent evt)
    {
        //dragging = false;
        //repaint();
    }*/

    public void drag(int x,int y)
    {
        
        //int x = evt.getX();   // posici—n del mouse
        //int y = evt.getY();
        x0 = x - offsetX;     // mover origen
        setY0(y - offsetY);
        repaint();
    }
    
    public void zoomer(int zoom) {
        //int zoom = evt.getWheelRotation();
        escalaY += -zoom;
        setEscalaX(getEscalaX() + -zoom);
        repaint();
    }

           //el resto hace nada 
    public void mouseMoved(MouseEvent evt) {}
    public void mouseClicked(MouseEvent evt) { }
    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) { }

    public void paintComponent(Graphics g)
    {
         super.paintComponent(g);
         Graficar(g, x0, getY0());                      
    }
 
    //METODO QUE PINTA TODA LA GRçFICA
    void Graficar(Graphics ap, int xg, int yg)
    {
        ap.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
        setBackground(new Color(36,85,102)); //COLOR FONDO/////////////////////////////////////////////////
        int xi=0,yi=0,xi1=0,yi1=0,numPuntos=1;
        int cxmin,cxmax,cymin,cymax;
        double valxi=0.0, valxi1=0.0, valyi=0.0,valyi1=0.0;
        //convertimos el objeto ap en un objeto Graphics2D para usar los mŽtodos Java2D
        Graphics2D g = (Graphics2D) ap;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setFont(Const.FT10.f); 
        //g.setPaint(new Color(130,216,245));// color ejes

        //PINTAMOS EL EJE X Y EL EJE Y
        dibujarLinea(g, xg, 10, xg, Const.GALTO.dim-10); //EJE Y
        dibujarLinea(g,10, yg, Const.GANCHO.dim-10, yg);//EJE X

        xmin=-1.0*xg/getEscalaX();
        xmax=(1.0*(Const.GANCHO.dim-xg)/getEscalaX());
        cxmin=(int)Math.round(xmin); //pantalla
        cxmax=(int)Math.round(xmax);

        if(getIntervaloA()==0){
            setIntervaloA(cxmin);
        }

            
        if(getIntervaloB()==0){
            setIntervaloB(cxmax);
        }


        //puntosIt = Integer.parseInt(campoNoPuntos.getText());
        //intervalos para pintar funci—n
        cymin=(int)Math.round(1.0*(yg-Const.GALTO.dim)/escalaY);
        cymax=(int)Math.round(1.0*yg/escalaY);

        numPuntos=Const.GANCHO.dim; //num pixels

        g.setPaint(new Color(49,173,215)); //COLOR CUADRICULA
        g.setFont(Const.FT7.f);

        //PINTAMOS TODOS LOS EJES PARA FORMAR LA CUADRICULA
        if(getEscalaX()>5)
        {
            for(int i=cxmin;i<=cxmax;i++)
            {   //EJES PARALELOS AL EJE Y
                g.setPaint(new Color(49,173,215)); //COLOR CUADRICULA
                dibujarLinea(g,xg+i*getEscalaX(), yg-2, xg+i*getEscalaX() , yg+2);
                if( (xg+i*getEscalaX()) != xg )
                    dibujarLinea(g,xg+i*getEscalaX(), 10, xg+i*getEscalaX(), Const.GALTO.dim-10);
                
                if(i>0){
                    g.setPaint(new Color(255,255,255));//COLOR NUMEROS
                    g.drawString(""+i, xg+i*getEscalaX()-aumento1, yg+12);
                }
                if(i<0){
                    g.setPaint(new Color(255,255,255));//COLOR NUMEROS
                    g.drawString(""+i, xg+i*getEscalaX()-8, yg+12);
                }
            }
        }

        if(escalaY>5)
        {
            for(int i=cymin+1;i<cymax;i++)
            {   //EJES PARALELOS AL EJE X
                g.setPaint(new Color(49,173,215)); //COLOR CUADRICULA
                dibujarLinea(g,xg-2, yg-i*escalaY, xg+2 , yg-i*escalaY);
                if( (yg-i*escalaY) != yg )
                    dibujarLinea(g,10, yg-i*escalaY, Const.GANCHO.dim, yg-i*escalaY);
                if(i>0){
                    g.setPaint(new Color(255,255,255));//COLOR NUMEROS
                    g.drawString(""+i, xg-12,yg-i*escalaY+8 );
                }
                if(i<0){
                    g.setPaint(new Color(255,255,255));//COLOR NUMEROS
                    g.drawString(""+i, xg-14,yg-i*escalaY+8 );
                }
            }
        }
        
        g.setPaint(new Color(29,220,248));//COLOR DE LA FUNCION
        
        g.setStroke(grosor1);
  
        E.parseExpresion(funcion);
        errorEnExpresion = E.hasError(); //hay error?
        
        
        String derivada = "";

        if(!errorEnExpresion)
        {
        	ctx.sendMessage("M�todos num�ricos", Color.BLACK);
        	
            //campoFuncion.setForeground(Color.black);
            
            //CICLO QUE PINTA LA FUNCIîN
            for(int i=(xg+getIntervaloA()*getEscalaX());i<(xg+getIntervaloB()*escalaY)-1;i++)//numPuntos
            {
                valxi =xmin +i*1.0/getEscalaX();
                valxi1=xmin+(i+1)*1.0/getEscalaX();
                //getE().addVariable("x", valxi);
                valyi=E.getValue(valxi);  
                //getE().addVariable("x", valxi1);
                valyi1 = E.getValue(valxi1);
                xi =(int)Math.round(getEscalaX()*(valxi));
                yi =(int)Math.round(escalaY*valyi); 
                xi1=(int)Math.round(getEscalaX()*(valxi1));
                yi1=(int)Math.round(escalaY*valyi1); 

                //control de discontinuidades groseras y complejos
                //control de puntos
                if(i%(100-puntosIt)==0){
                    //Complex valC = getE().getComplexValue();
                    //System.out.println("valc "+valC);

                    //double imgx = (double)Math.abs(valC.im()); 
                    if(dist(valxi,valyi,valxi1,valyi1)< 1000 && imgx==0)
                    {
                        dibujarLinea(g,xg+xi,yg-yi,xg+xi1,yg-yi1); 
                    }
                }
            }//fin del for 
        }else{
        	ctx.sendMessage(":. Hay un error.", Color.RED);
        	//Mensaje.setText();
            //campoFuncion.setForeground(Color.red);
        }
        
    }//Graficar
 
    double dist(double xA,double yA, double xB,double yB)
    {
        return (xA - xB)*(xA - xB)+(yA - yB)*(yA - yB);
    }//
    
  
    public void dibujarLinea(Graphics2D g, double a, double b,double c, double d) {
    	
    	g.draw(new Line2D.Double(a,b, c,d));
    	}
    
    
    
    
    public void setIntervaloA(int a) {
    	
    	intervaloA=a;
    	
    }
    
    
    public void setIntervaloB(int b) {
    	
    	intervaloB=b;
    	
    }
    
public void setnroPuntos(int p) {
    	
    	puntosIt=p;
    	
    }

public void setFuncion(String f) {
	
	funcion=f;
	
}

public void addContext(Context ctx) {
	this.ctx=ctx;
}


public int getIntervaloA() {
	return intervaloA;
}


public int getIntervaloB() {
	return intervaloB;
}


public int getY0() {
	return y0;
}


public void setY0(int y0) {
	this.y0 = y0;
}


public int getEscalaX() {
	return escalaX;
}


public void setEscalaX(int escalaX) {
	this.escalaX = escalaX;
}



public int getEscalaY() {
	return escalaY;
}

public void setEscalaY(int escalaY) {
	this.escalaY = escalaY;
}



public void setX0(int x0) {
	this.x0 = x0;
}



public double getXmin() {
	return xmin;
}

public void setXmin(double xmin) {
	this.xmin = xmin;
}

public double getXmax() {
	return xmax;
}

public void setXmax(double xmax) {
	this.xmax = xmax;
}


public int getX0() 
{
	
	return x0;
}


public Evaluador getE() {
	return E;
}


public void setE(Evaluador e) {
	E = e;
}



 
} // class