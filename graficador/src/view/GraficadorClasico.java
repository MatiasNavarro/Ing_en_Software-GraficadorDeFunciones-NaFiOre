
package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import org.nfunk.jep.*;
import org.lsmp.djep.djep.DJep;

import controller.Context;



public class GraficadorClasico extends JPanel
{
    //VARIABLES PARA EL EVALUADOR DE FUNCIONES
    
    
    

	
	
	JTextField campoIntervaloA,campoIntervaloB;
    JTextField campoNoPuntos;
    JTextField campoNumeroDeInteraciones;
    JTextField campoXo;
	JTextField campoFuncion;
	

    

    


    
    JLabel Mensaje;
    


	JScrollPane scrollPane;
    
    
    //BOTONES
    JButton BtnAyuda;
    private JButton BtnGraficar;
    private JButton botonMetodo1;
    private JButton botonMetodo2;
    JButton botonMetodo3;
    JButton botonMetodo4;
    int puntosInt;
    
    //PANELES
    //JPanel panelEscalas; //Panel para las escalas
    ZonaGrafica panelGrafico; //Aqu’ va la Zona grafica
    JPanel panelControles ; //panel para botones y campos de texto,etc
    JPanel panelBotones;//
    JPanel DisplayPanel1 = new JPanel(); //Panel grande para la grafica
    JPanel DisplayPanel2 = new JPanel(); //panel grande para todos los controles
    
    private JFrame fFrame; //ventana de ayuda
    
    private Context ctx;
    

   



    


    

    GraficadorClasico propio;
    private JPanel panel;
    private JLabel label;
    private JLabel lblXi;
    

    
    public GraficadorClasico(Container Contenedor)
    {
        BtnGraficar = new JButton("Graficar");
        botonMetodo1 = new JButton("Bisecci�n");
        botonMetodo2 = new JButton("Newton");
        botonMetodo3 = new JButton("Secante");
        botonMetodo4 = new JButton("Regula falsa");
        campoFuncion = new JTextField("(x+2)^2-1",15);//x^3+2x^2+7x-20//cos(x)//ACA CAMBIE
        
        
        
     
        panelGrafico = new ZonaGrafica(); //zona grafica
        ctx = new Context(this);
        panelGrafico.addContext(ctx);
        panelControles = new JPanel();
        panelControles.setSize(200,200);
        
        scrollPane = new JScrollPane(panelGrafico);///////
        
        DisplayPanel1.setLayout(new BorderLayout());
        DisplayPanel1.add(scrollPane, BorderLayout.CENTER);//panelGrafico
     
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4,1));
        panelBotones.add(getBotonMetodo1());
        panelBotones.add(getBotonMetodo2());
        panelBotones.add(botonMetodo3);
        panelBotones.add(botonMetodo4);
	    
        panelControles.setLayout(new GridLayout(4,2));
        panelControles.add(campoFuncion);
        panelControles.add(getBtnGraficar());
           
           campoIntervaloA = new JTextField("-2",3);
           campoIntervaloB = new JTextField("2",3);
           campoNoPuntos = new JTextField("99",3);
           
              JPanel miniPanelintervalos = new JPanel();//mini panel para intervalos a y b
              JLabel etiquetaA = new JLabel("           a: ");
              JLabel etiquetaB = new JLabel("           b: ");
              JLabel etiquetaNumPuntos = new JLabel("N. de puntos: ");
              miniPanelintervalos.setLayout(new GridLayout(1,6));
              miniPanelintervalos.add(etiquetaA);
              miniPanelintervalos.add(campoIntervaloA);
              miniPanelintervalos.add(etiquetaB);
              miniPanelintervalos.add(campoIntervaloB);
              miniPanelintervalos.add(etiquetaNumPuntos);
              miniPanelintervalos.add(campoNoPuntos);
              panelControles.add(miniPanelintervalos);
              campoIntervaloA.addActionListener(ctx);
              campoIntervaloB.addActionListener(ctx);
              campoNoPuntos.addActionListener(ctx);
        

           
           Mensaje = new JLabel("",JLabel.LEFT);
           panelControles.add(Mensaje);
        
        JPanel miniPanelX0 = new JPanel();
        FlowLayout fl_miniPanelX0 = (FlowLayout) miniPanelX0.getLayout();
        fl_miniPanelX0.setAlignOnBaseline(true);
        panelControles.add(miniPanelX0);
        
        lblXi = new JLabel("Xi");
        miniPanelX0.add(lblXi);
        campoXo = new JTextField("",15);

        miniPanelX0.add(campoXo);
	
        //BORDES
        Border colorline = BorderFactory.createLineBorder(new Color(220,220,220));
        DisplayPanel1.setBorder(colorline);
        TitledBorder rotulo;
	
        rotulo = BorderFactory.createTitledBorder(" M�todos ");
        rotulo.setTitleFont(Const.FT10.f);
        rotulo.setTitleColor(new Color(0,0,128));
        panelBotones.setBorder(rotulo);
	
        rotulo = BorderFactory.createTitledBorder(" Funci�n ");
        rotulo.setTitleColor(new Color(0,0,128));
        rotulo.setTitleFont(Const.FT10.f);
        panelControles.setBorder(rotulo);
        //fin de Bordes
        
	
        DisplayPanel1.setPreferredSize( new Dimension(Const.GANCHO.dim,Const.GALTO.dim));
	
        DisplayPanel2.setLayout(new BorderLayout(1,1));
        DisplayPanel2.add("Center", panelControles);
        campoNumeroDeInteraciones = new JTextField("3",3);
        
           JPanel miniPanelInteraciones = new JPanel();
           JLabel etiquetaInteraciones = new JLabel("N. de iteraciones: ");
           miniPanelInteraciones.add(etiquetaInteraciones);
           miniPanelInteraciones.add(campoNumeroDeInteraciones);
           panelControles.add(miniPanelInteraciones);
        
        
       
        
        // BOTONES
        BtnAyuda = new JButton("Ayuda");
        panelControles.add(BtnAyuda);
        BtnAyuda.addActionListener(ctx);
        
        panel = new JPanel();
        panelControles.add(panel);
        
        label = new JLabel("");
        panel.add(label);
        DisplayPanel2.add("West", panelBotones);	
     
        Contenedor.setLayout(new BorderLayout());
        Contenedor.add("North",DisplayPanel1);
        Contenedor.add("South",DisplayPanel2);
	 
        

        
 
        

	 
	 		
		campoFuncion.addActionListener(ctx);
		getBtnGraficar().addActionListener(ctx);
        setfFrame(new AyudaJFrame (this));
        propio=this;//OJO CON ESTO
        
        
        botonMetodo1.addActionListener(ctx); 
        
        
        botonMetodo2.addActionListener(ctx);
            

        
        
    }//
    
    public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public int getPuntosInt() {
		return puntosInt;
	}

	public void setPuntosInt(int puntosInt) {
		this.puntosInt = puntosInt;
	}

	public JPanel getPanelGrafico() {
		return panelGrafico;
	}

	public void setPanelGrafico(ZonaGrafica panelGrafico) {
		this.panelGrafico = panelGrafico;
	}
/*
	*/
	


	public JTextField getCampoFuncion() {
		return campoFuncion;
	}

	public void setCampoFuncion(JTextField campoFuncion) {
		this.campoFuncion = campoFuncion;
	}

	public JTextField getCampoIntervaloA() {
		return campoIntervaloA;
	}

	public void setCampoIntervaloA(JTextField campoIntervaloA) {
		this.campoIntervaloA = campoIntervaloA;
	}

	public JTextField getCampoIntervaloB() {
		return campoIntervaloB;
	}

	public void setCampoIntervaloB(JTextField campoIntervaloB) {
		this.campoIntervaloB = campoIntervaloB;
	}

	public JTextField getCampoNoPuntos() {
		return campoNoPuntos;
	}

	public void setCampoNoPuntos(JTextField campoNoPuntos) {
		this.campoNoPuntos = campoNoPuntos;
	}

	public JTextField getCampoNumeroDeInteraciones() {
		return campoNumeroDeInteraciones;
	}

	public void setCampoNumeroDeInteraciones(JTextField campoNumeroDeInteraciones) {
		this.campoNumeroDeInteraciones = campoNumeroDeInteraciones;
	}

	public JPanel getDisplayPanel1() {
		return DisplayPanel1;
	}

	public void setDisplayPanel1(JPanel displayPanel1) {
		DisplayPanel1 = displayPanel1;
	}

	public JPanel getDisplayPanel2() {
		return DisplayPanel2;
	}

	public void setDisplayPanel2(JPanel displayPanel2) {
		DisplayPanel2 = displayPanel2;
	}
	
	/*public JEP getMiEvaluador() {
		return miEvaluador;
	}

	public void setMiEvaluador(JEP miEvaluador) {
		this.miEvaluador = miEvaluador;
	}*/




/*
    public DefaultListModel getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel listModel) {
		this.listModel = listModel;
	}
	*/
	public JLabel getMensaje() {
		return Mensaje;
	}

	public void setMensaje(JLabel mensaje) {
		Mensaje = mensaje;
	}





    


   

 
class AyudaJFrame extends JFrame
{    
    JTextArea p;
    GraficadorClasico fApplet;

    AyudaJFrame(GraficadorClasico applet)
    {
        super ("textos");
        fApplet=applet;
        Container content_pane = getContentPane ();

        p = new JTextArea(300,400);
        p.setText(information());
        p.setEditable(false);

        JScrollPane sp = new JScrollPane(p);
        //content_pane.add(listBusca,BorderLayout.CENTER);

        pack ();
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed (ActionEvent e)
    {
        //nada por hoy
    }


    String information(){
        String message =
    " :.\n"
+ " Mover ejes : arrastre el mouse\n\n" 
+" ------ ------ EJEMPLO\n" 
+ " + suma x+2\n"
+ " - resta x-5\n"
+ " * multiplicación 3*x\n"
+ " / división -1/x\n"
+ " () agrupación (x+2)/(3*x)\n"
+ " ^ potenciación (-3*x)^2\n"
+ " % resto de la división x%5\n"
+ " RAIZ(x) raíz cuadrada RAIZ(x)\n"
+ " sqrt() raíz cuadrada sqrt(x)\n"
+ " mod() resto de la división mod(x,5)\n"
+ " sen() seno 4*sen(x^2)\n"
+ " cos() coseno 6*cos(-3*x)\n"
+ " tan() tangente 3*tan(x)\n"
+ " atan() arcotangente atan(x-3)\n"
+ " asin() arcoseno asen((x+5)/(3^x))\n"
+ " acos() arcocoseno 2-acos(-x+3)\n"
+ " sinh() seno hiperbólico sinh(x)\n"
+ " cosh() coseno hiperbólico -3*cosh(1/x)\n"
+ " tanh() tangente hiperbólica tanh(x)/2\n"
+ " asinh() arcoseno hiperbólico 2*asinh(x)/3\n"
+ " acosh() arcocoseno hiperbólico (2+acosh(x))/(1-x)\n"
+ " atanh() arcotangente hiperbólica atanh(x)*(3-x^(1/x))\n"
+ " ln() logaritmo natural ln(x)+1\n"
+ " log() logaritmo decimal -2*log(x)-1\n"
+ " abs() valor absoluto abs(x-2)\n"
+ " rand() valor aleatorio rand()\n"
+ " re() parte real de un Complejo re(2+9*i)\n"
+ " im() parte imaginaria im(-8+7*i)\n"
+ " angle() ángulo en pos. estándar angle(x,2)\n\n" 
+ " pi 3,141592653589793 pi+cos(x)\n"
+ " e 2,718281828459045 e+1\n" 
+ " Usa JEP,(Nathan Funk http://jep.sourceforge.net)\n\n"
+ " :. Diseño e implementación: \n \n"
+ " :. Walter Mora F., wmora2@yahoo.com.mx\n\n"
+ " :. CRV: Centro de Recursos Virtuales (www.cidse.itcr.ac.cr)\n"
+ " :. Instituto Tecnológico de Costa Rica\n"
+ " :. Escuela de Matemática\n";
return message;
}//information

} // class AyudaFrame


////////CLASE PARA EL MANEJO DE LOS EVENTOS ///////////////////////////////////



public void showMessage(String s, Color C ) {
   
	Mensaje.setText(s);
    Mensaje.setForeground(C);
    
    //graficador.getCampoFuncion().setForeground(Color.red);
	
}

public JFrame getfFrame() {
	return fFrame;
}

public void setfFrame(JFrame fFrame) {
	this.fFrame = fFrame;
}

public JButton getBtnGraficar() {
	return BtnGraficar;
}

public void setBtnGraficar(JButton btnGraficar) {
	BtnGraficar = btnGraficar;
}

public JButton getBotonMetodo1() {
	return botonMetodo1;
}

public void setBotonMetodo1(JButton botonMetodo1) {
	this.botonMetodo1 = botonMetodo1;
}

public JButton getBotonMetodo2() {
	return botonMetodo2;
}

public void setBotonMetodo2(JButton botonMetodo2) {
	this.botonMetodo2 = botonMetodo2;
}


} // end APPLET


