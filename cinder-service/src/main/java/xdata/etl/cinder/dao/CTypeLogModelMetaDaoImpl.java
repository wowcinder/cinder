package xdata.etl.cinder.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.cinder.hbasemeta.shared.entity.base.HbaseTableVersion;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelGroupColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelSimpleColumn;
import xdata.etl.cinder.logmodelmeta.shared.entity.c.CTypeLogModelVersion;
import xdata.etl.cinder.shared.HbaseVersionChangeUtil;
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

	@Override
	public CTypeLogModelVersion updateLogModelVersion(
			CTypeLogModelVersion logModelVersion) {
		CTypeLogModelVersion old = (CTypeLogModelVersion) getSession().load(
				CTypeLogModelVersion.class, logModelVersion.getId());
		HbaseTableVersion oldVersion = old.getRootNode().getHbaseTableVersion();
		HbaseTableVersion newVersion = logModelVersion.getRootNode()
				.getHbaseTableVersion();
		if (HbaseVersionChangeUtil.isChange(oldVersion, newVersion)) {
			if (old.getRootNode().getColumns() != null) {
				for (CTypeLogModelColumn column : old.getRootNode()
						.getColumns()) {
					if (column instanceof CTypeLogModelSimpleColumn) {
						((CTypeLogModelSimpleColumn) column)
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
	public <T extends CTypeLogModelColumn> T updateLogModelColumn(T column) {
		if (column instanceof CTypeLogModelGroupColumn) {
			checkGroupColumn((CTypeLogModelGroupColumn) column);
			getSession().merge(column);
		} else {
			getSession().update(column);
		}
		return column;
	}

	@Override
	public <T extends CTypeLogModelColumn> T saveLogModelColumn(T column) {
		CTypeLogModelGroupColumn parent = (CTypeLogModelGroupColumn) getSession()
				.load(CTypeLogModelGroupColumn.class,
						column.getGroupColumn().getId());
		int pos = 1;
		if (parent.getColumns() != null) {
			pos = parent.getColumns().size() + 1;
		}
		column.setPos(pos);
		getSession().persist(column);
		return column;
	}

	protected void checkGroupColumn(CTypeLogModelGroupColumn column) {
		CTypeLogModelGroupColumn old = (CTypeLogModelGroupColumn) getSession()
				.load(CTypeLogModelGroupColumn.class, column.getId());

		HbaseTableVersion oldVersion = old.getHbaseTableVersion();
		HbaseTableVersion newVersion = column.getHbaseTableVersion();
		if (HbaseVersionChangeUtil.isChange(oldVersion, newVersion)
				&& old.getColumns() != null) {
			for (CTypeLogModelColumn item : old.getColumns()) {
				if (item instanceof CTypeLogModelSimpleColumn) {
					((CTypeLogModelSimpleColumn) item)
							.setHbaseTableColumn(null);
					getSession().update(item);
				}
			}
		}
	}

	@Override
	public void deleteLogModelColumn(Integer id) {
		CTypeLogModelColumn column = (CTypeLogModelColumn) getSession().load(
				CTypeLogModelColumn.class, id);
		getSession().delete(column);
		fixedIndex(column.getGroupColumn());
	}

	protected void fixedIndex(CTypeLogModelGroupColumn parent) {
		if (parent == null) {
			return;
		}
		@SuppressWarnings("unchecked")
		List<CTypeLogModelColumn> columns = (List<CTypeLogModelColumn>) getSession()
				.createCriteria(CTypeLogModelColumn.class)
				.add(Restrictions.eq("groupColumn", parent))
				.addOrder(Order.asc("pos")).list();
		if (columns != null) {
			int pos = 1;
			for (CTypeLogModelColumn column : columns) {
				column.setPos(pos);
				getSession().update(column);
				pos++;
			}
		}
	}
}
