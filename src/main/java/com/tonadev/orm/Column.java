package com.tonadev.orm;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention( RetentionPolicy.RUNTIME )
@interface Column {
	boolean primaryKey() default false;
	SQLType sqlType();
}
