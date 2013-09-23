package xdata.etl.cinder.dao;

import javax.validation.ConstraintViolationException;

import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import xdata.etl.cinder.shared.exception.SharedException;

public interface CTypeLogModelMetaDao {
	CTypeLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException;

	/**
	 * @param logModelVersion
	 * @return
	 */
	CTypeLogModelVersion updateLogModelVersion(
			CTypeLogModelVersion logModelVersion);

	<T extends CTypeLogModelColumn> T updateLogModelColumn(T column);

	<T extends CTypeLogModelColumn> T saveLogModelColumn(T column);

	public void deleteLogModelColumn(Integer id);

	/**
	 * @param prev
	 * @param curr
	 * @return
	 */
	CTypeLogModelColumn move(CTypeLogModelColumn prev,
			CTypeLogModelColumn parent, CTypeLogModelColumn curr);
}
