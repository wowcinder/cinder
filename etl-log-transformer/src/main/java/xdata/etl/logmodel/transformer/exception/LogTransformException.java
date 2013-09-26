/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.logmodel.transformer.exception;

/**
 * @author XuehuiHe
 * @date 2013年9月26日
 */
public class LogTransformException extends RuntimeException {

	private static final long serialVersionUID = -5372795647196270506L;

	public LogTransformException() {
		super();
	}

	public LogTransformException(String message) {
		super(message);
	}

	public LogTransformException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogTransformException(Throwable cause) {
		super(cause);
	}

}
