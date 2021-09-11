

# Coding for AWS Lambda
*   Implement a "Handler" Class that implements "RequestHandler" interface From AWS . 
*   The signature of **handleRequest** method (which has to be overridden) is such that the return type should be what the method is supposed to return . 
*   The first argument to method is the input to the Lambda (called Event). AWS auto serializes the input to primitive (Wrapper) types, Strings, or into Lists or Maps (for JSON). 
*   The second is "context" object containing information about the environment of Lambda and giving access to system parameters etc. 

*   The "Handler" class in initialized once so all initializations can be done in constructor, static block etc and then "handleRequest" method is run .


## Branch: Using JSON as Maps and Lists in Request
Takes in a Custom JSON   and maps to Java Types (automatic marshalling by AWS from JSON Input) and returns a response with a custom JSON As well.
{"age":30,
    "Country": "US",
    "County":"LA",
    "name":["Sylvester","Rambo"],
    "anotherName":{
        "first": "Arnold",
        "last":"Schwarzenegger"
    }
}


# Ways to update in AWS Lambda

1.  Using AWS Management Console:
	  Login to the aws console and in Lambda console upload the JAR file. You also have to mention the entry method "handleRequest" and fully qualified name of the class.  
2.  Using SAM:

