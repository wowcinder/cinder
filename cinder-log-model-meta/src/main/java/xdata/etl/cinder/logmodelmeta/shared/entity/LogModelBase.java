package xdata.etl.cinder.logmodelmeta.shared.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import xdata.etl.cinder.common.shared.entity.timestamp.EntityHasTimeStampImpl;

@MappedSuperclass
public class LogModelBase extends EntityHasTimeStampImpl {
	private static final long serialVersionUID = -2232446874622551048L;
	private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
