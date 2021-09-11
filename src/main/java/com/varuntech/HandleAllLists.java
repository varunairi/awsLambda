package com.varuntech;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class HandleAllLists implements RequestHandler<Map<String,List<String>>, String> {
	private static final Logger LOG=LogManager.getLogger(Handler.class);
	public HandleAllLists() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String handleRequest(Map<String, List<String>> input, Context context) {
		LOG.info("Request is"+input);
		return "The time is : " + (new Date().toGMTString());
	}

}
