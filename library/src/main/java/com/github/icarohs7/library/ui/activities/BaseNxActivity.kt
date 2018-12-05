package com.github.icarohs7.library.ui.activities

import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.github.icarohs7.library.UnoxAndroid
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
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
    val job: Job = SupervisorJob()

    /**
     * Context in which coroutines are executed by default inside the activity
     */
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    /**
     * Composite disposable aggregating all subscriptions on the scope of the activity
     */
    override val disposable: CompositeDisposable = CompositeDisposable()

    /**
     * Cancel all coroutines and dispose all
     * subscriptions when stopped
     */
    @CallSuper
    override fun onStop() {
        super.onStop()
        job.cancelChildren()
        disposable.clear()
    }
}