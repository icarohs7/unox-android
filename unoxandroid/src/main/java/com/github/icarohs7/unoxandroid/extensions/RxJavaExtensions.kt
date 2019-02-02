package com.github.icarohs7.unoxandroid.extensions

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Apply the computation scheduler do the upstream
 * with subscribeOn and the mainThread scheduler
 * to the downstream with observeOn
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T> Observable<T>.subscribeOnComputationObserveOnMainThread(
        subscribeScheduler: Scheduler = Schedulers.computation(),
        observeScheduler: Scheduler = AndroidSchedulers.mainThread()
): Observable<T> = this.subscribeOn(subscribeScheduler).observeOn(observeScheduler)