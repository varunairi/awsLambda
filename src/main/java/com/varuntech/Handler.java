package com.varuntech;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.varuntech.vo.Response;

public class Handler implements RequestHandler<Map<String,String>,String> {

	private static final Logger LOG=LogManager.getLogger(Handler.class);

	public String handleRequest(Map<String, String> input, Context context) {
		LOG.info("Recieved request for time : " + input.toString());
		Response responseBody = new Response("The time is : " + (new Date().toGMTString()), "200");
		
		return responseBody.getMessage();
	}
	

}
