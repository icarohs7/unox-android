package com.github.icarohs7.app.testutils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.icarohs7.unoxandroid.UnoxAndroid
import org.junit.Before
import kotlin.reflect.KClass

abstract class BaseFragmentTestClass<A : AppCompatActivity, T : Fragment>(clazz: KClass<A>)
    : BaseActivityTestClass<A>(clazz) {

    @Before
    fun bootstrapActivity() {
        launchAct()
    }

    protected val fragment: T get() = activity.supportFragmentManager.findFragmentById(UnoxAndroid.masterContainer) as T
}