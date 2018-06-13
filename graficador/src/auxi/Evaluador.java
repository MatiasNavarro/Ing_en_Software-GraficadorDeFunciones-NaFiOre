package auxi;

import org.lsmp.djep.*;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.type.Complex;
import org.lsmp.djep.djep.*;

public class Evaluador {
	
	
	//private    JEP miEvaluador;
	private	  DJep miEvaluador;
	Node funcion;
	Node derivada;
	
	

	
	private static Evaluador INSTANCE = null;
	   
        
        
    private Evaluador() {
    		 	//miEvaluador = new JEP();
    			miEvaluador = new DJep();
    	        miEvaluador.addStandardFunctions();  //agrega las funciones matematicas comunes
    	        miEvaluador.addStandardConstants();
    	        miEvaluador.addComplex();
    	        miEvaluador.addFunction("sen", new org.nfunk.jep.function.Sine());//habilitar 'sen'
    	        miEvaluador.addVariable("x", 0);
    	        miEvaluador.setImplicitMul(true); //permite 2x en vez de 2*x
//
    	        
    	        /*derivador.addStandardConstants();
    	        derivador.addStandardFunctions();
    	        derivador.addComplex();
    	        derivador.setAllowUndeclared(true);
    	        derivador.setAllowAssignment(true);
    	        derivador.setImplicitMul(true);*/

    	        // Sets up standard rules for differentiating sin(x) etc.
    	        //derivador.addStandardDiffRules();
            }

    
	    // Private constructor suppresses 
	    

	    // creador sincronizado para protegerse de posibles problemas  multi-hilo
	    // otra prueba para evitar instanciación múltiple 
	    private synchronized static void createInstance() {
	        if (INSTANCE == null) { 
	            INSTANCE = new Evaluador();
	        }
	    }

	    public static Evaluador getInstance() {
	        if (INSTANCE == null) createInstance();
	        return INSTANCE;
	    }


	
	
	public double getValue(double arg1) {
		
		Object v=0;
		
		miEvaluador.addVariable("x", arg1);
		try {
		 v = miEvaluador.evaluate(funcion);
		
		 return ((Number)v).doubleValue();
		}
		catch(ParseException e) {return 0; }
		
		

		
	}
	
public double getDValue(double arg1) {
		
		Object v=0;
		
		miEvaluador.addVariable("x", arg1);
		try {
		 v = miEvaluador.evaluate(derivada);
		
		 return ((Number)v).doubleValue();
		}
		catch(ParseException e) {return 0; }
		
		

		
	}
	
	public void addVariable(String arg0,double arg1) {
		miEvaluador.addVariable(arg0, arg1);
	}
	
	public Complex getComplexValue() {
		return miEvaluador.getComplexValue();
	}
	
	public void parseExpresion(String arg0) {
		
		try {
		miEvaluador.addVariable("x", 0);
		funcion= miEvaluador.parse(arg0);
		derivada= miEvaluador.differentiate(funcion,"x");
		//derivada=miEvaluador.parse(arg0);
		miEvaluador.println(derivada);
		Node simp = miEvaluador.simplify(derivada);
        // print
        miEvaluador.println(simp);
        
		}
		catch(ParseException e)
	    {
	        System.out.println("Error with parsing");
	    }
		
		
	}
	
	public boolean hasError() {
		return miEvaluador.hasError();
		
	}

}
// This time the differentiation is specified by
// the diff(eqn,var) function
//Node node2 = j.parse("diff(cos(x^3),x)");
// To actually make diff do its work the
// equation needs to be preprocessed
//Node processed = j.preprocess(node2);
//j.println(processed);
// finally simplify
//Node simp2 = j.simplify(processed);
//j.println(simp2);