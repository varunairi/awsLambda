package com.varuntech;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.varuntech.vo.Response;


public class Handler implements RequestHandler<Map<String,Object>,Response> {

	private static final Logger LOG=LogManager.getLogger(Handler.class);

	public Response handleRequest(Map<String,Object>  input, Context context) {
	//	LOG.info("Recieved request for time : " + input.toString());
		//bad coding
		Object val=null;
		for (Map.Entry<String, Object> entry: input.entrySet()) {
			val=entry.getValue();
			if(val instanceof List) {
				LOG.info("Got an Array : " + entry.toString());
			}
			else if (val instanceof Map) {
				LOG.info("Handle Nexted JSON");
			}
			else {
				LOG.info("element: " + entry.getValue());
			}
		}
		Response responseBody = new Response("The time is : " + (new Date().toGMTString()), "200");
		
		return responseBody;
	}
	

}
