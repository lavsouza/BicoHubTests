package main.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.Calculadora;

public class CalculadoraTest {
	
	
	private Calculadora c;
	
	@BeforeEach
	public void configurar() {
		this.c = new Calculadora();
	}

	@Test
	public void somaTest() {
		double res = c.soma(3, 4);
		
		Assertions.assertEquals(7.0, res);
	}
	
	@Test
	public void somaNegativoTest() {
		double res = c.soma(-3, 4);
		
		Assertions.assertEquals(1.0, res);
	}
	
	
	@Test
	public void somaLimiteDoubleTest() {
		double res = c.soma(Double.MAX_VALUE, Double.MAX_VALUE);
		
		Assertions.assertEquals(Double.POSITIVE_INFINITY, res);
		
	}
	
	@Test
	public void divTest() {
		double res = c.div(4, 2);
		
		Assertions.assertEquals(2.0, res);
	}
	
	@Test
	public void divPorZeroTest() {
		Assertions.assertThrows(
				ArithmeticException.class, 
				() -> c.div(1,0));
	}
}
