package com.varuntech;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class Handler implements RequestHandler<S3Event,Map<String,String> > {

	private static final Logger LOG=LogManager.getLogger(Handler.class);
	private S3Client s3Client;
	public Handler() {
		super();
		s3Client = S3Client.builder().region(Region.US_EAST_2).build();
	}

	public Map<String,String>  handleRequest(S3Event  input, Context context) {
		List<S3EventNotificationRecord> notificationRecords = input.getRecords();
		LOG.info("Recieved %d events " ,notificationRecords.size());

		context.getLogger().log("ContextLogger Recieved %s events " + notificationRecords.size());
		S3EventNotificationRecord record = notificationRecords.get(0);
		String key = record.getS3().getObject().getKey();
			GetObjectRequest getRequest = GetObjectRequest.builder()
					.bucket(System.getenv("INPUT_BUCKET_NAME")).key(key)
					.build();
			
			ResponseInputStream<GetObjectResponse> responseIpStream=s3Client.getObject(getRequest);
			
			try {
				BufferedImage bimage = ImageIO.read(responseIpStream);
				BufferedImage newImage = new BufferedImage(bimage.getWidth(), bimage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
				newImage.createGraphics().drawImage(bimage, 0, 0, null);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ImageIO.write(newImage,"jpg",bos);

//			bimage.getTile(9, 9).
//			//bimage.getData()
//			responseIpStream.
			PutObjectRequest putRequest = PutObjectRequest.builder().bucket(System.getenv("OUTPUT_BUCKET_NAME")).key(key)
					.build();
			s3Client.putObject(putRequest, RequestBody.fromBytes(bos.toByteArray()));
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		Map<String,String> responseMap = new HashMap<>();
		responseMap.put("message:", "Successfully Sent Message");
		
		return responseMap;
	}
	
	
	private String getMessageBody(String message) {
		//Add body here
		return "{\"message\":\""+message + "\"}";
	}

}
