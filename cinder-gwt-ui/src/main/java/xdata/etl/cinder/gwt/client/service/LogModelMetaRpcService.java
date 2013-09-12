/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;

import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;
import xdata.etl.cinder.shared.exception.SharedException;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@RemoteServiceRelativePath("rpc/log_model.rpc")
public interface LogModelMetaRpcService extends RemoteService {
	LogModel saveLogModel(LogModel logModel) throws SharedException,
			ConstraintViolationException;

	LogModel updateLogModel(LogModel logModel) throws SharedException,
			ConstraintViolationException;

	void deleteLogModels(List<Integer> ids) throws SharedException,
			ConstraintViolationException;

	PagingLoadResult<LogModel> pagingLogModel(EtlPagingLoadConfigBean config)
			throws SharedException, ConstraintViolationException;

	List<LogModel> getLogModels() throws SharedException,
			ConstraintViolationException;

	ValidationSupport dummy();

	LogModelVersion saveVersion(LogModelVersion version)
			throws SharedException, ConstraintViolationException;
}
