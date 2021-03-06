package xdata.etl.cinder.service;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xdata.etl.cinder.dao.CTypeLogModelMetaDao;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import xdata.etl.cinder.shared.exception.SharedException;

@Service
@Transactional
public class CTypeLogModelMetaServiceImpl implements CTypeLogModelMetaService {
	@Autowired
	private CTypeLogModelMetaDao cTypeLogModelMetaDao;

	@Override
	public CTypeLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException {
		return cTypeLogModelMetaDao.getLogModelVersionRootNode(versionId);
	}

	@Override
	public CTypeLogModelVersion updateLogModelVersion(
			CTypeLogModelVersion logModelVersion) {
		return cTypeLogModelMetaDao.updateLogModelVersion(logModelVersion);
	}

	@Override
	public <T extends CTypeLogModelColumn> T updateLogModelColumn(T column) {
		return cTypeLogModelMetaDao.updateLogModelColumn(column);
	}

	@Override
	public <T extends CTypeLogModelColumn> T saveLogModelColumn(T column) {
		return cTypeLogModelMetaDao.saveLogModelColumn(column);
	}

	@Override
	public void deleteLogModelColumn(Integer id) {
		cTypeLogModelMetaDao.deleteLogModelColumn(id);
	}

	@Override
	public CTypeLogModelColumn move(CTypeLogModelColumn prev,
			CTypeLogModelColumn parent, CTypeLogModelColumn curr) {
		return cTypeLogModelMetaDao.move(prev, parent, curr);
	}

}
