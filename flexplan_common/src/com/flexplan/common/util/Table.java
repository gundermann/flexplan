package com.flexplan.common.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value = { ElementType.TYPE })
public @interface Table {

	String Name();

	String ForeignTable() default "";

}
