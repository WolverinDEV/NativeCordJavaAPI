package dev.wolveringer.nativecord;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Only for me for noticed
 */
@SuppressWarnings(value = "Only an native element!")
@Retention( RetentionPolicy.SOURCE )
@Target(value = {ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR})
public @interface Native {}
