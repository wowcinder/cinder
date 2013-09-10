package xdata.etl.cinder.server;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

public class CinderValidator {
	public static final Validator validator = Validation
			.buildDefaultValidatorFactory().getValidator();

	public static <T> void validate(T t) {
		Set<ConstraintViolation<T>> violations = validator.validate(t);
		if (!violations.isEmpty()) {
			Set<ConstraintViolation<?>> temp = new HashSet<ConstraintViolation<?>>(
					violations);
			throw new ConstraintViolationException(temp);
		}
	}

}
