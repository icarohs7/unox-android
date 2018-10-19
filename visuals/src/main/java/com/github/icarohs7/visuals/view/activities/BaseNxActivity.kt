package com.github.icarohs7.visuals.view.activities

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * Activity containing a list of disposables
 * and automatically unsubscribing to them
 * on pause
 */
abstract class BaseNxActivity : AppCompatActivity() {
    val disposables: CompositeDisposable = CompositeDisposable()
}