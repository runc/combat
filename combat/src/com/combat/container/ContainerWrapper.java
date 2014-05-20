package com.combat.container;

import java.util.Collection;

import com.combat.domain.consumer.ConsumerMethodHolder;

@SuppressWarnings("all")
public interface ContainerWrapper {

	Collection lookupConsumer(String topic);

    Collection lookupOnEventConsumer(String topic);

    void registerConsumer(String topic, String className);

    void registerOnEventConsumer(String topic, ConsumerMethodHolder o);

    Object lookupOriginal(String className);

    void registerOriginal(String className, Object o);
	
	
}
