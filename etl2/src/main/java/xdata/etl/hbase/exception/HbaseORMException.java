package xdata.etl.hbase.exception;

public class HbaseORMException extends Exception {

	private static final long serialVersionUID = 952185224737687534L;

	public HbaseORMException() {
	}

	public HbaseORMException(String message) {
		super(message);
	}

	public HbaseORMException(Throwable cause) {
		super(cause);
	}

	public HbaseORMException(String message, Throwable cause) {
		super(message, cause);
	}

}
