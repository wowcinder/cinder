package xdata.etl.cinder.logmodelmeta.shared.entity.json;

import javax.persistence.Entity;
import javax.persistence.Table;

import xdata.etl.cinder.logmodelmeta.shared.entity.LogModel;

@Entity
@Table(name = "log_model_json")
public class JsonLogModel extends LogModel<JsonLogModelVersion> {

	private static final long serialVersionUID = 1522140156107047006L;

}
