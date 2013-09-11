/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.gwt.client.service;

import java.util.Date;

import org.hibernate.validator.engine.ValidationSupport;

import xdata.etl.cinder.hbasemeta.shared.entity.query.HbaseRecord;
import xdata.etl.cinder.shared.exception.SharedException;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
@RemoteServiceRelativePath("rpc/hbase_query.rpc")
public interface HbaseQueryRpcService extends RemoteService {
	PagingLoadResult<HbaseRecord<String>> get(EtlPagingLoadConfigBean config)
			throws SharedException;

	Short dummyShort();

	Double dummyDouble();

	Integer dummyInteger();

	Long dummyLong();

	Boolean dummyBoolean();

	Date dummyDate();

	Character dummyCharacter();

	ValidationSupport dummy();
}
