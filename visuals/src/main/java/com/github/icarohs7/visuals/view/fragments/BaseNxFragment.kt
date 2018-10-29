package com.github.icarohs7.visuals.view.fragments

import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.github.icarohs7.visuals.UnoxAndroidVisualsModule
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Fragment containing a composite disposable
 * and a coroutine context, cancelling them when
 * stopped
 */
abstract class BaseNxFragment : Fragment(), CoroutineScope, UnoxAndroidVisualsModule.DisposableEntity {
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