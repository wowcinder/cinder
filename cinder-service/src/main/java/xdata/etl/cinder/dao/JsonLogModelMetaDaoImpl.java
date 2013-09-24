package xdata.etl.cinder.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelSimpleColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.json.JsonLogModelVersion;
import xdata.etl.cinder.shared.HbaseVersionChangeUtil;
import xdata.etl.cinder.shared.exception.SharedException;

@Repository
public class JsonLogModelMetaDaoImpl implements JsonLogModelMetaDao {
	@Resource(name = "cinderSf")
	private SessionFactory sf;

	Session getSession() {
		return sf.getCurrentSession();
	}

	@Override
	public JsonLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException {
		JsonLogModelVersion version = (JsonLogModelVersion) getSession().get(
				JsonLogModelVersion.class, versionId);

		initChildColumns(version.getRootNode());
		return version.getRootNode();
	}

	protected void initChildColumns(JsonLogModelGroupColumn parentNode) {
		if (!Hibernate.isInitialized(parentNode.getColumns())) {
			Hibernate.initialize(parentNode.getColumns());
		}

		List<JsonLogModelColumn> columns = parentNode.getColumns();
		for (JsonLogModelColumn column : columns) {
			if (column instanceof JsonLogModelGroupColumn) {
				initChildColumns((JsonLogModelGroupColumn) column);
			}
		}
	}

	@Override
	public JsonLogModelVersion updateLogModelVersion(
			JsonLogModelVersion logModelVersion) {
		JsonLogModelVersion old = (JsonLogModelVersion) getSession().load(
				JsonLogModelVersion.class, logModelVersion.getId());
		HbaseTableVersion oldVersion = old.getRootNode().getHbaseTableVersion();
		HbaseTableVersion newVersion = logModelVersion.getRootNode()
				.getHbaseTableVersion();
		if (HbaseVersionChangeUtil.isChange(oldVersion, newVersion)) {
			if (old.getRootNode().getColumns() != null) {
				for (JsonLogModelColumn column : old.getRootNode().getColumns()) {
					if (column instanceof JsonLogModelSimpleColumn) {
						((JsonLogModelSimpleColumn) column)
								.setHbaseTableColumn(null);
						getSession().update(column);
					}
				}
			}

		}
		getSession().merge(logModelVersion);
		return logModelVersion;
	}

	@Override
	public <T extends JsonLogModelColumn> T updateLogModelColumn(T column) {
		if (column instanceof JsonLogModelGroupColumn) {
			checkGroupColumn((JsonLogModelGroupColumn) column);
			getSession().merge(column);
		} else {
			getSession().update(column);
		}
		return column;
	}

	@Override
	public <T extends JsonLogModelColumn> T saveLogModelColumn(T column) {
		getSession().persist(column);
		return column;
	}

	protected void checkGroupColumn(JsonLogModelGroupColumn column) {
		JsonLogModelGroupColumn old = (JsonLogModelGroupColumn) getSession()
				.load(JsonLogModelGroupColumn.class, column.getId());

		HbaseTableVersion oldVersion = old.getHbaseTableVersion();
		HbaseTableVersion newVersion = column.getHbaseTableVersion();
		if (HbaseVersionChangeUtil.isChange(oldVersion, newVersion)
				&& old.getColumns() != null) {
			for (JsonLogModelColumn item : old.getColumns()) {
				if (item instanceof JsonLogModelSimpleColumn) {
					((JsonLogModelSimpleColumn) item).setHbaseTableColumn(null);
					getSession().update(item);
				}
			}
		}
	}

	@Override
	public void deleteLogModelColumn(Integer id) {
		JsonLogModelColumn column = (JsonLogModelColumn) getSession().load(
				JsonLogModelColumn.class, id);
		getSession().delete(column);
	}
	
}
