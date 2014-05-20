package com.combat.event.disuptor;

import com.combat.domain.message.DomainMessage;


public class EventDisruptor {

    protected String topic;

    protected DomainMessage domainMessage;

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public DomainMessage getDomainMessage() {
        return domainMessage;
    }

    public void setDomainMessage(DomainMessage domainMessage) {
        this.domainMessage = domainMessage;
    }
}
