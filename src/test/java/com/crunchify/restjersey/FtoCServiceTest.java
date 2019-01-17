package com.crunchify.restjersey;

import static org.junit.Assert.*;

import org.junit.Test;
import javax.ws.rs.core.Response;

//import java.io.*;
//import java.util.stream.Collectors;

public class FtoCServiceTest {

	@Test
	public void testConvertFtoC() {
		FtoCService test = new FtoCService();		
		String expected = FtoCServiceTest.getExpected(36.8, 98.24);		
		Response response = test.convertFtoC();
		String actual = FtoCServiceTest.getActual(response);
		//System.out.println("->" + expected +  "<-");
		//System.out.println("->" + actual +  "<-");
		assertEquals("El resultado debe ser JSON",
		        expected, 
				actual);
	}

	@Test
	public void testConvertFtoCfromInput() {
		FtoCService test = new FtoCService();
		double celsius = 17.01;
		double fahrenheit = 62.618;
		String expected = FtoCServiceTest.getExpected(celsius, fahrenheit);		
		Response response = test.convertFtoCfromInput(fahrenheit);
		String actual = FtoCServiceTest.getActual(response);
		//System.out.println("->" + expected +  "<-");
		//System.out.println("->" + actual +  "<-");
		assertEquals("El resultado debe ser JSON",
		        expected, 
				actual);
	}
	
	private static String getExpected(double celsius, double fahrenheit) {
		StringBuilder sb = new StringBuilder("@Produces(\"application/json\") Output: F to C Converter Output: {\"C Value\":")
		.append(celsius)
		.append(",\"F Value\":")
		.append(fahrenheit)
		.append("}");
		
		return sb.toString();
	}
	
	private static String getActual(Response response) {
		String actual = "";
		if (response.getEntity() instanceof String) {
			actual = (String)response.getEntity();
		}
		/*if (response.getEntity() instanceof InputStream) {
			InputStream is = (InputStream)response.getEntity();
			actual = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
		}*/
		
		return actual;
	}

}
