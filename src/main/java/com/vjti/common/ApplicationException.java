package com.vjti.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationException extends Exception {

	private String errorCode;
	private String exceptionMsg;
	
	private static Logger logger = LogManager.getLogger("ApplicationException");
	 
	public ApplicationException(String errorCode, String exceptionMsg) {
		this.errorCode = errorCode;
		this.exceptionMsg = exceptionMsg;
	}
	
	public ApplicationException(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getExceptionMsg(){
		return this.exceptionMsg;
	}
	
	public void setExceptionMsg(String exceptionMsg) {
		logger.error("ApplicationException: Error: " + exceptionMsg);
		this.exceptionMsg = exceptionMsg;
	}
}
