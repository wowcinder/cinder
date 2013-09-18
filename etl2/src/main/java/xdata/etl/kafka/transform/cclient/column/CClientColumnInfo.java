package xdata.etl.kafka.transform.cclient.column;

import java.lang.reflect.Field;

import xdata.etl.kafka.annotatins.KafkaMD5String;

public class CClientColumnInfo {
	private Field field;
	private boolean isMd5String = false;

	public CClientColumnInfo(Field field) {
		this.field = field;
		if (field.getAnnotation(KafkaMD5String.class) != null) {
			isMd5String = true;
		}
	}

	public Field getField() {
		return field;
	}

	public boolean isMd5String() {
		return isMd5String;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public void setMd5String(boolean isMd5String) {
		this.isMd5String = isMd5String;
	}

}
