package xdata.etl.cinder.logmodelmeta.shared.entity.c;

import javax.persistence.Entity;
import javax.persistence.Table;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModel;

@Entity
@Table(name = "log_model_c")
public class CTypeLogModel extends LogModel<CTypeLogModelVersion> {
	private static final long serialVersionUID = 5087339932449259266L;

}
