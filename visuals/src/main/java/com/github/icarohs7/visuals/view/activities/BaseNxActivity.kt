package com.github.icarohs7.visuals.view.activities

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import com.github.icarohs7.visuals.UnoxAndroidVisualsModule
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Activity containing a composite disposable
 * and a coroutine context, cancelling them when
 * destroyed
 */
abstract class BaseNxActivity : AppCompatActivity(), CoroutineScope, UnoxAndroidVisualsModule.DisposableEntity {
    var job: Job = Job()
        private set
    override val disposable: CompositeDisposable = CompositeDisposable()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    /**
     * Cancel all coroutines and dispose all
     * subscriptions when destroyed
     */
    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        disposable.clear()
    }
}