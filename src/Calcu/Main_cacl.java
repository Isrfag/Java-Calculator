package Calcu;

import javax.swing.JFrame;

public class Main_cacl extends Calculadora {

	public static void main(String[] args) {
		 
		Calculadora lacalcu=new Calculadora();
		lacalcu.iniciar();
		lacalcu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lacalcu.setVisible(true);
		
		
	}

}
