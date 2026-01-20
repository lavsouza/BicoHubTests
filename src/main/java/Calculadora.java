package main.java;

public class Calculadora {
	
	public double soma(double a, double b) {
		return a+b;
	}
	
	public double div(int a, int b) {
		
		if (b == 0) {
			throw new ArithmeticException("Não pode dividir por zero!!");
		}
		
		return a/b;
	}

}
