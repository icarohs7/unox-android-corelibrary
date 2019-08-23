package base.corelibrary.presentation._bindingbuilders

import base.corelibrary.databinding.PartialNumberStepperBinding

fun PartialNumberStepperBinding.build(initialValue: Int = 0, onUpdate: (Int) -> Unit) {
    number = initialValue
    val getNumber = { txtNumber.text.toString().toInt() }
    setRemoveHandler {
        if (getNumber() > 0)
            number = getNumber() - 1; onUpdate(getNumber())
    }
    setAddHandler {
        number = getNumber() + 1; onUpdate(getNumber())
    }
}