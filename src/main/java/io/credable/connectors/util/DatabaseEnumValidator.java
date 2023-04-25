package io.credable.connectors.util;

import io.credable.connectors.DatabaseType;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PartOfEnum.class)
@Documented
public @interface DatabaseEnumValidator {
    String message() default "Must be one of {value}";
    DatabaseType[]   value();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
