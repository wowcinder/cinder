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
import xdata.etl.cinder.gwt.client.service.CTypeLogModelMetaRpcService;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModel;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import xdata.etl.cinder.service.CTypeLogModelMetaService;
import xdata.etl.cinder.service.SimpleService;
import xdata.etl.cinder.shared.exception.SharedException;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年9月12日
 */
@Service
@AuthorizeGroupAnnotation("CType日志模型")
public class CTypeLogModelMetaRpcServiceImpl implements
		CTypeLogModelMetaRpcService {
	@Autowired
	private SimpleService simpleService;
	@Autowired
	private CTypeLogModelMetaService cTypeLogModelMetaService;

	@Override
	@AuthorizeAnnotation("添加日志模型")
	public CTypeLogModel saveLogModel(CTypeLogModel logModel)
			throws SharedException, ConstraintViolationException {
		return simpleService.save(logModel);
	}

	@Override
	@AuthorizeAnnotation("修改日志模型")
	public CTypeLogModel updateLogModel(CTypeLogModel logModel)
			throws SharedException, ConstraintViolationException {
		return simpleService.update(logModel);
	}

	@Override
	@AuthorizeAnnotation("删除日志模型")
	public void deleteLogModels(List<Integer> ids) throws SharedException,
			ConstraintViolationException {
		simpleService.delete(CTypeLogModel.class, ids);
	}

	@Override
	@AuthorizeAnnotation("查询日志模型")
	public PagingLoadResult<CTypeLogModel> pagingLogModel(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return simpleService.paging(CTypeLogModel.class, config);
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

	@Override
	@AuthorizeAnnotations({ @AuthorizeAnnotation("查询日志模型版本"),
			@AuthorizeAnnotation("修改日志模型版本"), @AuthorizeAnnotation("添加日志模型版本") })
	public List<CTypeLogModel> getLogModels() throws SharedException,
			ConstraintViolationException {
		return simpleService.get(CTypeLogModel.class);
	}

	@Override
	@AuthorizeAnnotation("添加日志模型版本")
	public CTypeLogModelVersion saveLogModelVersion(
			CTypeLogModelVersion LogModelVersion) throws SharedException,
			ConstraintViolationException {
		return simpleService.save(LogModelVersion);
	}

	@Override
	@AuthorizeAnnotation("修改日志模型版本")
	public CTypeLogModelVersion updateLogModelVersion(
			CTypeLogModelVersion LogModelVersion) throws SharedException,
			ConstraintViolationException {
		return simpleService.update(LogModelVersion);
	}

	@Override
	@AuthorizeAnnotation("删除日志模型版本")
	public void deleteLogModelVersions(List<Integer> ids)
			throws SharedException, ConstraintViolationException {
		simpleService.delete(CTypeLogModelVersion.class, ids);
	}

	@Override
	@AuthorizeAnnotation("查询日志模型版本")
	public PagingLoadResult<CTypeLogModelVersion> pagingLogModelVersion(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return simpleService.paging(CTypeLogModelVersion.class, config);
	}

	@Override
	@AuthorizeAnnotation("查询日志模型版本")
	public List<CTypeLogModelVersion> getLogModelVersions()
			throws SharedException, ConstraintViolationException {
		return simpleService.get(CTypeLogModelVersion.class);
	}

	@Override
	@AuthorizeAnnotations({ @AuthorizeAnnotation("查询日志模型版本"),
			@AuthorizeAnnotation("修改日志模型版本"), @AuthorizeAnnotation("添加日志模型版本") })
	public CTypeLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException {
		return cTypeLogModelMetaService.getLogModelVersionRootNode(versionId);
	}

}
