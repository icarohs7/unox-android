package com.github.icarohs7.visuals.view.activities

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * Activity containing a list of disposables
 * and automatically unsubscribing to them
 * on pause
 */
abstract class BaseDisposerActivity : AppCompatActivity() {

    /**
     * Collection of disposable objects to be disposed on pause
     */
    val disposables: CompositeDisposable = CompositeDisposable()

    /**
     * Default error handler of the activity,
     * called when errors are thrown when disposing
     * disposables
     */
    open fun onErrorThrown(e: Exception) {
    }

    override fun onPause() {
        errorSafeRun { disposables.dispose() }
        super.onPause()
    }

    /**
     * Run a transaction and if any exception is thrown,
     * invoke the handler to handle it
     */
    protected inline fun errorSafeRun(fn: () -> Unit) {
        try {
            fn()
        } catch (e: Exception) {
            onErrorThrown(e)
        }
    }
}