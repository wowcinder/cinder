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
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import xdata.etl.cinder.server.AuthorizeNames.AuthorizeAnnotationNamesForCTypeLogModelMeta;
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
@AuthorizeGroupAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.GROUP)
public class CTypeLogModelMetaRpcServiceImpl implements
		CTypeLogModelMetaRpcService {
	@Autowired
	private SimpleService simpleService;
	@Autowired
	private CTypeLogModelMetaService cTypeLogModelMetaService;

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.ADD_LOG_MODEL)
	public CTypeLogModel saveLogModel(CTypeLogModel logModel)
			throws SharedException, ConstraintViolationException {
		return simpleService.save(logModel);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.UPDATE_LOG_MODEL)
	public CTypeLogModel updateLogModel(CTypeLogModel logModel)
			throws SharedException, ConstraintViolationException {
		return simpleService.update(logModel);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.DELETE_LOG_MODEL)
	public void deleteLogModels(List<Integer> ids) throws SharedException,
			ConstraintViolationException {
		simpleService.delete(CTypeLogModel.class, ids);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.QUERY_LOG_MODEL)
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
	@AuthorizeAnnotations({
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.QUERY_LOG_MODEL_VERSION),
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.UPDATE_LOG_MODEL_VERSION),
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.ADD_LOG_MODEL_VERSION) })
	public List<CTypeLogModel> getLogModels() throws SharedException,
			ConstraintViolationException {
		return simpleService.get(CTypeLogModel.class);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.ADD_LOG_MODEL_VERSION)
	public CTypeLogModelVersion saveLogModelVersion(
			CTypeLogModelVersion LogModelVersion) throws SharedException,
			ConstraintViolationException {
		return simpleService.save(LogModelVersion);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.UPDATE_LOG_MODEL_VERSION)
	public CTypeLogModelVersion updateLogModelVersion(
			CTypeLogModelVersion LogModelVersion) throws SharedException,
			ConstraintViolationException {
		return cTypeLogModelMetaService.updateLogModelVersion(LogModelVersion);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.DELETE_LOG_MODEL_VERSION)
	public void deleteLogModelVersions(List<Integer> ids)
			throws SharedException, ConstraintViolationException {
		simpleService.delete(CTypeLogModelVersion.class, ids);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.QUERY_LOG_MODEL_VERSION)
	public PagingLoadResult<CTypeLogModelVersion> pagingLogModelVersion(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return simpleService.paging(CTypeLogModelVersion.class, config);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.QUERY_LOG_MODEL_VERSION)
	public List<CTypeLogModelVersion> getLogModelVersions()
			throws SharedException, ConstraintViolationException {
		return simpleService.get(CTypeLogModelVersion.class);
	}

	@Override
	@AuthorizeAnnotations({
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.QUERY_LOG_MODEL_VERSION),
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.UPDATE_LOG_MODEL_VERSION),
			@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.ADD_LOG_MODEL_VERSION) })
	public CTypeLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException {
		return cTypeLogModelMetaService.getLogModelVersionRootNode(versionId);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.ADD_LOG_MODEL_VERSION)
	public CTypeLogModelSimpleColumn saveLogModelSimpleColumn(
			CTypeLogModelSimpleColumn column) throws SharedException,
			ConstraintViolationException {
		return cTypeLogModelMetaService.saveLogModelColumn(column);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.UPDATE_LOG_MODEL_VERSION)
	public CTypeLogModelSimpleColumn updateLogModelSimpleColumn(
			CTypeLogModelSimpleColumn column) throws SharedException,
			ConstraintViolationException {
		return cTypeLogModelMetaService.updateLogModelColumn(column);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.ADD_LOG_MODEL_VERSION)
	public CTypeLogModelGroupColumn saveLogModelGroupColumn(
			CTypeLogModelGroupColumn column) throws SharedException,
			ConstraintViolationException {
		return cTypeLogModelMetaService.saveLogModelColumn(column);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.UPDATE_LOG_MODEL_VERSION)
	public CTypeLogModelGroupColumn updateLogModelGroupColumn(
			CTypeLogModelGroupColumn column) throws SharedException,
			ConstraintViolationException {
		return cTypeLogModelMetaService.updateLogModelColumn(column);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.DELETE_LOG_MODEL_VERSION)
	public void deleteLogModelColumn(Integer id) throws SharedException,
			ConstraintViolationException {
		cTypeLogModelMetaService.deleteLogModelColumn(id);
	}

	@Override
	@AuthorizeAnnotation(AuthorizeAnnotationNamesForCTypeLogModelMeta.UPDATE_LOG_MODEL_VERSION)
	public CTypeLogModelColumn move(CTypeLogModelColumn prev,
			CTypeLogModelColumn parent, CTypeLogModelColumn curr)
			throws SharedException, ConstraintViolationException {
		return cTypeLogModelMetaService.move(prev, parent, curr);
	}

}
