package com.mads2202.gitclient.eventBus

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class EventBus {
    private val bus=BehaviorSubject.create<AnalyticsEvent>()

    fun post(event: AnalyticsEvent){
        bus.onNext(event)
    }

    fun get():Observable<AnalyticsEvent> = bus
}