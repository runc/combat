package com.combat.domain.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.combat.event.EventResult;


public class DomainMessage {
    private final Logger logger = LoggerFactory.getLogger(DomainMessage.class);
    protected Object eventSource;
    protected EventResult resultEvent;
    private int timeoutForReturnResult = 10000; 
    /**
     * setup time out(MILLISECONDS) value for get a Event Result
     *
     * @param timeoutForReturnResult MILLISECONDS
     */
    public void setTimeoutForReturnResult(int timeoutForReturnResult) {
        this.timeoutForReturnResult = timeoutForReturnResult;
    }

    /**
     * get a Event Result until time out value
     *
     * @return Event Result
     */
    public Object getEventResult() {
        if (resultEvent == null) {
            logger.error("eventMessage:{} is null.", eventSource.getClass());
            return null;
        } else
            return resultEvent.get(timeoutForReturnResult);
    }

    /**
     * * Blocking until get a Event Result
     *
     * @return
     */
    public Object getBlockEventResult() {
        if (resultEvent == null) {
            logger.error("eventMessage:{} is null.", eventSource.getClass());
            return null;
        } else
            return resultEvent.getBlockedValue();
    }

    public void setEventResult(Object eventResultValue) {
        if (resultEvent != null)
            resultEvent.send(eventResultValue);
        else
            logger.error("eventMessage:{} is null.", eventSource.getClass());
    }


    public DomainMessage(Object eventSource) {
        super();
        this.eventSource = eventSource;
    }

    public Object getEventSource() {
        return eventSource;
    }

    public void setEventSource(Object eventSource) {
        this.eventSource = eventSource;
    }

    public void setResultEvent(EventResult resultEvent) {
        this.resultEvent = resultEvent;
    }

    public EventResult getResultEvent() {
        return resultEvent;
    }
}
