/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.connector;

/**
 * @author XuehuiHe
 * @date 2013年10月8日
 */
public interface ConsumerConnectorHolder {
	public void run();

	public void shutdown();

	public boolean isShutdown();
}
