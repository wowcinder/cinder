/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.server.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import xdata.etl.cinder.gwt.client.service.AuthorizeRpcService;
import xdata.etl.cinder.server.AuthorizeNames.AuthorizeAnnotationNamesForMenu;
import xdata.etl.cinder.service.AuthorizeService;
import xdata.etl.cinder.shared.entity.authorize.Authorize;

/**
 * @author XuehuiHe
 * @date 2013年9月9日
 */
@Service
public class AuthorizeRpcServiceImpl implements AuthorizeRpcService {
	@Autowired
	private AuthorizeService authorizeService;

	@Override
	@AuthorizeAnnotation(group = AuthorizeAnnotationNamesForMenu.GROUP, value = AuthorizeAnnotationNamesForMenu.ALL)
	public List<Authorize> getAllocatenbeAuthorizes() {
		return authorizeService.getAllocatenbeAuthorizes();
	}

}
