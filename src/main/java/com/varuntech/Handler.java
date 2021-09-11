package com.varuntech;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.varuntech.vo.Request;

public class Handler implements RequestHandler<Request,Map<String,String> > {

	private static final Logger LOG=LogManager.getLogger(Handler.class);

	public Map<String,String>  handleRequest(Request  input, Context context) {
		LOG.info("Recieved request for time : " + input.toString());
		String db=System.getenv("db_key");
		String environment = System.getenv("env");
		Map<String,String> responseMap = new HashMap<>();
		responseMap.put("message:", "The time is : " + (new Date().toGMTString()));
		responseMap.put("environment",environment);
		responseMap.put("db",db);
		
		return responseMap;
	}
	

}
