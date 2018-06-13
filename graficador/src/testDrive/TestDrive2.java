package testDrive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import auxi.Evaluador;

class TestDrive2 {
	
	Evaluador e;
	


	@Test
	void getValueTest() {
		e=Evaluador.getInstance();
		String s ="x+2";
		e.parseExpresion(s);
		assertEquals(3.0, e.getValue(1));
		
		
		s="tan(x)";
		e.parseExpresion(s);
		assertEquals(0, Math.round(e.getValue(0)));
		assertEquals(1, Math.round(e.getValue(Math.PI/4)));
		s="ln(x)";
		e.parseExpresion(s);
		assertEquals(0, Math.round(e.getValue(1)));
		assertEquals(0.6931471805599453, e.getValue(2));
				

	}
	
	@Test
	void hasErrorTest() {
		e=Evaluador.getInstance();
		String s ="x+2fjklasdñ";
		e.parseExpresion(s);
		assertEquals(true, e.hasError());
		
		
		s="tan(x)";
		e.parseExpresion(s);
		assertEquals(false, e.hasError());
		
		s="ln(x)+15-22*8x-x^2^5";
		e.parseExpresion(s);
		assertEquals(false, e.hasError());
		
				

	}
	
	

}
