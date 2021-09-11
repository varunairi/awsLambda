package com.varuntech;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.varuntech.vo.Response;

public class HandlerWithDefinedJSON  implements RequestHandler<Map<String,Map<String,String>>,Response> {

	private static final Logger LOG=LogManager.getLogger(Handler.class);
	//will handle nested json but not array
	public Response handleRequest(Map<String,Map<String,String>>  input, Context context) {
		LOG.info("Recieved request for time : " + input.toString());
		
		Response responseBody = new Response("The time is : " + (new Date().toGMTString()), "200");
		
		return responseBody;
	}
	

}
