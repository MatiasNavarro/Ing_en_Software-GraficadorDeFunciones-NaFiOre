package view;

import java.awt.Font;

public enum Const {
	
	
	

    FT7			(0  ,new Font("Arial",Font.PLAIN,12) ),
    FT10		(0  ,new Font("Arial",Font.PLAIN,12) ),
    FT12		(0  ,new Font("Arial",Font.PLAIN,12) ),
	GALTO    	(900-10-380  ,   null),
	GANCHO  	((70*690/100)+900, null); 
	
	public final int dim;
	public final Font f;
	
	private Const(int dim, Font f) {
		
		this.dim=dim;
		this.f = f;
	}

}
