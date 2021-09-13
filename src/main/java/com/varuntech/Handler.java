package com.varuntech;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNSRecord;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class Handler implements RequestHandler<SNSEvent,Map<String,String> > {

	private static final Logger LOG=LogManager.getLogger(Handler.class);
	private SqsClient sqsClient;
	private  GetQueueUrlRequest getQueueRequest=null;
	String queueName=null;
	public Handler() {
		super();
		sqsClient= SqsClient.builder().region(Region.US_EAST_2).build();
		queueName = System.getenv("sqs_name");
		getQueueRequest = GetQueueUrlRequest.builder()
                .queueName(queueName)
                .build();
	}

	public Map<String,String>  handleRequest(SNSEvent  input, Context context) {
		List<SNSRecord> notificationRecords = input.getRecords();
		LOG.info("Recieved %s events " ,notificationRecords.size());
		
		StringBuilder message = new StringBuilder();
		for(SNSRecord record:notificationRecords) {
			 message.append(record.getSNS().getMessage());
			
		}
		
		String queueUrl = sqsClient.getQueueUrl(getQueueRequest()).queueUrl();

		SendMessageRequest smr = SendMessageRequest.builder().queueUrl(queueUrl)
				.messageBody(getMessageBody(message.toString())).build();
		
		sqsClient.sendMessage(smr);
		Map<String,String> responseMap = new HashMap<>();
		responseMap.put("message:", "Successfully Sent Message");
		
		return responseMap;
	}
	
	
	private GetQueueUrlRequest getQueueRequest() {
		if(!this.queueName.equalsIgnoreCase((String)System.getenv("sqs_name")))
			getQueueRequest = GetQueueUrlRequest.builder()
						        .queueName(queueName)
						        .build();
		return this.getQueueRequest;
	}
	
	private String getMessageBody(String message) {
		//Add body here
		return "{\"message\":\""+message + "\"}";
	}

}
