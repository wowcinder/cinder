package xdata.etl.cinder.gwt.client.common.validator;

import java.util.Collection;

public interface CinderValidator {
	boolean validate(Object t);

	boolean validateCollection(Collection<?> list);
}
