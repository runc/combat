package com.combat.event.disuptor;

import com.combat.domain.message.DomainEventHandler;
import com.lmax.disruptor.EventHandler;


@SuppressWarnings("all")
public class DomainEventHandlerAdapter implements EventHandler<EventDisruptor> {
	
    private DomainEventHandler handler;

    public DomainEventHandlerAdapter(DomainEventHandler handler) {
        super();
        this.handler = handler;
    }

    public void onEvent(EventDisruptor event, long sequence, boolean endOfBatch) throws Exception {
        handler.onEvent(event, endOfBatch);
    }
}
