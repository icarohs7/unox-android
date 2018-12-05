package com.github.icarohs7.library.ui.fragments

import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.github.icarohs7.library.UnoxAndroid
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

/**
 * Fragment containing a composite disposable
 * and a coroutine context, cancelling them when
 * stopped
 */
abstract class BaseNxFragment : Fragment(), CoroutineScope, UnoxAndroid.DisposableEntity {

    /**
     * Parent job of coroutines on the scope of the fragment
     */
    val job: Job = SupervisorJob()

    /**
     * Context in which coroutines are executed by default inside the fragment
     */
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    /**
     * Composite disposable aggregating all subscriptions on the scope of the fragment
     */
    override val disposable: CompositeDisposable = CompositeDisposable()

    /**
     * Cancel all coroutines and dispose all
     * subscriptions when destroyed
     */
    @CallSuper
    override fun onStop() {
        super.onStop()
        job.cancelChildren()
        disposable.clear()
    }
}