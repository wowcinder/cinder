package xdata.etl.cinder.dao.hbasemeta;

import java.util.List;

import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTable;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.shared.exception.SharedException;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface HbaseMetaDao {
	HbaseTable saveHbaseTable(HbaseTable hbaseTable);

	void deleteHbaseTable(Integer id);

	HbaseTable updateHbaseTable(HbaseTable hbaseTable);

	PagingLoadResult<HbaseTable> pagingHbaseTable(EtlPagingLoadConfigBean config)
			throws SharedException;

	HbaseTableVersion saveHbaseTableVersion(HbaseTableVersion hbaseTableVersion);

	void deleteHbaseTableVersion(Integer id);

	HbaseTableVersion updateHbaseTableVersion(
			HbaseTableVersion hbaseTableVersion);

	PagingLoadResult<HbaseTableVersion> pagingHbaseTableVersion(
			EtlPagingLoadConfigBean config) throws SharedException;

	HbaseTableColumn saveHbaseTableColumn(HbaseTableColumn hbaseTableColumn);

	void deleteHbaseTableColumn(Integer id);

	HbaseTableColumn updateHbaseTableColumn(HbaseTableColumn hbaseTableColumn);

	PagingLoadResult<HbaseTableColumn> pagingHbaseTableColumn(
			EtlPagingLoadConfigBean config) throws SharedException;

	<T> void delete(Class<T> clazz, List<Integer> ids);

	/**
	 * @param id
	 * @return
	 */
	List<HbaseTableColumn> getColumnsByVersionId(Integer id);
}
