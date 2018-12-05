package com.github.icarohs7.library.ui.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.github.icarohs7.library.UnoxAndroid
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

/**
 * Base viewmodel class with a coroutine and subscription scope,
 * cancelling all coroutines and subscriptions when cleared
 */
abstract class BaseNxViewModel : ViewModel(), CoroutineScope, UnoxAndroid.DisposableEntity {
    /**
     * Parent job of coroutines on the scope of the viewmodel
     */
    val job: Job = SupervisorJob()

    /**
     * Composite disposable aggregating all subscriptions on the scope of the viewmodel
     */
    override val disposable: CompositeDisposable = CompositeDisposable()

    /**
     * Context in which coroutines are executed by default inside the viewmodel
     */
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    /**
     * Cancel all coroutines and dispose all
     * subscriptions when destroyed
     */
    @CallSuper
    override fun onCleared() {
        super.onCleared()
        job.cancelChildren()
        disposable.clear()
    }
}