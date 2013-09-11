package xdata.etl.cinder.server.rpc;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotation;
import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeAnnotations;
import xdata.etl.cinder.annotations.AuthorizeSystemAnnotations.AuthorizeGroupAnnotation;
import xdata.etl.cinder.gwt.client.service.HbaseMetaRpcService;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTable;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.service.HbaseMetaService;
import xdata.etl.cinder.shared.exception.SharedException;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@Service
@AuthorizeGroupAnnotation("hbase_meta")
public class HbaseMetaRpcServiceImpl implements HbaseMetaRpcService {
	@Autowired
	private HbaseMetaService hbaseMetaService;

	@Override
	@AuthorizeAnnotation("添加table")
	public HbaseTable saveHbaseTable(HbaseTable hbaseTable)
			throws SharedException, ConstraintViolationException {
		return getService().saveHbaseTable(hbaseTable);
	}

	@Override
	@AuthorizeAnnotation("删除table")
	public void deleteHbaseTable(Integer id) throws SharedException,
			ConstraintViolationException {
		getService().deleteHbaseTable(id);
	}

	@Override
	@AuthorizeAnnotation("修改table")
	public HbaseTable updateHbaseTable(HbaseTable hbaseTable)
			throws SharedException, ConstraintViolationException {
		return getService().updateHbaseTable(hbaseTable);
	}

	@Override
	@AuthorizeAnnotation("查询table")
	public PagingLoadResult<HbaseTable> pagingHbaseTable(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return getService().pagingHbaseTable(config);
	}

	@Override
	@AuthorizeAnnotation("添加table_version")
	public HbaseTableVersion saveHbaseTableVersion(
			HbaseTableVersion hbaseTableVersion) throws SharedException,
			ConstraintViolationException {
		return getService().saveHbaseTableVersion(hbaseTableVersion);
	}

	@Override
	@AuthorizeAnnotation("删除table_version")
	public void deleteHbaseTableVersion(Integer id) throws SharedException,
			ConstraintViolationException {
		getService().deleteHbaseTableVersion(id);
	}

	@Override
	@AuthorizeAnnotation("修改table_version")
	public HbaseTableVersion updateHbaseTableVersion(
			HbaseTableVersion hbaseTableVersion) throws SharedException,
			ConstraintViolationException {
		return getService().updateHbaseTableVersion(hbaseTableVersion);
	}

	@Override
	@AuthorizeAnnotation("查询table_version")
	public PagingLoadResult<HbaseTableVersion> pagingHbaseTableVersion(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return getService().pagingHbaseTableVersion(config);
	}

	@Override
	@AuthorizeAnnotation("添加table_column")
	public HbaseTableColumn saveHbaseTableColumn(
			HbaseTableColumn hbaseTableColumn) throws SharedException,
			ConstraintViolationException {
		return getService().saveHbaseTableColumn(hbaseTableColumn);
	}

	@Override
	@AuthorizeAnnotation("删除table_column")
	public void deleteHbaseTableColumn(Integer id) throws SharedException,
			ConstraintViolationException {
		getService().deleteHbaseTableColumn(id);
	}

	@Override
	@AuthorizeAnnotation("修改table_column")
	public HbaseTableColumn updateHbaseTableColumn(
			HbaseTableColumn hbaseTableColumn) throws SharedException,
			ConstraintViolationException {
		return getService().updateHbaseTableColumn(hbaseTableColumn);
	}

	@Override
	@AuthorizeAnnotation("查询table_column")
	public PagingLoadResult<HbaseTableColumn> pagingHbaseTableColumn(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return getService().pagingHbaseTableColumn(config);
	}

	public HbaseMetaService getService() {
		return hbaseMetaService;
	}

	@Override
	@AuthorizeAnnotation("删除table")
	public void deleteHbaseTables(List<Integer> ids) {
		getService().delete(HbaseTable.class, ids);
	}

	@Override
	@AuthorizeAnnotation("删除table_version")
	public void deleteHbaseTableVersions(List<Integer> ids) {
		getService().delete(HbaseTableVersion.class, ids);
	}

	@Override
	@AuthorizeAnnotation("删除table_column")
	public void deleteHbaseTableColumns(List<Integer> ids) {
		getService().delete(HbaseTableColumn.class, ids);
	}

	@Override
	@AuthorizeAnnotations({ @AuthorizeAnnotation("添加table_version"),
			@AuthorizeAnnotation("修改table_version"),
			@AuthorizeAnnotation("查询table_version") })
	public List<HbaseTable> getHbaseTablesForCombox() {
		return getService().getHbaseTablesForCombox();
	}

	@Override
	@AuthorizeAnnotations({ @AuthorizeAnnotation("添加table_version"),
			@AuthorizeAnnotation("修改table_version"),
			@AuthorizeAnnotation("查询table_version") })
	public List<HbaseTableColumn> getColumnsByVersionId(Integer id) {
		return hbaseMetaService.getColumnsByVersionId(id);
	}

	@Override
	public ValidationSupport dummy() {
		return null;
	}

	@Override
	@AuthorizeAnnotation(group = "hbase", value = "查询")
	public List<HbaseTableColumn> getTableAllColumns(String table,
			String[] versions) throws SharedException,
			ConstraintViolationException {
		return getService().getTableAllColumns(table, versions);
	}

}
