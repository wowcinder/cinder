package xdata.etl.cinder.service;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xdata.etl.cinder.dao.CTypeLogModelMetaDao;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
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

}
