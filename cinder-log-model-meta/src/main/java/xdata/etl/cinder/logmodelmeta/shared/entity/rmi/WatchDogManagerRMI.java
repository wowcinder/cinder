/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.logmodelmeta.shared.entity.rmi;


/**
 * 服务端接收的RMI
 * 
 * @author XuehuiHe
 * @date 2013年9月26日
 */
public interface WatchDogManagerRMI {
	/**
	 * 注册
	 * 
	 * @param rmiPort
	 * @return WatchDog id
	 */
	public Integer registerPrcessSever(Integer rmiPort);

	/**
	 * 发送心跳
	 */
	public void tick();
}
