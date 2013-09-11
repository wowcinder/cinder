/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.server.rpc;

import java.util.Date;

import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import xdata.etl.cinder.gwt.client.service.HbaseQueryRpcService;
import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;
import xdata.etl.cinder.service.HbaseQueryService;
import xdata.etl.cinder.shared.exception.SharedException;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
@Service
@AuthorizeGroupAnnotation("hbase")
public class HbaseRpcQueryServiceImpl implements HbaseQueryRpcService {
	@Autowired
	private HbaseQueryService hbaseQueryService;

	@Override
	@AuthorizeAnnotation("查询")
	public PagingLoadResult<HbaseRecord<String>> get(
			EtlPagingLoadConfigBean config) throws SharedException {
		return hbaseQueryService.get(config);
	}

	@Override
	public Short dummyShort() {
		return null;
	}

	@Override
	public Double dummyDouble() {
		return null;
	}

	@Override
	public Integer dummyInteger() {
		return null;
	}

	@Override
	public Long dummyLong() {
		return null;
	}

	@Override
	public Boolean dummyBoolean() {
		return null;
	}

	@Override
	public Date dummyDate() {
		return null;
	}

	@Override
	public Character dummyCharacter() {
		return null;
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

}
