package com.statsperform.lambda.s3;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;

import java.util.List;

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
			List<S3EventNotificationRecord> records = input.getRecords();
			logger.info("Number of S3 notification records: " + records.size()
					+ ", context data: AWS Request ID: " + context.getAwsRequestId()
					+ "Function name: " + context.getFunctionName()
					+ ", Function version: " + context.getFunctionVersion()
					+ ", invoked function urn:" + context.getInvokedFunctionArn()
					+ ", log group name: " + context.getLogGroupName()
					+ ", memory limit in mb: " + context.getMemoryLimitInMB()
					+ ", remaining time in millis: " + context.getRemainingTimeInMillis());
			
			for (S3EventNotificationRecord record : records)
			{
				logger.info("AWS Region: " + record.getAwsRegion()
						+ ", event name:" + record.getEventName()
						+ ", event source: " + record.getEventSource()
						+ ", event version: " + record.getEventVersion()
						+ ", eventNameAsEnum: " + record.getEventNameAsEnum().toString()
						+ ", request param source ip: " + record.getRequestParameters().getSourceIPAddress()
						+ ", response element xamzid2: " + record.getResponseElements().getxAmzId2()
						+ ", response element amzrequestid: " + record.getResponseElements().getxAmzRequestId()
						+ ", user identity principal id: " + record.getUserIdentity().getPrincipalId()
						+ ", s3 configurationId: " + record.getS3().getConfigurationId()
						+ ", s3 schema version: " + record.getS3().getS3SchemaVersion()
						+ ", s3 bucket name: " + record.getS3().getBucket()
						+ ", s3 object etag: " + record.getS3().getObject().geteTag()
						+ ", s3 object key: " + record.getS3().getObject().getKey()
						+ ", s3 object sequencer: " + record.getS3().getObject().getSequencer()
						+ ", s3 object urldecoded key: "+ record.getS3().getObject().getUrlDecodedKey()
						+ ", s3 object version: " + record.getS3().getObject().getVersionId()
						+ ", s3 object size: " + record.getS3().getObject().getSizeAsLong());
			}
		}
		catch (Exception e)
		{
			logger.error("Error", e);
			result = "NOK, there was an exception";
		}
		return result;
	}
}