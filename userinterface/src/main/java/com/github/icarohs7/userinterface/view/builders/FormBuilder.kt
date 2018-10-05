package com.github.icarohs7.userinterface.view.builders

import android.content.Context
import android.widget.LinearLayout
import androidx.core.view.plusAssign
import androidx.lifecycle.MutableLiveData
import com.github.icarohs7.templates.bindings.NXBindings
import com.github.icarohs7.templates.databinding.FragmentBaseVerticalLayoutBinding
import com.github.icarohs7.templates.databinding.PartialFormFieldBinding
import com.github.icarohs7.templates.databinding.PartialFormMaskedFieldBinding
import com.github.icarohs7.templates.databinding.PartialFormPasswordFieldBinding
import org.jetbrains.anko.layoutInflater
import java.lang.ref.WeakReference

/**
 * Builds a form
 */
fun Context.form(fn: FormBuilder.() -> Unit): LinearLayout {
    val builder = FormBuilder(this)
    builder.fn()
    return builder.rootLayout
}

/**
 * Builder used to create a DSL for building forms
 */
class FormBuilder(context: Context) {
    val rootLayout: LinearLayout = FragmentBaseVerticalLayoutBinding.inflate(context.layoutInflater).linearLayout
    private var weakContext: WeakReference<Context> = WeakReference(context)
    private val getContext: () -> Context = { requireNotNull(weakContext.get()) }

    /**
     * Add a text field to the form
     */
    fun textField(
            label: String = "",
            boundLiveData: MutableLiveData<String> = MutableLiveData(),
            init: PartialFormFieldBinding.() -> Unit = {}
    ): PartialFormFieldBinding {
        return NXBindings.formField(
                getContext(),
                label,
                boundLiveData
        ).apply(init).apply { rootLayout += root }
    }

    /**
     * Add a masked text field to the form
     */
    fun maskedField(
            label: String = "",
            mask: String = "",
            boundLiveData: MutableLiveData<String> = MutableLiveData(),
            init: PartialFormMaskedFieldBinding.() -> Unit = {}
    ): PartialFormMaskedFieldBinding {
        return NXBindings.formMaskedField(
                getContext(),
                label,
                mask,
                boundLiveData
        ).apply(init).apply { rootLayout += root }
    }

    /**
     * Add a text field with input type number to the form
     */
    fun numberField(
            label: String = "",
            boundLiveData: MutableLiveData<String> = MutableLiveData(),
            init: PartialFormFieldBinding.() -> Unit = {}
    ): PartialFormFieldBinding {
        return NXBindings.formField(
                getContext(),
                label,
                boundLiveData
        ).apply(init)
                .apply { this.editField.inputType = android.text.InputType.TYPE_CLASS_NUMBER }
                .apply { rootLayout += root }
    }


    /**
     * Add a password text field to the form
     */
    fun passwordField(
            label: String = "",
            boundLiveData: MutableLiveData<String> = MutableLiveData(),
            init: PartialFormPasswordFieldBinding.() -> Unit = {}
    ): PartialFormPasswordFieldBinding {

        return NXBindings.formPasswordField(
                getContext(),
                label,
                boundLiveData
        ).apply(init).apply { rootLayout += root }
    }

}