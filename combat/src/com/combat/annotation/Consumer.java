package com.combat.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface Consumer {
    /**
     * topic name
     *
     * @return topic name
     * @Send(topicName) ==> @Consumer(topicName);
     */
    String value();
}
