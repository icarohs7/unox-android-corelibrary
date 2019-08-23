package base.corelibrary.presentation._bindingbuilders

import base.corelibrary.databinding.PartialNumberStepperBinding

fun PartialNumberStepperBinding.build(initialValue: Int = 0, onUpdate: (Int) -> Unit) {
    number = initialValue
    val getNumber = { txtNumber.text.toString().toInt() }
    val updateNumber = { op: (Int) -> Int -> number = op(getNumber()); onUpdate(op(getNumber())) }
    setRemoveHandler { if (getNumber() > 0) updateNumber(Int::dec) }
    setAddHandler { updateNumber(Int::inc) }
}