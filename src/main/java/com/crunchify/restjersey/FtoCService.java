package com.crunchify.restjersey;

import java.math.BigDecimal;

/**
 * @author Crunchify
 */
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;
 
@Path("/ftocservice")
public class FtoCService {
 
	  @GET
	  @Produces("application/json")
	  public Response convertFtoC() throws JSONException {
 
		JSONObject jsonObject = new JSONObject();
		Double fahrenheit = 98.24;
		Double celsius;
		celsius = (fahrenheit - 32)*5/9; 
		jsonObject.put("F Value", fahrenheit); 
		jsonObject.put("C Value", celsius);
 
		String result = "@Produces(\"application/json\") Output: F to C Converter Output: " + jsonObject;
		return Response.status(200).entity(result).build();
	  }
 
	  @Path("{f}")
	  @GET
	  @Produces("application/json")
	  public Response convertFtoCfromInput(@PathParam("f") Double f) throws JSONException {
 
		JSONObject jsonObject = new JSONObject();
		Double fahrenheit = f;
		Double celsius = (fahrenheit - 32)*5/9;		
		jsonObject.put("F Value", fahrenheit); 
		jsonObject.put("C Value", celsius);
 
		String result = "@Produces(\"application/json\") Output: F to C Converter Output: " + jsonObject;
		return Response.status(200).entity(result).build();
	  }
	  
	  private static float roundFloat(float f, int scale) {
		  BigDecimal bd = new BigDecimal(String.valueOf(f));
		  bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
		  return bd.floatValue();
	  }
}
