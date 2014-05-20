package com.combat.domain.message;

import com.combat.event.disuptor.EventDisruptor;


public interface DomainEventHandler<T> {
    void onEvent(EventDisruptor event, final boolean endOfBatch) throws Exception;
}
