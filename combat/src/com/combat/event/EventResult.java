package com.combat.event;


public interface EventResult {

    /**
     * get a Event Result until time out value: timeoutForReturnResult
     *
     * @return
     */
    Object get(int timeoutForReturnResult);

    /**
     * Blocking until get a Event Result
     *
     * @return
     */
    Object getBlockedValue();

    void send(Object eventResult);
}
