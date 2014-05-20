package com.combat.event.disuptor;

import com.combat.domain.message.DomainMessage;


public interface DisruptorListener {
    void action(DomainMessage domainMessage);
}
