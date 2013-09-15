package xdata.etl.cinder.service;

import javax.validation.ConstraintViolationException;

import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.shared.exception.SharedException;

public interface CTypeLogModelMetaService {
	CTypeLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException;
}
