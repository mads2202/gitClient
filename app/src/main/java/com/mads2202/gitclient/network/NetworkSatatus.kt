package com.mads2202.gitclient.network

import io.reactivex.Observable
import io.reactivex.Single


interface NetworkStatus{
    fun isOnline(): Observable<Boolean>
    fun isOnlineSingle(): Single<Boolean>
}