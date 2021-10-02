

# Coding for AWS Lambda
*   Implement a "Handler" Class that implements "RequestHandler" interface From AWS . 
*   The signature of **handleRequest** method (which has to be overridden) is such that the return type should be what the method is supposed to return . 
*   The first argument to method is the input to the Lambda (called Event). AWS auto serializes the input to primitive (Wrapper) types, Strings, or into Lists or Maps (for JSON). 
*   The second is "context" object containing information about the environment of Lambda and giving access to system parameters etc. 

*   The "Handler" class in initialized once so all initializations can be done in constructor, static block etc and then "handleRequest" method is run .


## Branch: readAndWriteS3
Triggered whenever a "PUT" is performed on a S3 bucket, an event is published to Lambda configured to react to it. This configuration is in Event Notification of S3. 
A JSON with metadata and the details of action comes to Lambda as input (S3Event) and contains affected bucket, file name etc. 
This code reads the bucket name from system env (although the same is available in Input S3eVent as well, reads the file name for which event has ben generated and then turns it into grayscale.
Lambda then writes to another bucket.
To get this to succeed Lambda needs a role with proper policies on S3. 

# Ways to update in AWS Lambda

1.  Using AWS Management Console:
	  Login to the aws console and in Lambda console upload the JAR file. You also have to mention the entry method "handleRequest" and fully qualified name of the class.  
	  Also create SNS Topic and SQS Queue , then configure the sqs queue as environment variable for the Lambda
2.  Using SAM:
3.	Using AWS CLI: aws lambda update-function-code --function-name simple-http-endpoint --zip-file fileb://simpleawsserverless-dev-SNAPSHOT.jar --region us-east-2

