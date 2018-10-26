package com.github.icarohs7.visuals.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.github.icarohs7.visuals.UnoxAndroidVisualsModule
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Base fragment using databinding
 */
abstract class BaseBindingFragment<B : ViewDataBinding>
    : Fragment(), CoroutineScope, UnoxAndroidVisualsModule.DisposableEntity {

    var job: Job = Job()
        private set
    override val disposable: CompositeDisposable = CompositeDisposable()

    /**
     * Initialized on [onCreateView]
     */
    protected lateinit var binding: B

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil
                .inflate<B>(inflater, getLayout(), container, false)
                .apply { setLifecycleOwner(this@BaseBindingFragment) }

        onBindingCreated(inflater, container, savedInstanceState)
        return binding.root
    }

    /**
     * Cancel all coroutines and dispose all
     * subscriptions when destroyed
     */
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        disposable.clear()
    }

    /**
     * Called after the databinding of the fragment is set
     */
    open fun onBindingCreated(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) {
    }

    /**
     * @return layout to setup data binding.
     */
    @LayoutRes
    protected abstract fun getLayout(): Int

}