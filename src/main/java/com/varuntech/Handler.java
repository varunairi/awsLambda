package com.varuntech;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.varuntech.vo.Request;
import com.varuntech.vo.Response;

public class Handler implements RequestHandler<Request,Response> {

	private static final Logger LOG=LogManager.getLogger(Handler.class);

	public Response handleRequest(Request  input, Context context) {
		LOG.info("Recieved request for time : " + input.toString());
		Response responseBody = new Response("The time is : " + (new Date().toGMTString()), "200");
		
		return responseBody;
	}
	

}
