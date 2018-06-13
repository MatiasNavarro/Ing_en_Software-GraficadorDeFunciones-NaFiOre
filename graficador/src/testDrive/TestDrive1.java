package testDrive;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import java.awt.geom.Line2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


import java.awt.Container;

import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import auxi.Evaluador;
import controller.Context;
import model.Biseccion;
import model.Metodo;
import view.GraficadorClasico;
import view.ZonaGrafica;
import view.myFrame;

class TestDrive1 {
	
	Evaluador e;
	Biseccion b;
	GraficadorClasico g;
	Context ctx;

	@Test
	void test() {
		
		e=Evaluador.getInstance();
		String s ="x+1";
		e.parseExpresion(s);
		assertEquals(2.0, e.getValue(1));
		
		Container contenedor = new Container();
		g= new GraficadorClasico(contenedor);
		ctx= new Context(g);
		((ZonaGrafica)g.getPanelGrafico()).paintComponent(g.getPanelGrafico().getGraphics());
		b=new Biseccion((ZonaGrafica) g.getPanelGrafico(),ctx);
		
		b.run();
		assertEquals(-1, b.getP3());
		

	}
	

	
	


}
