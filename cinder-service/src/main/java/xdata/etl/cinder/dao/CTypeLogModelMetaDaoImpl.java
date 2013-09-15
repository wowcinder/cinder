package xdata.etl.cinder.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import xdata.etl.cinder.shared.exception.SharedException;

@Repository
public class CTypeLogModelMetaDaoImpl implements CTypeLogModelMetaDao {
	@Resource(name = "cinderSf")
	private SessionFactory sf;

	Session getSession() {
		return sf.getCurrentSession();
	}

	@Override
	public CTypeLogModelGroupColumn getLogModelVersionRootNode(Integer versionId)
			throws SharedException, ConstraintViolationException {
		CTypeLogModelVersion version = (CTypeLogModelVersion) getSession().get(
				CTypeLogModelVersion.class, versionId);
		initChildColumns(version.getRootNode());
		return version.getRootNode();
	}

	protected void initChildColumns(CTypeLogModelGroupColumn parentNode) {
		List<CTypeLogModelColumn> columns = parentNode.getColumns();
		if (!Hibernate.isInitialized(columns)) {
			Hibernate.initialize(columns);
		}
		for (CTypeLogModelColumn column : columns) {
			if (column instanceof CTypeLogModelGroupColumn) {
				initChildColumns((CTypeLogModelGroupColumn) column);
			}
		}
	}

}
