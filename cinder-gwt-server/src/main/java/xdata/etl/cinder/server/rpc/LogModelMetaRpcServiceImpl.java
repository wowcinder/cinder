/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.server.rpc;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotations;
import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import xdata.etl.cinder.gwt.client.service.LogModelMetaRpcService;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.base.LogModelVersion;
import xdata.etl.cinder.service.SimpleService;
import xdata.etl.cinder.shared.exception.SharedException;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Service
@AuthorizeGroupAnnotation("日志模型")
public class LogModelMetaRpcServiceImpl implements LogModelMetaRpcService {
	@Autowired
	private SimpleService simpleService;

	@Override
	@AuthorizeAnnotation("添加日志模型")
	public LogModel saveLogModel(LogModel logModel) throws SharedException,
			ConstraintViolationException {
		return simpleService.save(logModel);
	}

	@Override
	@AuthorizeAnnotation("修改日志模型")
	public LogModel updateLogModel(LogModel logModel) throws SharedException,
			ConstraintViolationException {
		return simpleService.update(logModel);
	}

	@Override
	@AuthorizeAnnotation("删除日志模型")
	public void deleteLogModels(List<Integer> ids) throws SharedException,
			ConstraintViolationException {
		simpleService.delete(LogModel.class, ids);
	}

	@Override
	@AuthorizeAnnotation("查询日志模型")
	public PagingLoadResult<LogModel> pagingLogModel(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return simpleService.paging(LogModel.class, config);
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

	@Override
	@AuthorizeAnnotations({ @AuthorizeAnnotation("查询日志模型版本"),
			@AuthorizeAnnotation("修改日志模型版本"), @AuthorizeAnnotation("添加日志模型版本") })
	public List<LogModel> getLogModels() throws SharedException,
			ConstraintViolationException {
		return simpleService.get(LogModel.class);
	}

	@Override
	public LogModelVersion saveVersion(LogModelVersion version)
			throws SharedException, ConstraintViolationException {
		return simpleService.save(version);
	}

}
