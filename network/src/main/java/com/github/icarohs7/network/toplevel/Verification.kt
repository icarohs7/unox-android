package com.github.icarohs7.network.toplevel

import android.annotation.SuppressLint
import com.github.icarohs7.core.toplevel.NXBGPOOL
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
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
            .subscribeOn(Schedulers.from(NXBGPOOL.executor))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    { hasConn -> fn(hasConn) },
                    { Unit })
}