/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.service;

import java.util.List;

import xdata.etl.cinder.shared.entity.authorize.Authorize;
import xdata.etl.cinder.shared.exception.SharedException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@RemoteServiceRelativePath("rpc/authorize.rpc")
public interface AuthorizeRpcService extends RemoteService {
	List<Authorize> getAllocatenbeAuthorizes() throws SharedException;
}
