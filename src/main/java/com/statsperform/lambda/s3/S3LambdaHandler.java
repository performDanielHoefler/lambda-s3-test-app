package com.statsperform.lambda.s3;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class S3LambdaHandler implements RequestHandler<S3Event, String>
{
	private static final Logger logger = LoggerFactory.getLogger(S3LambdaHandler.class);
	
	@Override
	public String handleRequest(S3Event input, Context context)
	{
		String result = "OK";
		try
		{
			logger.info("S3 input received: " + input.toString()
					+ ", Context data: AWS Request ID: " + context.getAwsRequestId()
					+ "Function name: " + context.getFunctionName()
					+ ", Function version: " + context.getFunctionVersion()
					+ ", invoked function urn:" + context.getInvokedFunctionArn()
					+ ", log group name: " + context.getLogGroupName()
					+ ", memory limit in mb: " + context.getMemoryLimitInMB()
					+ ", remining time in millis: " + context.getRemainingTimeInMillis());
		}
		catch (Exception e)
		{
			logger.error("Error", e);
			result = "NOK, there was an exception";
		}
		return result;
	}
}