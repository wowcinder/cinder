package xdata.etl.cinder.dao;

import javax.validation.ConstraintViolationException;

import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import xdata.etl.cinder.shared.exception.SharedException;

public interface JsonLogModelMetaDao {
	JsonLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException;

	/**
	 * @param logModelVersion
	 * @return
	 */
	JsonLogModelVersion updateLogModelVersion(
			JsonLogModelVersion logModelVersion);

	<T extends JsonLogModelColumn> T updateLogModelColumn(T column);

	<T extends JsonLogModelColumn> T saveLogModelColumn(T column);

	public void deleteLogModelColumn(Integer id);

}
