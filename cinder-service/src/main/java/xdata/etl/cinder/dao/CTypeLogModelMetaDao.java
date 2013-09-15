package xdata.etl.cinder.dao;

import javax.validation.ConstraintViolationException;

import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.shared.exception.SharedException;

public interface CTypeLogModelMetaDao {
	CTypeLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException;
}
