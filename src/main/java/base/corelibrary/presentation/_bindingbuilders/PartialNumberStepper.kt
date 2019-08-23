package base.corelibrary.presentation._bindingbuilders

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import base.corelibrary.databinding.PartialNumberStepperBinding

fun PartialNumberStepperBinding.build(lifecycle: LifecycleOwner, number: MutableLiveData<Int>) {
    (this as ViewDataBinding).lifecycleOwner = lifecycle
    this.number = number.map { "$it" }
    setRemoveHandler { number.value = ((number.value ?: 0) - 1).coerceAtLeast(0) }
    setAddHandler { number.value = (number.value ?: 0) + 1 }
}