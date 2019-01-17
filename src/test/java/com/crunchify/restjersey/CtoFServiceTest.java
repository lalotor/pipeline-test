package com.crunchify.restjersey;

import static org.junit.Assert.*;

import org.junit.Test;

public class CtoFServiceTest {

	@Test
	public void testConvertCtoF() {
		CtoFService test = new CtoFService();
		String expected = CtoFServiceTest.getExpected(36.8, 98.245);
		String actual = test.convertCtoF();
		//System.out.println("->" + expected +  "<-");
		//System.out.println("->" + actual +  "<-");
		assertEquals("El resultado debe ser XML",
		        expected, 
				actual);
	}

	@Test
	public void testConvertCtoFfromInput() {
		CtoFService test = new CtoFService();
		double celsius = 17.01;
		double fahrenheit = 62.618;
		String expected = CtoFServiceTest.getExpected(celsius, fahrenheit);
		String actual = test.convertCtoFfromInput(celsius);
		//System.out.println("->" + expected +  "<-");
		//System.out.println("->" + actual +  "<-");
		assertEquals("El resultado debe ser XML",
		        expected, 
				actual);
	}
	
	
	private static String getExpected(double celsius, double fahrenheit) {
		StringBuilder sb = new StringBuilder("<ctofservice><celsius>")
		.append(celsius)
		.append("</celsius><ctofoutput>@Produces(\"application/xml\") ")
		.append("Output: C to F Converter Output: ")
		.append(fahrenheit)
		.append("</ctofoutput></ctofservice>");
		
		return sb.toString();
	}

}
