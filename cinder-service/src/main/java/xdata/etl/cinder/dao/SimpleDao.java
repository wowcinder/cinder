/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.dao;

import java.io.Serializable;
import java.util.List;

import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年9月11日
 */
public interface SimpleDao {
	public <K extends Serializable, V> void delete(Class<V> clazz, List<K> ids);

	public <T> T save(T t);

	public <K extends Serializable, V> void delete(Class<V> clazz, K id);

	public <T> T update(T t);

	public <T> PagingLoadResult<T> paging(Class<T> clazz,
			EtlPagingLoadConfigBean config);

	public <T> List<T> get(Class<T> clazz);
}
