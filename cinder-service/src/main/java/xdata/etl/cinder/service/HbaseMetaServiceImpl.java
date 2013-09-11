package xdata.etl.cinder.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xdata.etl.cinder.dao.SimpleDao;
import xdata.etl.cinder.dao.hbasemeta.HbaseMetaDao;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTable;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableColumn;
import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.shared.exception.SharedException;
import xdata.etl.cinder.shared.paging.EtlPagingLoadConfigBean;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@Service
@Transactional
public class HbaseMetaServiceImpl implements HbaseMetaService {
	@Autowired
	private HbaseMetaDao hbaseMetaDao;
	@Autowired
	private SimpleDao simpleDao;

	@Override
	public HbaseTable saveHbaseTable(HbaseTable hbaseTable)
			throws SharedException, ConstraintViolationException {
		return getDao().saveHbaseTable(hbaseTable);
	}

	@Override
	public void deleteHbaseTable(Integer id) throws SharedException,
			ConstraintViolationException {
		getDao().deleteHbaseTable(id);
	}

	@Override
	public HbaseTable updateHbaseTable(HbaseTable hbaseTable)
			throws SharedException, ConstraintViolationException {
		return getDao().updateHbaseTable(hbaseTable);
	}

	@Override
	public PagingLoadResult<HbaseTable> pagingHbaseTable(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return getDao().pagingHbaseTable(config);
	}

	@Override
	public HbaseTableVersion saveHbaseTableVersion(
			HbaseTableVersion hbaseTableVersion) throws SharedException,
			ConstraintViolationException {
		return getDao().saveHbaseTableVersion(hbaseTableVersion);
	}

	@Override
	public void deleteHbaseTableVersion(Integer id) throws SharedException,
			ConstraintViolationException {
		getDao().deleteHbaseTableVersion(id);
	}

	@Override
	public HbaseTableVersion updateHbaseTableVersion(
			HbaseTableVersion hbaseTableVersion) throws SharedException,
			ConstraintViolationException {
		return getDao().updateHbaseTableVersion(hbaseTableVersion);
	}

	@Override
	public PagingLoadResult<HbaseTableVersion> pagingHbaseTableVersion(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return getDao().pagingHbaseTableVersion(config);
	}

	@Override
	public HbaseTableColumn saveHbaseTableColumn(
			HbaseTableColumn hbaseTableColumn) throws SharedException,
			ConstraintViolationException {
		return getDao().saveHbaseTableColumn(hbaseTableColumn);
	}

	@Override
	public void deleteHbaseTableColumn(Integer id) throws SharedException,
			ConstraintViolationException {
		getDao().deleteHbaseTableColumn(id);
	}

	@Override
	public HbaseTableColumn updateHbaseTableColumn(
			HbaseTableColumn hbaseTableColumn) throws SharedException,
			ConstraintViolationException {
		return getDao().updateHbaseTableColumn(hbaseTableColumn);
	}

	@Override
	public PagingLoadResult<HbaseTableColumn> pagingHbaseTableColumn(
			EtlPagingLoadConfigBean config) throws SharedException,
			ConstraintViolationException {
		return getDao().pagingHbaseTableColumn(config);
	}

	private HbaseMetaDao getDao() {
		return hbaseMetaDao;
	}

	@Override
	public <T> void delete(Class<T> clazz, List<Integer> ids) {
		getDao().delete(clazz, ids);
	}

	@Override
	public List<HbaseTable> getHbaseTablesForCombox() {
		return simpleDao.get(HbaseTable.class);
	}

	@Override
	public List<HbaseTableColumn> getColumnsByVersionId(Integer id) {
		return getDao().getColumnsByVersionId(id);
	}

}
