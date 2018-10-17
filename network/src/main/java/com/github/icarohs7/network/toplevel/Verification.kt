package com.github.icarohs7.network.toplevel

import android.annotation.SuppressLint
import com.github.icarohs7.core.UnoxAndroidCoreModule
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Verify if the device is connected to the
 * internet and execute a callback passing
 * the flag, true if connected or false otherwise
 */
@SuppressLint("CheckResult")
fun appHasInternetConnection(fn: (Boolean) -> Unit) {
    ReactiveNetwork
            .checkInternetConnectivity()
            .subscribeOn(Schedulers.from(UnoxAndroidCoreModule.EXECUTOR))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    { hasConn -> fn(hasConn) },
                    { Unit })
}

/**
 * Simplified observation of the app connection to the internet,
 * @return The [Observable] used and the [Disposable] subscription
 */
fun appOnInternetConnectionChange(fn: (Boolean) -> Unit): Pair<Observable<Boolean>, Disposable> {
    val obs = ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.from(UnoxAndroidCoreModule.EXECUTOR))
            .observeOn(AndroidSchedulers.mainThread())

    val subs = obs.subscribe({ hasConn -> fn(hasConn) },
                             { Unit })

    return obs to subs
}