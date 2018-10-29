package com.github.icarohs7.visuals.view.activities

import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.github.icarohs7.visuals.UnoxAndroidVisualsModule
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
abstract class BaseNxActivity : AppCompatActivity(), CoroutineScope, UnoxAndroidVisualsModule.DisposableEntity {
    var job: Job = Job()
        private set
    override val disposable: CompositeDisposable = CompositeDisposable()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

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