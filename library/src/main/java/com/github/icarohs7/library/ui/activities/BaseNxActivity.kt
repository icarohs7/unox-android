package com.github.icarohs7.library.ui.activities

import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.github.icarohs7.library.UnoxAndroid
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Activity containing a composite disposable
 * and a coroutine context, cancelling them when
 * stopped
 */
abstract class BaseNxActivity : AppCompatActivity(), CoroutineScope, UnoxAndroid.DisposableEntity {

    /**
     * Parent job of coroutines on the scope of the activity
     */
    var job: Job = Job()
        private set

    /**
     * Composite disposable aggregating all subscriptions on the scope of the activity
     */
    override val disposable: CompositeDisposable = CompositeDisposable()

    /**
     * Context in which coroutines are executed by default inside the activity
     */
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    /**
     * Create a new parent job when the activity is started
     */
    @CallSuper
    override fun onStart() {
        super.onStart()
        job = Job()
    }

    /**
     * Cancel all coroutines and dispose all
     * subscriptions when destroyed
     */
    @CallSuper
    override fun onStop() {
        super.onStop()
        job.cancel()
        disposable.clear()
    }
}